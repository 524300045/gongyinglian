package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.WarehouseInfo;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库信息 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-21
 */
public interface WarehouseInfoMapper extends BaseMapper<WarehouseInfo> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-21
     */
    List<WarehouseInfoResult> customList(@Param("paramCondition") WarehouseInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WarehouseInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-21
     */
    Page<WarehouseInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") WarehouseInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WarehouseInfoParam paramCondition);

}
