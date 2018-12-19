package club.bugmakers.bean;

/**
 * TemplatePath
 *
 * @Author Bruce
 * @Date 2018/12/19 11:05
 * @Version 1.0
 **/
public class TemplatePath {
    private TemplatePath() {}

    public static final String CLASS_PO               = "classpath:templates/class_bean_po.ftl";
    public static final String CLASS_BO_PAGE          = "classpath:templates/class_bean_bo_page.ftl";
    public static final String CLASS_BO               = "classpath:templates/class_bean_bo.ftl";
    public static final String CLASS_DTO              = "classpath:templates/class_bean_dto.ftl";

    public static final String CLASS_MAPPER           = "classpath:templates/class_mapper.ftl";
    public static final String CLASS_SERVICE          = "classpath:templates/class_service.ftl";
    public static final String CLASS_CONTROLLER       = "classpath:templates/class_controller.ftl";

    public static final String XML_MAPPER             = "classpath:templates/xml_mapper.ftl";

    public static final String VUE_BEAN               = "classpath:templates/vue_bean.ftl";
    public static final String VUE_MOCK               = "classpath:templates/vue_mock_js.ftl";
    public static final String VUE_ROUTER             = "classpath:templates/vue_router_js.ftl";
}
