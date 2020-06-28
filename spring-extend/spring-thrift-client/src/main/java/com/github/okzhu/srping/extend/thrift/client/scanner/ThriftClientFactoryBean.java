package com.github.okzhu.srping.extend.thrift.client.scanner;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Proxy;
import java.util.Objects;


@Data
public class ThriftClientFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String beanName;

    private Class<?> beanClass;

//    private String beanClassName;

    private ApplicationContext applicationContext;

//    private Class<?> serviceClass;
//
//    private ThriftServiceSignature serviceSignature;
//
//    private Class<?> clientClass;
//
//    private Constructor<? extends TServiceClient> clientConstructor;

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() throws Exception {
        if (beanClass.isInterface()) {
            log.info("Prepare to generate proxy for {} with JDK", beanClass.getName());


            ThriftClientInvocationHandler invocationHandler = new ThriftClientInvocationHandler(applicationContext);


            return (T) Proxy.newProxyInstance(beanClass.getClassLoader(), new Class<?>[]{beanClass}, invocationHandler);
        } else {
            log.info("Prepare to generate proxy for {} with Cglib", beanClass.getName());

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
            enhancer.setUseFactory(true);

            MethodInterceptor callback = (target, method, args, methodProxy) -> {
                return methodProxy.invokeSuper(target, args);
            };

            enhancer.setCallback(callback);
            return (T) enhancer.create();
        }

    }

    @Override
    public Class<?> getObjectType() {
        if (Objects.isNull(beanClass) && StringUtils.isBlank(beanName)) {
            log.warn("Bean class is not found");
            return null;
        }

        if (Objects.nonNull(beanClass)) {
            return beanClass;
        }

//        if (StringUtils.isNotBlank(beanClassName)) {
//            try {
//                beanClass = Class.forName(beanClassName);
//                return beanClass;
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } else {
//            log.warn("Bean class is not found");
//        }

        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Succeed to instantiate an instance of ThriftClientFactoryBean: {}", this);
    }


}
