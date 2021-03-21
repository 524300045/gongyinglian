layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 仓库信息管理
     */
    var WarehouseInfo = {
        tableId: "warehouseInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WarehouseInfo.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称 '},
            {field: 'status', sort: false, title: '状态1:启用 0:停用'},
            {field: 'address', sort: false, title: ''},

            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WarehouseInfo.search = function () {
        var queryData = {};


        table.reload(WarehouseInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    WarehouseInfo.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/warehouseInfo/add'
    };

    WarehouseInfo.openAddWarehouseInfo = function () {
        func.open({
            height: 470,
            title: '添加仓库',
            content: Feng.ctxPath + '/warehouseInfo/add',
            tableId: WarehouseInfo.tableId
        });
    };

    WarehouseInfo.onEditWarehouseInfo = function (data) {
        func.open({
            height: 470,
            title: '编辑仓库',
            content: Feng.ctxPath + "/warehouseInfo/edit?id=" + data.id,
            tableId: WarehouseInfo.tableId
        });
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    WarehouseInfo.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/warehouseInfo/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    WarehouseInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WarehouseInfo.tableId);
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
    WarehouseInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/warehouseInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WarehouseInfo.tableId);
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
        elem: '#' + WarehouseInfo.tableId,
        url: Feng.ctxPath + '/warehouseInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WarehouseInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WarehouseInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    //WarehouseInfo.jumpAddPage();
        WarehouseInfo.openAddWarehouseInfo();

    });

    // 导出excel
    $('#btnExp').click(function () {
        WarehouseInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WarehouseInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
           // WarehouseInfo.jumpEditPage(data);
            WarehouseInfo.onEditWarehouseInfo(data);
        } else if (layEvent === 'delete') {
            WarehouseInfo.onDeleteItem(data);
        }
    });
});
