package club.bugmakers.seed.config;

import club.bugmakers.seed.config.setting.SwaggerSettings;
import club.bugmakers.seed.global.interceptor.LoginInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring MVC 配置
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(SwaggerSettings.class)
public class MvcConfiguration implements WebMvcConfigurer {

    @Resource
    private SwaggerSettings swaggerSettings;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>();
        //判断是否禁止调用swagger
        if (swaggerSettings.getEnable()){
            excludePathPatterns.add("/swagger-resources/**");
            excludePathPatterns.add("/webjars/**");
            excludePathPatterns.add("/v2/**");
            excludePathPatterns.add("/swagger-ui.html/**");
        }
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**/").excludePathPatterns(excludePathPatterns);
    }
}
