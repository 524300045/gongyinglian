package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 客退单
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
@Data
public class CustomerBackOrderParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 客退单号
     */
    private String customerBackOrderNo;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 销售单号
     */
    private String orderNo;

    /**
     * 状态0:新建 10:已审核 20:入库中  30:完成
     */
    private Integer orderState;

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
     * 完成时间
     */
    private Date finishTime;

    /**
     * 完成人
     */
    private String finishUser;

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
