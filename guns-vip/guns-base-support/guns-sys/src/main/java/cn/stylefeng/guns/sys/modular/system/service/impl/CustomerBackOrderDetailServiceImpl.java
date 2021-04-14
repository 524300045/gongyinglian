package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrderDetail;
import cn.stylefeng.guns.sys.modular.system.mapper.CustomerBackOrderDetailMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.params.PmsOrderPurchaseDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CustomerBackOrderDetailResult;
import  cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderDetailService;
import cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderService;
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
 * 客退明细 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
@Service
public class CustomerBackOrderDetailServiceImpl extends ServiceImpl<CustomerBackOrderDetailMapper, CustomerBackOrderDetail> implements CustomerBackOrderDetailService {

    @Autowired
    private CustomerBackOrderService customerBackOrderService;

    @Override
    public void add(CustomerBackOrderDetailParam param){
        CustomerBackOrderDetail entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CustomerBackOrderDetailParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CustomerBackOrderDetailParam param){
        CustomerBackOrderDetail oldEntity = getOldEntity(param);
        CustomerBackOrderDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CustomerBackOrderDetailResult findBySpec(CustomerBackOrderDetailParam param){
        return null;
    }

    @Override
    public List<CustomerBackOrderDetailResult> findListBySpec(CustomerBackOrderDetailParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CustomerBackOrderDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CustomerBackOrderDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private CustomerBackOrderDetail getOldEntity(CustomerBackOrderDetailParam param) {
        return this.getById(getKey(param));
    }

    private CustomerBackOrderDetail getEntity(CustomerBackOrderDetailParam param) {
        CustomerBackOrderDetail entity = new CustomerBackOrderDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public void createCustomerBackOrder(CustomerBackOrderParam customerBackOrderParam,
                                        List<CustomerBackOrderDetailParam> paramList){

        for (CustomerBackOrderDetailParam customerBackOrderDetailParam:paramList)
        {
            CustomerBackOrderDetail entity = getEntity(customerBackOrderDetailParam);
            this.save(entity);
        }
        this.customerBackOrderService.add(customerBackOrderParam);
    }

    @Override
    public LayuiPageInfo selectPageInfo(CustomerBackOrderDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.selectPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public int updateRealityNum(CustomerBackOrderDetail customerBackOrderDetail) {
        return this.baseMapper.updateRealityNum(customerBackOrderDetail);
    }
}
