package com.github.okzhu.srping.extend.thrift.server;

import lombok.extern.log4j.Log4j2;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by kaiqian.zhu on 2019/1/11.
 */
@Log4j2
@Component
public class ThriftServerConfiguration {

//    @Autowired
//    @Qualifier("GeneralService")
//    private GeneralServiceImpl generalService;


//    @Autowired
//    @Qualifier("ThriftRPCService")
//    private ThriftRPCServiceImpl thriftRPCService;


//    @Autowired
//    private Tracer tracer;


    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        TProtocolFactory binaryProtocol = new TBinaryProtocol.Factory();

        TMultiplexedProcessor processor = new TMultiplexedProcessor();


//        TProcessor generalRpcProcessor = new ThriftGeneralService.Processor<>(generalService);
////        TProcessor generalSpanProcessor = new SpanProcessor(generalRpcProcessor, tracer);
//        processor.registerProcessor("generalProcessor", generalRpcProcessor);


//        TProcessor thriftRpcProcessor = new ThriftRPCService.Processor<>(thriftRPCService);
//        TProcessor thriftSpanProcessor = new SpanProcessor(generalRpcProcessor, tracer);
//        processor.registerProcessor("thriftRPCService", thriftRpcProcessor);


        TServlet tServlet = new TServlet(processor, binaryProtocol);
        return new ServletRegistrationBean(tServlet, "/servlet/thriftRPC");
    }


}
