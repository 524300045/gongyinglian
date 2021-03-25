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
 * 库存
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@TableName("ware_stock")
public class WareStock implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 仓库编码
     */
    @TableField("warehouse_code")
    private String warehouseCode;

    /**
     * 仓库名称
     */
    @TableField("warehouse_name")
    private String warehouseName;

    /**
     * 商品编码
     */
    @TableField("sku_code")
    private String skuCode;

    /**
     * 仓商品名称
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
     * 库存
     */
    @TableField("real_stock")
    private BigDecimal realStock;

    /**
     * 可用库存
     */
    @TableField("for_order_stock")
    private BigDecimal forOrderStock;

    /**
     * 预占数量
     */
    @TableField("occupy_stock")
    private BigDecimal occupyStock;

    /**
     * 锁定数量
     */
    @TableField("lock_stock")
    private BigDecimal lockStock;

    /**
     * 残品库存
     */
    @TableField("broken_stock")
    private BigDecimal brokenStock;

    /**
     * 在途库存
     */
    @TableField("onway_stock")
    private BigDecimal onwayStock;

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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public BigDecimal getRealStock() {
        return realStock;
    }

    public void setRealStock(BigDecimal realStock) {
        this.realStock = realStock;
    }

    public BigDecimal getForOrderStock() {
        return forOrderStock;
    }

    public void setForOrderStock(BigDecimal forOrderStock) {
        this.forOrderStock = forOrderStock;
    }

    public BigDecimal getOccupyStock() {
        return occupyStock;
    }

    public void setOccupyStock(BigDecimal occupyStock) {
        this.occupyStock = occupyStock;
    }

    public BigDecimal getLockStock() {
        return lockStock;
    }

    public void setLockStock(BigDecimal lockStock) {
        this.lockStock = lockStock;
    }

    public BigDecimal getBrokenStock() {
        return brokenStock;
    }

    public void setBrokenStock(BigDecimal brokenStock) {
        this.brokenStock = brokenStock;
    }

    public BigDecimal getOnwayStock() {
        return onwayStock;
    }

    public void setOnwayStock(BigDecimal onwayStock) {
        this.onwayStock = onwayStock;
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
        return "WareStock{" +
        "id=" + id +
        ", warehouseCode=" + warehouseCode +
        ", warehouseName=" + warehouseName +
        ", skuCode=" + skuCode +
        ", goodsName=" + goodsName +
        ", goodsModel=" + goodsModel +
        ", unitName=" + unitName +
        ", realStock=" + realStock +
        ", forOrderStock=" + forOrderStock +
        ", occupyStock=" + occupyStock +
        ", lockStock=" + lockStock +
        ", brokenStock=" + brokenStock +
        ", onwayStock=" + onwayStock +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", yn=" + yn +
        "}";
    }
}
