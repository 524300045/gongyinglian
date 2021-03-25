package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 采购订单表
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
@Data
public class PmsOrderPurchaseParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 采购订单编码
     */
    private String orderNo;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 供应商编码
     */
    private String partnerCode;

    /**
     * 供应商名称
     */
    private String partnerName;

    /**
     * 状态
     */
    private Integer orderState;

    /**
     * 到货日期
     */
    private Date arrivalDate;

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

    private String cancelUser;

    private String auditUser;


    @Override
    public String checkParam() {
        return null;
    }

}
