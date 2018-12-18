package ${cfg.beanBoPackage};

import ${cfg.baseBoPackage}.BaseBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#if table.importBeanList??>
 <#list table.importBeanList as item>
import ${item};
 </#list>
</#if>

/**
 * ${table.tableComment}
 * @author BOY_Code_Generator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Data
public class ${table.beanName}BO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;

<#list table.propertyList as col>
 <#if col.columnComment?has_content>
  /**
   * ${col.columnComment}
   */
  @ApiModelProperty("${col.columnComment}")
 </#if>
  private ${col.propertyType} ${col.propertyName};
</#list>
}