package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 库存流水表
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Data
public class WareStockWasteResult implements Serializable {

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
     * 关联单号
     */
    private String businessNo;

    /**
     * 0:减 1:加
     */
    private Integer direction;

    /**
     * 变动数量
     */
    private BigDecimal amount;

    /**
     * 变动后数量
     */
    private BigDecimal balanceAmount;

    /**
     * 流水类型
     */
    private Integer type;

    /**
     * 业务类型
     */
    private Integer businessType;

    /**
     * 操作类型
     */
    private Integer operationType;

    private String remark;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer yn;

}
