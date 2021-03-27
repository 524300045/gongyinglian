package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 销售订单表
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
@Data
public class SaleOrderParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 采购订单编码
     */
    private String orderNo;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 状态0:新建 10:已审核  30:已发货
     */
    private Integer orderState;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 省
     */
    private String provinceCode;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区编码
     */
    private String areaCode;

    /**
     * 区
     */
    private String areaName;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 电话
     */
    private String receiverPhone;

    /**
     * 地址
     */
    private String address;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 取消人
     */
    private String cancelUser;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 出库时间
     */
    private Date deliveryTime;

    /**
     * 发运人
     */
    private String deliveryUser;

    /**
     * 发货日期
     */
    private Date deliveryDate;

    /**
     * 备注
     */
    private String remark;

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
