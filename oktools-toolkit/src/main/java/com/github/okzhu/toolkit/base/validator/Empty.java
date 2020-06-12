package com.github.okzhu.toolkit.base.validator;

import com.github.okzhu.toolkit.base.type.CollectionUtil;
import com.github.okzhu.toolkit.base.type.StringUtil;

import java.util.Collection;

/**
 *
 */
public class Empty {

    /**
     * 不允许 new
     */
    private Empty() {
    }


    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtil.isEmpty(collection);
    }

    /**
     * @param cha
     * @return
     */
    public static boolean isEmpty(final CharSequence cha) {
        return StringUtil.isEmpty(cha);
    }

}
