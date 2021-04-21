package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 车间
 * </p>
 *
 * @author zx
 * @since 2021-04-21
 */
@Data
public class WorkshopResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 车间编码
     */
    private String shopCode;

    /**
     * 车间名称 
     */
    private String shopName;

    /**
     * 状态1:启用 0:停用
     */
    private Integer status;

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
