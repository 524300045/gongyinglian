package cn.stylefeng.guns.sys.modular.system.enums;

import java.util.HashMap;
import java.util.Map;

public enum LocationStockTypeEnum {

    REAL_STOCK(10, "有效库存流水"),
    USABEL_STOCK(15, "可用库存流水"),
    UNQUALIFIED_STOCK(20, "残品库存流水"),
    OCCUPY_STOCK(30, "预占库存流水"),
    LOCK_STOCK(40, "锁定库存流水"),
    SYSTEM_STOCK(999, "系统处理"),
    ;

    Integer code;

    String name;

    LocationStockTypeEnum(Integer code, String name) {
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
        for (LocationStockTypeEnum p : LocationStockTypeEnum.values()) {
            map.put(p.getCode(), p.getName());
        }
        return map;
    }

}
