package com.github.okzhu.srping.extend.redis.serializer;


import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class OkRedisSerializer implements RedisSerializer<String> {


    private final Charset charset = StandardCharsets.UTF_8;
    private String keyPrefix = "";
    private List<String> whiteKeyList = new ArrayList<String>();

    public OkRedisSerializer(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public OkRedisSerializer(String keyPrefix, List<String> whiteKeyList) {
        this.keyPrefix = keyPrefix;
        this.whiteKeyList = whiteKeyList;
    }


    @Override
    public byte[] serialize(String s) throws SerializationException {
        if (null == s) {
            return null;
        } else {

            if (!whiteKeyList.isEmpty()) {
                for (int i = 0; i < whiteKeyList.size(); i++) {
                    int indexOf = s.indexOf(whiteKeyList.get(i));
                    if (indexOf == 0) {
                        return s.getBytes(charset);
                    }
                }
            }

            int indexOf = s.indexOf(keyPrefix);
            if (indexOf != 0) {
                s = keyPrefix + s;
            } else {

            }
            log.info(s);
            return s.getBytes(charset);
        }
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        String saveKey = new String(bytes, charset);
        int indexOf = saveKey.indexOf(keyPrefix);
        if (indexOf == 0) {
            saveKey = saveKey.substring(keyPrefix.length());
        } else {

        }
        log.info(saveKey);
        return (null == saveKey.getBytes(charset) ? null : saveKey);
    }


}