package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;
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
public class CodeResult implements Serializable {

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

}
