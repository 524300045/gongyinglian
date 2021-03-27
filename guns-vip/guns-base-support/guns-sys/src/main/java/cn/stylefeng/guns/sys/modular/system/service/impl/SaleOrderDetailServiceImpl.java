package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.SaleOrderDetail;
import cn.stylefeng.guns.sys.modular.system.mapper.SaleOrderDetailMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.SaleOrderDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.SaleOrderDetailResult;
import  cn.stylefeng.guns.sys.modular.system.service.SaleOrderDetailService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
        return null;
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

}
