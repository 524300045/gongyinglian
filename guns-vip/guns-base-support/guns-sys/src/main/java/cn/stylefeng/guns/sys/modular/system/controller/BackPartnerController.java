package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartner;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.enums.BackPartnerStatusEnum;
import cn.stylefeng.guns.sys.modular.system.enums.LocationStockDirectionEnum;
import cn.stylefeng.guns.sys.modular.system.enums.OperationTypeEnum;
import cn.stylefeng.guns.sys.modular.system.enums.SaleOrderStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.*;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerDetailResult;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerResult;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderDetailResult;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import cn.stylefeng.guns.sys.modular.system.service.BackPartnerDetailService;
import cn.stylefeng.guns.sys.modular.system.service.BackPartnerService;
import cn.stylefeng.guns.sys.modular.system.service.PartnerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 退供单表控制器
 *
 * @author zx
 * @Date 2021-04-14 17:42:29
 */
@Controller
@RequestMapping("/backPartner")
public class BackPartnerController extends BaseController {

    private String PREFIX = "/modular/system/backPartner";

    @Autowired
    private BackPartnerService backPartnerService;

    @Autowired
    private BackPartnerDetailService backPartnerDetailService;

    @Autowired
    private PartnerService partnerService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("")
    public String index(Model model)
    {
        List<Partner> partnerList=partnerService.list();

        model.addAttribute("partners", partnerList);
        return PREFIX + "/backPartner.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/backPartner_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/backPartner_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BackPartnerParam backPartnerParam) {
        this.backPartnerService.add(backPartnerParam);
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
    public ResponseData editItem(BackPartnerParam backPartnerParam) {
        this.backPartnerService.update(backPartnerParam);
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
    public ResponseData detail(BackPartnerParam backPartnerParam) {
        BackPartner detail = this.backPartnerService.getById(backPartnerParam.getId());
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
    public LayuiPageInfo list(BackPartnerParam backPartnerParam) {
        return this.backPartnerService.findPageBySpec(backPartnerParam);
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(BackPartnerParam backPartnerParam) {

        //   saleOrderParam.setYn(0);
        backPartnerParam.setCancelTime(new Date());
        backPartnerParam.setCancelUser(LoginContextHolder.getContext().getUser().getUsername());
        backPartnerParam.setOrderState(BackPartnerStatusEnum.CANCEL.getStatusValue());
        backPartnerParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        backPartnerParam.setUpdateTime(new Date());
        this.backPartnerService.update(backPartnerParam);


        return ResponseData.success();
    }

    @RequestMapping("/audit")
    @ResponseBody
    public ResponseData audit(BackPartnerParam backPartnerParam) {

        backPartnerParam.setAuditTime(new Date());
        backPartnerParam.setAuditUser(LoginContextHolder.getContext().getUser().getUsername());
        backPartnerParam.setOrderState(BackPartnerStatusEnum.AUDIT.getStatusValue());
        backPartnerParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        backPartnerParam.setUpdateTime(new Date());
        this.backPartnerService.update(backPartnerParam);


        return ResponseData.success();
    }


    @RequestMapping("backPartnerOut")
    public String backPartnerOut() {
        return PREFIX + "/backPartnerOut.html";
    }



    /**
     * 订单出库
     * @param orderNo
     * @return
     */
    @RequestMapping("/outBound")
    @ResponseBody
    public ResponseData outBound( @RequestParam("orderNo") String orderNo) {

        BackPartnerParam backPartnerParam=new BackPartnerParam();
        backPartnerParam.setBackOrderNo(orderNo);
        BackPartnerResult backPartnerResult=backPartnerService.findBySpec(backPartnerParam);
        if (backPartnerResult==null)
        {
            return ResponseData.error("查询不到退供单详细信息");
        }
        if (backPartnerResult.getOrderState()!=BackPartnerStatusEnum.AUDIT.getStatusValue())
        {
            return ResponseData.error("退供订单状态存在问题!");
        }
        BackPartnerDetailParam backPartnerDetailParam=new BackPartnerDetailParam();
        backPartnerDetailParam.setBackOrderNo(orderNo);
        backPartnerDetailParam.setYn(1);
        List<BackPartnerDetailResult> backPartnerDetailResultList=backPartnerDetailService.findListBySpec(backPartnerDetailParam);
        if (backPartnerDetailResultList==null|| backPartnerDetailResultList.size()==0)
        {
            return ResponseData.error("查询不到商品明细!");
        }
        List<BackPartnerDetailResult> curList=backPartnerDetailResultList.stream().filter(p->p.getRealityNum().compareTo(new BigDecimal(0))==1).collect(Collectors.toList());
        if (curList==null||curList.size()==0)
        {
            return ResponseData.error("出库数量不能为0!");
        }
        backPartnerParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());

        List<LocationStockParam> locationStockParams=new ArrayList<>();
        for (BackPartnerDetailResult backPartnerDetailResult:backPartnerDetailResultList)
        {
            if (backPartnerDetailResult.getRealityNum().compareTo(new BigDecimal(0))<=0)
            {
                continue;
            }
            LocationStockParam locationStockParam=new LocationStockParam();
            locationStockParam.setRelOrderNo(backPartnerResult.getBackOrderNo());
            locationStockParam.setLocationCode("");
            locationStockParam.setSkuCode(backPartnerDetailResult.getSkuCode());
            locationStockParam.setWarehouseCode(backPartnerResult.getWarehouseCode());
            locationStockParam.setWarehouseName(backPartnerResult.getWarehouseName());
            locationStockParam.setQuantity(backPartnerDetailResult.getRealityNum());
            locationStockParams.add(locationStockParam);

        }

        String user=LoginContextHolder.getContext().getUser().getUsername();
        backPartnerParam.setOutTime(new Date());
        backPartnerParam.setOutUser(user);
        backPartnerParam.setUpdateUser(user);
        backPartnerParam.setBackOrderNo(backPartnerResult.getBackOrderNo());
        backPartnerParam.setOrderState(BackPartnerStatusEnum.FINISH.getStatusValue());

        backPartnerDetailService.saveOutBound(backPartnerParam,locationStockParams,user);


        return ResponseData.success();
    }


}


