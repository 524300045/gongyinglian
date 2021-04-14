package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartnerDetail;
import cn.stylefeng.guns.sys.modular.system.enums.BackPartnerStatusEnum;
import cn.stylefeng.guns.sys.modular.system.enums.PmsPurchaseStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.*;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerResult;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import cn.stylefeng.guns.sys.modular.system.service.*;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 退供明细表控制器
 *
 * @author zx
 * @Date 2021-04-14 17:42:29
 */
@Controller
@RequestMapping("/backPartnerDetail")
public class BackPartnerDetailController extends BaseController {

    private String PREFIX = "/modular/system/backPartnerDetail";

    @Autowired
    private BackPartnerDetailService backPartnerDetailService;


    @Autowired
    private WarehouseInfoService warehouseInfoService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PartnerGoodsService partnerGoodsService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/backPartnerDetail.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-14
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
        return PREFIX + "/backPartnerDetail_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/backPartnerDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BackPartnerDetailParam backPartnerDetailParam) {
        this.backPartnerDetailService.add(backPartnerDetailParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(BackPartnerDetailParam backPartnerDetailParam) {
        this.backPartnerDetailService.update(backPartnerDetailParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(BackPartnerDetailParam backPartnerDetailParam) {
        this.backPartnerDetailService.delete(backPartnerDetailParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(BackPartnerDetailParam backPartnerDetailParam) {
        BackPartnerDetail detail = this.backPartnerDetailService.getById(backPartnerDetailParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-04-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(BackPartnerDetailParam backPartnerDetailParam) {
        return this.backPartnerDetailService.findPageBySpec(backPartnerDetailParam);
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
                                      @RequestParam("detailStr") String detailStr,
                                      @RequestParam("remark") String remark
    ) {

        List<BackPartnerDetailParam>  backPartnerDetailParams= JSONObject.parseArray(detailStr,BackPartnerDetailParam.class);

        if (backPartnerDetailParams==null||backPartnerDetailParams.size()==0)
        {
            return ResponseData.error("没有商品明细");
        }
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("warehouseId", warehouseCode);
        String orderNo = this.codeService.generateCode(CodeExpressEnum.billnoBackPartner, replaceMap);

        if (Strings.isNullOrEmpty(orderNo))
        {
            return ResponseData.error("退供单号为空");
        }

        for (BackPartnerDetailParam backPartnerDetailParam:backPartnerDetailParams)
        {
            GoodsResult goodsResult=goodsService.getGoodsBySkuCode(backPartnerDetailParam.getSkuCode());
            backPartnerDetailParam.setGoodsModel(goodsResult.getGoodsModel());
            backPartnerDetailParam.setUnitName(goodsResult.getUnitName());
            backPartnerDetailParam.setRealityNum(new BigDecimal(0));
            backPartnerDetailParam.setTaxRate(goodsResult.getTaxRate());
            backPartnerDetailParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
            backPartnerDetailParam.setCreateTime(new Date());
            backPartnerDetailParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
            backPartnerDetailParam.setUpdateTime(new Date());
            backPartnerDetailParam.setYn(1);
            backPartnerDetailParam.setBackOrderNo(orderNo);
        }

        BackPartnerParam backPartnerParam=new BackPartnerParam();
        backPartnerParam.setBackOrderNo(orderNo);
        backPartnerParam.setWarehouseCode(warehouseCode);
        backPartnerParam.setPartnerCode(partnerCode);

        backPartnerParam.setRemark(remark);
        backPartnerParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        backPartnerParam.setCreateTime(new Date());
        backPartnerParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        backPartnerParam.setUpdateTime(new Date());
        backPartnerParam.setOrderState(BackPartnerStatusEnum.NEW.getStatusValue());
        backPartnerParam.setWarehouseName(warehouseName);
        backPartnerParam.setPartnerName(partnerName);
        backPartnerParam.setYn(1);
        backPartnerDetailService.saveBackPartner(backPartnerParam,backPartnerDetailParams);

        return ResponseData.success();
    }


}


