package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.entity.WarehouseInfo;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerResult;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import cn.stylefeng.guns.sys.modular.system.service.PartnerService;
import cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseDetailService;
import cn.stylefeng.guns.sys.modular.system.service.WarehouseInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


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

    @Autowired
    private WarehouseInfoService warehouseInfoService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private GoodsService goodsService;

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
    public String add(Model model) {

        WarehouseInfoParam warehouseInfoParam=new WarehouseInfoParam();
        warehouseInfoParam.setYn(1);
        List<WarehouseInfoResult> warehouseInfoList=warehouseInfoService.findListBySpec(warehouseInfoParam);

        PartnerParam partnerParam=new PartnerParam();
        partnerParam.setYn(1);
        List<PartnerResult> partnerList=partnerService.findListBySpec(partnerParam);
        model.addAttribute("partners", partnerList);


        model.addAttribute("warelist", warehouseInfoList);
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

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    @ResponseBody
    @RequestMapping("/goodslist")
    public LayuiPageInfo goodsList(GoodsParam goodsParam) {
        return this.goodsService.findPageBySpec(goodsParam);
    }

    @RequestMapping("selectGoods")
    public String selectGoods(@RequestParam("formName") String formName,
                              @RequestParam("formId") String formId,
                               Model model) {
        if (ToolUtil.isOneEmpty(formName, formId)) {
            throw new RequestEmptyException("请求数据不完整！");
        }


        try {
            model.addAttribute("formName", URLDecoder.decode(formName, "UTF-8"));
            model.addAttribute("formId", URLDecoder.decode(formId, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RequestEmptyException("请求数据不完整！");
        }
        return PREFIX + "/selectGoods.html";
    }

}


