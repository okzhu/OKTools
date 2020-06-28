package com.github.okzhu.srping.extend.thrift.server;


import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.yiwill.cloud.framework.thrift.generated.ThriftRPCService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThriftServerAutoConfiguration implements SmartLifecycle {

    @Autowired
    @Qualifier("ThriftRPCService")
    private ThriftRPCServiceImpl thriftRPCService;

    @Value("${yw.thrift.port}")
    private int thriftPort;

    private boolean isRunning = false;

    private NettyServerTransport server = null;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {

        log.info("ThriftServerAutoConfigurationV2 stop(Runnable callback)");
        try {
            server.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.run();
    }

    @Override
    public void start() {
        log.info("ThriftServerAutoConfigurationV2 start()");

        TProcessor rpcProcessor = new ThriftRPCService.Processor<>(thriftRPCService);
//        TProcessor rpcAsyncProcessor = new ThriftRPCService.AsyncProcessor<>(thriftRPCAsyncService);

        TMultiplexedProcessor processor = new TMultiplexedProcessor();
        processor.registerProcessor("thriftRPCService", rpcProcessor);
//        processor.registerProcessor("thriftRPCAsyncService",rpcAsyncProcessor);

        TProtocolFactory binaryProtocol = new TBinaryProtocol.Factory();

//        TTransportFactory factory = new TFramedTransport.Factory();
//        TMultiplexedProtocol rpcServiceProtocol = new TMultiplexedProtocol(binaryProtocol,"thriftRPCService");
//        TMultiplexedProtocol asyncServiceProtocol = new TMultiplexedProtocol(binaryProtocol,"thriftRPCAsyncService");
//        ThriftRPCService.AsyncProcessor asyncProcessor = new ThriftRPCService.AsyncProcessor<>(thriftRPCAsyncService);

        ThriftServerDef serverDef = new ThriftServerDefBuilder()
                .protocol(binaryProtocol)
                .listen(thriftPort)
                .withProcessor(processor)
                .build();

//        NettyServerConfig nettyServerConfig = NettyServerConfig.newBuilder().build();

        server = new NettyServerTransport(serverDef);

        server.start();

//        // Arrange to stop the server at shutdown
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                try {
//                    server.stop();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        });

        isRunning = true;
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("ThriftServerAutoConfigurationV2 stop()");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 0;
    }


//    @Bean
//    public NettyServerTransport thriftServerBootstrap(@Qualifier("ThriftRPCService") ThriftRPCServiceImpl thriftRPCService,
//                                                      @Qualifier("ThriftRPCAsyncService") ThriftRPCAsyncServiceImpl thriftRPCAsyncService) {
//
//        applicationContext.
//
//
//        TProcessor rpcProcessor = new ThriftRPCService.Processor<>(thriftRPCService);
//        TProcessor rpcAsyncProcessor = new ThriftRPCService.AsyncProcessor<>(thriftRPCAsyncService);
//
//
//        TMultiplexedProcessor processor = new TMultiplexedProcessor();
//        processor.registerProcessor("thriftRPCService",rpcProcessor);
//        processor.registerProcessor("thriftRPCAsyncService",rpcAsyncProcessor);
//
//        TProtocolFactory binaryProtocol =  new TBinaryProtocol.Factory();
//
//        TTransportFactory factory = new TFramedTransport.Factory();
//
////        TMultiplexedProtocol rpcServiceProtocol = new TMultiplexedProtocol(binaryProtocol,"thriftRPCService");
////        TMultiplexedProtocol asyncServiceProtocol = new TMultiplexedProtocol(binaryProtocol,"thriftRPCAsyncService");
////        ThriftRPCService.AsyncProcessor asyncProcessor = new ThriftRPCService.AsyncProcessor<>(thriftRPCAsyncService);
//
//
//        ThriftServerDef serverDef = new ThriftServerDefBuilder()
//                                        .protocol(binaryProtocol)
//                                        .listen(8095)
//                                        .withProcessor(processor)
//                                        .build();
//
////        NettyServerConfig nettyServerConfig = NettyServerConfig.newBuilder().build();
//
//        final NettyServerTransport server = new NettyServerTransport(serverDef);
//
//        server.start();
//
//        // Arrange to stop the server at shutdown
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                try {
//                    server.stop();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        });
//
//
//
//        return server;
////        return new YWThriftServerBootstrap(thriftServerGroup);
//    }

}
