package club.bugmakers.seed.bean.bo.user;

import club.bugmakers.seed.bean.bo.base.PageBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息表
 * @author BOY_Code_Generator
 * @date 2018-12-18 11:55:32
 */
@Data
public class UserInfoBoPage extends PageBO {
  /**
   * 用户姓名
   */
  @ApiModelProperty("用户姓名")
  private String name;
  /**
   * 英文名
   */
  @ApiModelProperty("英文名")
  private String ename;
  /**
   * 备注
   */
  @ApiModelProperty("备注")
  private String remark;
}