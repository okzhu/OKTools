package com.okzhu.oktools.util.validator;

import com.okzhu.oktools.util.type.CollectionUtil;
import com.okzhu.oktools.util.type.StringUtil;

import java.util.Collection;

/**
 *
 */
public class Empty {

    /**
     *
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
