package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Workshop;
import cn.stylefeng.guns.sys.modular.system.model.params.WorkshopParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WorkshopResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 车间 服务类
 * </p>
 *
 * @author zx
 * @since 2021-04-21
 */
public interface WorkshopService extends IService<Workshop> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-04-21
     */
    void add(WorkshopParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-04-21
     */
    void delete(WorkshopParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-04-21
     */
    void update(WorkshopParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-21
     */
    WorkshopResult findBySpec(WorkshopParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-04-21
     */
    List<WorkshopResult> findListBySpec(WorkshopParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-04-21
     */
     LayuiPageInfo findPageBySpec(WorkshopParam param);

}
