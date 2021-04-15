package cn.stylefeng.guns.sys.modular.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum OperationTypeEnum {

    TYPE_PURCHASE_INSTORE(100, "采购入库"),
    TYPE_SALE_OUTSTORE(120, "销售出库"),
    TYPE_BACKPARTNER_OUTSTORE(130, "退供出库"),
    ;

    Integer code;

    String name;

    OperationTypeEnum(Integer code, String name) {
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
        for (OperationTypeEnum p : OperationTypeEnum.values()) {
            map.put(p.getCode(), p.getName());
        }
        return map;
    }
    public static Map<String,String> getStringAll(){
        Map<String, String> map = new HashMap<String, String>();
        for (OperationTypeEnum p : OperationTypeEnum.values()) {
            map.put(p.getCode().toString(), p.getName());
        }
        return map;
    }
}
