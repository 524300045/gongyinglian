package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WareStockWaste;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockWasteParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockWasteResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 库存流水表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface WareStockWasteService extends IService<WareStockWaste> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-25
     */
    void add(WareStockWasteParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-25
     */
    void delete(WareStockWasteParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-25
     */
    void update(WareStockWasteParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    WareStockWasteResult findBySpec(WareStockWasteParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<WareStockWasteResult> findListBySpec(WareStockWasteParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
     LayuiPageInfo findPageBySpec(WareStockWasteParam param);

}
