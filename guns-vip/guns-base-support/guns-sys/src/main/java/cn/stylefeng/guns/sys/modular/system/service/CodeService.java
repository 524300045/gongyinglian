package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Code;
import cn.stylefeng.guns.sys.modular.system.model.params.CodeParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CodeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 编码表 服务类
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-14
 */
public interface CodeService extends IService<Code> {

    /**
     * 新增
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    void add(CodeParam param);

    /**
     * 删除
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    void delete(CodeParam param);

    /**
     * 更新
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    void update(CodeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    CodeResult findBySpec(CodeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    List<CodeResult> findListBySpec(CodeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
     LayuiPageInfo findPageBySpec(CodeParam param);

    /**
     * 生成流水号
     * @date 2016年2月16日
     * @param tableName
     * @return
     */
    public long getObjectId(String tableName);

    /**
     * 不区分高位低位
     * @date 2016年2月17日
     * @param tableName
     * @return
     */
    public long getDBObjectId(String tableName);

    /**
     * 生成其他公司编码
     * @param type
     * @param expStr
     * @param dateFormatStr
     * @param replaceMap
     * @return
     */
    public String generateCode(String type, String expStr, String dateFormatStr, Map<String, String> replaceMap);

    /**
     * 生成编码
     * @param codeExpressEnum
     * @param replaceMap
     * @return
     */
    public String generateCode(CodeExpressEnum codeExpressEnum, Map<String, String> replaceMap);


}
