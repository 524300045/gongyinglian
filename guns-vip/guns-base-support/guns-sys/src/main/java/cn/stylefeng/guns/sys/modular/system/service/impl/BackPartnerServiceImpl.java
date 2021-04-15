package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartner;
import cn.stylefeng.guns.sys.modular.system.mapper.BackPartnerMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerResult;
import  cn.stylefeng.guns.sys.modular.system.service.BackPartnerService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 退供单表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
@Service
public class BackPartnerServiceImpl extends ServiceImpl<BackPartnerMapper, BackPartner> implements BackPartnerService {

    @Override
    public void add(BackPartnerParam param){
        BackPartner entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BackPartnerParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BackPartnerParam param){
        BackPartner oldEntity = getOldEntity(param);
        BackPartner newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BackPartnerResult findBySpec(BackPartnerParam param){

        List<BackPartnerResult> backPartnerResults=this.baseMapper.customList(param);
        if (backPartnerResults!=null&&backPartnerResults.size()>0)
        {
            return backPartnerResults.get(0);
        }
        return  null;
    }

    @Override
    public List<BackPartnerResult> findListBySpec(BackPartnerParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BackPartnerParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BackPartnerParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private BackPartner getOldEntity(BackPartnerParam param) {
        return this.getById(getKey(param));
    }

    private BackPartner getEntity(BackPartnerParam param) {
        BackPartner entity = new BackPartner();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public int updateOutBound(BackPartnerParam backPartnerParam) {
        return this.baseMapper.updateOutBound(backPartnerParam);
    }
}
