package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.enums.PmsPurchaseStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.service.PartnerService;
import cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


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

    @Autowired
    private PartnerService partnerService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("")
    public String index(Model model) {

        List<Partner> partnerList=partnerService.list();

        model.addAttribute("partners", partnerList);
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

    /**
     * 审核接口
     *
     * @author zx
     * @Date 2021-03-22
     */
    @RequestMapping("/audit")
    @ResponseBody
    public ResponseData audit(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        pmsOrderPurchaseParam.setOrderState(PmsPurchaseStatusEnum.AUDIT.getStatusValue());
        pmsOrderPurchaseParam.setAuditUser(LoginContextHolder.getContext().getUser().getUsername());
        pmsOrderPurchaseParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.pmsOrderPurchaseService.updateAudit(pmsOrderPurchaseParam);
        return ResponseData.success();
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public ResponseData cancel(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        pmsOrderPurchaseParam.setOrderState(PmsPurchaseStatusEnum.CANCEL.getStatusValue());
        pmsOrderPurchaseParam.setCancelUser(LoginContextHolder.getContext().getUser().getUsername());
        pmsOrderPurchaseParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.pmsOrderPurchaseService.updateCancel(pmsOrderPurchaseParam);
        return ResponseData.success();
    }

}


