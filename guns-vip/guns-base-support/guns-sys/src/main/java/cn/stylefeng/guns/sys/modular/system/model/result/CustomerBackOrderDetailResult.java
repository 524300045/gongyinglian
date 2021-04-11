package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 客退明细
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
@Data
public class CustomerBackOrderDetailResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 客退单号
     */
    private String customerBackOrderNo;

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
     * 计划量
     */
    private BigDecimal planNum;

    /**
     * 入库量
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
