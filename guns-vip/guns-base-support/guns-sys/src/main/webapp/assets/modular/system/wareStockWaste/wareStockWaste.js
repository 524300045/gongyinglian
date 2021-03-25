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
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'businessNo', sort: true, title: '关联单号'},
            {field: 'direction', sort: true, title: '0:减 1:加'},
            {field: 'amount', sort: true, title: '变动数量'},
            {field: 'balanceAmount', sort: true, title: '变动后数量'},
            {field: 'type', sort: true, title: '流水类型'},
            {field: 'businessType', sort: true, title: '业务类型'},
            {field: 'operationType', sort: true, title: '操作类型'},
            {field: 'remark', sort: true, title: ''},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'yn', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WareStockWaste.search = function () {
        var queryData = {};


        table.reload(WareStockWaste.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    WareStockWaste.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/wareStockWaste/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    WareStockWaste.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/wareStockWaste/edit?id=' + data.id
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

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    WareStockWaste.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wareStockWaste/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WareStockWaste.tableId);
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
    $('#btnAdd').click(function () {

    WareStockWaste.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        WareStockWaste.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WareStockWaste.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WareStockWaste.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            WareStockWaste.onDeleteItem(data);
        }
    });
});
