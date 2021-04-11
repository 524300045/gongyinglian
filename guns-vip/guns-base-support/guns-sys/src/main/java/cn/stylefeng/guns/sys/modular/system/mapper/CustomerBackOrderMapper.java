package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrder;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CustomerBackOrderResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客退单 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
public interface CustomerBackOrderMapper extends BaseMapper<CustomerBackOrder> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    List<CustomerBackOrderResult> customList(@Param("paramCondition") CustomerBackOrderParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CustomerBackOrderParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    Page<CustomerBackOrderResult> customPageList(@Param("page") Page page, @Param("paramCondition") CustomerBackOrderParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CustomerBackOrderParam paramCondition);

}
