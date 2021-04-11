package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrder;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderParam;
import cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

}


