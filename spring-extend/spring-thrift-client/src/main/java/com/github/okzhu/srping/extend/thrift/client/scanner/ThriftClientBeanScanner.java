package com.github.okzhu.srping.extend.thrift.client.scanner;

import com.github.okzhu.srping.extend.thrift.client.annotation.OKThriftClient;
import com.github.okzhu.srping.extend.thrift.client.common.ThriftClientDefinitionProperty;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Set;


public final class ThriftClientBeanScanner extends ClassPathBeanDefinitionScanner {

    private Logger log = LoggerFactory.getLogger(getClass());

    public ThriftClientBeanScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(OKThriftClient.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {


        ApplicationContext applicationContext = (ApplicationContext) getResourceLoader();


        Set<BeanDefinitionHolder> definitionHolders = super.doScan(basePackages);

        log.info("Packages scanned by thriftClientBeanDefinitionScanner is [{}]", StringUtils.join(basePackages, ", "));

        for (BeanDefinitionHolder definitionHolder : definitionHolders) {
            GenericBeanDefinition definition = (GenericBeanDefinition) definitionHolder.getBeanDefinition();

            log.info("Scanned and found thrift client, bean {} assigned from {}",
                    definitionHolder.getBeanName(),
                    definition.getBeanClassName());

            Class<?> beanClass;
            try {
                beanClass = Class.forName(definition.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }

            OKThriftClient thriftClient = AnnotationUtils.findAnnotation(beanClass, OKThriftClient.class);
            if (thriftClient == null) {
                log.warn("Thrift client is not found");
                continue;
            }

            String beanName = StringUtils.isNotBlank(thriftClient.value())
                    ? thriftClient.value()
                    : (StringUtils.isNotBlank(thriftClient.name()) ? thriftClient.name() : StringUtils.uncapitalize(beanClass.getSimpleName()));

//            String beanName = StringUtils.uncapitalize(beanClass.getSimpleName());

            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.BEAN_NAME, beanName);
            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.BEAN_CLASS, beanClass);
//            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.BEAN_CLASS_NAME, beanClass.getName());
            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.APPLICATION_CONTEXT, applicationContext);


//            Method[] methods = beanClass.getDeclaredMethods();
//            for (Method method:methods) {
//                OKThriftClientMethod thriftClientMethod = method.getDeclaredAnnotation(OKThriftClientMethod.class);
//                if(Objects.nonNull(thriftClientMethod)){
//
//                }
//            }
//            AnnotationUtils.findAnnotation()
//            Class<?> referClass = thriftClient.refer();
//            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.SERVICE_CLASS, referClass);
//
//            ThriftServiceSignature serviceSignature = new ThriftServiceSignature(
//                    thriftClient.serviceId(), thriftClient.refer(), thriftClient.version());
//
//            Class<? extends TServiceClient> clientClass = getClientClassFromAnnotation(beanClass);
//            Constructor<? extends TServiceClient> constructor;
//
//            try {
//                constructor = clientClass.getConstructor(TProtocol.class);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Failed to get constructor with args TProtocol", e);
//            }
//
//            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.SERVICE_SIGNATURE, serviceSignature);
//            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.CLIENT_CLASS, clientClass);
//            definition.getPropertyValues().addPropertyValue(ThriftClientDefinitionProperty.CLIENT_CONSTRUCTOR, constructor);
//
            definition.setBeanClassName(beanName);
            definition.setBeanClass(ThriftClientFactoryBean.class);

        }

        return definitionHolders;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends TServiceClient> getClientClassFromAnnotation(Class<?> beanClass) {
        ParameterizedType clientAwareType = (ParameterizedType) beanClass.getGenericInterfaces()[0];
        if (Objects.isNull(clientAwareType)) {
            throw new RuntimeException("Interface annotated with @ThriftClient should be inherited from ThriftClientAware");
        }

        Type[] typeArguments = clientAwareType.getActualTypeArguments();
        if (ArrayUtils.isEmpty(typeArguments) || typeArguments.length == 0) {
            throw new RuntimeException("ThriftClientAware should declare an argument");
        }

        Class<?> clientClass = (Class<?>) typeArguments[0];
        if (!ClassUtils.isAssignable(TServiceClient.class, clientClass)) {
            throw new RuntimeException("ThriftClientAware without argument inherited from TServiceClient");
        }

        return (Class<? extends TServiceClient>) clientClass;
    }


    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.hasAnnotation(OKThriftClient.class.getName())
                && metadata.isInterface();
    }


}
