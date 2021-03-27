package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.InBoundDetail;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.enums.PmsPurchaseStatusEnum;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.InBoundDetailService;
import cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseDetailService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import oshi.jna.platform.mac.SystemB;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 入库单明细表控制器
 *
 * @author zx
 * @Date 2021-03-25 18:32:32
 */
@Controller
@RequestMapping("/inBoundDetail")
public class InBoundDetailController extends BaseController {

    private String PREFIX = "/modular/system/inBoundDetail";

    @Autowired
    private InBoundDetailService inBoundDetailService;

    @Autowired
    private PmsOrderPurchaseDetailService pmsOrderPurchaseDetailService;

    @Autowired
    private CodeService codeService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/inBoundDetail.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/inBoundDetail_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/inBoundDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-25
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InBoundDetailParam inBoundDetailParam) {
        this.inBoundDetailService.add(inBoundDetailParam);
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
    public ResponseData editItem(InBoundDetailParam inBoundDetailParam) {
        this.inBoundDetailService.update(inBoundDetailParam);
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
    public ResponseData delete(InBoundDetailParam inBoundDetailParam) {
        this.inBoundDetailService.delete(inBoundDetailParam);
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
    public ResponseData detail(InBoundDetailParam inBoundDetailParam) {
        InBoundDetail detail = this.inBoundDetailService.getById(inBoundDetailParam.getId());
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
    public LayuiPageInfo list(InBoundDetailParam inBoundDetailParam) {
        //return this.inBoundDetailService.findPageBySpec(inBoundDetailParam);
        return this.inBoundDetailService.selectPage(inBoundDetailParam);
    }


    @RequestMapping("/inbound")
    @ResponseBody
    public ResponseData inbound(@RequestParam("id") long id,
                                      @RequestParam("num") BigDecimal num

    ) {

         PmsOrderPurchaseDetail pmsOrderPurchaseDetail=pmsOrderPurchaseDetailService.getById(id);
         if (pmsOrderPurchaseDetail==null)
         {
             return ResponseData.error("查询不到明细");
         }
         if (num.compareTo(new BigDecimal(0))<=0)
         {
             return ResponseData.error("入库数量不能小于0");
         }
         BigDecimal curNum=pmsOrderPurchaseDetail.getRealityNum().add(num);
         if (curNum.compareTo(pmsOrderPurchaseDetail.getPlanNum())==1)
         {
             return ResponseData.error("入库数量不能大于计划数量");
         }

        pmsOrderPurchaseDetail.setRealityNum(num);
        pmsOrderPurchaseDetail.setUpdateTime(new Date());
        pmsOrderPurchaseDetail.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        inBoundDetailService.updateInboundNum(pmsOrderPurchaseDetail);

        return ResponseData.success();
    }

}


