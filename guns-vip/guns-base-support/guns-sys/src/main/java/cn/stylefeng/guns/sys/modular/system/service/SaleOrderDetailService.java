package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrderDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 销售订单明细 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
public interface SaleOrderDetailService extends IService<SaleOrderDetail> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-27
     */
    void add(SaleOrderDetailParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-27
     */
    void delete(SaleOrderDetailParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-27
     */
    void update(SaleOrderDetailParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-27
     */
    SaleOrderDetailResult findBySpec(SaleOrderDetailParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-27
     */
    List<SaleOrderDetailResult> findListBySpec(SaleOrderDetailParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-27
     */
     LayuiPageInfo findPageBySpec(SaleOrderDetailParam param);

}
