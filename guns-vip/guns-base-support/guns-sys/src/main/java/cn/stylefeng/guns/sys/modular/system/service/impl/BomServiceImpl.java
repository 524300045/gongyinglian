package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Bom;
import cn.stylefeng.guns.sys.modular.system.mapper.BomMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.BomParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BomResult;
import  cn.stylefeng.guns.sys.modular.system.service.BomService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * bom表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-04-16
 */
@Service
public class BomServiceImpl extends ServiceImpl<BomMapper, Bom> implements BomService {

    @Override
    public void add(BomParam param){
        Bom entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BomParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BomParam param){
        Bom oldEntity = getOldEntity(param);
        Bom newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BomResult findBySpec(BomParam param){
        return null;
    }

    @Override
    public List<BomResult> findListBySpec(BomParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BomParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BomParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Bom getOldEntity(BomParam param) {
        return this.getById(getKey(param));
    }

    private Bom getEntity(BomParam param) {
        Bom entity = new Bom();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
