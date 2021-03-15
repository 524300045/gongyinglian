package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-15
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    List<GoodsResult> customList(@Param("paramCondition") GoodsParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") GoodsParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    Page<GoodsResult> customPageList(@Param("page") Page page, @Param("paramCondition") GoodsParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") GoodsParam paramCondition);

}
