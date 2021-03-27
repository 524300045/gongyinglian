package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.SaleOrderDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderDetailResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售订单明细 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
public interface SaleOrderDetailMapper extends BaseMapper<SaleOrderDetail> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    List<SaleOrderDetailResult> customList(@Param("paramCondition") SaleOrderDetailParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SaleOrderDetailParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    Page<SaleOrderDetailResult> customPageList(@Param("page") Page page, @Param("paramCondition") SaleOrderDetailParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SaleOrderDetailParam paramCondition);

}
