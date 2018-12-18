package club.bugmakers.util;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Bean 工具
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
public class SpringBeanUtil {
    public static ConfigurableApplicationContext context = null;

    public static Object getBeanByName(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> Map<String, T> getBeanByType(Class<T> type) {
        return context.getBeansOfType(type);
    }

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

}
