package com.github.okzhu.srping.extend.redis.util;


import com.github.okzhu.srping.extend.redis.RedisEnumInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
public class RedisUtil {

    private String name;
    private String key;
    private Object value;
    private long expireTime;

    public static RedisUtil of(RedisEnumInterface redisEnum) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setExpireTime(redisEnum.getExpireTime());
        redisUtil.setKey(redisEnum.getKey());
        redisUtil.setName(redisEnum.getName());
        return redisUtil;
    }


    public RedisUtil key(Object... arguments) {
        key = MessageFormat.format(key, arguments);
        return this;
    }

    public RedisUtil name(Object... arguments) {
        name = MessageFormat.format(name, arguments);
        return this;
    }

    public RedisUtil value(Object value) {
        this.value = value;
        return this;
    }

    public String keysPattern() {
        Matcher m = Pattern.compile("\\{\\w+\\}").matcher(key);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }


}
