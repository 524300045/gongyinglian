package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.WarehouseInfo;
import cn.stylefeng.guns.sys.modular.system.mapper.WarehouseInfoMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.WarehouseInfoParam;
import cn.stylefeng.guns.sys.modular.system.model.result.WarehouseInfoResult;
import  cn.stylefeng.guns.sys.modular.system.service.WarehouseInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 仓库信息 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-21
 */
@Service
public class WarehouseInfoServiceImpl extends ServiceImpl<WarehouseInfoMapper, WarehouseInfo> implements WarehouseInfoService {

    @Override
    public void add(WarehouseInfoParam param){
        WarehouseInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WarehouseInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WarehouseInfoParam param){
        WarehouseInfo oldEntity = getOldEntity(param);
        WarehouseInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WarehouseInfoResult findBySpec(WarehouseInfoParam param){
        return null;
    }

    @Override
    public List<WarehouseInfoResult> findListBySpec(WarehouseInfoParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WarehouseInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WarehouseInfoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WarehouseInfo getOldEntity(WarehouseInfoParam param) {
        return this.getById(getKey(param));
    }

    private WarehouseInfo getEntity(WarehouseInfoParam param) {
        WarehouseInfo entity = new WarehouseInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
