package com.github.okzhu.toolkit.base.mapper;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ExecutionException;


public class SpringBeanCopier implements IBeanCopier {

    private static Cache<String, BeanCopier> cache = CacheBuilder.newBuilder()
            .maximumSize(2000)
            .build();

    private static BeanCopier getBeanCopier(Class source, Class target) throws ExecutionException {

        String key = source.getName() + "_" + target.getName();

        return cache.get(key, () -> BeanCopier.create(source, target, false));

    }

    @Override
    public void copy(Object source, Object target) throws ExecutionException {
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, null);
    }
}
