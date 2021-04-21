package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.Workshop;
import cn.stylefeng.guns.sys.modular.system.model.params.WorkshopParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WorkshopResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车间 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-04-21
 */
public interface WorkshopMapper extends BaseMapper<Workshop> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-04-21
     */
    List<WorkshopResult> customList(@Param("paramCondition") WorkshopParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-04-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WorkshopParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-04-21
     */
    Page<WorkshopResult> customPageList(@Param("page") Page page, @Param("paramCondition") WorkshopParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-04-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WorkshopParam paramCondition);

}
