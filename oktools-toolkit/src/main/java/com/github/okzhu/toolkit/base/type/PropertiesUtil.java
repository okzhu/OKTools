package com.github.okzhu.toolkit.base.type;

import com.github.okzhu.toolkit.base.number.NumberUtil;
import com.github.okzhu.toolkit.base.text.Charsets;
import com.github.okzhu.toolkit.base.util.UrlResourceUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

/**
 * @author Administrator
 */
@Log4j2
@UtilityClass
public class PropertiesUtil {

    public static Boolean getBoolean(Properties p, String name, Boolean defaultValue) {
        return BooleanUtil.toBooleanObject(p.getProperty(name), defaultValue);
    }

    public static Integer getInt(Properties p, String name, Integer defaultValue) {
        return NumberUtil.toIntObject(p.getProperty(name), defaultValue);
    }

    public static Long getLong(Properties p, String name, Long defaultValue) {
        return NumberUtil.toLongObject(p.getProperty(name), defaultValue);
    }

    public static Double getDouble(Properties p, String name, Double defaultValue) {
        return NumberUtil.toDoubleObject(p.getProperty(name), defaultValue);
    }

    public static String getString(Properties p, String name, String defaultValue) {
        return p.getProperty(name, defaultValue);
    }

    /////////// 加载Properties////////

    /**
     * 从文件路径加载properties. 默认使用utf-8编码解析文件
     * <p>
     * 路径支持从外部文件或resources文件加载, "file://"或无前缀代表外部文件, "classpath:"代表resources
     */
    public static Properties loadFromFile(String generalPath) {
        Properties p = new Properties();
        try (Reader reader = new InputStreamReader(UrlResourceUtil.asStream(generalPath), Charsets.UTF_8)) {
            p.load(reader);
        } catch (IOException e) {
            log.error("Load property from " + generalPath + " failed", e);
        }
        return p;
    }

    /**
     * 从字符串内容加载Properties
     */
    public static Properties loadFromString(String content) {
        Properties p = new Properties();
        try (Reader reader = new StringReader(content)) {
            p.load(reader);
        } catch (IOException ignored) { // NOSONAR
        }
        return p;
    }
}
