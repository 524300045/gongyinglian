package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PartnerGoods;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerGoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerGoodsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 供应商商品表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-18
 */
public interface PartnerGoodsService extends IService<PartnerGoods> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-18
     */
    void add(PartnerGoodsParam param);

    void addBatch(List<PartnerGoodsParam> paramList);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-18
     */
    void delete(PartnerGoodsParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-18
     */
    void update(PartnerGoodsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-18
     */
    PartnerGoodsResult findBySpec(PartnerGoodsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-18
     */
    List<PartnerGoodsResult> findListBySpec(PartnerGoodsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-18
     */
     LayuiPageInfo findPageBySpec(PartnerGoodsParam param);

}
