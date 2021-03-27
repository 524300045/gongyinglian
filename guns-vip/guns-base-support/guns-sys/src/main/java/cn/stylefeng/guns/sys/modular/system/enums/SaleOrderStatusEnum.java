package cn.stylefeng.guns.sys.modular.system.enums;

import cn.stylefeng.guns.sys.modular.system.entity.Option;

import java.util.*;

public enum SaleOrderStatusEnum {

    CANCEL(-10, "已取消"),
    NEW(0, "新建"),
    AUDIT(10, "已审核"),
    DELIVERY_FINISH(30, "已发货");


    /**
     * map存储索引与枚举关系
     */
    private static final Map<Integer, String> statusMap = new HashMap<Integer, String>();

    static {
        for (SaleOrderStatusEnum s : EnumSet.allOf(SaleOrderStatusEnum.class)) {
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

    private SaleOrderStatusEnum(Integer statusValue, String statusRemark) {
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
        SaleOrderStatusEnum[] arr = SaleOrderStatusEnum.values();
        for (SaleOrderStatusEnum e : arr) {
            Option option = new Option();
            option.setCode(e.getStatusValue()+ "");
            option.setValue(e.getStatusRemark());

            list.add(option);
        }

        return list;
    }
}
