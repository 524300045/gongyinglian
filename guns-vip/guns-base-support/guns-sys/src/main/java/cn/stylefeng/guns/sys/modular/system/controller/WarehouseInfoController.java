package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.WarehouseInfo;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.WarehouseInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 仓库信息控制器
 *
 * @author zx
 * @Date 2021-03-21 09:42:40
 */
@Controller
@RequestMapping("/warehouseInfo")
public class WarehouseInfoController extends BaseController {

    private String PREFIX = "/modular/system/warehouseInfo";

    @Autowired
    private WarehouseInfoService warehouseInfoService;

    @Autowired
    private CodeService codeService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/warehouseInfo.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/warehouseInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/warehouseInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WarehouseInfoParam warehouseInfoParam) {

        String code=this.codeService.generateCode(
                CodeExpressEnum.wareCode, null);
        if (Strings.isNullOrEmpty(code))
        {
            return ResponseData.error("编码为空");
        }

        warehouseInfoParam.setWarehouseCode(code);
        warehouseInfoParam.setStatus(1);
        warehouseInfoParam.setCreateTime(new Date());
        warehouseInfoParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        warehouseInfoParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        warehouseInfoParam.setUpdateTime(new Date());
        this.warehouseInfoService.add(warehouseInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WarehouseInfoParam warehouseInfoParam) {
        warehouseInfoParam.setStatus(1);
        warehouseInfoParam.setCreateTime(new Date());
        warehouseInfoParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        warehouseInfoParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        warehouseInfoParam.setUpdateTime(new Date());
        this.warehouseInfoService.update(warehouseInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WarehouseInfoParam warehouseInfoParam) {
        this.warehouseInfoService.delete(warehouseInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-21
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WarehouseInfoParam warehouseInfoParam) {
        WarehouseInfo detail = this.warehouseInfoService.getById(warehouseInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-21
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WarehouseInfoParam warehouseInfoParam) {
        return this.warehouseInfoService.findPageBySpec(warehouseInfoParam);
    }

}


