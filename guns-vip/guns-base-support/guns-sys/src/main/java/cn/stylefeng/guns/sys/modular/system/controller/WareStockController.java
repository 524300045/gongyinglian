package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WareStock;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockParam;
import cn.stylefeng.guns.sys.modular.system.service.WareStockService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 库存控制器
 *
 * @author zx
 * @Date 2021-03-25 18:33:01
 */
@Controller
@RequestMapping("/wareStock")
public class WareStockController extends BaseController {

    private String PREFIX = "/modular/system/wareStock";

    @Autowired
    private WareStockService wareStockService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wareStock.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wareStock_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wareStock_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WareStockParam wareStockParam) {
        this.wareStockService.add(wareStockParam);
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
    public ResponseData editItem(WareStockParam wareStockParam) {
        this.wareStockService.update(wareStockParam);
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
    public ResponseData delete(WareStockParam wareStockParam) {
        this.wareStockService.delete(wareStockParam);
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
    public ResponseData detail(WareStockParam wareStockParam) {
        WareStock detail = this.wareStockService.getById(wareStockParam.getId());
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
    public LayuiPageInfo list(WareStockParam wareStockParam) {
        return this.wareStockService.findPageBySpec(wareStockParam);
    }


    @RequestMapping("wareStockInventory")
    public String wareStockInventory() {
        return PREFIX + "/wareStockInventory.html";
    }


    /**
     * 更新库存
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/updateStock")
    @ResponseBody
    public ResponseData updateStock(WareStockParam wareStockParam) {
        if (wareStockParam.getRealStock().compareTo(new BigDecimal(0))<=0)
        {
            return ResponseData.error("库存数量必须大于0");
        }
        wareStockParam.setForOrderStock(wareStockParam.getRealStock());
        wareStockParam.setUpdateTime(new Date());
        wareStockParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.wareStockService.update(wareStockParam);
        return ResponseData.success();
    }
}


