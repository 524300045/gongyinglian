package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.enums.CommonStatus;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.entity.Position;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerParam;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.PartnerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 供应商表控制器
 *
 * @author zhangxiang
 * @Date 2021-03-10 19:26:36
 */
@Controller
@RequestMapping("/partner")
public class PartnerController extends BaseController {

    private String PREFIX = "/modular/system/partner";

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private CodeService codeService;

    /**
     * 跳转到主页面
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/partner.html";
    }

    /**
     * 新增页面
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/partner_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/partner_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PartnerParam partnerParam) {

        String code=this.codeService.generateCode(
                CodeExpressEnum.partnerCode, null);
        if (Strings.isNullOrEmpty(code))
        {
            return ResponseData.error("供应商编码为空");
        }

        partnerParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        partnerParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        partnerParam.setCreateTime(new Date());
        partnerParam.setUpdateTime(new Date());
        partnerParam.setStatus(1);
        partnerParam.setPartnerCode(code);
        partnerParam.setYn(1);
        this.partnerService.add(partnerParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(PartnerParam partnerParam) {
        partnerParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.partnerService.update(partnerParam);
        return ResponseData.success();
    }

    /**
     * 修改状态
     *
     * @author stylefeng
     * @Date 2019-06-27
     */
    @ResponseBody
    @RequestMapping("/changeStatus")
    public ResponseData changeStatus(@RequestParam("id") Long id,
                                     @RequestParam("status") Integer status) {

        Partner partner = this.partnerService.getById(id);
        if (partner == null) {
            throw new RequestEmptyException();
        }

            partner.setStatus(status);


        this.partnerService.updateById(partner);

        return new SuccessResponseData();
    }


    /**
     * 删除接口
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PartnerParam partnerParam) {
       // this.partnerService.delete(partnerParam);

        Partner partner = this.partnerService.getById(partnerParam.getId());
        if (partner == null) {
            throw new RequestEmptyException();
        }
        partner.setYn(0);
        partner.setUpdateTime(new Date());
        partner.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        this.partnerService.updateById(partner);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PartnerParam partnerParam) {
        Partner detail = this.partnerService.getById(partnerParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zhangxiang
     * @Date 2021-03-10
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(PartnerParam partnerParam) {
        return this.partnerService.findPageBySpec(partnerParam);
    }

}


