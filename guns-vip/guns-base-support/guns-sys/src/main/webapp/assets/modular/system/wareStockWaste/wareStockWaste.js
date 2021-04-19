layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 库存流水表管理
     */
    var WareStockWaste = {
        tableId: "wareStockWasteTable"
    };

    /**
     * 初始化表格的列
     */
    WareStockWaste.initColumn = function () {
        return [[

            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'businessNo', sort: true, title: '关联单号'},
            {field: 'direction', sort: true, title: '方向' , templet: function (d)
                {
                    if (d.direction ===0) {
                        return "减";
                    }
                    else  if (d.direction ===1)
                    {
                        return "加";
                    }
                    return "";
                }
                },
            {field: 'amount', sort: true, title: '变动数量'},
            {field: 'balanceAmount', sort: true, title: '变动后数量'},
            {field: 'type', sort: true, title: '流水类型', templet: function (d)
                {
                    if (d.type ===10) {
                        return "有效库存";
                    }
                    else  if (d.type ===15)
                    {
                        return "可订库存";
                    }
                    return "";
                }

            },
            {field: 'businessType', sort: true, title: '业务类型',templet: function (d)
                {
                    if (d.businessType ===100) {
                        return "采购订单";
                    }
                    return "";
                }
            },
            {field: 'operationType', sort: true, title: '操作类型',templet: function (d)
                {
                    if (d.operationType ===100) {
                        return "采购入库";
                    }
                    return "";
                }},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'}

        ]];
    };

    /**
     * 点击查询按钮
     */
    WareStockWaste.search = function () {
        var queryData = {};
        queryData['warehouseCode'] =$("#warehouseCode").val();
        queryData['goodsName'] = $("#goodsName").val();

        table.reload(WareStockWaste.tableId, {
            where: queryData, page: {curr: 1}
        });
    };



    /**
     * 导出excel按钮
     */
    WareStockWaste.exportExcel = function () {
        var checkRows = table.checkStatus(WareStockWaste.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WareStockWaste.tableId,
        url: Feng.ctxPath + '/wareStockWaste/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WareStockWaste.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WareStockWaste.search();
    });

    // 添加按钮点击事件


    // 导出excel
    $('#btnExp').click(function () {
        WareStockWaste.exportExcel();
    });


});
