package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-10
 */
public interface PartnerService extends IService<Partner> {

    /**
     * 新增
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    void add(PartnerParam param);

    /**
     * 删除
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    void delete(PartnerParam param);

    /**
     * 更新
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    void update(PartnerParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    PartnerResult findBySpec(PartnerParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    List<PartnerResult> findListBySpec(PartnerParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
     LayuiPageInfo findPageBySpec(PartnerParam param);

}
