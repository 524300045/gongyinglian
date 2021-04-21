package cn.stylefeng.guns.sys.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum CodeExpressEnum {
    partnerCode("partner", "G,^6#", Boolean.FALSE ,"供应商编码"),
    categoryCode("category", "1,^4#", Boolean.FALSE ,"分类编码"),
    skuCode("sku", "1,^6#", Boolean.FALSE ,"sku编码"),
    wareCode("ware", "1,^3#", Boolean.FALSE ,"仓库编码"),
    billnoPurchase("billnoPurchase", "CG,^7#", Boolean.FALSE ,"采购单号"),
    billnoInbound("billnoInbound", "RK,^7#", Boolean.FALSE ,"入库单号"),
    billnoSaleOrder("billnoSaleOrder", "S,^7#", Boolean.FALSE ,"销售单号"),
    billnoCustomerBack("billnoCustomerBack", "KT,^7#", Boolean.FALSE ,"客退单号"),
    billnoBackPartner("billnoBackPartner", "TG,^7#", Boolean.FALSE ,"退供单号"),
    shopCode("shop", "1,^3#", Boolean.FALSE ,"车间编码"),
    ;

    /**
     * type
     */
    String type;

    /**
     * expStr
     * 	split ,
     * 	create number must #    if start use ^
     */
    String expStr;

    String dateStr;

    /**
     * 是否使用高低位生成规则
     */
    boolean useHighLow;

    String message;

    CodeExpressEnum(String type, String expStr, boolean useHighLow, String message) {
        this.type = type;
        this.expStr = expStr;
        this.useHighLow = useHighLow;
        this.message = message;
    }

    CodeExpressEnum(String type, String expStr, String dateStr, boolean useHighLow, String message) {
        this.type = type;
        this.expStr = expStr;
        this.dateStr = dateStr;
        this.useHighLow = useHighLow;
        this.message = message;
    }

    public static Map<String, CodeExpressEnum> getMap(){
        Map<String, CodeExpressEnum> map = new HashMap<String, CodeExpressEnum>();
        for (CodeExpressEnum enumObj : CodeExpressEnum.values()) {
            map.put(enumObj.getType(), enumObj);
        }
        return map;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpStr() {
        return expStr;
    }

    public void setExpStr(String expStr) {
        this.expStr = expStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public boolean getUseHighLow() {
        return useHighLow;
    }

    public void setUseHighLow(boolean useHighLow) {
        this.useHighLow = useHighLow;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
