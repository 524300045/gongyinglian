package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.InBound;
import cn.stylefeng.guns.sys.modular.system.mapper.InBoundMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundResult;
import  cn.stylefeng.guns.sys.modular.system.service.InBoundService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 入库单 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Service
public class InBoundServiceImpl extends ServiceImpl<InBoundMapper, InBound> implements InBoundService {

    @Override
    public void add(InBoundParam param){
        InBound entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InBoundParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InBoundParam param){
        InBound oldEntity = getOldEntity(param);
        InBound newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InBoundResult findBySpec(InBoundParam param){
        return null;
    }

    @Override
    public List<InBoundResult> findListBySpec(InBoundParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InBoundParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(InBoundParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InBound getOldEntity(InBoundParam param) {
        return this.getById(getKey(param));
    }

    private InBound getEntity(InBoundParam param) {
        InBound entity = new InBound();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
