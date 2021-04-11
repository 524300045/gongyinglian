package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrderDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderDetailService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 客退明细控制器
 *
 * @author zx
 * @Date 2021-04-11 19:27:27
 */
@Controller
@RequestMapping("/customerBackOrderDetail")
public class CustomerBackOrderDetailController extends BaseController {

    private String PREFIX = "/modular/system/customerBackOrderDetail";

    @Autowired
    private CustomerBackOrderDetailService customerBackOrderDetailService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/customerBackOrderDetail.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/customerBackOrderDetail_add.html";
    }

    @RequestMapping("/saleOrderDetail")
    public String saleOrderDetail() {
        return PREFIX + "/saleOrderDetail.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/customerBackOrderDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CustomerBackOrderDetailParam customerBackOrderDetailParam) {
        this.customerBackOrderDetailService.add(customerBackOrderDetailParam);
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
    public ResponseData editItem(CustomerBackOrderDetailParam customerBackOrderDetailParam) {
        this.customerBackOrderDetailService.update(customerBackOrderDetailParam);
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
    public ResponseData delete(CustomerBackOrderDetailParam customerBackOrderDetailParam) {
        this.customerBackOrderDetailService.delete(customerBackOrderDetailParam);
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
    public ResponseData detail(CustomerBackOrderDetailParam customerBackOrderDetailParam) {
        CustomerBackOrderDetail detail = this.customerBackOrderDetailService.getById(customerBackOrderDetailParam.getId());
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
    public LayuiPageInfo list(CustomerBackOrderDetailParam customerBackOrderDetailParam) {
        return this.customerBackOrderDetailService.findPageBySpec(customerBackOrderDetailParam);
    }

}


