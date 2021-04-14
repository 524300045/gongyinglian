package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BackPartnerDetail;
import cn.stylefeng.guns.sys.modular.system.mapper.BackPartnerDetailMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerDetailParam;
import cn.stylefeng.guns.sys.modular.system.model.params.BackPartnerParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BackPartnerDetailResult;
import  cn.stylefeng.guns.sys.modular.system.service.BackPartnerDetailService;
import cn.stylefeng.guns.sys.modular.system.service.BackPartnerService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 退供明细表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-04-14
 */
@Service
public class BackPartnerDetailServiceImpl extends ServiceImpl<BackPartnerDetailMapper, BackPartnerDetail> implements BackPartnerDetailService {

    @Autowired
    private BackPartnerService backPartnerService;

    @Override
    public void add(BackPartnerDetailParam param){
        BackPartnerDetail entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BackPartnerDetailParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BackPartnerDetailParam param){
        BackPartnerDetail oldEntity = getOldEntity(param);
        BackPartnerDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BackPartnerDetailResult findBySpec(BackPartnerDetailParam param){
        return null;
    }

    @Override
    public List<BackPartnerDetailResult> findListBySpec(BackPartnerDetailParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BackPartnerDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BackPartnerDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private BackPartnerDetail getOldEntity(BackPartnerDetailParam param) {
        return this.getById(getKey(param));
    }

    private BackPartnerDetail getEntity(BackPartnerDetailParam param) {
        BackPartnerDetail entity = new BackPartnerDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public void saveBackPartner(BackPartnerParam backPartnerParam, List<BackPartnerDetailParam> backPartnerDetailParamList) {
        backPartnerService.add(backPartnerParam);
        for (BackPartnerDetailParam backPartnerDetailParam:backPartnerDetailParamList)
        {
            this.add(backPartnerDetailParam);
        }
    }
}
