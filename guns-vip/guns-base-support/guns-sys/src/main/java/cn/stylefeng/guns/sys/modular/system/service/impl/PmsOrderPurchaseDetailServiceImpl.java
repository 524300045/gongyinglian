package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchaseDetail;
import cn.stylefeng.guns.sys.modular.system.mapper.PmsOrderPurchaseDetailMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PmsOrderPurchaseDetailResult;
import  cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseDetailService;
import cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 采购订单明细表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
@Service
public class PmsOrderPurchaseDetailServiceImpl extends ServiceImpl<PmsOrderPurchaseDetailMapper, PmsOrderPurchaseDetail> implements PmsOrderPurchaseDetailService {

    @Autowired
    private PmsOrderPurchaseService pmsOrderPurchaseService;

    @Override
    public void add(PmsOrderPurchaseDetailParam param){
        PmsOrderPurchaseDetail entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PmsOrderPurchaseDetailParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PmsOrderPurchaseDetailParam param){
        PmsOrderPurchaseDetail oldEntity = getOldEntity(param);
        PmsOrderPurchaseDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PmsOrderPurchaseDetailResult findBySpec(PmsOrderPurchaseDetailParam param){
        return null;
    }

    @Override
    public List<PmsOrderPurchaseDetailResult> findListBySpec(PmsOrderPurchaseDetailParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PmsOrderPurchaseDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PmsOrderPurchaseDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PmsOrderPurchaseDetail getOldEntity(PmsOrderPurchaseDetailParam param) {
        return this.getById(getKey(param));
    }

    private PmsOrderPurchaseDetail getEntity(PmsOrderPurchaseDetailParam param) {
        PmsOrderPurchaseDetail entity = new PmsOrderPurchaseDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public boolean savePmsPurchase(PmsOrderPurchaseParam pmsOrderPurchaseParam,
                                   List<PmsOrderPurchaseDetailParam> pmsOrderPurchaseDetailParams)
    {
        pmsOrderPurchaseService.add(pmsOrderPurchaseParam);
        for (PmsOrderPurchaseDetailParam pmsOrderPurchaseDetailParam:pmsOrderPurchaseDetailParams)
        {
            pmsOrderPurchaseDetailParam.setOrderNo(pmsOrderPurchaseParam.getOrderNo());
            add(pmsOrderPurchaseDetailParam);
        }
        return true;
    }

}
