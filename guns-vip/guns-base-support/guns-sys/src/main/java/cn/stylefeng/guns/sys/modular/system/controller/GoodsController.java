package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Category;
import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.model.params.CategoryParam;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CategoryResult;
import cn.stylefeng.guns.sys.modular.system.service.CategoryService;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 商品表控制器
 *
 * @author zx
 * @Date 2021-03-15 17:16:02
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private String PREFIX = "/modular/system/goods";

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CodeService codeService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/goods.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/goods_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/goods_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(GoodsParam goodsParam) {

        CategoryParam categoryParam=new CategoryParam();
        categoryParam.setCategoryCode(goodsParam.getTwoCategoryCode());
        Category twoCategory=categoryService.selectCategoryByCode(categoryParam);
        if (twoCategory==null)
            return ResponseData.error("查询不到分类信息");

        if (Strings.isNullOrEmpty(twoCategory.getParentCode())||twoCategory.getParentCode().equals("0"))
        {
            return ResponseData.error("只能选择末级分类");
        }

        String code=this.codeService.generateCode(
                CodeExpressEnum.skuCode, null);
        if (Strings.isNullOrEmpty(code))
        {
            return ResponseData.error("商品编码为空");
        }

        CategoryParam categoryParamTwo=new CategoryParam();
        categoryParamTwo.setCategoryCode(twoCategory.getParentCode());

        Category category=categoryService.selectCategoryByCode(categoryParamTwo);

        goodsParam.setSkuCode(code);
        goodsParam.setCategoryCode(category.getCategoryCode());
        goodsParam.setCategoryName(category.getCategoryName());
        goodsParam.setYn(1);
        goodsParam.setStatus(1);
        goodsParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setCreateTime(new Date());
        goodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setUpdateTime(new Date());
        this.goodsService.add(goodsParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(GoodsParam goodsParam) {


        CategoryParam categoryParam=new CategoryParam();
        categoryParam.setCategoryCode(goodsParam.getTwoCategoryCode());
        Category twoCategory=categoryService.selectCategoryByCode(categoryParam);
        if (twoCategory==null)
            return ResponseData.error("查询不到分类信息");

        if (Strings.isNullOrEmpty(twoCategory.getParentCode())||twoCategory.getParentCode().equals("0"))
        {
            return ResponseData.error("只能选择末级分类");
        }
        CategoryParam categoryParamTwo=new CategoryParam();
        categoryParamTwo.setCategoryCode(twoCategory.getParentCode());

        Category category=categoryService.selectCategoryByCode(categoryParamTwo);

        goodsParam.setCategoryCode(category.getCategoryCode());
        goodsParam.setCategoryName(category.getCategoryName());
        goodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setUpdateTime(new Date());
        this.goodsService.update(goodsParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(GoodsParam goodsParam) {
        goodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setUpdateTime(new Date());
        goodsParam.setYn(0);
        this.goodsService.update(goodsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(GoodsParam goodsParam) {
        Goods detail = this.goodsService.getById(goodsParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(GoodsParam goodsParam) {
        return this.goodsService.findPageBySpec(goodsParam);
    }

}


