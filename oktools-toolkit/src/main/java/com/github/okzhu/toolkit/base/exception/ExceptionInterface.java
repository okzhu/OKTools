package com.github.okzhu.toolkit.base.exception;

/**
 * 异常信息用的。
 * 大部分场景下。我们对于异常信息是希望是一个枚举。
 * 然后通过这个枚举 我们就能直接知道 所有关于这个异常的用的地址。
 * <p>
 * 最简单的含义就是。我们定义了一个错误编码 根据错误编码  你直接搜索错误码找到对应的地方有点麻烦
 * 那么我们定义枚举 看这个枚举的这个错误信息用在哪里。直接ide 可以帮你直接找到了。
 * @author Administrator
 */
public interface ExceptionInterface {

    long getErrCode();

    String getMessage();

}
