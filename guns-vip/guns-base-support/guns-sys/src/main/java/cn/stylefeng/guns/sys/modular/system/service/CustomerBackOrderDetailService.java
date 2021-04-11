package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrderDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CustomerBackOrderDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 客退明细 服务类
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
public interface CustomerBackOrderDetailService extends IService<CustomerBackOrderDetail> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-04-11
     */
    void add(CustomerBackOrderDetailParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-04-11
     */
    void delete(CustomerBackOrderDetailParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-04-11
     */
    void update(CustomerBackOrderDetailParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-11
     */
    CustomerBackOrderDetailResult findBySpec(CustomerBackOrderDetailParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-04-11
     */
    List<CustomerBackOrderDetailResult> findListBySpec(CustomerBackOrderDetailParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-11
     */
     LayuiPageInfo findPageBySpec(CustomerBackOrderDetailParam param);

}
