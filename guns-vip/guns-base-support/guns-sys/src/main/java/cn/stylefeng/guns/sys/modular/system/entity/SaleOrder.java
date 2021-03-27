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
 * 销售订单表
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
@TableName("sale_order")
public class SaleOrder implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 采购订单编码
     */
    @TableField("order_no")
    private String orderNo;

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
     * 状态0:新建 10:已审核  30:已发货
     */
    @TableField("order_state")
    private Integer orderState;

    /**
     * 订单总金额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 省
     */
    @TableField("province_code")
    private String provinceCode;

    /**
     * 省
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 市编码
     */
    @TableField("city_code")
    private String cityCode;

    /**
     * 市
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 区编码
     */
    @TableField("area_code")
    private String areaCode;

    /**
     * 区
     */
    @TableField("area_name")
    private String areaName;

    /**
     * 收货人姓名
     */
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 审核人
     */
    @TableField("audit_user")
    private String auditUser;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private Date auditTime;

    /**
     * 取消人
     */
    @TableField("cancel_user")
    private String cancelUser;

    /**
     * 取消时间
     */
    @TableField("cancel_time")
    private Date cancelTime;

    /**
     * 出库时间
     */
    @TableField("delivery_time")
    private Date deliveryTime;

    /**
     * 发运人
     */
    @TableField("delivery_user")
    private String deliveryUser;

    /**
     * 发货日期
     */
    @TableField("delivery_date")
    private Date deliveryDate;

    /**
     * 备注
     */
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(String cancelUser) {
        this.cancelUser = cancelUser;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryUser() {
        return deliveryUser;
    }

    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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
        return "SaleOrder{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", warehouseCode=" + warehouseCode +
        ", warehouseName=" + warehouseName +
        ", orderState=" + orderState +
        ", totalAmount=" + totalAmount +
        ", provinceCode=" + provinceCode +
        ", provinceName=" + provinceName +
        ", cityCode=" + cityCode +
        ", cityName=" + cityName +
        ", areaCode=" + areaCode +
        ", areaName=" + areaName +
        ", receiverName=" + receiverName +
        ", receiverPhone=" + receiverPhone +
        ", address=" + address +
        ", auditUser=" + auditUser +
        ", auditTime=" + auditTime +
        ", cancelUser=" + cancelUser +
        ", cancelTime=" + cancelTime +
        ", deliveryTime=" + deliveryTime +
        ", deliveryUser=" + deliveryUser +
        ", deliveryDate=" + deliveryDate +
        ", remark=" + remark +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", yn=" + yn +
        "}";
    }
}
