//package com.github.okzhu.toolkit.base.mapper;
//
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
//import org.springframework.cglib.beans.BeanCopier;
//
//import java.util.concurrent.ExecutionException;
//
//
///**
// * 这称为重新打包：项目不是使用某个库作为依赖项,而是将依赖项的副本作为其自己项目的一部分,并将其放在不同的包中.
// * 这样做的原因是使用Spring的项目可能想要使用cglib本身.
// * 如果Spring将特定版本的cglib作为依赖项,
// * 那么使用Spring的项目就不可能选择不同的版本.
// * 但是如果Spring使用不同包中的重新打包的cglib,则没有版本冲突,如果他们喜欢,项目可以使用任何版本的cglib.
// *
// * 一些项目以类似的方式重新打包Guava,Netty或其他流行的库.
// * @author Administrator
// */
//@SuppressFBWarnings("IICU_INCORRECT_INTERNAL_CLASS_USE")
//public class SpringBeanCopier implements IBeanCopier {
//
//    private static Cache<String, BeanCopier> cache = CacheBuilder.newBuilder()
//            .maximumSize(2000)
//            .build();
//
//    private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) throws ExecutionException {
//
//        String key = source.getName() + "_" + target.getName();
//
//        return cache.get(key, () -> BeanCopier.create(source, target, false));
//
//    }
//
//    @Override
//    public void copy(Object source, Object target) throws ExecutionException {
//        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
//        beanCopier.copy(source, target, null);
//    }
//}
