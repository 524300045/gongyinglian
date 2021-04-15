package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.BackPartner;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退供单表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
public interface BackPartnerMapper extends BaseMapper<BackPartner> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-04-14
     */
    List<BackPartnerResult> customList(@Param("paramCondition") BackPartnerParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-04-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") BackPartnerParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-04-14
     */
    Page<BackPartnerResult> customPageList(@Param("page") Page page, @Param("paramCondition") BackPartnerParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-04-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") BackPartnerParam paramCondition);

    int updateOutBound(BackPartnerParam backPartnerParam);

}
