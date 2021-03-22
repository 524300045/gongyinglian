package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 采购订单明细表
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
@Data
public class PmsOrderPurchaseDetailParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 采购订单编码
     */
    private String orderNo;

    /**
     * 仓库编码
     */
    private String skuCode;

    /**
     * 仓库名称
     */
    private String goodsName;

    /**
     * 规格
     */
    private String goodsModel;

    /**
     * 单位
     */
    private String unitName;

    /**
     * 0:否 1:是
     */
    private Integer isFresh;

    /**
     * 计划量
     */
    private BigDecimal planNum;

    /**
     * 收货量
     */
    private BigDecimal realityNum;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 单价
     */
    private BigDecimal taxPrice;

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

    @Override
    public String checkParam() {
        return null;
    }

}
