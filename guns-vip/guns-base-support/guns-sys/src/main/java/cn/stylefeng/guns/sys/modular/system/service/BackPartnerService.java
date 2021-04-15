package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartner;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 退供单表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
public interface BackPartnerService extends IService<BackPartner> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-04-14
     */
    void add(BackPartnerParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-04-14
     */
    void delete(BackPartnerParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-04-14
     */
    void update(BackPartnerParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-14
     */
    BackPartnerResult findBySpec(BackPartnerParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-04-14
     */
    List<BackPartnerResult> findListBySpec(BackPartnerParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-14
     */
     LayuiPageInfo findPageBySpec(BackPartnerParam param);

    int updateOutBound(BackPartnerParam backPartnerParam);

}
