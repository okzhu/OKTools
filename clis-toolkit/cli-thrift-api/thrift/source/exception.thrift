namespace java com.github.okzhu.tools.thrift.api.exception
namespace php thrift.exception

exception ThriftGeneralException{
    1: optional bool success = false;
    2: optional i32 errCode;
    3: optional string message;
    4: optional string errorUrl;
}

