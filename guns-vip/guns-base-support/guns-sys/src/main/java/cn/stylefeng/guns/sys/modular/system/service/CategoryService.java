package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Category;
import cn.stylefeng.guns.sys.modular.system.model.params.CategoryParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CategoryResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author zx
 * @since 2021-03-14
 */
public interface CategoryService extends IService<Category> {

    /**
     * 新增
     *
     * @author zx
     * @Date 2021-03-14
     */
    void add(CategoryParam param);

    /**
     * 删除
     *
     * @author zx
     * @Date 2021-03-14
     */
    void delete(CategoryParam param);

    /**
     * 更新
     *
     * @author zx
     * @Date 2021-03-14
     */
    void update(CategoryParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-14
     */
    CategoryResult findBySpec(CategoryParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zx
     * @Date 2021-03-14
     */
    List<CategoryResult> findListBySpec(CategoryParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zx
     * @Date 2021-03-14
     */
     LayuiPageInfo findPageBySpec(CategoryParam param);

    public List<LayuiTreeNode> layuiTree();

    public List<ZTreeNode> tree();

    public     Category selectCategoryByCode(CategoryParam paramCondition);

}
