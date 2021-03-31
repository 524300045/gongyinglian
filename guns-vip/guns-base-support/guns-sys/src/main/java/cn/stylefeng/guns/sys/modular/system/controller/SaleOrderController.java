package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrder;
import cn.stylefeng.guns.sys.modular.system.enums.SaleOrderStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderService;
import cn.stylefeng.guns.sys.modular.system.service.WarehouseInfoService;
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
 * 销售订单表控制器
 *
 * @author zx
 * @Date 2021-03-27 15:10:22
 */
@Controller
@RequestMapping("/saleOrder")
public class SaleOrderController extends BaseController {

    private String PREFIX = "/modular/system/saleOrder";

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private WarehouseInfoService warehouseInfoService;

    @Autowired
    private CodeService codeService;


    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/saleOrder.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/add")
    public String add(Model model) {

        WarehouseInfoParam warehouseInfoParam=new WarehouseInfoParam();
        warehouseInfoParam.setYn(1);
        List<WarehouseInfoResult> warehouseInfoList=warehouseInfoService.findListBySpec(warehouseInfoParam);

        model.addAttribute("warelist", warehouseInfoList);
        return PREFIX + "/saleOrder_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/saleOrder_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SaleOrderParam saleOrderParam) {
        this.saleOrderService.add(saleOrderParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(SaleOrderParam saleOrderParam) {
        this.saleOrderService.update(saleOrderParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(SaleOrderParam saleOrderParam) {

     //   saleOrderParam.setYn(0);
        saleOrderParam.setCancelTime(new Date());
        saleOrderParam.setCancelUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderParam.setOrderState(SaleOrderStatusEnum.CANCEL.getStatusValue());
        saleOrderParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderParam.setUpdateTime(new Date());
        this.saleOrderService.update(saleOrderParam);


        return ResponseData.success();
    }

    @RequestMapping("/audit")
    @ResponseBody
    public ResponseData audit(SaleOrderParam saleOrderParam) {

        saleOrderParam.setAuditTime(new Date());
        saleOrderParam.setAuditUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderParam.setOrderState(SaleOrderStatusEnum.AUDIT.getStatusValue());
        saleOrderParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderParam.setUpdateTime(new Date());
        this.saleOrderService.update(saleOrderParam);


        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(SaleOrderParam saleOrderParam) {
        SaleOrder detail = this.saleOrderService.getById(saleOrderParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(SaleOrderParam saleOrderParam) {
        return this.saleOrderService.findPageBySpec(saleOrderParam);
    }

    @RequestMapping("/addOrderDetail")
    @ResponseBody
    public ResponseData addOrderDetail(
                                    @RequestParam("receiverName") String receiverName,
                                    @RequestParam("receiverPhone") String receiverPhone,
                                    @RequestParam("address") String address,
                                      @RequestParam("warehouseCode") String warehouseCode,
                                      @RequestParam("warehouseName") String warehouseName,
                                      @RequestParam("deliveryDate") Date deliveryDate,
                                    @RequestParam("detailStr") String detailStr,
                                      @RequestParam("remark") String remark




    ) {

        List<SaleOrderDetailParam>  saleOrderDetailParamList= JSONObject.parseArray(detailStr,SaleOrderDetailParam.class);

        if (saleOrderDetailParamList==null||saleOrderDetailParamList.size()==0)
        {
            return ResponseData.error("请选择商品!");
        }
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("warehouseId", warehouseCode);
        String orderNo = this.codeService.generateCode(CodeExpressEnum.billnoSaleOrder, replaceMap);

        if (Strings.isNullOrEmpty(orderNo))
        {
            return ResponseData.error("单号为空");
        }

      //  BigDecimal totalAmount =saleOrderDetailParamList.stream().map(SaleOrderDetailParam::getTaxPrice).reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal totalAmount = saleOrderDetailParamList.stream().reduce(BigDecimal.ZERO, (x, y) -> {
return x.add(y.getPlanNum().multiply(y.getTaxPrice()));
}, BigDecimal::add);


        SaleOrderParam saleOrderParam=new SaleOrderParam();
        saleOrderParam.setOrderNo(orderNo);
        saleOrderParam.setWarehouseCode(warehouseCode);
        saleOrderParam.setWarehouseName(warehouseName);
        saleOrderParam.setOrderState(SaleOrderStatusEnum.NEW.getStatusValue());
        saleOrderParam.setTotalAmount(totalAmount);
        saleOrderParam.setReceiverName(receiverName);
        saleOrderParam.setReceiverPhone(receiverPhone);
        saleOrderParam.setAddress(address);
        saleOrderParam.setDeliveryDate(deliveryDate);
        saleOrderParam.setRemark(remark);
        saleOrderParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderParam.setCreateTime(new Date());
        saleOrderParam.setUpdateTime(new Date());
        saleOrderParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderParam.setYn(1);
        for (SaleOrderDetailParam saleOrderDetailParam:saleOrderDetailParamList)
        {
            saleOrderDetailParam.setOrderNo(orderNo);
            saleOrderDetailParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
            saleOrderDetailParam.setCreateTime(new Date());
            saleOrderDetailParam.setUpdateTime(new Date());
            saleOrderDetailParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
            saleOrderDetailParam.setYn(1);
        }

        saleOrderService.addOrder(saleOrderParam,saleOrderDetailParamList);
        return  ResponseData.success();
    }


    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-27
     */
    @ResponseBody
    @RequestMapping("/outList")
    public LayuiPageInfo outList(SaleOrderParam saleOrderParam) {
        return this.saleOrderService.findPageBySpec(saleOrderParam);
    }

    @RequestMapping("saleOrderOutList")
    public String saleOrderOutList() {
        return PREFIX + "/saleOrderOutList.html";
    }


    /**
     * 订单出库
     * @param orderNo
     * @return
     */
    @RequestMapping("/outBound")
    @ResponseBody
    public ResponseData outBound( @RequestParam("orderNo") String orderNo) {

        SaleOrderResult saleOrderResult=saleOrderService.getByOrderNo(orderNo);
        if (saleOrderResult==null)
        {
            return ResponseData.error("查询不到订单详细信息");
        }
        if (saleOrderResult.getOrderState()!=SaleOrderStatusEnum.AUDIT.getStatusValue())
        {
            return ResponseData.error("订单状态存在问题!");
        }


        return ResponseData.success();
    }

}


