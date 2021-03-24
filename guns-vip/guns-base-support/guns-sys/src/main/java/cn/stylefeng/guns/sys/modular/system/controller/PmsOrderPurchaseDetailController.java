package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.entity.WarehouseInfo;
import cn.stylefeng.guns.sys.modular.system.enums.PmsPurchaseStatus;
import cn.stylefeng.guns.sys.modular.system.model.params.*;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerResult;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import cn.stylefeng.guns.sys.modular.system.service.*;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private PartnerGoodsService partnerGoodsService;

    @Autowired
    private CodeService codeService;

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


    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    @ResponseBody
    @RequestMapping("/partnergoodslist")
    public LayuiPageInfo partnerGoodsList(PartnerGoodsParam partnerGoodsParam) {
        if (Strings.isNullOrEmpty(partnerGoodsParam.getPartnerCode()))
        {
            return  LayuiPageFactory.createPageInfo(null);
        }
        return this.partnerGoodsService.findPageBySpec(partnerGoodsParam);
    }


    @RequestMapping("/addItemDetail")
    @ResponseBody
    public ResponseData addItemDetail(@RequestParam("partnerCode") String partnerCode,
                                      @RequestParam("partnerName") String partnerName,
                                      @RequestParam("warehouseCode") String warehouseCode,
                                      @RequestParam("warehouseName") String warehouseName,
                                      @RequestParam("arrivalDate") Date arrivalDate,
                                      @RequestParam("detailStr") String detailStr,
                                      @RequestParam("remark") String remark
    ) {

       List<PmsOrderPurchaseDetailParam>  pmsOrderPurchaseDetailParamList=JSONObject.parseArray(detailStr,PmsOrderPurchaseDetailParam.class);

       if (pmsOrderPurchaseDetailParamList==null||pmsOrderPurchaseDetailParamList.size()==0)
       {
           return ResponseData.error("没有商品明细");
       }
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("warehouseId", warehouseCode);
        String orderNo = this.codeService.generateCode(CodeExpressEnum.billnoPurchase, replaceMap);

        if (Strings.isNullOrEmpty(orderNo))
        {
            return ResponseData.error("采购单号为空");
        }

        for (PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam:pmsOrderPurchaseDetailParamList)
        {
            GoodsResult goodsResult=goodsService.getGoodsBySkuCode(pmsOrderPurchaseDetailParam.getSkuCode());
            pmsOrderPurchaseDetailParam.setGoodsModel(goodsResult.getGoodsModel());
            pmsOrderPurchaseDetailParam.setUnitName(goodsResult.getUnitName());
            pmsOrderPurchaseDetailParam.setRealityNum(new BigDecimal(0));
            pmsOrderPurchaseDetailParam.setTaxRate(goodsResult.getTaxRate());
            pmsOrderPurchaseDetailParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
            pmsOrderPurchaseDetailParam.setCreateTime(new Date());
            pmsOrderPurchaseDetailParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
            pmsOrderPurchaseDetailParam.setUpdateTime(new Date());
            pmsOrderPurchaseDetailParam.setIsFresh(goodsResult.getIsFresh());
            pmsOrderPurchaseDetailParam.setYn(1);
        }

        PmsOrderPurchaseParam pmsOrderPurchaseParam=new PmsOrderPurchaseParam();
        pmsOrderPurchaseParam.setOrderNo(orderNo);
        pmsOrderPurchaseParam.setWarehouseCode(warehouseCode);
        pmsOrderPurchaseParam.setPartnerCode(partnerCode);
        pmsOrderPurchaseParam.setArrivalDate(arrivalDate);
        pmsOrderPurchaseParam.setRemark(remark);
        pmsOrderPurchaseParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        pmsOrderPurchaseParam.setCreateTime(new Date());
        pmsOrderPurchaseParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        pmsOrderPurchaseParam.setUpdateTime(new Date());
        pmsOrderPurchaseParam.setOrderState(PmsPurchaseStatus.NEW.getStatusValue());
        pmsOrderPurchaseParam.setWarehouseName(warehouseName);
        pmsOrderPurchaseParam.setPartnerName(partnerName);
        pmsOrderPurchaseParam.setYn(1);
        pmsOrderPurchaseDetailService.savePmsPurchase(pmsOrderPurchaseParam,pmsOrderPurchaseDetailParamList);
        return ResponseData.success();
    }



}


