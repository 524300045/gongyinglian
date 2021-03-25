package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.InBoundDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundDetailResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 入库单明细表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface InBoundDetailMapper extends BaseMapper<InBoundDetail> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<InBoundDetailResult> customList(@Param("paramCondition") InBoundDetailParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") InBoundDetailParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<InBoundDetailResult> customPageList(@Param("page") Page page, @Param("paramCondition") InBoundDetailParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") InBoundDetailParam paramCondition);

}
