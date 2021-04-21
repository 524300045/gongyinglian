package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Workshop;
import cn.stylefeng.guns.sys.modular.system.model.params.WorkshopParam;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.WorkshopService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 车间控制器
 *
 * @author zx
 * @Date 2021-04-21 16:15:47
 */
@Controller
@RequestMapping("/workshop")
public class WorkshopController extends BaseController {

    private String PREFIX = "/modular/system/workshop";

    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private CodeService codeService;


    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/workshop.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/workshop_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/workshop_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WorkshopParam workshopParam) {

        String code=this.codeService.generateCode(
                CodeExpressEnum.shopCode, null);
        if (Strings.isNullOrEmpty(code))
        {
            return ResponseData.error("编码为空");
        }

        workshopParam.setShopCode(code);
        workshopParam.setYn(1);
        workshopParam.setStatus(1);
        workshopParam.setCreateTime(new Date());
        workshopParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        workshopParam.setUpdateTime(new Date());
        workshopParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.workshopService.add(workshopParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WorkshopParam workshopParam) {
        workshopParam.setCreateTime(new Date());
        workshopParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        workshopParam.setUpdateTime(new Date());
        workshopParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.workshopService.update(workshopParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WorkshopParam workshopParam) {

        workshopParam.setCreateTime(new Date());
        workshopParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        workshopParam.setUpdateTime(new Date());
        workshopParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        workshopParam.setYn(0);
        this.workshopService.update(workshopParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-04-21
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WorkshopParam workshopParam) {
        Workshop detail = this.workshopService.getById(workshopParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-04-21
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WorkshopParam workshopParam) {
        return this.workshopService.findPageBySpec(workshopParam);
    }

}


