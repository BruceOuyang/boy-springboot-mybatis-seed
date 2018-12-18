package club.bugmakers.seed.bean.bo.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Data
public class CrmUserBO {
    private Long id;
    private String name;
    private String mobile;
    private String email;
    private Integer gender;
    private String jobNumber;
    private String position;
    private String tel;
    private Date hiredDate;
    private String workPlace;
    private Integer status;
    private String avatar;
    private Integer hasCrm;
    private String idNo;
    private String subIdNo;
    private Integer idType;
}
