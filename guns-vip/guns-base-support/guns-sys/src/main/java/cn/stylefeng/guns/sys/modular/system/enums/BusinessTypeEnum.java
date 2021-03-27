package cn.stylefeng.guns.sys.modular.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum BusinessTypeEnum {


    TYPE_PROCUREMENT_IN(100, "采购订单")

    ;

    Integer code;

    String name;

    BusinessTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<Integer,String> getAll(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (BusinessTypeEnum p : BusinessTypeEnum.values()) {
            map.put(p.getCode(), p.getName());
        }
        return map;
    }
    public static Map<String,String> getStringAll(){
        Map<String, String> map = new HashMap<String, String>();
        for (BusinessTypeEnum p : BusinessTypeEnum.values()) {
            map.put(p.getCode().toString(), p.getName());
        }
        return map;
    }
}
