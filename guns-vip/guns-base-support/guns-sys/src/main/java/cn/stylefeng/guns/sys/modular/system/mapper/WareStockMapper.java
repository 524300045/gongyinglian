package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.WareStock;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface WareStockMapper extends BaseMapper<WareStock> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<WareStockResult> customList(@Param("paramCondition") WareStockParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WareStockParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<WareStockResult> customPageList(@Param("page") Page page, @Param("paramCondition") WareStockParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WareStockParam paramCondition);

    List<WareStockResult> selectListBySkuCode(WareStockParam wareStockParam);

    int updateRealAndForOrderStock(cn.stylefeng.guns.sys.modular.system.entity.WareStock wareStock);

}
