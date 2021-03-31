package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrder;
import cn.stylefeng.guns.sys.modular.system.mapper.SaleOrderDetailMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.SaleOrderMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderResult;
import cn.stylefeng.guns.sys.modular.system.service.SaleOrderDetailService;
import  cn.stylefeng.guns.sys.modular.system.service.SaleOrderService;
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
 * 销售订单表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-27
 */
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderService {

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @Override
    public void add(SaleOrderParam param){
        SaleOrder entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SaleOrderParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SaleOrderParam param){
        SaleOrder oldEntity = getOldEntity(param);
        SaleOrder newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SaleOrderResult findBySpec(SaleOrderParam param){
        return null;
    }

    @Override
    public List<SaleOrderResult> findListBySpec(SaleOrderParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SaleOrderParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SaleOrderParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SaleOrder getOldEntity(SaleOrderParam param) {
        return this.getById(getKey(param));
    }

    private SaleOrder getEntity(SaleOrderParam param) {
        SaleOrder entity = new SaleOrder();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public boolean addOrder(SaleOrderParam saleOrderParam, List<SaleOrderDetailParam> saleOrderDetailParamList) {

        add(saleOrderParam);
        for (SaleOrderDetailParam saleOrderDetailParam:saleOrderDetailParamList)
        {
            saleOrderDetailService.add(saleOrderDetailParam);
        }

        return true;
    }

    @Override
    public SaleOrderResult getByOrderNo(String orderNo) {
        SaleOrderParam saleOrderParam=new SaleOrderParam();
        List<SaleOrderResult> saleOrderResultList=this.baseMapper.customList(saleOrderParam);
        if (saleOrderResultList!=null&&saleOrderResultList.size()>0)
        {
            return  saleOrderResultList.get(0);
        }
        return null;
    }
}
