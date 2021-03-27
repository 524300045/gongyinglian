package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.entity.WareStock;
import cn.stylefeng.guns.sys.modular.system.entity.WareStockWaste;
import cn.stylefeng.guns.sys.modular.system.enums.BusinessTypeEnum;
import cn.stylefeng.guns.sys.modular.system.enums.LocationStockDirectionEnum;
import cn.stylefeng.guns.sys.modular.system.enums.OperationTypeEnum;
import cn.stylefeng.guns.sys.modular.system.enums.WareStockTypeEnum;
import cn.stylefeng.guns.sys.modular.system.mapper.GoodsMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.WareStockMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.WareStockWasteMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.LocationStockParam;
import cn.stylefeng.guns.sys.modular.system.model.params.WareStockParam;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import cn.stylefeng.guns.sys.modular.system.model.result.WareStockResult;
import cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import  cn.stylefeng.guns.sys.modular.system.service.WareStockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 库存 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-03-25
 */
@Service
public class WareStockServiceImpl extends ServiceImpl<WareStockMapper, WareStock> implements WareStockService {

    @Autowired
    private WareStockMapper wareStockMapper;

    @Autowired
    private WareStockWasteMapper wareStockWasteMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void add(WareStockParam param){
        WareStock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WareStockParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WareStockParam param){
        WareStock oldEntity = getOldEntity(param);
        WareStock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WareStockResult findBySpec(WareStockParam param){
        return null;
    }

    @Override
    public List<WareStockResult> findListBySpec(WareStockParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WareStockParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WareStockParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WareStock getOldEntity(WareStockParam param) {
        return this.getById(getKey(param));
    }

    private WareStock getEntity(WareStockParam param) {
        WareStock entity = new WareStock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, timeout = 10)
    public boolean updateRealAndForOrderStock(LocationStockParam locationStockParam, OperationTypeEnum operationTypeEnum, LocationStockDirectionEnum directionEnum, String oper) {

        BusinessTypeEnum businessTypeEnum=getTypeEnum(operationTypeEnum);

        if(directionEnum==LocationStockDirectionEnum.INCREASE)
        {
            //增加库存

            WareStockParam wareStockParam=new WareStockParam();
            wareStockParam.setWarehouseCode(locationStockParam.getWarehouseCode());
            wareStockParam.setSkuCode(locationStockParam.getSkuCode());

            List<WareStockResult> wareStockList=wareStockMapper.selectListBySkuCode(wareStockParam);
            if (wareStockList!=null&&wareStockList.size()>0)
            {
                //更新
                WareStock wareStock=new WareStock();
                wareStock.setWarehouseCode(locationStockParam.getWarehouseCode());

                wareStock.setSkuCode(locationStockParam.getSkuCode());
                wareStock.setRealStock(locationStockParam.getQuantity());
                wareStock.setForOrderStock(locationStockParam.getQuantity());
                wareStock.setUpdateUser(oper);

                int result=wareStockMapper.updateRealAndForOrderStock(wareStock);
                if (result<=0)
                {
                    throw new RuntimeException("增加库存失败");
                }
                //库存流水日志
                WareStockWaste wareStockWaste=new WareStockWaste();
                wareStockWaste.setWarehouseCode(locationStockParam.getWarehouseCode());
                wareStockWaste.setSkuCode(locationStockParam.getSkuCode());
                wareStockWaste.setBusinessNo(locationStockParam.getRelOrderNo());
                wareStockWaste.setDirection(LocationStockDirectionEnum.INCREASE.getCode());
                wareStockWaste.setAmount(locationStockParam.getQuantity());
                wareStockWaste.setType(WareStockTypeEnum.REAL_STOCK.getCode());
                wareStockWaste.setBusinessType(businessTypeEnum.getCode());
                wareStockWaste.setOperationType(operationTypeEnum.getCode());
                wareStockWaste.setRemark(businessTypeEnum.getName()+"-"+operationTypeEnum.getName());
                wareStockWaste.setCreateUser(oper);

                wareStockWasteMapper.insertRealStockWaste(wareStockWaste);

                //插入可订购量日志
                WareStockWaste wareForOrderStockWaste=new WareStockWaste();
                wareForOrderStockWaste.setWarehouseCode(locationStockParam.getWarehouseCode());
                wareForOrderStockWaste.setSkuCode(locationStockParam.getSkuCode());
                wareForOrderStockWaste.setBusinessNo(locationStockParam.getRelOrderNo());
                wareForOrderStockWaste.setDirection(LocationStockDirectionEnum.INCREASE.getCode());
                wareForOrderStockWaste.setAmount(locationStockParam.getQuantity());
                wareForOrderStockWaste.setType(WareStockTypeEnum.USABEL_STOCK.getCode());
                wareForOrderStockWaste.setBusinessType(businessTypeEnum.getCode());
                wareForOrderStockWaste.setOperationType(operationTypeEnum.getCode());
                wareForOrderStockWaste.setRemark(businessTypeEnum.getName()+"-"+operationTypeEnum.getName());
                wareForOrderStockWaste.setCreateUser(oper);
                wareStockWasteMapper.insertForOrderWaste(wareForOrderStockWaste);

                return  true;
            }
            else
            {
                //插入

                GoodsResult good=goodsService.getGoodsBySkuCode(locationStockParam.getSkuCode());
                if (good==null)
                {
                    throw new  RuntimeException("查询不到商品信息");
                }

                //Goods good=goods.get(0);
                WareStock wareStock=new WareStock();
                wareStock.setUpdateUser(oper);
                wareStock.setRealStock(locationStockParam.getQuantity());
                wareStock.setOccupyStock(new BigDecimal(0));
                wareStock.setLockStock(new BigDecimal(0));
                wareStock.setBrokenStock(new BigDecimal(0));
                wareStock.setSkuCode(locationStockParam.getSkuCode());
                wareStock.setUnitName(good.getUnitName());
                wareStock.setWarehouseCode(locationStockParam.getWarehouseCode());
                wareStock.setCreateUser(oper);
                wareStock.setGoodsModel(good.getGoodsModel());
                wareStock.setGoodsName(good.getGoodsName());
                wareStock.setWarehouseName(locationStockParam.getWarehouseName());
                wareStock.setForOrderStock(locationStockParam.getQuantity());
                wareStock.setYn(1);
                wareStock.setUpdateUser(oper);
                wareStock.setCreateTime(new Date());
                wareStock.setUpdateTime(new Date());
                int result=wareStockMapper.insert(wareStock);
                if (result<=0)
                {
                    throw new RuntimeException("插入库存失败");
                }

                //库存流水日志
                WareStockWaste wareStockWaste=new WareStockWaste();
                wareStockWaste.setWarehouseCode(locationStockParam.getWarehouseCode());
                wareStockWaste.setSkuCode(locationStockParam.getSkuCode());
                wareStockWaste.setBusinessNo(locationStockParam.getRelOrderNo());
                wareStockWaste.setDirection(LocationStockDirectionEnum.INCREASE.getCode());
                wareStockWaste.setAmount(locationStockParam.getQuantity());
                wareStockWaste.setType(WareStockTypeEnum.REAL_STOCK.getCode());
                wareStockWaste.setBusinessType(businessTypeEnum.getCode());
                wareStockWaste.setOperationType(operationTypeEnum.getCode());
                wareStockWaste.setRemark(businessTypeEnum.getName()+"-"+operationTypeEnum.getName());
                wareStockWaste.setCreateUser(oper);

                wareStockWaste.setCreateTime(new Date());


                wareStockWasteMapper.insertRealStockWaste(wareStockWaste);


                //可定量日志
                WareStockWaste wareForOrderStockWaste=new WareStockWaste();
                wareForOrderStockWaste.setWarehouseCode(locationStockParam.getWarehouseCode());
                wareForOrderStockWaste.setSkuCode(locationStockParam.getSkuCode());
                wareForOrderStockWaste.setBusinessNo(locationStockParam.getRelOrderNo());
                wareForOrderStockWaste.setDirection(LocationStockDirectionEnum.INCREASE.getCode());
                wareForOrderStockWaste.setAmount(locationStockParam.getQuantity());
                wareForOrderStockWaste.setType(WareStockTypeEnum.USABEL_STOCK.getCode());
                wareForOrderStockWaste.setBusinessType(businessTypeEnum.getCode());
                wareForOrderStockWaste.setOperationType(operationTypeEnum.getCode());
                wareForOrderStockWaste.setRemark(businessTypeEnum.getName()+"-"+operationTypeEnum.getName());
                wareForOrderStockWaste.setCreateUser(oper);

                wareForOrderStockWaste.setCreateTime(new Date());


                wareStockWasteMapper.insertForOrderWaste(wareForOrderStockWaste);
                return  true;
            }
        }
        if (directionEnum==LocationStockDirectionEnum.REDUCE)
        {
            //减少库存
            //增加库存
            WareStockParam wareStockParam=new WareStockParam();
            wareStockParam.setWarehouseCode(locationStockParam.getWarehouseCode());
            wareStockParam.setSkuCode(locationStockParam.getSkuCode());

            List<WareStockResult> wareStockList=wareStockMapper.selectListBySkuCode(wareStockParam);
            if (wareStockList==null||wareStockList.size()==0)
            {
                throw new RuntimeException("查询不到库存");
            }
            if (locationStockParam.getQuantity().compareTo(wareStockList.get(0).getRealStock())==1)
            {
                throw new RuntimeException("扣减数量不能大于总库存");
            }

            //更新
            WareStock wareStock=new WareStock();
            wareStock.setWarehouseCode(locationStockParam.getWarehouseCode());

            wareStock.setSkuCode(locationStockParam.getSkuCode());
            wareStock.setRealStock(locationStockParam.getQuantity().multiply(new BigDecimal(-1)));
            wareStock.setForOrderStock(locationStockParam.getQuantity().multiply(new BigDecimal(-1)));
            wareStock.setUpdateUser(oper);

            int result=wareStockMapper.updateRealAndForOrderStock(wareStock);
            if (result<=0)
            {
                throw new RuntimeException("扣减库存失败");
            }
            //库存流水日志
            WareStockWaste wareStockWaste=new WareStockWaste();
            wareStockWaste.setWarehouseCode(locationStockParam.getWarehouseCode());
            wareStockWaste.setSkuCode(locationStockParam.getSkuCode());
            wareStockWaste.setBusinessNo(locationStockParam.getRelOrderNo());
            wareStockWaste.setDirection(LocationStockDirectionEnum.REDUCE.getCode());
            wareStockWaste.setAmount(locationStockParam.getQuantity().multiply(new BigDecimal(-1)));
            wareStockWaste.setType(WareStockTypeEnum.REAL_STOCK.getCode());
            wareStockWaste.setBusinessType(businessTypeEnum.getCode());
            wareStockWaste.setOperationType(operationTypeEnum.getCode());
            wareStockWaste.setRemark(businessTypeEnum.getName()+"-"+operationTypeEnum.getName());
            wareStockWaste.setCreateUser(oper);
            wareStockWaste.setCreateTime(new Date());
            wareStockWasteMapper.insertRealStockWaste(wareStockWaste);

            //插入可订购量日志
            WareStockWaste wareForOrderStockWaste=new WareStockWaste();
            wareForOrderStockWaste.setWarehouseCode(locationStockParam.getWarehouseCode());
            wareForOrderStockWaste.setSkuCode(locationStockParam.getSkuCode());
            wareForOrderStockWaste.setBusinessNo(locationStockParam.getRelOrderNo());
            wareForOrderStockWaste.setDirection(LocationStockDirectionEnum.REDUCE.getCode());
            wareForOrderStockWaste.setAmount(locationStockParam.getQuantity().multiply(new BigDecimal(-1)));
            wareForOrderStockWaste.setType(WareStockTypeEnum.USABEL_STOCK.getCode());
            wareForOrderStockWaste.setBusinessType(businessTypeEnum.getCode());
            wareForOrderStockWaste.setOperationType(operationTypeEnum.getCode());
            wareForOrderStockWaste.setRemark(businessTypeEnum.getName()+"-"+operationTypeEnum.getName());
            wareForOrderStockWaste.setCreateUser(oper);
            wareStockWaste.setCreateTime(new Date());
            wareStockWasteMapper.insertForOrderWaste(wareForOrderStockWaste);

            return  true;

        }
        return false;
    }

    private BusinessTypeEnum getTypeEnum(OperationTypeEnum operationTypeEnum)
    {
        BusinessTypeEnum businessTypeEnum=null;


        if (operationTypeEnum.getCode().equals(OperationTypeEnum.TYPE_PURCHASE_INSTORE.getCode()))
        {
            businessTypeEnum=BusinessTypeEnum.TYPE_PROCUREMENT_IN;
        }

        return businessTypeEnum;
    }


}
