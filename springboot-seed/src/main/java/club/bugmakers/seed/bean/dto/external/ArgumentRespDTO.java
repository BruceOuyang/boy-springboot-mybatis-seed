package club.bugmakers.seed.bean.dto.external;

import lombok.Data;

/**
 * 请求相应 DTO
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Data
public class ArgumentRespDTO {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 值
     */
    private String value;
}
