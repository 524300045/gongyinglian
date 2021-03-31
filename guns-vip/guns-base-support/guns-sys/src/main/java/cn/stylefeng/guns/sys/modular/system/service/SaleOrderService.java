package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrder;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 销售订单表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
public interface SaleOrderService extends IService<SaleOrder> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-27
     */
    void add(SaleOrderParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-27
     */
    void delete(SaleOrderParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-27
     */
    void update(SaleOrderParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-27
     */
    SaleOrderResult findBySpec(SaleOrderParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-27
     */
    List<SaleOrderResult> findListBySpec(SaleOrderParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-27
     */
     LayuiPageInfo findPageBySpec(SaleOrderParam param);

     boolean addOrder(SaleOrderParam saleOrderParam, List<SaleOrderDetailParam> saleOrderDetailParamList);

    public SaleOrderResult getByOrderNo(String orderNo);

}
