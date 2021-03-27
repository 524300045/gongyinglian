package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PmsOrderPurchase;
import cn.stylefeng.guns.sys.modular.system.mapper.PmsOrderPurchaseMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PmsOrderPurchaseResult;
import  cn.stylefeng.guns.sys.modular.system.service.PmsOrderPurchaseService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 采购订单表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-22
 */
@Service
public class PmsOrderPurchaseServiceImpl extends ServiceImpl<PmsOrderPurchaseMapper, PmsOrderPurchase> implements PmsOrderPurchaseService {

    @Override
    public void add(PmsOrderPurchaseParam param){
        PmsOrderPurchase entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PmsOrderPurchaseParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PmsOrderPurchaseParam param){
        PmsOrderPurchase oldEntity = getOldEntity(param);
        PmsOrderPurchase newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PmsOrderPurchaseResult findBySpec(PmsOrderPurchaseParam param){
        return null;
    }

    @Override
    public List<PmsOrderPurchaseResult> findListBySpec(PmsOrderPurchaseParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PmsOrderPurchaseParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PmsOrderPurchaseParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PmsOrderPurchase getOldEntity(PmsOrderPurchaseParam param) {
        return this.getById(getKey(param));
    }

    private PmsOrderPurchase getEntity(PmsOrderPurchaseParam param) {
        PmsOrderPurchase entity = new PmsOrderPurchase();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public boolean updateCancel(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
       int o=this.baseMapper.updateCancel(pmsOrderPurchaseParam);
       if (o>0)
       {
           return  true;
       }
        return false;
    }

    @Override
    public boolean updateAudit(PmsOrderPurchaseParam pmsOrderPurchaseParam) {
        int o=this.baseMapper.updateAudit(pmsOrderPurchaseParam);
        if (o>0)
            return true;
        return false;
    }


}
