package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.InBound;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 入库单 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
public interface InBoundService extends IService<InBound> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-25
     */
    void add(InBoundParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-25
     */
    void delete(InBoundParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-25
     */
    void update(InBoundParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    InBoundResult findBySpec(InBoundParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
    List<InBoundResult> findListBySpec(InBoundParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-25
     */
     LayuiPageInfo findPageBySpec(InBoundParam param);

}
