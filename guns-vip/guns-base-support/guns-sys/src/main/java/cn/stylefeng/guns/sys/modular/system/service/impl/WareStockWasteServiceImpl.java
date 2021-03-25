package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WareStockWaste;
import cn.stylefeng.guns.sys.modular.system.mapper.WareStockWasteMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockWasteParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockWasteResult;
import  cn.stylefeng.guns.sys.modular.system.service.WareStockWasteService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 库存流水表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Service
public class WareStockWasteServiceImpl extends ServiceImpl<WareStockWasteMapper, WareStockWaste> implements WareStockWasteService {

    @Override
    public void add(WareStockWasteParam param){
        WareStockWaste entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WareStockWasteParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WareStockWasteParam param){
        WareStockWaste oldEntity = getOldEntity(param);
        WareStockWaste newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WareStockWasteResult findBySpec(WareStockWasteParam param){
        return null;
    }

    @Override
    public List<WareStockWasteResult> findListBySpec(WareStockWasteParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WareStockWasteParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WareStockWasteParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WareStockWaste getOldEntity(WareStockWasteParam param) {
        return this.getById(getKey(param));
    }

    private WareStockWaste getEntity(WareStockWasteParam param) {
        WareStockWaste entity = new WareStockWaste();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
