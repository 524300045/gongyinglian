layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 销售订单表管理
     */
    var SaleOrder = {
        tableId: "detailTable"
    };

    /**
     * 初始化表格的列
     */
    SaleOrder.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'orderNo', sort: true, title: '采购订单编码'},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'orderState', sort: true, title: '状态0:新建 10:已审核  30:已发货'},
            {field: 'totalAmount', sort: true, title: '订单总金额'},
            {field: 'provinceCode', sort: true, title: '省'},
            {field: 'provinceName', sort: true, title: '省'},
            {field: 'cityCode', sort: true, title: '市编码'},
            {field: 'cityName', sort: true, title: '市'},
            {field: 'areaCode', sort: true, title: '区编码'},
            {field: 'areaName', sort: true, title: '区'},
            {field: 'receiverName', sort: true, title: '收货人姓名'},
            {field: 'receiverPhone', sort: true, title: '电话'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'auditUser', sort: true, title: '审核人'},
            {field: 'auditTime', sort: true, title: '审核时间'},
            {field: 'cancelUser', sort: true, title: '取消人'},
            {field: 'cancelTime', sort: true, title: '取消时间'},
            {field: 'deliveryTime', sort: true, title: '出库时间'},
            {field: 'deliveryUser', sort: true, title: '发运人'},
            {field: 'deliveryDate', sort: true, title: '发货日期'},
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
    SaleOrder.search = function () {
        var queryData = {};


        table.reload(SaleOrder.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    SaleOrder.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/saleOrder/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    SaleOrder.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/saleOrder/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    SaleOrder.exportExcel = function () {
        var checkRows = table.checkStatus(SaleOrder.tableId);
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
    SaleOrder.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/saleOrder/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SaleOrder.tableId);
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
        elem: '#' + SaleOrder.tableId,
        url: Feng.ctxPath + '/saleOrder/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SaleOrder.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SaleOrder.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    SaleOrder.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        SaleOrder.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SaleOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SaleOrder.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            SaleOrder.onDeleteItem(data);
        }
    });
});