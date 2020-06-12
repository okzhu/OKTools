package com.github.okzhu.toolkit.base.mapper;

import net.sf.cglib.beans.BeanCopier;

public class SfBeanCopier implements IBeanCopier {
    
    private BeanCopier beanCopier;

    @Override
    public IBeanCopier create(Class source, Class target) {
        beanCopier = BeanCopier.create(source, target, false);
        return this;
    }

    @Override
    public void copy(Object source, Object target) {
         beanCopier.copy(source, target, null);
    }
}
