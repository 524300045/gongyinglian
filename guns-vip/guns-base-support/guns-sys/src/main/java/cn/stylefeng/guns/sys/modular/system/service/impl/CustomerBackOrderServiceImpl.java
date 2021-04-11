package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.CustomerBackOrder;
import cn.stylefeng.guns.sys.modular.system.mapper.CustomerBackOrderMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.CustomerBackOrderParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CustomerBackOrderResult;
import  cn.stylefeng.guns.sys.modular.system.service.CustomerBackOrderService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 客退单 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-04-11
 */
@Service
public class CustomerBackOrderServiceImpl extends ServiceImpl<CustomerBackOrderMapper, CustomerBackOrder> implements CustomerBackOrderService {

    @Override
    public void add(CustomerBackOrderParam param){
        CustomerBackOrder entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CustomerBackOrderParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CustomerBackOrderParam param){
        CustomerBackOrder oldEntity = getOldEntity(param);
        CustomerBackOrder newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CustomerBackOrderResult findBySpec(CustomerBackOrderParam param){
        return null;
    }

    @Override
    public List<CustomerBackOrderResult> findListBySpec(CustomerBackOrderParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CustomerBackOrderParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CustomerBackOrderParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private CustomerBackOrder getOldEntity(CustomerBackOrderParam param) {
        return this.getById(getKey(param));
    }

    private CustomerBackOrder getEntity(CustomerBackOrderParam param) {
        CustomerBackOrder entity = new CustomerBackOrder();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
