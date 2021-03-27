package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrder;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderService;
import cn.stylefeng.guns.sys.modular.system.service.WarehouseInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


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
        this.saleOrderService.delete(saleOrderParam);
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

}


