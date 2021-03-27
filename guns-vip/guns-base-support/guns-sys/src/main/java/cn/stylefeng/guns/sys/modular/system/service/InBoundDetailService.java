package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.InBoundDetail;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 入库单明细表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface InBoundDetailService extends IService<InBoundDetail> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-25
     */
    void add(InBoundDetailParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-25
     */
    void delete(InBoundDetailParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-25
     */
    void update(InBoundDetailParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    InBoundDetailResult findBySpec(InBoundDetailParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<InBoundDetailResult> findListBySpec(InBoundDetailParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
     LayuiPageInfo findPageBySpec(InBoundDetailParam param);

     String updateInboundNum(PmsOrderPurchaseDetail pmsOrderPurchaseDetail);

}
