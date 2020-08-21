package com.github.okzhu.srping.extend.thrift.server;//package com.yiwill.cloud.framework.thrift.server;
//
//import com.alibaba.fastjson.JSON;
//import com.yiwill.cloud.framework.thrift.generated.OKRequest;
//import com.yiwill.cloud.framework.thrift.generated.OKResponse;
//import com.yiwill.cloud.framework.thrift.generated.ThriftRPCService;
//import org.apache.thrift.TException;
//import org.apache.thrift.async.AsyncMethodCallback;
//import org.springframework.stereotype.Component;
//
///**
// * Created by kaiqian.zhu on 2018/3/14.
// * 放弃异步方法
// */
//@Component("ThriftRPCAsyncService")
//public class ThriftRPCAsyncServiceImpl implements ThriftRPCService.AsyncIface{
//
//
//    @Override
//    public void handle(OKRequest req, AsyncMethodCallback<OKResponse> resultHandler) throws TException {
//        long startTime = System.currentTimeMillis();
//
//        OKResponse okResponse = new OKResponse();
//        okResponse.setStartTime(startTime);
//
//System.out.print("ThriftRPCAsyncServiceImpl");
//
//        long stopTime = System.currentTimeMillis();
//        okResponse.setStopTime(stopTime);
//
//
//        okResponse.setData("dfdfsd");
//
//
//        resultHandler.onComplete(okResponse);
//
//    }
//
//}
