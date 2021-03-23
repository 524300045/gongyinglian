package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 采购订单表控制器
 *
 * @author zx
 * @Date 2021-03-22 14:50:13
 */
@Controller
@RequestMapping("/pmsOrderPurchase")
public class PmsOrderPurchaseController extends BaseController {

    private String PREFIX = "/modular/system/pmsOrderPurchase";

    @Autowired
    private PmsOrderPurchaseService pmsOrderPurchaseService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/pmsOrderPurchase.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/pmsOrderPurchase_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/pmsOrderPurchase_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        this.pmsOrderPurchaseService.add(pmsOrderPurchaseParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        this.pmsOrderPurchaseService.update(pmsOrderPurchaseParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        this.pmsOrderPurchaseService.delete(pmsOrderPurchaseParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        PmsOrderPurchase detail = this.pmsOrderPurchaseService.getById(pmsOrderPurchaseParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        return this.pmsOrderPurchaseService.findPageBySpec(pmsOrderPurchaseParam);
    }

}

