package club.bugmakers.seed.bean.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页信息 DTO
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Data
public class PageResultDTO<T> {

    /**总行数*/
    @ApiModelProperty("总行数")
    private long totalCount;

    /**当前页*/
    @ApiModelProperty("当前页")
    private int pageNo;

    /**每页显示*/
    @ApiModelProperty("每页显示")
    private int pageSize;

    /** 分页数据 */
    @ApiModelProperty("分页数据")
    private List<T> data;
}
