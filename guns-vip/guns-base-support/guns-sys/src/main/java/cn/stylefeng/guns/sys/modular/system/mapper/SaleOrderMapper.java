package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.SaleOrder;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售订单表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    List<SaleOrderResult> customList(@Param("paramCondition") SaleOrderParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SaleOrderParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    Page<SaleOrderResult> customPageList(@Param("page") Page page, @Param("paramCondition") SaleOrderParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SaleOrderParam paramCondition);

    int updateOutBound(SaleOrderParam saleOrderParam);

}
