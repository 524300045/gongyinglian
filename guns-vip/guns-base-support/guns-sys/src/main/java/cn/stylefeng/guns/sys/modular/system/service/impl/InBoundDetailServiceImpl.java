package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.InBoundDetail;
import cn.stylefeng.guns.sys.modular.system.mapper.InBoundDetailMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.InBoundDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.result.InBoundDetailResult;
import  cn.stylefeng.guns.sys.modular.system.service.InBoundDetailService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 入库单明细表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Service
public class InBoundDetailServiceImpl extends ServiceImpl<InBoundDetailMapper, InBoundDetail> implements InBoundDetailService {

    @Override
    public void add(InBoundDetailParam param){
        InBoundDetail entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InBoundDetailParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InBoundDetailParam param){
        InBoundDetail oldEntity = getOldEntity(param);
        InBoundDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InBoundDetailResult findBySpec(InBoundDetailParam param){
        return null;
    }

    @Override
    public List<InBoundDetailResult> findListBySpec(InBoundDetailParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InBoundDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(InBoundDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InBoundDetail getOldEntity(InBoundDetailParam param) {
        return this.getById(getKey(param));
    }

    private InBoundDetail getEntity(InBoundDetailParam param) {
        InBoundDetail entity = new InBoundDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
