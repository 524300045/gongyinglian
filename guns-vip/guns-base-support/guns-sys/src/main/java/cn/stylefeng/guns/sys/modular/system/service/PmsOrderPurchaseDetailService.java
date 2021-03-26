package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PmsOrderPurchaseDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 采购订单明细表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
public interface PmsOrderPurchaseDetailService extends IService<PmsOrderPurchaseDetail> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-22
     */
    void add(PmsOrderPurchaseDetailParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-22
     */
    void delete(PmsOrderPurchaseDetailParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-22
     */
    void update(PmsOrderPurchaseDetailParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-22
     */
    PmsOrderPurchaseDetailResult findBySpec(PmsOrderPurchaseDetailParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-22
     */
    List<PmsOrderPurchaseDetailResult> findListBySpec(PmsOrderPurchaseDetailParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-22
     */
     LayuiPageInfo findPageBySpec(PmsOrderPurchaseDetailParam param);

     boolean savePmsPurchase(PmsOrderPurchaseParam pmsOrderPurchaseParam,
                                   List<PmsOrderPurchaseDetailParam> pmsOrderPurchaseDetailParams);

       LayuiPageInfo selectPageInfo(PmsOrderPurchaseDetailParam param);



}
