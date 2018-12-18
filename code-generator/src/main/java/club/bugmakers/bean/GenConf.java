package club.bugmakers.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Component
@Data
public class GenConf extends BaseEntity {

    @Value("${output.dir}")
    private String outputDir;

    @Value("${table.include}")
    private String tableInclude;

    @Value("${table.ignored}")
    private String tableIgnored;

    @Value("${base.bo.package}")
    private String baseBoPackage;

    @Value("${base.dto.package}")
    private String baseDtoPackage;

    @Value("${base.po.package}")
    private String basePoPackage;

    @Value("${bean.po.package}")
    private String beanPoPackage;

    @Value("${bean.bo.package}")
    private String beanBoPackage;

    @Value("${bean.dto.package}")
    private String beanDtoPackage;

    @Value("${base.mapper.package}")
    private String baseMapperPackage;

    @Value("${bean.mapper.package}")
    private String beanMapperPackage;

    @Value("${bean.service.package}")
    private String beanServicePackage;

    @Value("${bean.controller.package}")
    private String beanControllerPackage;

    @Value("${schema}")
    private String schema;

}
