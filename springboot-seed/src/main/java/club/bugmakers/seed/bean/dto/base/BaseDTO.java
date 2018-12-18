package club.bugmakers.seed.bean.dto.base;

import lombok.Data;

import java.util.Date;

/**
 * DTO 基类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Data
public class BaseDTO implements java.io.Serializable {

    private static final long serialVersionUID = 836651607659349464L;
    /**
     * 主键
     */
    private Integer id;
}
