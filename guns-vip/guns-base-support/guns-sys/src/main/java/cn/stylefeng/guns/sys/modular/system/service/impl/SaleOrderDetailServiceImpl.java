package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrderDetail;
import cn.stylefeng.guns.sys.modular.system.enums.LocationStockDirectionEnum;
import cn.stylefeng.guns.sys.modular.system.enums.OperationTypeEnum;
import cn.stylefeng.guns.sys.modular.system.enums.SaleOrderStatusEnum;
import cn.stylefeng.guns.sys.modular.system.mapper.SaleOrderDetailMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.LocationStockParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderDetailResult;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import  cn.stylefeng.guns.sys.modular.system.service.SaleOrderDetailService;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderService;
import cn.stylefeng.guns.sys.modular.system.service.WareStockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 销售订单明细 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
@Service
public class SaleOrderDetailServiceImpl extends ServiceImpl<SaleOrderDetailMapper, SaleOrderDetail> implements SaleOrderDetailService {

    @Autowired
    private WareStockService wareStockService;

    @Autowired
    private SaleOrderService saleOrderService;

    @Override
    public void add(SaleOrderDetailParam param){
        SaleOrderDetail entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SaleOrderDetailParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SaleOrderDetailParam param){
        SaleOrderDetail oldEntity = getOldEntity(param);
        SaleOrderDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SaleOrderDetailResult findBySpec(SaleOrderDetailParam param){
        return null;
    }

    @Override
    public List<SaleOrderDetailResult> findListBySpec(SaleOrderDetailParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(SaleOrderDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SaleOrderDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SaleOrderDetail getOldEntity(SaleOrderDetailParam param) {
        return this.getById(getKey(param));
    }

    private SaleOrderDetail getEntity(SaleOrderDetailParam param) {
        SaleOrderDetail entity = new SaleOrderDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public boolean outBound(SaleOrderResult saleOrderResult) {

        String user=saleOrderResult.getUpdateUser();

        SaleOrderDetailParam saleOrderDetailParam=new SaleOrderDetailParam();
        saleOrderDetailParam.setYn(1);
        saleOrderDetailParam.setOrderNo(saleOrderResult.getOrderNo());
        List<SaleOrderDetailResult> saleOrderDetailResultList=findListBySpec(saleOrderDetailParam);
        for (SaleOrderDetailResult saleOrderDetailResult:saleOrderDetailResultList)
        {
            if (saleOrderDetailResult.getDeliveryNum().compareTo(new BigDecimal(0))<=0)
            {
                continue;
            }
            LocationStockParam locationStockParam=new LocationStockParam();
            locationStockParam.setRelOrderNo(saleOrderResult.getOrderNo());
            locationStockParam.setLocationCode("");
            locationStockParam.setSkuCode(saleOrderDetailResult.getSkuCode());
            locationStockParam.setWarehouseCode(saleOrderResult.getWarehouseCode());
            locationStockParam.setWarehouseName(saleOrderResult.getWarehouseName());
            locationStockParam.setQuantity(saleOrderDetailResult.getDeliveryNum());
            wareStockService.updateRealAndForOrderStock(locationStockParam, OperationTypeEnum.TYPE_SALE_OUTSTORE, LocationStockDirectionEnum.REDUCE,user);
        }

        SaleOrderParam saleOrderParam=new SaleOrderParam();
        saleOrderParam.setDeliveryTime(new Date());
        saleOrderParam.setDeliveryUser(user);
        saleOrderParam.setUpdateUser(user);
        saleOrderParam.setOrderNo(saleOrderResult.getOrderNo());
        saleOrderParam.setOrderState(SaleOrderStatusEnum.DELIVERY_FINISH.getStatusValue());
        saleOrderService.updateOutBound(saleOrderParam);


        return true;
    }

    @Override
    public int updateDeliveryNum(SaleOrderDetailParam saleOrderDetailParam) {
        return this.baseMapper.updateDeliveryNum(saleOrderDetailParam);
    }
}
