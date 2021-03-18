package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.PartnerGoods;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerGoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerGoodsResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商商品表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-18
 */
public interface PartnerGoodsMapper extends BaseMapper<PartnerGoods> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    List<PartnerGoodsResult> customList(@Param("paramCondition") PartnerGoodsParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PartnerGoodsParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    Page<PartnerGoodsResult> customPageList(@Param("page") Page page, @Param("paramCondition") PartnerGoodsParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PartnerGoodsParam paramCondition);

}
