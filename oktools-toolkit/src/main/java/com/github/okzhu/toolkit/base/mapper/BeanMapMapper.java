package com.github.okzhu.toolkit.base.mapper;

import com.github.okzhu.lib.cglib.beans.BeanMap;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BeanMapMapper {

    private static Lock initLock = new ReentrantLock();

    private static Map<String, BeanMap> beanMaps = new HashMap<>();

    @SuppressWarnings("rawtypes")
    private static BeanMap initBeanMap(Class source) {
        initLock.lock();
        BeanMap beanMap = null;
        try {
            beanMap = beanMaps.get(source.getName());
            if (beanMap == null) {
                beanMap = BeanMap.create(source);
                beanMaps.put(source.getName(), beanMap);
            }
        } finally {
            initLock.unlock();
        }
        return beanMap;
    }


    @SuppressWarnings("unchecked")
    public static Map<String, Object> beanToMap(Object source) {
        if (source == null) {
            return null;
        }
        Map<String, Object> map = Maps.newHashMap();
        if (source != null) {
            BeanMap beanMap = initBeanMap(source.getClass());
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;

    }

    @SuppressWarnings("unchecked")
    public static <T> T mapToBean(Map<String, Object> map, Class<T> targetClass) {
        try {
            T target = targetClass.newInstance();
            BeanMap beanMap = initBeanMap(targetClass);
            beanMap.putAll(map);
            return target;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}