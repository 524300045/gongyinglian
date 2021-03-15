package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Category;
import cn.stylefeng.guns.sys.modular.system.factory.LayuiTreeFactory;
import cn.stylefeng.guns.sys.modular.system.model.params.CategoryParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CategoryResult;
import cn.stylefeng.guns.sys.modular.system.service.CategoryService;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 分类表控制器
 *
 * @author zx
 * @Date 2021-03-14 18:51:24
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private String PREFIX = "/modular/system/category";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CodeService codeService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/category.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/category_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/category_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CategoryParam categoryParam) {
        categoryParam.setStatus(1);
        categoryParam.setCreateTime(new Date());
        categoryParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        categoryParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        categoryParam.setUpdateTime(new Date());
        categoryParam.setLevel(1);
        categoryParam.setYn(1);
        CategoryParam singeleCategoryParam=new CategoryParam();
        singeleCategoryParam.setCategoryCode(categoryParam.getParentCode());
        Category categoryResult=categoryService.selectCategoryByCode(singeleCategoryParam);
        if (categoryResult!=null)
        {
            Integer level=categoryResult.getLevel();
            level=level+1;
            categoryParam.setLevel(level);
        }

        String code=this.codeService.generateCode(
                CodeExpressEnum.categoryCode, null);
        if (Strings.isNullOrEmpty(code))
        {
            return ResponseData.error("分类编码为空");
        }

        categoryParam.setCategoryCode(code);
        this.categoryService.add(categoryParam);
        return ResponseData.success();
    }

    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.categoryService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(CategoryParam categoryParam) {
        this.categoryService.update(categoryParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CategoryParam categoryParam) {
        categoryParam.setYn(0);
        categoryParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        categoryParam.setUpdateTime(new Date());
        this.categoryService.update(categoryParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(CategoryParam categoryParam) {
        Category detail = this.categoryService.getById(categoryParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(CategoryParam categoryParam) {
        return this.categoryService.findPageBySpec(categoryParam);
    }


    @RequestMapping(value = "/layuiTree")
    @ResponseBody
    public List<LayuiTreeNode> layuiTree() {

        List<LayuiTreeNode> list = this.categoryService.layuiTree();
        list.add(LayuiTreeFactory.createRoot());

        DefaultTreeBuildFactory<LayuiTreeNode> treeBuildFactory = new DefaultTreeBuildFactory<>();
        treeBuildFactory.setRootParentId("-1");
        return treeBuildFactory.doTreeBuild(list);
    }

}


