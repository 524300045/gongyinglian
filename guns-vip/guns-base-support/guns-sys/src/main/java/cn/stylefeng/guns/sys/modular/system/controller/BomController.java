package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Bom;
import cn.stylefeng.guns.sys.modular.system.model.params.BomParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BomResult;
import cn.stylefeng.guns.sys.modular.system.service.BomService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * bom表控制器
 *
 * @author zx
 * @Date 2021-04-16 19:15:30
 */
@Controller
@RequestMapping("/bom")
public class BomController extends BaseController {

    private String PREFIX = "/modular/system/bom";

    @Autowired
    private BomService bomService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/bom.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/bom_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/bom_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BomParam bomParam) {

        if(bomParam.getSkuCode().equals(bomParam.getChildSkuCode()))
        {
            return ResponseData.error("成品SKU和组装SKU不能一样");
        }
        List<BomResult> bomResultList=bomService.findListBySpec(bomParam);
        if (bomResultList!=null&&bomResultList.size()>0)
        {
            return ResponseData.error("关系已经存在");
        }

        bomParam.setCreateTime(new Date());
        bomParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        bomParam.setUpdateTime(new Date());
        bomParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        bomParam.setYn(1);
        this.bomService.add(bomParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(BomParam bomParam) {
        bomParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        bomParam.setUpdateTime(new Date());

        this.bomService.update(bomParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(BomParam bomParam) {
        bomParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        bomParam.setUpdateTime(new Date());
        bomParam.setYn(0);
        this.bomService.update(bomParam);
        return ResponseData.success();
    }



    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-04-16
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(BomParam bomParam) {
        Bom detail = this.bomService.getById(bomParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-04-16
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(BomParam bomParam) {
        return this.bomService.findPageBySpec(bomParam);
    }



    @RequestMapping("addGoods")
    public String addGoods(Model model) {

        return PREFIX + "/addGoods.html";
    }

    @ResponseBody
    @RequestMapping("/childBomList")
    public LayuiPageInfo childBomList(BomParam bomParam) {
        return this.bomService.findChildPageBySpec(bomParam);
    }
}


