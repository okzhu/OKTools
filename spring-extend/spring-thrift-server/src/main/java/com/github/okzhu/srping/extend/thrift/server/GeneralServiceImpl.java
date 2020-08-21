package com.github.okzhu.srping.extend.thrift.server;


import com.github.okzhu.tools.thrift.api.exception.ThriftGeneralException;
import com.github.okzhu.tools.thrift.api.service.general.ThriftGeneralRequest;
import com.github.okzhu.tools.thrift.api.service.general.ThriftGeneralResponse;
import com.github.okzhu.tools.thrift.api.service.general.ThriftGeneralService;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import lombok.extern.log4j.Log4j2;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by kaiqian.zhu on 2019/1/11.
 */
@Log4j2
@Component("GeneralService")
public class GeneralServiceImpl implements ThriftGeneralService.Iface {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    @Qualifier("unLimitThriftAsync")
    private AsyncTaskExecutor taskExecutor;


    private ThriftGeneralResponse handle(ThriftGeneralRequest req) throws ThriftGeneralException {
        String url = req.getUrl();
        Tracer tracer = GlobalTracer.get();
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan(url);
        Span span = spanBuilder.start();
        Tags.HTTP_URL.set(span, url);
        Scope scope = tracer.activateSpan(span);
        try {
            URI uri = null;
            try {
                uri = new URI(url);
            } catch (URISyntaxException e) {
                Tags.ERROR.set(span, Boolean.TRUE);
                span.log("URI not support");
                log.error("{}", e);
                ThriftGeneralException thriftGeneralException = new ThriftGeneralException();
                thriftGeneralException.setErrCode(1001);
                thriftGeneralException.setMessage(e.getMessage());
                thriftGeneralException.setErrorUrl(req.getUrl());
                throw thriftGeneralException;
            }

            HttpMethod httpMethod = HttpMethod.resolve(req.getMethod());
            Map<String, String> reqHeaders = req.getHeaders();
            MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.request(httpMethod, uri);
            if (reqHeaders != null && !reqHeaders.isEmpty()) {
                reqHeaders.forEach((k, v) -> {
                    if (v != null && v.length() > 0) {
                        builder.header(k, v);
                    }
                });
            }

            String body = req.getBody();
            log.info("url {}", url);
            log.info("reqHeaders {}", reqHeaders);
            log.info("body {}", body);
            if (body != null && body.length() > 0) {
                builder.content(req.getBody());
            }

            MvcResult mvcResult = null;
            try {
                mvcResult = mockMvc.perform(builder).andReturn();
            } catch (Exception e) {
                Tags.ERROR.set(span, Boolean.TRUE);
                span.log("perform error");
                log.error("{}", e);
                Map<String, Object> errorLogs = new HashMap<>(2);
                errorLogs.put("event", Tags.ERROR.getKey());
                errorLogs.put("error.object", e);
                String message = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
                if (message != null) {
                    errorLogs.put("message", message);
                }
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                errorLogs.put("stack", sw.toString());
                span.log(errorLogs);

                ThriftGeneralException thriftGeneralException = new ThriftGeneralException();
                thriftGeneralException.setErrCode(1002);
                thriftGeneralException.setMessage(e.getMessage());
                thriftGeneralException.setErrorUrl(req.getUrl());
                throw thriftGeneralException;
            }

            MockHttpServletResponse response = mvcResult.getResponse();

            ThriftGeneralResponse generalResponse = new ThriftGeneralResponse();
            Collection<String> headers = response.getHeaderNames();

            if (headers != null && !headers.isEmpty()) {
                Map<String, String> resHeaderMap = new HashMap<String, String>();
                headers.forEach(x -> {
                    String headerValue = response.getHeader(x);
                    resHeaderMap.put(x, headerValue);
                });
                generalResponse.setHeaders(resHeaderMap);
            }

            generalResponse.setStatusCode(response.getStatus());
            try {
                generalResponse.setBody(response.getContentAsString());
            } catch (UnsupportedEncodingException e) {
                Tags.ERROR.set(span, Boolean.TRUE);
                span.log("UnsupportedEncodingException error");
                log.error("{}", e);
                ThriftGeneralException thriftGeneralException = new ThriftGeneralException();
                thriftGeneralException.setErrCode(10002);
                thriftGeneralException.setMessage(e.getMessage());
                thriftGeneralException.setErrorUrl(req.getUrl());
                throw thriftGeneralException;
            }
            return generalResponse;
        } finally {
            scope.close();
            span.finish();
        }

    }


