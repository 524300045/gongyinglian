package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 编码表
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-14
 */
@Data
public class CodeParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    private String objectType;

    private Long firstId;

    /**
     * 修改时间
     */
    private Date updateTime;

    @Override
    public String checkParam() {
        return null;
    }

}
