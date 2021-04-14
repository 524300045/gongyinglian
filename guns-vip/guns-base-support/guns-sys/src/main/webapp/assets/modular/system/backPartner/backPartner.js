layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 退供单表管理
     */
    var BackPartner = {
        tableId: "backPartnerTable"
    };

    /**
     * 初始化表格的列
     */
    BackPartner.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'backOrderNo', sort: true, title: '订单号'},
            {field: 'backType', sort: true, title: '退供类型'},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'partnerCode', sort: true, title: '供应商编码'},
            {field: 'partnerName', sort: true, title: '供应商名称'},
            {field: 'orderState', sort: true, title: '状态'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'auditUser', sort: true, title: '审核人'},
            {field: 'auditTime', sort: true, title: '审核时间'},
            {field: 'cancelUser', sort: true, title: '取消人'},
            {field: 'cancelTime', sort: true, title: '取消时间'},
            {field: 'outUser', sort: true, title: '出库人'},
            {field: 'outTime', sort: true, title: '出库时间'},
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
    BackPartner.search = function () {
        var queryData = {};


        table.reload(BackPartner.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    BackPartner.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/backPartner/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    BackPartner.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/backPartner/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    BackPartner.exportExcel = function () {
        var checkRows = table.checkStatus(BackPartner.tableId);
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
    BackPartner.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/backPartner/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(BackPartner.tableId);
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
        elem: '#' + BackPartner.tableId,
        url: Feng.ctxPath + '/backPartner/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: BackPartner.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        BackPartner.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    BackPartner.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        BackPartner.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + BackPartner.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            BackPartner.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            BackPartner.onDeleteItem(data);
        }
    });
});
