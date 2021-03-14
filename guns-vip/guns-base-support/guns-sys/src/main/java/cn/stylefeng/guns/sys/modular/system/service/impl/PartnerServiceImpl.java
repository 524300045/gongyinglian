package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Partner;
import cn.stylefeng.guns.sys.modular.system.mapper.PartnerMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerResult;
import  cn.stylefeng.guns.sys.modular.system.service.PartnerService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-10
 */
@Service
public class PartnerServiceImpl extends ServiceImpl<PartnerMapper, Partner> implements PartnerService {

    @Override
    public void add(PartnerParam param){
        Partner entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PartnerParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PartnerParam param){
        Partner oldEntity = getOldEntity(param);
        Partner newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PartnerResult findBySpec(PartnerParam param){
        return null;
    }

    @Override
    public List<PartnerResult> findListBySpec(PartnerParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PartnerParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PartnerParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Partner getOldEntity(PartnerParam param) {
        return this.getById(getKey(param));
    }

    private Partner getEntity(PartnerParam param) {
        Partner entity = new Partner();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
