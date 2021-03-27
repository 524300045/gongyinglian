package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WareStock;
import cn.stylefeng.guns.sys.modular.system.enums.LocationStockDirectionEnum;
import cn.stylefeng.guns.sys.modular.system.enums.OperationTypeEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.LocationStockParam;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 库存 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface WareStockService extends IService<WareStock> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-25
     */
    void add(WareStockParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-25
     */
    void delete(WareStockParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-25
     */
    void update(WareStockParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    WareStockResult findBySpec(WareStockParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<WareStockResult> findListBySpec(WareStockParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
     LayuiPageInfo findPageBySpec(WareStockParam param);

    public boolean updateRealAndForOrderStock(LocationStockParam locationStockParam, OperationTypeEnum operationTypeEnum, LocationStockDirectionEnum directionEnum, String oper);

}
