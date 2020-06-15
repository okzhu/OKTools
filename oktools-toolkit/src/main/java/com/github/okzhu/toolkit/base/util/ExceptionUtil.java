package com.github.okzhu.toolkit.base.util;

import com.github.okzhu.toolkit.base.exception.ExceptionInterface;
import com.github.okzhu.toolkit.base.exception.OKBaseException;
import com.github.okzhu.toolkit.base.exception.OKRestException;
import com.github.okzhu.toolkit.base.vo.Result;
import com.google.common.base.Throwables;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.MessageFormat;

/**
 * @author Administrator
 */
@UtilityClass
public class ExceptionUtil {
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];

    ///// Checked/Unchecked及Wrap(如ExecutionException)的转换/////

    /**
     * 将CheckedException转换为RuntimeException重新抛出, 可以减少函数签名中的CheckException定义.
     * <p>
     * CheckedException会用UndeclaredThrowableException包裹，RunTimeException和Error则不会被转变.
     * <p>
     * copy from Commons Lang 3.5 ExceptionUtils.
     * <p>
     * 虽然unchecked()里已直接抛出异常，但仍然定义返回值，方便欺骗Sonar。因此本函数也改变了一下返回值
     * <p>
     * 示例代码:
     *
     * <pre>
     * try{ ... }catch(Exception e){ throw unchecked(t); }
     * </pre>
     *
     * @see ExceptionUtils#wrapAndThrow(Throwable)
     */
    public static RuntimeException unchecked(Throwable t) {
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        }
        if (t instanceof Error) {
            throw (Error) t;
        }

        throw new OKBaseException(t);
    }

    /**
     * 如果是著名的包裹类，从cause中获得真正异常. 其他异常则不变.
     * <p>
     * Future中使用的ExecutionException 与 反射时定义的InvocationTargetException， 真正的异常都封装在Cause中
     * <p>
     * 前面 unchecked() 使用的UncheckedException同理.
     */
    public static Throwable unwrap(Throwable t) {
        if (t instanceof OKBaseException || t instanceof java.util.concurrent.ExecutionException
                || t instanceof java.lang.reflect.InvocationTargetException
                || t instanceof UndeclaredThrowableException) {
            return t.getCause();
        }

        return t;
    }

    /**
     * 组合unwrap与unchecked，用于处理反射/Callable的异常
     */
    public static RuntimeException unwrapAndUnchecked(Throwable t) {
        throw unchecked(unwrap(t));
    }


    @SuppressWarnings("all")
    public static OKBaseException unwrapAndChecked(Throwable t) throws OKBaseException {
        throw unchecked(unwrap(t));
    }


    public static OKRestException handle(ExceptionInterface exception, Object... arguments) {
        return new OKRestException(exception.getErrCode(), MessageFormat.format(exception.getMessage(), arguments));
    }

    public static OKRestException handle(Result<?> result) {
        return new OKRestException(result.getErrCode(), result.getMessage(), result.getData());
    }

    public static OKRestException handle(ExceptionInterface exception) {
        return new OKRestException(exception.getErrCode(), exception.getMessage());
    }

    public static OKRestException handleWithData(ExceptionInterface exception, Object data) {
        return new OKRestException(exception.getErrCode(), exception.getMessage(), data);
    }

    public static OKRestException handleWithData(ExceptionInterface exception, Object data, Object... arguments) {
        return new OKRestException(exception.getErrCode(), MessageFormat.format(exception.getMessage(), arguments), data);
    }

    /**
     * 拼装 短异常类名: 异常信息.
     * <p>
     * 与Throwable.toString()相比使用了短类名
     *
     * @see ExceptionUtils#getMessage(Throwable)
     */
    public static String toStringWithShortName(Throwable t) {
        return ExceptionUtils.getMessage(t);
    }


    ////////// Cause 相关 /////////

    /**
     * 获取异常的Root Cause.
     * <p>
     * 如无底层Cause, 则返回自身
     *
     * @see Throwables#getRootCause(Throwable)
     */
    public static Throwable getRootCause(Throwable t) {
        return Throwables.getRootCause(t);
    }

    /**
     * 获取某种类型的cause，如果没有则返回空
     * <p>
     * copy from Jodd ExceptionUtil
     */
    public static <T extends Throwable> T findCause(Throwable throwable, Class<T> cause) {
        while (throwable != null) {
            if (throwable.getClass().equals(cause)) {
                return (T) throwable;
            }
            throwable = throwable.getCause();
        }
        return null;
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     */
    @SuppressWarnings("unchecked")
    public static boolean isCausedBy(Throwable throwable,
                                     Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = throwable;

        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
    /////////// StackTrace 性能优化相关////////

    /**
     * copy from Netty, 为静态异常设置StackTrace.
     * <p>
     * 对某些已知且经常抛出的异常, 不需要每次创建异常类并很消耗性能的并生成完整的StackTrace. 此时可使用静态声明的异常.
     * <p>
     * 如果异常可能在多个地方抛出，使用本函数设置抛出的类名和方法名.
     *
     * <pre>
     * private static RuntimeException TIMEOUT_EXCEPTION = ExceptionUtil.setStackTrace(new RuntimeException("Timeout"),
     * 		MyClass.class, "mymethod");
     * </pre>
     */
    public static <T extends Throwable> void setStackTrace(T throwable, Class<?> throwClass, String throwClazz) {
        throwable.setStackTrace(
                new StackTraceElement[]{new StackTraceElement(throwClass.getName(), throwClazz, null, -1)});
    }

    /**
     * 清除StackTrace. 假设StackTrace已生成, 但把它打印出来也有不小的消耗.
     * <p>
     * 如果不能控制StackTrace的生成，也不能控制它的打印端(如logger)，可用此方法暴力清除Trace.
     * <p>
     * 但Cause链依然不能清除, 只能清除每一个Cause的StackTrace.
     */
    public static <T extends Throwable> T clearStackTrace(T throwable) {
        Throwable cause = throwable;
        while (cause != null) {
            cause.setStackTrace(EMPTY_STACK_TRACE);
            cause = cause.getCause();
        }
        return throwable;
    }


}
