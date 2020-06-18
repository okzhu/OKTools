package om.github.okzhu.srping.extend.redis;

/**
 * 特别注意
 * 如果你用的 hash 结构 一定要注意 结束时间.  除非你没有结束时间。否则劲量不用
 * expire只对顶级key有效，不能对hash结构中的某个files设置。
 * 链表和集合就不支持过期时间
 **/

public interface RedisEnumInterface {
    String getKey();

    String getName();

    Object getValue();

    long getExpireTime();
}
