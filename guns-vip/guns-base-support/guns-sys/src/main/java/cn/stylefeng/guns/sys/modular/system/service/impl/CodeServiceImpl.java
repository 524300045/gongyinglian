package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Code;
import cn.stylefeng.guns.sys.modular.system.mapper.CodeMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.CodeParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CodeResult;
import  cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 编码表 服务实现类
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-14
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {


    private static Map<String, Long> firstMap = new ConcurrentHashMap<String, Long>();
    private static Map<String, AtomicInteger> nextIncMap = new ConcurrentHashMap<String, AtomicInteger>();
    private Integer maxValue = 999;
    private final static String ERROR = "-1";

    @Override
    public void add(CodeParam param){
        Code entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CodeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CodeParam param){
        Code oldEntity = getOldEntity(param);
        Code newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CodeResult findBySpec(CodeParam param){
        return null;
    }

    @Override
    public List<CodeResult> findListBySpec(CodeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CodeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CodeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Code getOldEntity(CodeParam param) {
        return this.getById(getKey(param));
    }

    private Code getEntity(CodeParam param) {
        Code entity = new Code();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, timeout = 10)
    public long getObjectId(String type) {
        if (!firstMap.containsKey(type)) {
            firstMap.put(type, this.getAndSaveObjectFirstId(type, 1));
            nextIncMap.put(type, new AtomicInteger(0));
        }
        Integer lastId = nextIncMap.get(type).addAndGet(1);
        if (lastId.intValue() > maxValue.intValue()) {
            firstMap.put(type, this.getAndSaveObjectFirstId(type, 1));
            nextIncMap.put(type, new AtomicInteger(1));
            lastId = 1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstMap.get(type));

        int idLen = lastId.toString().length();
        int zeroLen = maxValue.toString().length() - idLen;
        for (int j = 0; j < zeroLen; j++) {
            stringBuilder.append("0");
        }
        stringBuilder.append(lastId);
        return Long.parseLong(stringBuilder.toString());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, timeout = 10)
    public long getDBObjectId(String type) {

        firstMap.put(type, this.getAndSaveObjectFirstId(type, 1));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstMap.get(type));
        return Long.parseLong(stringBuilder.toString());
    }


    private synchronized long getAndSaveObjectFirstId(String type, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("name", type);
        int rowCount = this.baseMapper.updateFirstId(map);
        long firstId = 0;
        if (rowCount == 0) {
            map.put("firstId", count);
            this.baseMapper.insertFirstId(map);
            firstId = 1L;
        } else {
            firstId = this.baseMapper.queryFirstId(map);
        }
        return firstId;
    }

    public String generateCode(String type, String expStr, String dateFormatStr, Map<String, String> replaceMap) {

        if (StringUtils.isBlank(type) || StringUtils.isBlank(expStr)) {
            return ERROR;
        }

        String dateStr = null;
        if (StringUtils.isNotBlank(dateFormatStr)) {
            // dateStr = DateHelper.getCurrentDateStr(dateFormatStr);
            dateStr = DateUtil.format(new Date(),dateFormatStr);
        }

        StringBuffer result = new StringBuffer();

        String[] exps = expStr.split(",");

        StringBuffer dbKey = new StringBuffer();
        dbKey.append(type);

        for (int i = 0; i < exps.length; i++) {
            String exp = exps[i];

            if (exp.contains("*")) {
                String key = exp.replace("*", "");
                if (replaceMap == null || replaceMap.size() == 0 || !replaceMap.containsKey(key)) {
                    return ERROR;
                }
                dbKey.append(replaceMap.get(key));
                result.append(replaceMap.get(key));
            } else if (exp.contains("DATE")) {
                result.append(dateStr);
                dbKey.append(dateStr);
            } else if (exp.contains("#")) {
                long generatorId = this.getDBObjectId(dbKey.toString());

                exp = exp.replace("#", "");
                int len = 0;
                String[] generatorParams = exp.split("\\^");
                if (generatorParams.length > 1) {
                    result.append(generatorParams[0]);
                    len = Integer.parseInt(generatorParams[1]);
                } else {
                    len = Integer.parseInt(exp);
                }

                // 不够位补 0
                for(int x = 0; x < (len - String.valueOf(generatorId).length()); x++){
                    result.append("0");
                }

                result.append(generatorId);
            } else {
                result.append(exp);
            }
        }


        return result.toString();
    }

    public String generateCode(CodeExpressEnum codeExpressEnum, Map<String, String> replaceMap) {
        return this.generateCode(codeExpressEnum.getType(), codeExpressEnum.getExpStr(), codeExpressEnum.getDateStr(), replaceMap);
    }


}
