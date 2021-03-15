package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
        this.goodsService.delete(goodsParam);
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


