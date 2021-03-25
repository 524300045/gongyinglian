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
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '仓商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'realStock', sort: true, title: '库存'},
            {field: 'forOrderStock', sort: true, title: '可用库存'},
            {field: 'occupyStock', sort: true, title: '预占数量'},
            {field: 'lockStock', sort: true, title: '锁定数量'},
            {field: 'brokenStock', sort: true, title: '残品库存'},
            {field: 'onwayStock', sort: true, title: '在途库存'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateUser', sort: true, title: '更新人'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'yn', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
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
     * 跳转到添加页面
     */
    WareStock.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/wareStock/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    WareStock.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/wareStock/edit?id=' + data.id
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

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    WareStock.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wareStock/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WareStock.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
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

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    WareStock.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        WareStock.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WareStock.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WareStock.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            WareStock.onDeleteItem(data);
        }
    });
});
