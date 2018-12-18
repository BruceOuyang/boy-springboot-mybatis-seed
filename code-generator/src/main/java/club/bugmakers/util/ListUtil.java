package club.bugmakers.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * List 工具类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
public class ListUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static List<Map<String, Object>> initMapList() {
        return new ArrayList<Map<String, Object>>();
    }

    public static <T> List<T> initArrayList(Class<T> requiredType) {
        return new ArrayList<T>();
    }
}
