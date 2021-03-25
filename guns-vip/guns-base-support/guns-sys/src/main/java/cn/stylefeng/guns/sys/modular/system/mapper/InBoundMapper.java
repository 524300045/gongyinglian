package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.InBound;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 入库单 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface InBoundMapper extends BaseMapper<InBound> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<InBoundResult> customList(@Param("paramCondition") InBoundParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") InBoundParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<InBoundResult> customPageList(@Param("page") Page page, @Param("paramCondition") InBoundParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") InBoundParam paramCondition);

}
