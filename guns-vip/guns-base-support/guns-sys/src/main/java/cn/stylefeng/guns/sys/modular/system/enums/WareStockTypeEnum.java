package cn.stylefeng.guns.sys.modular.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum WareStockTypeEnum {

    REAL_STOCK(10, "有效库存流水"),
    USABEL_STOCK(15, "可订库存流水"),
    UNQUALIFIED_STOCK(20, "残品库存流水"),
    SYSTEM_STOCK(999, "系统处理"),
    ;

    Integer code;

    String name;

    WareStockTypeEnum(Integer code, String name) {
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
        for (WareStockTypeEnum p : WareStockTypeEnum.values()) {
            map.put(p.getCode(), p.getName());
        }
        return map;
    }

}
