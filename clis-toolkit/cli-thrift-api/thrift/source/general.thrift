namespace java com.github.okzhu.tools.thrift.api.service.general
namespace php thrift.general

include './exception.thrift'

/**
 * 如果仅仅是想优化网络层面的 可以直接使用这个类来处理
 * 他的处理过程是 如果你以前的是一个http 请求。
 * 那么他就是直接使用内部的类调用 srping Servlet 可以直接内部模拟一个http 请求。
 * 不用额外处理。返回的结果和原来是一样的
*/
struct ThriftGeneralRequest {
/**
 * 就是HTTP的请求头。你 http 是什么这个就是什么
*/
  1: map<string, string> headers,
  /**
   * 就是HTTP的 Url。你 http 是什么这个就是什么
  */
  2: string url,
    /**
     * 就是HTTP的 method。你 http 是什么这个就是什么
    */
  3: string method,
  /**
   * 就是HTTP的 body。你 http 是什么这个就是什么
  */
  4: optional string body,
}

struct ThriftGeneralResponse {
/**
 * 就是HTTP的返回头。你 http 是什么这个就是什么，
 * 比如你要在头里面设置 cookies 也是在这里
*/
  1: map<string, string> headers,
  /**
   * 就是HTTP的返回 状态码。你 http 是什么这个就是什么，
  */
  2: i32 statusCode,
    /**
     * 就是HTTP的返回 内容。你 http 是什么这个就是什么，
    */
  3: optional string body,
}



service ThriftGeneralService {
    ThriftGeneralResponse send(1: ThriftGeneralRequest req) throws (1: exception.ThriftGeneralException e);

    list<ThriftGeneralResponse> listSend(1: list<ThriftGeneralRequest> req) throws (1: exception.ThriftGeneralException e);

    void voidSend(1: ThriftGeneralRequest req);

}
