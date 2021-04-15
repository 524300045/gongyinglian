package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartner;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartnerDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.params.LocationStockParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 退供明细表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
public interface BackPartnerDetailService extends IService<BackPartnerDetail> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-04-14
     */
    void add(BackPartnerDetailParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-04-14
     */
    void delete(BackPartnerDetailParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-04-14
     */
    void update(BackPartnerDetailParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-14
     */
    BackPartnerDetailResult findBySpec(BackPartnerDetailParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-04-14
     */
    List<BackPartnerDetailResult> findListBySpec(BackPartnerDetailParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-14
     */
     LayuiPageInfo findPageBySpec(BackPartnerDetailParam param);

     void saveBackPartner(BackPartnerParam backPartnerParam,List<BackPartnerDetailParam> backPartnerDetailParamList);

     void saveOutBound(BackPartnerParam backPartnerParam, List<LocationStockParam> locationStockParams, String user);
}
