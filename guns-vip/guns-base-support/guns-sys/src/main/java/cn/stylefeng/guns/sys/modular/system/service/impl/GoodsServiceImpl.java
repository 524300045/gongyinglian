package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.mapper.GoodsMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import  cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-15
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public void add(GoodsParam param){
        Goods entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(GoodsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(GoodsParam param){
        Goods oldEntity = getOldEntity(param);
        Goods newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public GoodsResult findBySpec(GoodsParam param){
        return null;
    }

    @Override
    public List<GoodsResult> findListBySpec(GoodsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(GoodsParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(GoodsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Goods getOldEntity(GoodsParam param) {
        return this.getById(getKey(param));
    }

    private Goods getEntity(GoodsParam param) {
        Goods entity = new Goods();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
