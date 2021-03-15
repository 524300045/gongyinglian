package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Category;
import cn.stylefeng.guns.sys.modular.system.mapper.CategoryMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.CategoryParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CategoryResult;
import  cn.stylefeng.guns.sys.modular.system.service.CategoryService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public void add(CategoryParam param){
        Category entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CategoryParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CategoryParam param){
        Category oldEntity = getOldEntity(param);
        Category newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CategoryResult findBySpec(CategoryParam param){
        return null;
    }

    @Override
    public List<CategoryResult> findListBySpec(CategoryParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CategoryParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CategoryParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Category getOldEntity(CategoryParam param) {
        return this.getById(getKey(param));
    }

    private Category getEntity(CategoryParam param) {
        Category entity = new Category();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public List<LayuiTreeNode> layuiTree() {
        return this.baseMapper.layuiTree();
    }

    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    public     Category selectCategoryByCode(CategoryParam paramCondition)
    {
        return this.baseMapper.selectCategoryByCode(paramCondition);
    }

}
