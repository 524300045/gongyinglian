package cn.stylefeng.guns.sys.modular.system.enums;

import cn.stylefeng.guns.sys.modular.system.entity.Option;

import java.util.*;

public enum CustomerBackOrderStatusEnum {

    CANCEL(-10, "已取消"),
    NEW(0, "新建"),
    AUDIT(10, "已审核"),
    RECEIVEING(20, "入库中"),
    FINISH(30, "已完成");


    /**
     * map存储索引与枚举关系
     */
    private static final Map<Integer, String> statusMap = new HashMap<Integer, String>();

    static {
        for (CustomerBackOrderStatusEnum s : EnumSet.allOf(CustomerBackOrderStatusEnum.class)) {
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

    private CustomerBackOrderStatusEnum(Integer statusValue, String statusRemark) {
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
        CustomerBackOrderStatusEnum[] arr = CustomerBackOrderStatusEnum.values();
        for (CustomerBackOrderStatusEnum e : arr) {
            Option option = new Option();
            option.setCode(e.getStatusValue()+ "");
            option.setValue(e.getStatusRemark());

            list.add(option);
        }

        return list;
    }
}
