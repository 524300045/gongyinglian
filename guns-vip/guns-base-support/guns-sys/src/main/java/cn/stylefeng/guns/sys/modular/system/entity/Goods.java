package cn.stylefeng.guns.sys.modular.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author zx
 * @since 2021-03-15
 */
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("sku_code")
    private String skuCode;

    /**
     * 分类名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 一级分类编码
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 一级分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 二级分类编码
     */
    @TableField("two_category_code")
    private String twoCategoryCode;

    /**
     * 二级分类名称
     */
    @TableField("two_category_name")
    private String twoCategoryName;

    /**
     * 0:停用 1:启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 规格型号
     */
    @TableField("goods_model")
    private String goodsModel;

    /**
     * 品牌
     */
    @TableField("goods_brand")
    private String goodsBrand;

    /**
     * 重量
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * 单位名称
     */
    @TableField("unit_name")
    private String unitName;

    /**
     * 单位编码
     */
    @TableField("unit_code")
    private String unitCode;

    /**
     * 是否生鲜 0:否 1:是
     */
    @TableField("is_fresh")
    private Integer isFresh;

    /**
     * 是否称重 0:否 1:是
     */
    @TableField("weighed")
    private Integer weighed;

    /**
     * 商品类型 0:成品 1：半成品 2：原料
     */
    @TableField("product_type")
    private Integer productType;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 税率
     */
    @TableField("tax_rate")
    private BigDecimal taxRate;

    /**
     * 创建人
     */
      @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
      @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

    /**
     * 更新时间
     */
      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField("yn")
    private Integer yn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTwoCategoryCode() {
        return twoCategoryCode;
    }

    public void setTwoCategoryCode(String twoCategoryCode) {
        this.twoCategoryCode = twoCategoryCode;
    }

    public String getTwoCategoryName() {
        return twoCategoryName;
    }

    public void setTwoCategoryName(String twoCategoryName) {
        this.twoCategoryName = twoCategoryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Integer getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(Integer isFresh) {
        this.isFresh = isFresh;
    }

    public Integer getWeighed() {
        return weighed;
    }

    public void setWeighed(Integer weighed) {
        this.weighed = weighed;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "id=" + id +
        ", skuCode=" + skuCode +
        ", goodsName=" + goodsName +
        ", categoryCode=" + categoryCode +
        ", categoryName=" + categoryName +
        ", twoCategoryCode=" + twoCategoryCode +
        ", twoCategoryName=" + twoCategoryName +
        ", status=" + status +
        ", goodsModel=" + goodsModel +
        ", goodsBrand=" + goodsBrand +
        ", weight=" + weight +
        ", unitName=" + unitName +
        ", unitCode=" + unitCode +
        ", isFresh=" + isFresh +
        ", weighed=" + weighed +
        ", productType=" + productType +
        ", price=" + price +
        ", taxRate=" + taxRate +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", yn=" + yn +
        "}";
    }
}
