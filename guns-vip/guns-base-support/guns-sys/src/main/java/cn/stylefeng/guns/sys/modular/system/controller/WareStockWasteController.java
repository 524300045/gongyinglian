package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WareStockWaste;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockWasteParam;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import cn.stylefeng.guns.sys.modular.system.service.WareStockWasteService;
import cn.stylefeng.guns.sys.modular.system.service.WarehouseInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import java.util.List;


/**
 * 库存流水表控制器
 *
 * @author zx
 * @Date 2021-03-25 18:33:32
 */
@Controller
@RequestMapping("/wareStockWaste")
public class WareStockWasteController extends BaseController {

    private String PREFIX = "/modular/system/wareStockWaste";

    @Autowired
    private WareStockWasteService wareStockWasteService;

    @Autowired
    private WarehouseInfoService warehouseInfoService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("")
    public String index(Model model) {
        WarehouseInfoParam warehouseInfoParam=new WarehouseInfoParam();
        warehouseInfoParam.setYn(1);
        List<WarehouseInfoResult> warehouseInfoList=warehouseInfoService.findListBySpec(warehouseInfoParam);
        model.addAttribute("warelist", warehouseInfoList);

        return PREFIX + "/wareStockWaste.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wareStockWaste_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wareStockWaste_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WareStockWasteParam wareStockWasteParam) {
        this.wareStockWasteService.add(wareStockWasteParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WareStockWasteParam wareStockWasteParam) {
        this.wareStockWasteService.update(wareStockWasteParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WareStockWasteParam wareStockWasteParam) {
        this.wareStockWasteService.delete(wareStockWasteParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WareStockWasteParam wareStockWasteParam) {
        WareStockWaste detail = this.wareStockWasteService.getById(wareStockWasteParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-25
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WareStockWasteParam wareStockWasteParam) {
        return this.wareStockWasteService.findPageBySpec(wareStockWasteParam);
    }

}


