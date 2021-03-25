package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 库存
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Data
public class WareStockResult implements Serializable {

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
     * 商品编码
     */
    private String skuCode;

    /**
     * 仓商品名称
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
     * 库存
     */
    private BigDecimal realStock;

    /**
     * 可用库存
     */
    private BigDecimal forOrderStock;

    /**
     * 预占数量
     */
    private BigDecimal occupyStock;

    /**
     * 锁定数量
     */
    private BigDecimal lockStock;

    /**
     * 残品库存
     */
    private BigDecimal brokenStock;

    /**
     * 在途库存
     */
    private BigDecimal onwayStock;

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
