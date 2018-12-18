package club.bugmakers.seed.bean.po.base;

import lombok.Data;

import java.util.Date;

/**
 * PO 基类
 * @Author Bruce
 * @Date 2018/12/17 11:21
 * @Version 1.0
 **/
@Data
public class BasePO implements java.io.Serializable {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 创建时间
     */
    private Date createDt;
    /**
     * 创建人标识
     */
    private String createBy;
    /**
     * 修改时间
     */
    private Date updateDt;
    /**
     * 修改人标识
     */
    private String updateBy;
    /**
     * 删除标识 0-未删除；1-已删除
     */
    private Integer isDelete;
}
