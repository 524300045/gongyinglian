package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WarehouseInfo;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-21
 */
public interface WarehouseInfoService extends IService<WarehouseInfo> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-21
     */
    void add(WarehouseInfoParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-21
     */
    void delete(WarehouseInfoParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-21
     */
    void update(WarehouseInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-21
     */
    WarehouseInfoResult findBySpec(WarehouseInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-21
     */
    List<WarehouseInfoResult> findListBySpec(WarehouseInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-21
     */
     LayuiPageInfo findPageBySpec(WarehouseInfoParam param);

}
