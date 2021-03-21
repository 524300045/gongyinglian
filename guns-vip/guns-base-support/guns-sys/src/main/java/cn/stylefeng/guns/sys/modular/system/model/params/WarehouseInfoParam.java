package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 仓库信息
 * </p>
 *
 * @author zx
 * @since 2021-03-21
 */
@Data
public class WarehouseInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 仓库名称 
     */
    private String warehouseName;

    /**
     * 状态1:启用 0:停用
     */
    private Integer status;

    private String address;

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
