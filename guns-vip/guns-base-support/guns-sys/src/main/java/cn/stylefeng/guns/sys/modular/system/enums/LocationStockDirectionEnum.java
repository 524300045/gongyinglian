package cn.stylefeng.guns.sys.modular.system.enums;

public enum LocationStockDirectionEnum {

    REDUCE(0,"扣减库存"),
    INCREASE(1,"增加库存");
    private Integer code;
    private String message;

    private LocationStockDirectionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
