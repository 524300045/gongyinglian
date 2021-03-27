package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.InBound;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundParam;
import cn.stylefeng.guns.sys.modular.system.service.InBoundService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 入库单控制器
 *
 * @author zx
 * @Date 2021-03-25 18:31:51
 */
@Controller
@RequestMapping("/inBound")
public class InBoundController extends BaseController {

    private String PREFIX = "/modular/system/inBound";

    @Autowired
    private InBoundService inBoundService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/inBound.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/inBound_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/inBound_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InBoundParam inBoundParam) {
        this.inBoundService.add(inBoundParam);
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
    public ResponseData editItem(InBoundParam inBoundParam) {
        this.inBoundService.update(inBoundParam);
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
    public ResponseData delete(InBoundParam inBoundParam) {
        this.inBoundService.delete(inBoundParam);
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
    public ResponseData detail(InBoundParam inBoundParam) {
        InBound detail = this.inBoundService.getById(inBoundParam.getId());
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
    public LayuiPageInfo list(InBoundParam inBoundParam) {
        return this.inBoundService.findPageBySpec(inBoundParam);
    }

}


