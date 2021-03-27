layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 库存管理
     */
    var WareStock = {
        tableId: "wareStockTable"
    };

    /**
     * 初始化表格的列
     */
    WareStock.initColumn = function () {
        return [[

            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'realStock', sort: true, title: '库存'},
            {field: 'forOrderStock', sort: true, title: '可用库存'},
            {field: 'brokenStock', sort: true, title: '残品库存'},
            {field: 'updateUser', sort: true, title: '更新人'},
            {field: 'updateTime', sort: true, title: '更新时间'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WareStock.search = function () {
        var queryData = {};


        table.reload(WareStock.tableId, {
            where: queryData, page: {curr: 1}
        });
    };



    /**
     * 导出excel按钮
     */
    WareStock.exportExcel = function () {
        var checkRows = table.checkStatus(WareStock.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WareStock.tableId,
        url: Feng.ctxPath + '/wareStock/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WareStock.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WareStock.search();
    });



    // 导出excel
    $('#btnExp').click(function () {
        WareStock.exportExcel();
    });


});
