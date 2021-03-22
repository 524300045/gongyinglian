layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 采购订单明细表管理
     */
    var PmsOrderPurchaseDetail = {
        tableId: "pmsOrderPurchaseDetailTable"
    };

    /**
     * 初始化表格的列
     */
    PmsOrderPurchaseDetail.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'orderNo', sort: true, title: '采购订单编码'},
            {field: 'skuCode', sort: true, title: '仓库编码'},
            {field: 'goodsName', sort: true, title: '仓库名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'isFresh', sort: true, title: '0:否 1:是'},
            {field: 'planNum', sort: true, title: '计划量'},
            {field: 'realityNum', sort: true, title: '收货量'},
            {field: 'taxRate', sort: true, title: '税率'},
            {field: 'taxPrice', sort: true, title: '单价'},
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
    PmsOrderPurchaseDetail.search = function () {
        var queryData = {};


        table.reload(PmsOrderPurchaseDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    PmsOrderPurchaseDetail.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/pmsOrderPurchaseDetail/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    PmsOrderPurchaseDetail.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/pmsOrderPurchaseDetail/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    PmsOrderPurchaseDetail.exportExcel = function () {
        var checkRows = table.checkStatus(PmsOrderPurchaseDetail.tableId);
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
    PmsOrderPurchaseDetail.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/pmsOrderPurchaseDetail/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PmsOrderPurchaseDetail.tableId);
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
        elem: '#' + PmsOrderPurchaseDetail.tableId,
        url: Feng.ctxPath + '/pmsOrderPurchaseDetail/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: PmsOrderPurchaseDetail.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PmsOrderPurchaseDetail.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    PmsOrderPurchaseDetail.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        PmsOrderPurchaseDetail.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PmsOrderPurchaseDetail.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PmsOrderPurchaseDetail.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            PmsOrderPurchaseDetail.onDeleteItem(data);
        }
    });
});
