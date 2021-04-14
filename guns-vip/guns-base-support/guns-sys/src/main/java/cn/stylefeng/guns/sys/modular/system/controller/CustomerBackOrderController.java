package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrder;
import cn.stylefeng.guns.sys.modular.system.enums.CustomerBackOrderStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderDetailService;
import cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderService;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 客退单控制器
 *
 * @author zx
 * @Date 2021-04-11 19:27:27
 */
@Controller
@RequestMapping("/customerBackOrder")
public class CustomerBackOrderController extends BaseController {

    private String PREFIX = "/modular/system/customerBackOrder";

    @Autowired
    private CustomerBackOrderService customerBackOrderService;

    @Autowired
    private CustomerBackOrderDetailService customerBackOrderDetailService;

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private CodeService codeService;


    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/customerBackOrder.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/customerBackOrder_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/customerBackOrder_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CustomerBackOrderParam customerBackOrderParam) {
        this.customerBackOrderService.add(customerBackOrderParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(CustomerBackOrderParam customerBackOrderParam) {
        this.customerBackOrderService.update(customerBackOrderParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CustomerBackOrderParam customerBackOrderParam) {
        this.customerBackOrderService.delete(customerBackOrderParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(CustomerBackOrderParam customerBackOrderParam) {
        CustomerBackOrder detail = this.customerBackOrderService.getById(customerBackOrderParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-04-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(CustomerBackOrderParam customerBackOrderParam) {
        return this.customerBackOrderService.findPageBySpec(customerBackOrderParam);
    }

    @RequestMapping("/addItemDetail")
    @ResponseBody
    public ResponseData addItemDetail(
                                      @RequestParam("orderNo") String orderNo,
                                      @RequestParam("detailStr") String detailStr
    )
    {
        List<CustomerBackOrderDetailParam> customerBackOrderDetailParamList= JSONObject.parseArray(detailStr,CustomerBackOrderDetailParam.class);
        if (customerBackOrderDetailParamList==null||customerBackOrderDetailParamList.size()==0)
        {
            return ResponseData.error("没有客退明细");
        }
        SaleOrderResult saleOrderResult=saleOrderService.getByOrderNo(orderNo);
        if (saleOrderResult==null)
        {
            return ResponseData.error("查询不到销售单信息");
        }
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("warehouseId", saleOrderResult.getWarehouseCode());
        String backOrderNo = this.codeService.generateCode(CodeExpressEnum.billnoCustomerBack, replaceMap);
        if (Strings.isNullOrEmpty(backOrderNo))
        {
            return ResponseData.error("客退单号为空");
        }

        CustomerBackOrderParam customerBackOrderParam=new CustomerBackOrderParam();
        customerBackOrderParam.setCustomerBackOrderNo(backOrderNo);
        customerBackOrderParam.setWarehouseCode(saleOrderResult.getWarehouseCode());
        customerBackOrderParam.setWarehouseName(saleOrderResult.getWarehouseName());
        customerBackOrderParam.setOrderNo(orderNo);
        customerBackOrderParam.setOrderState(CustomerBackOrderStatusEnum.NEW.getStatusValue());
        customerBackOrderParam.setReceiverName(saleOrderResult.getReceiverName());
        customerBackOrderParam.setReceiverPhone(saleOrderResult.getReceiverPhone());
        customerBackOrderParam.setAddress(saleOrderResult.getAddress());
        customerBackOrderParam.setCreateTime(new Date());
        customerBackOrderParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        customerBackOrderParam.setUpdateTime(new Date());
        customerBackOrderParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        customerBackOrderParam.setYn(1);

        for (CustomerBackOrderDetailParam customerBackOrderDetailParam:customerBackOrderDetailParamList)
        {
            customerBackOrderDetailParam.setCustomerBackOrderNo(backOrderNo);
            customerBackOrderDetailParam.setCreateTime(new Date());
            customerBackOrderDetailParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
            customerBackOrderDetailParam.setUpdateTime(new Date());
            customerBackOrderDetailParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
            customerBackOrderDetailParam.setYn(1);
        }

        customerBackOrderDetailService.createCustomerBackOrder(customerBackOrderParam,customerBackOrderDetailParamList);
        return ResponseData.success();
    }

}


