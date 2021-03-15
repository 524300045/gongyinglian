package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.sys.modular.system.entity.Category;
import cn.stylefeng.guns.sys.modular.system.model.params.CategoryParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CategoryResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-03-14
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取列表
     *
     * @author zx
     * @Date 2021-03-14
     */
    List<CategoryResult> customList(@Param("paramCondition") CategoryParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zx
     * @Date 2021-03-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CategoryParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zx
     * @Date 2021-03-14
     */
    Page<CategoryResult> customPageList(@Param("page") Page page, @Param("paramCondition") CategoryParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zx
     * @Date 2021-03-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CategoryParam paramCondition);

    List<LayuiTreeNode> layuiTree();

    List<ZTreeNode> tree();

    Category selectCategoryByCode(CategoryParam paramCondition);

}
