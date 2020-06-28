package com.github.okzhu.srping.extend.thrift.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.yiwill.cloud.framework.comm.util.Constants;
import com.yiwill.cloud.framework.comm.util.UserUtil;
import com.yiwill.cloud.framework.comm.util.YWAuthAdaptor;
import com.yiwill.cloud.framework.comm.vo.AccessCheckVO;
import com.yiwill.cloud.framework.comm.vo.Result;
import com.yiwill.cloud.framework.thrift.generated.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by kaiqian.zhu on 2018/3/14.
 */
@Slf4j
@Component("ThriftRPCService")
public class ThriftRPCServiceImpl implements ThriftRPCService.Iface {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.application.name}")
    protected String applicationName;

    private void handleAuthorization(HeaderAuth headerAuth) {
        if (Objects.nonNull(headerAuth) && Constants.YW_BIM_AUTHORIZATION_TOKEN.equals(headerAuth.getBimAuthorization())) {
            AuthData authData = headerAuth.getAuthData();
//            AuthUser user = authData.getUser();
//            log.info("handleAuthorization {}", authData);

            AccessCheckVO accessCheckVO = YWAuthAdaptor.toAccessCheck(authData);
            UserUtil.setAccessCheckHolder(accessCheckVO);
        }
    }

    public OKResponse send(OKRequest req) throws TException {
        long startTime = System.currentTimeMillis();

        Gson gson = new Gson();

        String controller = req.getController();
        String method = req.getMethod();
        if (Objects.isNull(controller)) {
            throw new RuntimeException("not found controller");
        }
        if (Objects.isNull(method)) {
            throw new RuntimeException("not found method");
        }
        Object reflectionBean = applicationContext.getBean(controller);
        if (Objects.isNull(reflectionBean)) {
            throw new RuntimeException("not found controller");
        }
//        log.info("accessCheck {}", controller);
//        log.info("method {}", method);
//        log.info("method {}", reflectionBean);
//        Method reflectionMethod = ReflectionUtils.findMethod(reflectionBean.getClass(), method, null);
        Method reflectionMethod = ReflectionUtils.findMethod(reflectionBean.getClass(), method, null);
        if (Objects.isNull(reflectionMethod)) {
            throw new RuntimeException("not found reflectionMethod");
        }

        HeaderAuth headerAuth = req.getHeaderAuth();
        handleAuthorization(headerAuth);

        String reqData = req.getData();
//        Class<?>[] parameterTypes = reflectionMethod.getParameterTypes();
        Type[] parameterTypes = reflectionMethod.getGenericParameterTypes();
        Object obj;
        if (parameterTypes.length > 0) {
            List<Object> list = new ArrayList<Object>();

//            JsonParser parser = new JsonParser();
//            JsonArray jarray = parser.parse(reqData).getAsJsonArray();

            JsonArray jsonArr = gson.fromJson(reqData, JsonArray.class);

            if (parameterTypes.length != jsonArr.size()) {
                throw new RuntimeException("parameter not match");
            }

            for (int i = 0; i < parameterTypes.length; i++) {
                list.add(gson.fromJson(jsonArr.get(i), parameterTypes[i]));
            }

//            for (Type parameterType:parameterTypes){
//            }
//            Object[] objects = new Object[parameterTypes.length];
//            JSONArray reqDataArr = JSON.parseArray(reqData);
//            for (int i = 0; i < parameterTypes.length; i++) {
//                objects[i] = YWBeanCopierUtil.convert(reqDataArr.get(i), parameterTypes[i].getClass());
//            }

            obj = ReflectionUtils.invokeMethod(reflectionMethod, reflectionBean, list.toArray());
        } else {
            obj = ReflectionUtils.invokeMethod(reflectionMethod, reflectionBean);
        }
//        Object obj = ReflectionUtils.invokeMethod(reflectionMethod, bean);
        OKResponse okResponse = new OKResponse();
        okResponse.setStartTime(startTime);


        if (obj instanceof Result) {
            Result result = (Result) obj;
            okResponse.setMessage(result.getMessage());
            okResponse.setSuccess(result.isSuccess());
            if (result.getErrCode() != null) {
                okResponse.setErrCode(result.getErrCode());
            }

            if (req.getRequestType().equals(RequestType.WRAP_RESULT)) {
                okResponse.setData(gson.toJson(obj));
            }

            if (req.getRequestType().equals(RequestType.SINGLE)) {
                okResponse.setData(gson.toJson(result.getData()));
            }
        } else {
            okResponse.setData(gson.toJson(obj));
        }

        long stopTime = System.currentTimeMillis();
        okResponse.setStopTime(stopTime);
        return okResponse;
    }


}
