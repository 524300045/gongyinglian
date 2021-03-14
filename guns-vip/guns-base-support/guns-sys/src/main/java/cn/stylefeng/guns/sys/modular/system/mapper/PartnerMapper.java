package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 Mapper 接口
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-10
 */
public interface PartnerMapper extends BaseMapper<Partner> {

    /**
     * 获取列表
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    List<PartnerResult> customList(@Param("paramCondition") PartnerParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PartnerParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    Page<PartnerResult> customPageList(@Param("page") Page page, @Param("paramCondition") PartnerParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PartnerParam paramCondition);

}
