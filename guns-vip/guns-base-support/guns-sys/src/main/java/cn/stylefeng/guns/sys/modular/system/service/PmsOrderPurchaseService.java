package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PmsOrderPurchaseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 采购订单表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
public interface PmsOrderPurchaseService extends IService<PmsOrderPurchase> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-22
     */
    void add(PmsOrderPurchaseParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-22
     */
    void delete(PmsOrderPurchaseParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-22
     */
    void update(PmsOrderPurchaseParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-22
     */
    PmsOrderPurchaseResult findBySpec(PmsOrderPurchaseParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-22
     */
    List<PmsOrderPurchaseResult> findListBySpec(PmsOrderPurchaseParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-22
     */
     LayuiPageInfo findPageBySpec(PmsOrderPurchaseParam param);

    boolean updateCancel(PmsOrderPurchaseParam pmsOrderPurchaseParam);

    boolean updateAudit(PmsOrderPurchaseParam pmsOrderPurchaseParam);



}
