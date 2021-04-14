package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartnerDetail;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerDetailParam;
import cn.stylefeng.guns.sys.modular.system.service.BackPartnerDetailService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 退供明细表控制器
 *
 * @author zx
 * @Date 2021-04-14 17:42:29
 */
@Controller
@RequestMapping("/backPartnerDetail")
public class BackPartnerDetailController extends BaseController {

    private String PREFIX = "/modular/system/backPartnerDetail";

    @Autowired
    private BackPartnerDetailService backPartnerDetailService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/backPartnerDetail.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/backPartnerDetail_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/backPartnerDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BackPartnerDetailParam backPartnerDetailParam) {
        this.backPartnerDetailService.add(backPartnerDetailParam);
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
    public ResponseData editItem(BackPartnerDetailParam backPartnerDetailParam) {
        this.backPartnerDetailService.update(backPartnerDetailParam);
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
    public ResponseData delete(BackPartnerDetailParam backPartnerDetailParam) {
        this.backPartnerDetailService.delete(backPartnerDetailParam);
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
    public ResponseData detail(BackPartnerDetailParam backPartnerDetailParam) {
        BackPartnerDetail detail = this.backPartnerDetailService.getById(backPartnerDetailParam.getId());
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
    public LayuiPageInfo list(BackPartnerDetailParam backPartnerDetailParam) {
        return this.backPartnerDetailService.findPageBySpec(backPartnerDetailParam);
    }

}


