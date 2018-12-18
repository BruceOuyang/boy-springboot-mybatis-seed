package club.bugmakers.seed.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringBean 工具类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }


}