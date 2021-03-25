package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.WareStockWaste;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockWasteParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockWasteResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存流水表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface WareStockWasteMapper extends BaseMapper<WareStockWaste> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<WareStockWasteResult> customList(@Param("paramCondition") WareStockWasteParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WareStockWasteParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<WareStockWasteResult> customPageList(@Param("page") Page page, @Param("paramCondition") WareStockWasteParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WareStockWasteParam paramCondition);

}
