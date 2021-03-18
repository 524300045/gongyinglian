package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PartnerGoods;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerGoodsParam;
import cn.stylefeng.guns.sys.modular.system.service.PartnerGoodsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 供应商商品表控制器
 *
 * @author zx
 * @Date 2021-03-18 17:43:35
 */
@Controller
@RequestMapping("/partnerGoods")
public class PartnerGoodsController extends BaseController {

    private String PREFIX = "/modular/system/partnerGoods";

    @Autowired
    private PartnerGoodsService partnerGoodsService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/partnerGoods.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/partnerGoods_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/partnerGoods_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PartnerGoodsParam partnerGoodsParam) {
        this.partnerGoodsService.add(partnerGoodsParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(PartnerGoodsParam partnerGoodsParam) {
        this.partnerGoodsService.update(partnerGoodsParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PartnerGoodsParam partnerGoodsParam) {
        this.partnerGoodsService.delete(partnerGoodsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PartnerGoodsParam partnerGoodsParam) {
        PartnerGoods detail = this.partnerGoodsService.getById(partnerGoodsParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(PartnerGoodsParam partnerGoodsParam) {
        return this.partnerGoodsService.findPageBySpec(partnerGoodsParam);
    }

}


