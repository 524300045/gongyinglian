package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.Bom;
import cn.stylefeng.guns.sys.modular.system.model.params.BomParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BomResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * bom表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-04-16
 */
public interface BomMapper extends BaseMapper<Bom> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-04-16
     */
    List<BomResult> customList(@Param("paramCondition") BomParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-04-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") BomParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-04-16
     */
    Page<BomResult> customPageList(@Param("page") Page page, @Param("paramCondition") BomParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-04-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") BomParam paramCondition);

}
