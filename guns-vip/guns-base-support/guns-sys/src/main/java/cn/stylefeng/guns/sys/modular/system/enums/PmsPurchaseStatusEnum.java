package cn.stylefeng.guns.sys.modular.system.enums;

import cn.stylefeng.guns.sys.modular.system.entity.Option;

import java.util.*;

public enum PmsPurchaseStatusEnum {

    CANCEL(-10, "已取消"),
    NEW(0, "新建"),
    AUDIT(10, "已审核"),
    RECIEVE_ING(20, "收货中"),
    RECIEVE_FINISH(30, "收货完成"),
    CLOSE(40, "关闭");

    /**
     * map存储索引与枚举关系
     */
    private static final Map<Integer, String> statusMap = new HashMap<Integer, String>();

    static {
        for (PmsPurchaseStatusEnum s : EnumSet.allOf(PmsPurchaseStatusEnum.class)) {
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

    private PmsPurchaseStatusEnum(Integer statusValue, String statusRemark) {
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

    public static List<Option> asList() {
        List<Option> list = new ArrayList<Option>();
        PmsPurchaseStatusEnum[] arr = PmsPurchaseStatusEnum.values();
        for (PmsPurchaseStatusEnum e : arr) {
            Option option = new Option();
            option.setCode(e.getStatusValue()+ "");
            option.setValue(e.getStatusRemark());

            list.add(option);
        }

        return list;
    }
}
