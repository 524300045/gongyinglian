package cn.stylefeng.guns.sys.modular.system.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum InboundStatusEnum {

    Initial(0, "新建"),
    Receiving(10, "收货中"),
    Received(20, "收货完成");

    /**
     * map存储索引与枚举关系
     */
    private static final Map<Integer,String> statusMap = new HashMap<Integer,String>();

    static {
        for(InboundStatusEnum s : EnumSet.allOf(InboundStatusEnum.class)){
            statusMap.put(s.getStatusValue(), s.getStatusRemark());
        }
    }

    /**
     * 状态值
     */
    private Integer statusValue;

    /**
     * 状态描述
     */
    private String statusRemark;

    private InboundStatusEnum(Integer statusValue, String statusRemark){
        this.statusValue = statusValue;
        this.statusRemark = statusRemark;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public String getStatusRemark() {
        return statusRemark;
    }



    public static Map<Integer, String> getStatusmap() {
        return statusMap;
    }


}
