package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.Code;
import cn.stylefeng.guns.sys.modular.system.model.params.CodeParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CodeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 编码表 Mapper 接口
 * </p>
 *
 * @author zhangxiang
 * @since 2021-03-14
 */
public interface CodeMapper extends BaseMapper<Code> {

    /**
     * 获取列表
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    List<CodeResult> customList(@Param("paramCondition") CodeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CodeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    Page<CodeResult> customPageList(@Param("page") Page page, @Param("paramCondition") CodeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zhangxiang
     * @Date 2021-03-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CodeParam paramCondition);


    /**
     * 更新高位
     * @date 2016年2月3日
     * @param map
     * @return
     */
    public int updateFirstId(Map<String, Object> map);

    /**
     * 插入高位
     * @date 2016年2月3日
     * @param map
     * @return
     */
    public int insertFirstId(Map<String, Object> map);

    /**
     * 获取高位
     * @date 2016年2月3日
     * @param map
     * @return
     */
    public Long queryFirstId(Map<String, Object> map);

}
