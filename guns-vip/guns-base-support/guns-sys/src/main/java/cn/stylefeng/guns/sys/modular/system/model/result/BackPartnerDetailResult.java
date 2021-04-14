package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 退供明细表
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
@Data
public class BackPartnerDetailResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 退供单号
     */
    private String backOrderNo;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 名称
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
     * 计划量
     */
    private BigDecimal planNum;

    /**
     * 出库量
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

}
