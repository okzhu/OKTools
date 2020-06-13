package com.github.okzhu.toolkit.base.mapper;


import com.github.okzhu.toolkit.base.exception.OKBaseException;
import com.github.okzhu.toolkit.base.type.ListUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Log4j2
public class BeanCopierMapper {

    private static IBeanCopier iBeanCopier;

    static {
        try {
            Class.forName("org.springframework.cglib.beans.BeanCopier");
            iBeanCopier = new SpringBeanCopier();
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("net.sf.cglib.beans.BeanCopier");
                iBeanCopier = new SfBeanCopier();
            } catch (ClassNotFoundException e1) {
                // ignore
                log.error("ClassNotFoundException {}", e);
            }
        }
    }


    private BeanCopierMapper() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("unchecked")
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            // 如果为基础类型，不存在默认构造函数，将自动封箱操作
            if (source.getClass().isPrimitive()) {
                return (T) source;
            } else if (source instanceof CharSequence) {
                return (T) source;
            } else {
                T target = targetClass.newInstance();
                iBeanCopier.copy(source, target);
                return target;
            }
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException {}", e);
        } catch (ExecutionException e) {
            log.error("ExecutionException {}", e);
        } catch (InstantiationException e) {
            log.error("InstantiationException {}", e);
        }
        // 将尝试进行强转
        return (T) source;
    }

    @SuppressFBWarnings("EXS_EXCEPTION_SOFTENING_NO_CONSTRAINTS")
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <E> List<E> convert(List<? extends Object> source, Class<E> targetClass) {
        if (source == null) {
            return ListUtil.emptyList();
        }
        try {
            if (source.isEmpty()) {
                return source.getClass().newInstance();
            }
            List<E> result = source.getClass().newInstance();

            for (Object each : source) {
                result.add(convert(each, targetClass));
            }
            return result;
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException {}", e);
        } catch (InstantiationException e) {
            log.error("InstantiationException {}", e);
        }
        throw new OKBaseException(source + "_" + targetClass);
    }
}
