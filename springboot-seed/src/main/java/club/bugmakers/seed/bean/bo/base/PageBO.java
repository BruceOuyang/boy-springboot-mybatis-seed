package club.bugmakers.seed.bean.bo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询基类
 * @Author Bruce
 * @Date 2018/12/17 11:21
 * @Version 1.0
 **/
@Data
public class PageBO implements Serializable{

    private static final long serialVersionUID = 7892991066246926189L;

    /** 当前页 */
    @ApiModelProperty("当前页")
    private int pageNo = 1;

    /** 排序列 */
    @ApiModelProperty("排序列")
    private String sort;

    /** 每页显示 */
    @ApiModelProperty("每页显示")
    private int pageSize = 10;
}
