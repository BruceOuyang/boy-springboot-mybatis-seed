package club.bugmakers.seed.bean.po.user;

import club.bugmakers.seed.bean.po.base.BasePO;

import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * 用户信息表
 * @author Code_Generator
 * @date 2019-01-09 15:15:00
 */
@Data
public class UserInfoPO extends BasePO implements Serializable {
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
