package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrderDetail;
import cn.stylefeng.guns.sys.modular.system.enums.SaleOrderStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderDetailService;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderService;
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
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;


/**
 * 销售订单明细控制器
 *
 * @author zx
 * @Date 2021-03-27 15:10:50
 */
@Controller
@RequestMapping("/saleOrderDetail")
public class SaleOrderDetailController extends BaseController {

    private String PREFIX = "/modular/system/saleOrderDetail";

    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SaleOrderService saleOrderService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/saleOrderDetail.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/saleOrderDetail_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/saleOrderDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SaleOrderDetailParam saleOrderDetailParam) {
        this.saleOrderDetailService.add(saleOrderDetailParam);
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
    public ResponseData editItem(SaleOrderDetailParam saleOrderDetailParam) {
        this.saleOrderDetailService.update(saleOrderDetailParam);
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
    public ResponseData delete(SaleOrderDetailParam saleOrderDetailParam) {
        this.saleOrderDetailService.delete(saleOrderDetailParam);
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
    public ResponseData detail(SaleOrderDetailParam saleOrderDetailParam) {
        SaleOrderDetail detail = this.saleOrderDetailService.getById(saleOrderDetailParam.getId());
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
    public LayuiPageInfo list(SaleOrderDetailParam saleOrderDetailParam) {
        return this.saleOrderDetailService.findPageBySpec(saleOrderDetailParam);
    }

    @ResponseBody
    @RequestMapping("/goodslist")
    public LayuiPageInfo goodsList(GoodsParam goodsParam) {
        return this.goodsService.findPageBySpec(goodsParam);
    }

    @RequestMapping("addGoods")
    public String addGoods(Model model) {

        return PREFIX + "/addGoods.html";
    }


    @ResponseBody
    @RequestMapping("/detailList")
    public LayuiPageInfo detailList(SaleOrderDetailParam saleOrderDetailParam) {
        saleOrderDetailParam.setYn(1);
        return this.saleOrderDetailService.findPageBySpec(saleOrderDetailParam);
    }

    @RequestMapping("/viewDetail")
    public String viewDetail() {
        return PREFIX + "/viewDetail.html";
    }


    @RequestMapping("/editDetail")
    public String editDetail() {
        return PREFIX + "/editDetail.html";
    }

    @RequestMapping("/updateDeliveryNum")
    @ResponseBody
    public ResponseData updateDeliveryNum(SaleOrderDetailParam saleOrderDetailParam) {

        SaleOrderDetail saleOrderDetail=saleOrderDetailService.getById(saleOrderDetailParam.getId());
        if (saleOrderDetailParam.getDeliveryNum().compareTo(saleOrderDetail.getPlanNum())==1)
        {
            return ResponseData.error("发运数量不能大于计划量");
        }

        if (saleOrderDetailParam.getDeliveryNum().compareTo(new BigDecimal(0))<=0)
        {
            return ResponseData.error("发运数量必须大于0");
        }

        SaleOrderResult saleOrderResult=saleOrderService.getByOrderNo(saleOrderDetail.getOrderNo());
        if (saleOrderResult.getOrderState().equals(SaleOrderStatusEnum.DELIVERY_FINISH.getStatusValue()))
        {
            return ResponseData.error("订单已发运，不能修改");
        }
        saleOrderDetailParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        saleOrderDetailParam.setUpdateTime(new Date());
        this.saleOrderDetailService.updateDeliveryNum(saleOrderDetailParam);
        return ResponseData.success();
    }

}


