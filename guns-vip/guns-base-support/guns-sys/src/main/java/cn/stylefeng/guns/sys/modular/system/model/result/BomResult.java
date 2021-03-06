package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * bom表
 * </p>
 *
 * @author zx
 * @since 2021-04-16
 */
@Data
public class BomResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 编码
     */
    private String skuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    private String unitName;



    /**
     * 组件SKU
     */
    private String childSkuCode;

    /**
     * 组件商品名称
     */
    private String childGoodsName;

    private String childUnitName;

    /**
     * 数量
     */
    private BigDecimal proportion;

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
