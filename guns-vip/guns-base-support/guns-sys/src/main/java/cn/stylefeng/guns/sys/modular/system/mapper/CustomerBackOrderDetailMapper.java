package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrderDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CustomerBackOrderDetailResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客退明细 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
public interface CustomerBackOrderDetailMapper extends BaseMapper<CustomerBackOrderDetail> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    List<CustomerBackOrderDetailResult> customList(@Param("paramCondition") CustomerBackOrderDetailParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CustomerBackOrderDetailParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    Page<CustomerBackOrderDetailResult> customPageList(@Param("page") Page page, @Param("paramCondition") CustomerBackOrderDetailParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CustomerBackOrderDetailParam paramCondition);

}
