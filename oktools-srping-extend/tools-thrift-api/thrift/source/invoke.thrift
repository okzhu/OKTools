namespace java com.github.okzhu.tools.thrift.api.service.invoke
namespace php thrift.invoke

include './exception.thrift'

/**
 * 劲量不要用这个方法来条用远程的方法
 * 这个是直接内部反射 内部类。这样就跳过了权限校验。非常不安全
*/
struct ThriftInvokeRequest {
  1: map<string, string> headers,
  /**
    * controller 就是java的类 注意这个 我们项目中间 controller 是需要取一个别名的
    */
  2: optional string controller,
  3: optional string method,
    /**
     * data 注意这个地方 为了兼容多个参数。传递的是一个 json的数组
     * 全部都是数组 哪怕你调用的method 只有一个对象 也用数组包裹一下  Arrays.asList
    */
  4: optional string data,
}

struct ThriftInvokeResponse {
  1: map<string, string> headers,
  2: i32 statusCode,
  3: optional string body,
}

service ThriftInvokeService {

    ThriftInvokeResponse send(1: ThriftInvokeRequest req) throws (1: exception.ThriftGeneralException e);

    list<ThriftInvokeResponse> listSend(1: list<ThriftInvokeRequest> req) throws (1: exception.ThriftGeneralException e);

    void voidSend(1: ThriftInvokeRequest req);

}
