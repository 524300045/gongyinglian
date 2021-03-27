package cn.stylefeng.guns.sys.modular.system.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LocationStockParam {

    /*
 仓库编码
  */
    private String warehouseCode;

    private String warehouseName;

    private String locationCode;

    private String skuCode;


    private Date productionDate;

    private String relOrderNo;

    private BigDecimal quantity;


}
