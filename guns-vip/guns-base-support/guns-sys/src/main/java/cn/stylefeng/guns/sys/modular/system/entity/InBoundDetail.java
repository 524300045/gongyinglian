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
 * 入库单明细表
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@TableName("in_bound_detail")
public class InBoundDetail implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 入库单号
     */
    @TableField("inbound_task_code")
    private String inboundTaskCode;

    /**
     * 商品编码
     */
    @TableField("sku_code")
    private String skuCode;

    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 规格
     */
    @TableField("goods_model")
    private String goodsModel;

    /**
     * 单位
     */
    @TableField("unit_name")
    private String unitName;

    /**
     * 0:否 1:是
     */
    @TableField("is_fresh")
    private Integer isFresh;

    /**
     * 入库数量
     */
    @TableField("reality_num")
    private BigDecimal realityNum;

    /**
     * 税率
     */
    @TableField("tax_rate")
    private BigDecimal taxRate;

    /**
     * 单价
     */
    @TableField("tax_price")
    private BigDecimal taxPrice;

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

    public String getInboundTaskCode() {
        return inboundTaskCode;
    }

    public void setInboundTaskCode(String inboundTaskCode) {
        this.inboundTaskCode = inboundTaskCode;
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

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(Integer isFresh) {
        this.isFresh = isFresh;
    }

    public BigDecimal getRealityNum() {
        return realityNum;
    }

    public void setRealityNum(BigDecimal realityNum) {
        this.realityNum = realityNum;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
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
        return "InBoundDetail{" +
        "id=" + id +
        ", inboundTaskCode=" + inboundTaskCode +
        ", skuCode=" + skuCode +
        ", goodsName=" + goodsName +
        ", goodsModel=" + goodsModel +
        ", unitName=" + unitName +
        ", isFresh=" + isFresh +
        ", realityNum=" + realityNum +
        ", taxRate=" + taxRate +
        ", taxPrice=" + taxPrice +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", yn=" + yn +
        "}";
    }
}
