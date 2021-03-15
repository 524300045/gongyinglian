package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author zx
 * @since 2021-03-14
 */
@Data
public class CategoryResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 编码
     */
    private String categoryCode;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 上级ID
     */
    private String parentCode;

    /**
     * 0:停用 1:启用
     */
    private Integer status;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer yn;

}