    @Override
    public ThriftGeneralResponse send(ThriftGeneralRequest req) throws ThriftGeneralException, TException {
        return handle(req);
    }

    @Override
    public List<ThriftGeneralResponse> listSend(List<ThriftGeneralRequest> req) throws ThriftGeneralException, TException {

        Set<ThriftGeneralRequest> tmpSet = req.stream().collect(Collectors.toSet());

        if (req.size() != tmpSet.size()) {
            ThriftGeneralException thriftGeneralException = new ThriftGeneralException();
            thriftGeneralException.setErrCode(10000);
//            为什么这样原因是因为。promise.all 对于返回的参数是有顺序限制的
//            我用了一个简易的办法来处理这个。用的是 list 的indexof 来判断 我返回的顺序
//            所以你的入参必须不一样。要不然我不知道返回的顺序。虽然有其他办法。比如循环什么的
//            但是我这个 indexof 的办法 是最方便 最快捷（效率）的办法。
            thriftGeneralException.setMessage("你传入的参数有相同的参数," +
                    "对于同一个批量提交你为什么会提交一个相同的参数给我呢? " +
                    "如果你一定要这样做。请在url 后面加一个随机数");
            throw thriftGeneralException;
        }


        ThriftGeneralResponse[] resArr = new ThriftGeneralResponse[req.size()];

        List<CompletableFuture<Void>> completableList = req.stream().map(msg -> {

            return CompletableFuture.
                    completedFuture(msg).
                    thenAcceptAsync(s -> {
                        int index = req.indexOf(s);
                        try {
                            ThriftGeneralResponse res = handle(s);
                            System.out.println(res.toString());
                            System.out.println(index);
                            resArr[index] = res;
                        } catch (ThriftGeneralException e) {
                            ThriftGeneralResponse thriftGeneralResponse = new ThriftGeneralResponse();
                            thriftGeneralResponse.setStatusCode(500);
                            thriftGeneralResponse.setBody(e.getMessage());
                            resArr[index] = thriftGeneralResponse;
                        }
                    }, taskExecutor)
                    .exceptionally((e) -> {
                        log.error("{}", e);
                        return null;
                    });

        }).collect(Collectors.toList());


        CompletableFuture<String>[] completableArr = completableList.toArray(new CompletableFuture[completableList.size()]);

        CompletableFuture.allOf(completableArr)
                .whenComplete(new BiConsumer<Void, Throwable>() {
                    @Override
                    public void accept(Void aVoid, Throwable throwable) {
                        System.out.println("执行完成！");
                    }
                }).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！" + t.getMessage());
                return null;
            }
        }).join();

        for (int i = 0; i < resArr.length; i++) {
            ThriftGeneralResponse res = resArr[i];
            System.out.println(res.toString());
        }

        List<ThriftGeneralResponse> resList = Arrays.stream(resArr).collect(Collectors.toList());

        resList.stream().forEach(x -> {
            System.out.println(x.toString());
        });

        return resList;
    }


    @Override
    public void voidSend(ThriftGeneralRequest req) throws TException {
        CompletableFuture.allOf(CompletableFuture
                .completedFuture(req)
                .thenAcceptAsync(s -> {
                    try {
                        handle(s);
                        TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        log.error("{}", e);
                    }
                }, taskExecutor)
                .exceptionally((e) -> {
                    log.error("{}", e);
                    return null;
                }));
    }

}
