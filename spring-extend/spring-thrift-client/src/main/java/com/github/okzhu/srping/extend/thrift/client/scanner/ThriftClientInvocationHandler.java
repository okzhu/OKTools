package com.github.okzhu.srping.extend.thrift.client.scanner;


import com.github.okzhu.srping.extend.thrift.client.annotation.OKThriftClient;
import com.github.okzhu.srping.extend.thrift.client.annotation.OKThriftMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

@Slf4j
public class ThriftClientInvocationHandler implements InvocationHandler {

    private String beanName;

    private Class<?> beanClass;

    private String beanClassName;


    private ProxyFactoryBean proxyFactoryBean;

    private ApplicationContext applicationContext;


    public ThriftClientInvocationHandler(ApplicationContext applicationContext) throws Exception {
        this.applicationContext = applicationContext;
//        this.proxyFactoryBean = initializeProxyFactoryBean();
    }

    @SuppressWarnings("unchecked")
    private ProxyFactoryBean initializeProxyFactoryBean() throws Exception {
//        Constructor<?> constructor = clientConstructor;
//        if (Objects.isNull(constructor)) {
//            constructor = clientClass.getConstructor(TProtocol.class);
//        }

//        Object target = BeanUtils.instantiateClass(constructor, (TProtocol) null);

        Object target = BeanUtils.instantiate(beanClass);


        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.setTarget(target);
        factoryBean.setBeanClassLoader(getClass().getClassLoader());

//        ThriftClientAdvice clientAdvice = new ThriftClientAdvice(serviceSignature, clientConstructor);
//        factoryBean.addAdvice(clientAdvice);
        factoryBean.setProxyTargetClass(true);
        factoryBean.setSingleton(true);
        factoryBean.setOptimize(true);
        factoryBean.setFrozen(true);
        return factoryBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        OKThriftMethod thriftMethod = method.getDeclaredAnnotation(OKThriftMethod.class);

        OKThriftClient thriftClient = method.getDeclaringClass().getDeclaredAnnotation(OKThriftClient.class);

        if (method.getName().equals("toString")) {
            return null;
        }

        String controller = thriftMethod.controller();
        String tMethod = thriftMethod.method();

        Object reflectionBean = applicationContext.getBean(controller);
        if (!Objects.isNull(reflectionBean)) {
            Method reflectionMethod = ReflectionUtils.findMethod(reflectionBean.getClass(), tMethod, null);
            if (Objects.isNull(reflectionMethod)) {
                throw new RuntimeException("not found reflectionMethod");
            }
            Type[] parameterTypes = reflectionMethod.getGenericParameterTypes();
            Object obj;
            if (parameterTypes.length > 0) {
                obj = ReflectionUtils.invokeMethod(reflectionMethod, reflectionBean, args);
            } else {
                obj = ReflectionUtils.invokeMethod(reflectionMethod, reflectionBean);
            }

            return obj;
        }

        LoadBalancerClient iLoadBalancer = applicationContext.getBean(LoadBalancerClient.class);


        ServiceInstance server = iLoadBalancer.choose(thriftClient.serviceId());
//        log.info("server {}", server);
//        if (server == null) {
//            throw YiwillException.handle(ExceptionEnum.COMM_NO_SERVER);
//        }

//        final NiftyClient niftyClient = new NiftyClient();
//        try {
//            InetSocketAddress address = new InetSocketAddress(server.getHost(), AllSystemPortEnum.getThriftPort(sysPortEnum));
//            FramedClientConnector framedClientConnector = new FramedClientConnector(address);
//
//            TTransport transport = niftyClient.connectSync(ThriftRPCService.Client.class, framedClientConnector);
//            TBinaryProtocol tp = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mProtocol = new TMultiplexedProtocol(tp, "thriftRPCService");
//
//            ThriftRPCService.Client client = new ThriftRPCService.Client(mProtocol);
//
//            OKRequest okRequest = new OKRequest();
//            Gson gson = new Gson();
//            AccessCheckVO accessCheckVO = UserUtil.getAccessCheckHolder();
//
//            if (accessCheckVO != null) {
//
//                log.info("accessCheckVO getAccessCheckHolder {}", accessCheckVO);
//
//                HeaderAuth headerAuth = new HeaderAuth();
//                headerAuth.setBimAuthorization(Constants.YW_BIM_AUTHORIZATION_TOKEN);
////            AuthData authData =  YWBeanCopierUtil.convert(accessCheckVO,AuthData.class);
//                AuthData authData = YWAuthAdaptor.toAuthData(accessCheckVO);
//                headerAuth.setAuthData(authData);
//                okRequest.setHeaderAuth(headerAuth);
//            }
//
//            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
//            if (attributes instanceof ServletRequestAttributes) {
//                HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
//                String uToken = CookieUtil.findCookieByName(request, Constants.COOKIE_U_TOKEN);
//                okRequest.setToken(uToken);
//            }
//
//            Type returnType = method.getGenericReturnType();
//            if (method.getReturnType().equals(Result.class)) {
//                okRequest.setRequestType(RequestType.WRAP_RESULT);
//            } else {
//                okRequest.setRequestType(RequestType.SINGLE);
//            }
//
//            okRequest.setController(controller);
//            okRequest.setMethod(tMethod);
//            if (args != null) {
//                String data = gson.toJson(args);
//                okRequest.setData(data);
//            }
//            OKResponse okResponse = client.send(okRequest);
//            return gson.fromJson(okResponse.getData(), returnType);
        return null;
//        } catch (NullPointerException nep) {
//            nep.printStackTrace();
//            throw nep;
//        } finally {
//            niftyClient.close();
//        }
    }

}
