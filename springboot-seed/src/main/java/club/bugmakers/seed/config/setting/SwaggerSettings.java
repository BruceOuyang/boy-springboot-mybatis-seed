package club.bugmakers.seed.config.setting;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger 设置
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerSettings {

    private Boolean enable = false;
}
