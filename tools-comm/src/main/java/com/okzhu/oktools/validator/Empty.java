package com.okzhu.oktools.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public class Empty {


    /**
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * @param map
     * @return
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return (map == null) || map.isEmpty();
    }

    /**
     * @param cha
     * @return
     */
    public static boolean isEmpty(final CharSequence cha) {
        return StringUtils.isEmpty(cha);
    }

}
