package club.bugmakers.seed.bean.bo.user;

import club.bugmakers.seed.bean.bo.base.BaseBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import java.util.Date;

/**
 * 用户信息表
 * @author Code_Generator
 * @date 2019-01-09 15:15:00
 */
@Data
public class UserInfoBO extends BaseBO implements Serializable {
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