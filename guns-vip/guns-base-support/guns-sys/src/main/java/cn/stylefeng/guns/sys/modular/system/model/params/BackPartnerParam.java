package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 退供单表
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
@Data
public class BackPartnerParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 订单号
     */
    private String backOrderNo;

    /**
     * 退供类型
     */
    private Integer backType;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 供应商编码
     */
    private String partnerCode;

    /**
     * 供应商名称
     */
    private String partnerName;

    /**
     * 状态
     */
    private Integer orderState;

    /**
     * 备注
     */
    private String remark;

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
     * 出库人
     */
    private String outUser;

    /**
     * 出库时间
     */
    private Date outTime;

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
