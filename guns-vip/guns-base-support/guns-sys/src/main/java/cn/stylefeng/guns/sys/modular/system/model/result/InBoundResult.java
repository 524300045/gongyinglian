package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 入库单
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Data
public class InBoundResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 入库单号
     */
    private String inboundTaskCode;

    /**
     * 采购订单编码
     */
    private String orderNo;

    /**
     * 供应商编码
     */
    private String partnerCode;

    /**
     * 供应商名称
     */
    private String partnerName;

    /**
     * 单据类型0:采购入库
     */
    private Integer orderType;

    /**
     * 状态
     */
    private Integer orderState;

    /**
     * 备注
     */
    private String remark;

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
