package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseDetailService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 采购订单明细表控制器
 *
 * @author zx
 * @Date 2021-03-22 14:50:13
 */
@Controller
@RequestMapping("/pmsOrderPurchaseDetail")
public class PmsOrderPurchaseDetailController extends BaseController {

    private String PREFIX = "/modular/system/pmsOrderPurchaseDetail";

    @Autowired
    private PmsOrderPurchaseDetailService pmsOrderPurchaseDetailService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/pmsOrderPurchaseDetail.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/pmsOrderPurchaseDetail_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/pmsOrderPurchaseDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam) {
        this.pmsOrderPurchaseDetailService.add(pmsOrderPurchaseDetailParam);
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
    public ResponseData editItem(PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam) {
        this.pmsOrderPurchaseDetailService.update(pmsOrderPurchaseDetailParam);
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
    public ResponseData delete(PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam) {
        this.pmsOrderPurchaseDetailService.delete(pmsOrderPurchaseDetailParam);
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
    public ResponseData detail(PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam) {
        PmsOrderPurchaseDetail detail = this.pmsOrderPurchaseDetailService.getById(pmsOrderPurchaseDetailParam.getId());
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
    public LayuiPageInfo list(PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam) {
        return this.pmsOrderPurchaseDetailService.findPageBySpec(pmsOrderPurchaseDetailParam);
    }

}


