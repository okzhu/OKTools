package com.github.okzhu.toolkit.base.mapper;

import com.github.okzhu.toolkit.base.mapper.IBeanCopier;
import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BeanCopierMapper {

    private static Lock initLock = new ReentrantLock();

    private static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

    public static final IBeanCopier BEAN_COPIER;

    static {
        IBeanCopier beanCopier;
        try {
            //boot2
            Class.forName("org.springframework.boot.context.properties.bind.Binder");
            beanCopier = new SfBeanCopier();
        } catch (Exception e) {
            //boot1
            beanCopier = new SpringBeanCopier();
        }
        BEAN_COPIER = beanCopier;
    }



    public static class SfBeanCopier implements IBeanCopier {


        @Override
        public IBeanCopier create(Class source, Class target) {
            return null;
        }

        @Override
        public void copy(Object var1, Object var2) {

        }
    }

    public static class SpringBeanCopier implements IBeanCopier {


        @Override
        public IBeanCopier create(Class source, Class target) {
            return null;
        }

        @Override
        public void copy(Object var1, Object var2) {

        }
    }




    @SuppressWarnings("rawtypes")
    private static BeanCopier initCopier(Class source, Class target) {
        initLock.lock();
        BeanCopier beanCopier = null;
        try {
            beanCopier = beanCopierMap.get(source.getName() + "_" + target.getName());
            if (beanCopier == null) {
                beanCopier = BeanCopier.create(source, target, false);
                beanCopierMap.put(source.getName() + "_" + target.getName(), beanCopier);
            }
        } finally {
            initLock.unlock();
        }
        return beanCopier;
    }

    @SuppressWarnings("rawtypes")
    private static BeanCopier getBeanCopier(Class source, Class target) {
        BeanCopier beanCopier = beanCopierMap.get(source.getClass().getName() + "_" + target.getName());
        if (beanCopier != null) {
            return beanCopier;
        }
        return initCopier(source, target);
    }

    @SuppressWarnings("unchecked")
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        BeanCopier beanCopier = getBeanCopier(source.getClass(), targetClass);
        try {
            // 如果为基础类型，不存在默认构造函数，将自动封箱操作
            if (source.getClass().isPrimitive()) {
                return (T) source;
            } else if (source instanceof CharSequence) {
                return (T) source;
            } else {
                T target = targetClass.newInstance();
                beanCopier.copy(source, target, null);
                return target;
            }


        } catch (Exception e) {
            // 将尝试进行强转
            return (T) source;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <E> List<E> convert(List source, Class<E> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            if (source.isEmpty()) {
                return source.getClass().newInstance();
            }
            List result = source.getClass().newInstance();

            for (Object each : source) {
                result.add(convert(each, targetClass));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(source + "_" + targetClass, e);
        }
    }
}
