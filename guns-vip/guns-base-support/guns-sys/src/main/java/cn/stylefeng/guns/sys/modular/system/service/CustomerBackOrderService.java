package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrder;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CustomerBackOrderResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 客退单 服务类
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
public interface CustomerBackOrderService extends IService<CustomerBackOrder> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-04-11
     */
    void add(CustomerBackOrderParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-04-11
     */
    void delete(CustomerBackOrderParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-04-11
     */
    void update(CustomerBackOrderParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-11
     */
    CustomerBackOrderResult findBySpec(CustomerBackOrderParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-04-11
     */
    List<CustomerBackOrderResult> findListBySpec(CustomerBackOrderParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-11
     */
     LayuiPageInfo findPageBySpec(CustomerBackOrderParam param);

}
