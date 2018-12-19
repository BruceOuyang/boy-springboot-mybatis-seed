package club.bugmakers.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class GenConf extends BaseEntity {

    @Value("${output.dir.mgt}")
    private String outputDirMgt;

    @Value("${output.dir.web}")
    private String outputDirWeb;

    @Value("${table.include}")
    private String tableInclude;

    @Value("${table.ignored}")
    private String tableIgnored;

    @Value("${project.name.mgt}")
    private String projectNameMgt;

    @Value("${project.name.web}")
    private String projectNameWeb;

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
