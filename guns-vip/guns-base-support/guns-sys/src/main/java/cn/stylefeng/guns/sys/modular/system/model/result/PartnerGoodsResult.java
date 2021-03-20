package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 供应商商品表
 * </p>
 *
 * @author zx
 * @since 2021-03-18
 */
@Data
public class PartnerGoodsResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 供应商编码
     */
    private String partnerCode;

    private String partnerName;

    /**
     * 商品编码
     */
    private String skuCode;


    private String goodsName;

    /**
     * 一级分类编码
     */
    private String categoryCode;

    /**
     * 一级分类名称
     */
    private String categoryName;

    /**
     * 二级分类编码
     */
    private String twoCategoryCode;

    /**
     * 二级分类名称
     */
    private String twoCategoryName;

    /**
     * 规格型号
     */
    private String goodsModel;

    /**
     * 品牌
     */
    private String goodsBrand;



    /**
     * 单位名称
     */
    private String unitName;

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
