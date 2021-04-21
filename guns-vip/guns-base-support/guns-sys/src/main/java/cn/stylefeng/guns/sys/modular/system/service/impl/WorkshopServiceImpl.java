package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Workshop;
import cn.stylefeng.guns.sys.modular.system.mapper.WorkshopMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.WorkshopParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WorkshopResult;
import  cn.stylefeng.guns.sys.modular.system.service.WorkshopService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 车间 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-04-21
 */
@Service
public class WorkshopServiceImpl extends ServiceImpl<WorkshopMapper, Workshop> implements WorkshopService {

    @Override
    public void add(WorkshopParam param){
        Workshop entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WorkshopParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WorkshopParam param){
        Workshop oldEntity = getOldEntity(param);
        Workshop newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WorkshopResult findBySpec(WorkshopParam param){
        return null;
    }

    @Override
    public List<WorkshopResult> findListBySpec(WorkshopParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WorkshopParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WorkshopParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Workshop getOldEntity(WorkshopParam param) {
        return this.getById(getKey(param));
    }

    private Workshop getEntity(WorkshopParam param) {
        Workshop entity = new Workshop();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
