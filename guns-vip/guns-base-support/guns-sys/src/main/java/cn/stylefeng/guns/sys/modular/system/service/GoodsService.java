package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-15
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-15
     */
    void add(GoodsParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-15
     */
    void delete(GoodsParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-15
     */
    void update(GoodsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-15
     */
    GoodsResult findBySpec(GoodsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-15
     */
    List<GoodsResult> findListBySpec(GoodsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-15
     */
     LayuiPageInfo findPageBySpec(GoodsParam param);

    GoodsResult getGoodsBySkuCode(String skuCode);

}
