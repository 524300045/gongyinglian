layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 采购订单表管理
     */
    var PmsOrderPurchase = {
        tableId: "pmsOrderPurchaseTable"
    };

    /**
     * 初始化表格的列
     */
    PmsOrderPurchase.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'orderNo', sort: true, title: '采购订单编码'},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'partnerCode', sort: true, title: '供应商编码'},
            {field: 'partnerName', sort: true, title: '供应商名称'},
            {field: 'orderState', sort: true, title: '状态'},
            {field: 'arrivalDate', sort: true, title: '到货日期'},
            {field: 'remark', sort: true, title: '备注'},
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
    PmsOrderPurchase.search = function () {
        var queryData = {};


        table.reload(PmsOrderPurchase.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    PmsOrderPurchase.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/pmsOrderPurchase/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    PmsOrderPurchase.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/pmsOrderPurchase/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    PmsOrderPurchase.exportExcel = function () {
        var checkRows = table.checkStatus(PmsOrderPurchase.tableId);
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
    PmsOrderPurchase.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/pmsOrderPurchase/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PmsOrderPurchase.tableId);
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
        elem: '#' + PmsOrderPurchase.tableId,
        url: Feng.ctxPath + '/pmsOrderPurchase/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: PmsOrderPurchase.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PmsOrderPurchase.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    PmsOrderPurchase.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        PmsOrderPurchase.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PmsOrderPurchase.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PmsOrderPurchase.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            PmsOrderPurchase.onDeleteItem(data);
        }
    });
});
