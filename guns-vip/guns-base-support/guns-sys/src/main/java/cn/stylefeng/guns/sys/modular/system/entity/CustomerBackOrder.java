package cn.stylefeng.guns.sys.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 客退单
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
@TableName("customer_back_order")
public class CustomerBackOrder implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客退单号
     */
    @TableField("customer_back_order_no")
    private String customerBackOrderNo;

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
     * 销售单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 状态0:新建 10:已审核 20:入库中  30:完成
     */
    @TableField("order_state")
    private Integer orderState;

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
     * 完成时间
     */
    @TableField("finish_time")
    private Date finishTime;

    /**
     * 完成人
     */
    @TableField("finish_user")
    private String finishUser;

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

    public String getCustomerBackOrderNo() {
        return customerBackOrderNo;
    }

    public void setCustomerBackOrderNo(String customerBackOrderNo) {
        this.customerBackOrderNo = customerBackOrderNo;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
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

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getFinishUser() {
        return finishUser;
    }

    public void setFinishUser(String finishUser) {
        this.finishUser = finishUser;
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
        return "CustomerBackOrder{" +
        "id=" + id +
        ", customerBackOrderNo=" + customerBackOrderNo +
        ", warehouseCode=" + warehouseCode +
        ", warehouseName=" + warehouseName +
        ", orderNo=" + orderNo +
        ", orderState=" + orderState +
        ", receiverName=" + receiverName +
        ", receiverPhone=" + receiverPhone +
        ", address=" + address +
        ", auditUser=" + auditUser +
        ", auditTime=" + auditTime +
        ", cancelUser=" + cancelUser +
        ", cancelTime=" + cancelTime +
        ", finishTime=" + finishTime +
        ", finishUser=" + finishUser +
        ", remark=" + remark +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", yn=" + yn +
        "}";
    }
}
