package club.bugmakers.seed.bean.bo.base;

import lombok.Data;

/**
 * BO 基类
 * @Author Bruce
 * @Date 2018/12/17 11:21
 * @Version 1.0
 **/
@Data
public class BaseBO implements java.io.Serializable {

    private static final long serialVersionUID = 4795998576905298649L;
    /**
     * 主键
     */
    private Integer id;
}
