package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 入库单明细表
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Data
public class InBoundDetailResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 入库单号
     */
    private String inboundTaskCode;

    /**
     * 商品编码
     */
    private String skuCode;

    /**
     * 商品名称
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
     * 入库数量
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


}
