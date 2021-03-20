package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.db.entity.DatabaseInfo;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.entity.PartnerGoods;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerGoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerGoodsResult;
import cn.stylefeng.guns.sys.modular.system.service.PartnerGoodsService;
import cn.stylefeng.guns.sys.modular.system.service.PartnerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 供应商商品表控制器
 *
 * @author zx
 * @Date 2021-03-18 17:43:35
 */
@Controller
@RequestMapping("/partnerGoods")
public class PartnerGoodsController extends BaseController {

    private String PREFIX = "/modular/system/partnerGoods";

    @Autowired
    private PartnerGoodsService partnerGoodsService;

    @Autowired
    private PartnerService partnerService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/partnerGoods.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/add")
    public String add(Model model) {

        List<Partner> partnerList=partnerService.list();

        model.addAttribute("partners", partnerList);
        return PREFIX + "/partnerGoods_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/partnerGoods_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(@RequestBody PartnerGoodsParam partnerGoodsParam) {

        if (Strings.isNullOrEmpty(partnerGoodsParam.getPartnerCode()))
        {
            return ResponseData.error("请选择供应商");
        }

        List<PartnerGoodsParam> partnerGoodsParams=new ArrayList<>();

        String[] skuCodes=partnerGoodsParam.getSkuCode().split(",");
        for (String item:skuCodes)
        {
            PartnerGoodsParam partnerParam=new PartnerGoodsParam();
            partnerParam.setPartnerCode(partnerGoodsParam.getPartnerCode());
            partnerParam.setSkuCode(item);
            partnerParam.setYn(1);
            PartnerGoodsResult partnerGoodsResult=partnerGoodsService.findBySpec(partnerParam);
            if (partnerGoodsResult!=null)
                 continue;

            PartnerGoodsParam addParam=new PartnerGoodsParam();
            addParam.setPartnerCode(partnerGoodsParam.getPartnerCode());
            addParam.setSkuCode(item);
            addParam.setCreateTime(new Date());
            addParam.setUpdateTime(new Date());
            addParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
            addParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
            partnerGoodsParams.add(addParam);
        }

        if (partnerGoodsParams.size()>0)
        {
            this.partnerGoodsService.addBatch(partnerGoodsParams);
        }

        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(PartnerGoodsParam partnerGoodsParam) {
        this.partnerGoodsService.update(partnerGoodsParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PartnerGoodsParam partnerGoodsParam) {
        partnerGoodsParam.setYn(0);
        partnerGoodsParam.setUpdateTime(new Date());
        partnerGoodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.partnerGoodsService.update(partnerGoodsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PartnerGoodsParam partnerGoodsParam) {
        PartnerGoods detail = this.partnerGoodsService.getById(partnerGoodsParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(PartnerGoodsParam partnerGoodsParam) {
        return this.partnerGoodsService.findPageBySpec(partnerGoodsParam);
    }

}


