package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.InBound;
import cn.stylefeng.guns.sys.modular.system.entity.InBoundDetail;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.enums.InboundStatusEnum;
import cn.stylefeng.guns.sys.modular.system.enums.LocationStockDirectionEnum;
import cn.stylefeng.guns.sys.modular.system.enums.OperationTypeEnum;
import cn.stylefeng.guns.sys.modular.system.mapper.InBoundDetailMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.PmsOrderPurchaseDetailMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.PmsOrderPurchaseMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundParam;
import cn.stylefeng.guns.sys.modular.system.model.params.LocationStockParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundDetailResult;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundResult;
import cn.stylefeng.guns.sys.modular.system.model.result.PmsOrderPurchaseResult;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import  cn.stylefeng.guns.sys.modular.system.service.InBoundDetailService;
import cn.stylefeng.guns.sys.modular.system.service.InBoundService;
import cn.stylefeng.guns.sys.modular.system.service.WareStockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 入库单明细表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Service
public class InBoundDetailServiceImpl extends ServiceImpl<InBoundDetailMapper, InBoundDetail> implements InBoundDetailService {

    @Autowired
    private PmsOrderPurchaseDetailMapper pmsOrderPurchaseDetailMapper;

    @Autowired
    private PmsOrderPurchaseMapper pmsOrderPurchaseMapper;

    @Autowired
    private CodeService codeService;

    @Autowired
    private WareStockService wareStockService;

    @Autowired
    private InBoundService inBoundService;

    @Autowired
    private InBoundDetailService inBoundDetailService;

    @Override
    public void add(InBoundDetailParam param){
        InBoundDetail entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InBoundDetailParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InBoundDetailParam param){
        InBoundDetail oldEntity = getOldEntity(param);
        InBoundDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InBoundDetailResult findBySpec(InBoundDetailParam param){
        return null;
    }

    @Override
    public List<InBoundDetailResult> findListBySpec(InBoundDetailParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InBoundDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(InBoundDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InBoundDetail getOldEntity(InBoundDetailParam param) {
        return this.getById(getKey(param));
    }

    private InBoundDetail getEntity(InBoundDetailParam param) {
        InBoundDetail entity = new InBoundDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public String updateInboundNum(PmsOrderPurchaseDetail pmsOrderPurchaseDetail) {

        PmsOrderPurchaseResult pmsOrderPurchaseResult=pmsOrderPurchaseMapper.selectByOrderNo(pmsOrderPurchaseDetail.getOrderNo());


        String inboundTaskCode="";
        InBoundParam inBoundParam=null;
        InBoundParam inBoundQueryParam=new InBoundParam();
        inBoundQueryParam.setOrderNo(pmsOrderPurchaseResult.getOrderNo());
        List<InBoundResult> inBoundList=inBoundService.findListBySpec(inBoundQueryParam);
        if (inBoundList!=null&&inBoundList.size()>0)
        {
            for (InBoundResult inBoundItem:inBoundList
            ) {
                if (inBoundItem.getOrderState()== InboundStatusEnum.Initial.getStatusValue()
                        ||inBoundItem.getOrderState()==InboundStatusEnum.Receiving.getStatusValue())
                {
                    inboundTaskCode=inBoundItem.getInboundTaskCode();
                    break;
                }
            }
        }
        if (StringUtils.isEmpty(inboundTaskCode))
        {
            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("warehouseId", pmsOrderPurchaseResult.getWarehouseCode());
            //生产单号
            inboundTaskCode = this.codeService.generateCode(CodeExpressEnum.billnoInbound, replaceMap);

            if (Strings.isNullOrEmpty(inboundTaskCode))
            {
                return "入库单号为空";
            }

            inBoundParam=new InBoundParam();
            BeanUtils.copyProperties(pmsOrderPurchaseResult,inBoundParam);
            inBoundParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
            inBoundParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
            inBoundParam.setCreateTime(new Date());
            inBoundParam.setUpdateTime(new Date());
            inBoundParam.setYn(1);
            inBoundParam.setInboundTaskCode(inboundTaskCode);

        }



        InBoundDetailParam inBoundDetail=new InBoundDetailParam();
        BeanUtils.copyProperties(pmsOrderPurchaseDetail,inBoundDetail);
        inBoundDetail.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        inBoundDetail.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        inBoundDetail.setCreateTime(new Date());
        inBoundDetail.setUpdateTime(new Date());
        inBoundDetail.setYn(1);
        inBoundDetail.setInboundTaskCode(inboundTaskCode);



        //增加货位库存
        LocationStockParam locationStockParam=new LocationStockParam();
        locationStockParam.setRelOrderNo(pmsOrderPurchaseResult.getOrderNo());
        locationStockParam.setLocationCode("");
        locationStockParam.setSkuCode(pmsOrderPurchaseDetail.getSkuCode());
        locationStockParam.setWarehouseCode(pmsOrderPurchaseResult.getWarehouseCode());
        locationStockParam.setWarehouseName(pmsOrderPurchaseResult.getWarehouseName());
        locationStockParam.setQuantity(pmsOrderPurchaseDetail.getRealityNum());

        String user=LoginContextHolder.getContext().getUser().getUsername();

        int o=pmsOrderPurchaseDetailMapper.updateRealityNum(pmsOrderPurchaseDetail);

        if (inBoundParam!=null)
        {
            inBoundService.add(inBoundParam);
        }
        inBoundDetailService.add(inBoundDetail);
        wareStockService.updateRealAndForOrderStock(locationStockParam, OperationTypeEnum.TYPE_PURCHASE_INSTORE, LocationStockDirectionEnum.INCREASE,user);

        return "";
    }

    @Override
    public LayuiPageInfo selectPage(InBoundDetailParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.selectPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }
}
