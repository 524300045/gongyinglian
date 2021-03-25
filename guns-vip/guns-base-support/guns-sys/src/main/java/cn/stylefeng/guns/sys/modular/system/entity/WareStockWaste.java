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
 * 库存流水表
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@TableName("ware_stock_waste")
public class WareStockWaste implements Serializable {

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
     * 关联单号
     */
    @TableField("business_no")
    private String businessNo;

    /**
     * 0:减 1:加
     */
    @TableField("direction")
    private Integer direction;

    /**
     * 变动数量
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 变动后数量
     */
    @TableField("balance_amount")
    private BigDecimal balanceAmount;

    /**
     * 流水类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 业务类型
     */
    @TableField("business_type")
    private Integer businessType;

    /**
     * 操作类型
     */
    @TableField("operation_type")
    private Integer operationType;

    @TableField("remark")
    private String remark;

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

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        return "WareStockWaste{" +
        "id=" + id +
        ", warehouseCode=" + warehouseCode +
        ", warehouseName=" + warehouseName +
        ", skuCode=" + skuCode +
        ", goodsName=" + goodsName +
        ", goodsModel=" + goodsModel +
        ", unitName=" + unitName +
        ", businessNo=" + businessNo +
        ", direction=" + direction +
        ", amount=" + amount +
        ", balanceAmount=" + balanceAmount +
        ", type=" + type +
        ", businessType=" + businessType +
        ", operationType=" + operationType +
        ", remark=" + remark +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", yn=" + yn +
        "}";
    }
}
