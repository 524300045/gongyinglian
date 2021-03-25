package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WareStock;
import cn.stylefeng.guns.sys.modular.system.mapper.WareStockMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockResult;
import  cn.stylefeng.guns.sys.modular.system.service.WareStockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 库存 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Service
public class WareStockServiceImpl extends ServiceImpl<WareStockMapper, WareStock> implements WareStockService {

    @Override
    public void add(WareStockParam param){
        WareStock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WareStockParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WareStockParam param){
        WareStock oldEntity = getOldEntity(param);
        WareStock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WareStockResult findBySpec(WareStockParam param){
        return null;
    }

    @Override
    public List<WareStockResult> findListBySpec(WareStockParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WareStockParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WareStockParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WareStock getOldEntity(WareStockParam param) {
        return this.getById(getKey(param));
    }

    private WareStock getEntity(WareStockParam param) {
        WareStock entity = new WareStock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
