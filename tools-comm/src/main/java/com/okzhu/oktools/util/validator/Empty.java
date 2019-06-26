package com.okzhu.oktools.util.validator;

import com.okzhu.oktools.util.type.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class Empty {

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
