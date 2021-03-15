package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author zx
 * @since 2021-03-15
 */
@Data
public class GoodsParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 编码
     */
    private String skuCode;

    /**
     * 分类名称
     */
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
     * 0:停用 1:启用
     */
    private Integer status;

    /**
     * 规格型号
     */
    private String goodsModel;

    /**
     * 品牌
     */
    private String goodsBrand;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 单位编码
     */
    private String unitCode;

    /**
     * 是否生鲜 0:否 1:是
     */
    private Integer isFresh;

    /**
     * 是否称重 0:否 1:是
     */
    private Integer weighed;

    /**
     * 商品类型 0:成品 1：半成品 2：原料
     */
    private Integer productType;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 税率
     */
    private BigDecimal taxRate;

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
