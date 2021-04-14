package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartner;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerParam;
import cn.stylefeng.guns.sys.modular.system.service.BackPartnerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("")
    public String index() {
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
     * 删除接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(BackPartnerParam backPartnerParam) {
        this.backPartnerService.delete(backPartnerParam);
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

}


