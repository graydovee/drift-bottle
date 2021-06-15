package cn.graydove.driftbottle.utils;

import java.net.URL;
import java.util.Objects;

/**
 * @author graydove
 */
public class ResourceUtil {

    public static URL getResource(String path) {
        return Objects.requireNonNull(ResourceUtil.class.getClassLoader().getResource(path));
    }
}
