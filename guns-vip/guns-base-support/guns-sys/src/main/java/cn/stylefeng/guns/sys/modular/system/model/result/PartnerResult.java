package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-10
 */
@Data
public class PartnerResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 供应商编码
     */
    private String partnerCode;

    /**
     * 供应商名称
     */
    private String partnerName;

    /**
     * 0:停用 1:启用
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 联系人
     */
    private String contacts;

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
    private String updateTime;

    private Integer yn;

}
