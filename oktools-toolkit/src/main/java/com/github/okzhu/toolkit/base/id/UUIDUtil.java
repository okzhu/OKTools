package com.github.okzhu.toolkit.base.id;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Administrator
 */
public class UUIDUtil {
    private UUIDUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 返回使用ThreadLocalRandom的UUID，比默认的UUID性能更优
     */
    public static UUID fastUuid() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong());
    }

}
