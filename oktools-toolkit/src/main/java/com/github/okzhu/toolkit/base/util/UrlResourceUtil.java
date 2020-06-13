package com.github.okzhu.toolkit.base.util;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 兼容文件url为无前缀, classpath:, file:// 三种方式的Resource读取工具集
 * <p>
 * e.g: classpath:com/myapp/config.xml, file:///data/config.xml, /data/config.xml
 * <p>
 * 参考Spring ResourceUtils
 * @author Administrator
 */
public class UrlResourceUtil {

    private static final String CLASSPATH_PREFIX = "classpath:";
    private static final String URL_PROTOCOL_FILE = "file";

    private UrlResourceUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 兼容无前缀, classpath:, file:// 的情况获取文件
     * <p>
     * 如果以classpath: 定义的文件不存在会抛出IllegalArgumentException异常，以file://定义的则不会
     */
//    @SuppressFBWarnings("PATH_TRAVERSAL_IN")
    @SuppressFBWarnings("PATH_TRAVERSAL_IN")
    @SuppressWarnings("all")
    public static File asFile(String generalPath) throws IOException {
        if (StringUtils.startsWith(generalPath, CLASSPATH_PREFIX)) {
            String resourceName = StringUtils.substringAfter(generalPath, CLASSPATH_PREFIX);
            return getFileByUrl(ResourceUtil.asUrl(resourceName));
        }
        try {
            // try URL
            return getFileByUrl(new URL(generalPath));
        } catch (MalformedURLException ex) {
            // no URL -> treat as file path
            return new File(generalPath);
        }
    }

    /**
     * 兼容无前缀, classpath:, file:// 的情况打开文件成Stream
     */

    public static InputStream asStream(String generalPath) throws IOException {
        if (StringUtils.startsWith(generalPath, CLASSPATH_PREFIX)) {
            String resourceName = StringUtils.substringAfter(generalPath, CLASSPATH_PREFIX);
            return ResourceUtil.asStream(resourceName);
        }

        try {
            // try URL
            return FileUtil.asInputStream(getFileByUrl(new URL(generalPath)));
        } catch (MalformedURLException ex) {
            // no URL -> treat as file path
            return FileUtil.asInputStream(generalPath);
        }
    }

    @SuppressFBWarnings("PATH_TRAVERSAL_IN")
    private static File getFileByUrl(URL fileUrl) throws FileNotFoundException {
        Validate.notNull(fileUrl, "Resource URL must not be null");
        if (!URL_PROTOCOL_FILE.equals(fileUrl.getProtocol())) {
            throw new FileNotFoundException("URL cannot be resolved to absolute file path "
                    + "because it does not reside in the file system: " + fileUrl);
        }
        try {
            return new File(toUri(fileUrl.toString()).getSchemeSpecificPart());
        } catch (URISyntaxException ex) { // NOSONAR
            // Fallback for URLs that are not valid URIs (should hardly ever happen).
            return new File(fileUrl.getFile());
        }
    }

    public static URI toUri(String location) throws URISyntaxException {
        return new URI(StringUtils.replace(location, " ", "%20"));
    }
}
