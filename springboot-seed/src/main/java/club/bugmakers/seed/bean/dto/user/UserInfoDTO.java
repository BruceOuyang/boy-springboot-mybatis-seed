package club.bugmakers.seed.bean.dto.user;

import club.bugmakers.seed.bean.dto.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表
 * @author BOY_Code_Generator
 * @date 2018-12-18 11:55:32
 */
@Data
public class UserInfoDTO extends BaseDTO implements Serializable {
  /**
   * 用户姓名
   */
  private String name;
  /**
   * 英文名
   */
  private String ename;
  /**
   * 备注
   */
  private String remark;
}
