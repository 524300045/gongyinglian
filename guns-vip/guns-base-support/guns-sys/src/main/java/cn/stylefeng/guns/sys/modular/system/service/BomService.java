package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Bom;
import cn.stylefeng.guns.sys.modular.system.model.params.BomParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BomResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * bom表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-04-16
 */
public interface BomService extends IService<Bom> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-04-16
     */
    void add(BomParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-04-16
     */
    void delete(BomParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-04-16
     */
    void update(BomParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-16
     */
    BomResult findBySpec(BomParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-04-16
     */
    List<BomResult> findListBySpec(BomParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-16
     */
     LayuiPageInfo findPageBySpec(BomParam param);

}
