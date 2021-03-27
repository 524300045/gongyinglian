package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PmsOrderPurchaseResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购订单表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
public interface PmsOrderPurchaseMapper extends BaseMapper<PmsOrderPurchase> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-22
     */
    List<PmsOrderPurchaseResult> customList(@Param("paramCondition") PmsOrderPurchaseParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PmsOrderPurchaseParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-22
     */
    Page<PmsOrderPurchaseResult> customPageList(@Param("page") Page page, @Param("paramCondition") PmsOrderPurchaseParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PmsOrderPurchaseParam paramCondition);

    int updateCancel(PmsOrderPurchaseParam pmsOrderPurchaseParam);

    int updateAudit(PmsOrderPurchaseParam pmsOrderPurchaseParam);

    PmsOrderPurchaseResult selectByOrderNo(String orderNo);

}
