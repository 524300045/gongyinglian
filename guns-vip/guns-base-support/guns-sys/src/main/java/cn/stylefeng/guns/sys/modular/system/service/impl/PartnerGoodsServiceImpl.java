package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.PartnerGoods;
import cn.stylefeng.guns.sys.modular.system.mapper.PartnerGoodsMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.PartnerGoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PartnerGoodsResult;
import  cn.stylefeng.guns.sys.modular.system.service.PartnerGoodsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 供应商商品表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-18
 */
@Service
public class PartnerGoodsServiceImpl extends ServiceImpl<PartnerGoodsMapper, PartnerGoods> implements PartnerGoodsService {

    @Override
    public void add(PartnerGoodsParam param){
        PartnerGoods entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PartnerGoodsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PartnerGoodsParam param){
        PartnerGoods oldEntity = getOldEntity(param);
        PartnerGoods newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PartnerGoodsResult findBySpec(PartnerGoodsParam param){
        return null;
    }

    @Override
    public List<PartnerGoodsResult> findListBySpec(PartnerGoodsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PartnerGoodsParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PartnerGoodsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PartnerGoods getOldEntity(PartnerGoodsParam param) {
        return this.getById(getKey(param));
    }

    private PartnerGoods getEntity(PartnerGoodsParam param) {
        PartnerGoods entity = new PartnerGoods();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
