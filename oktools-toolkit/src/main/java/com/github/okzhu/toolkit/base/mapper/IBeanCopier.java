package com.github.okzhu.toolkit.base.mapper;

import com.github.okzhu.toolkit.base.util.BeanCopierUtil;

public interface IBeanCopier {

        IBeanCopier create(Class source, Class target);

        void copy(Object var1, Object var2);

}
