layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 销售订单明细管理
     */
    var SaleOrderDetail = {
        tableId: "saleOrderDetailTable"
    };

    /**
     * 初始化表格的列
     */
    SaleOrderDetail.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'orderNo', sort: true, title: '采购订单编码'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'isFresh', sort: true, title: '0:否 1:是'},
            {field: 'planNum', sort: true, title: '计划量'},
            {field: 'deliveryNum', sort: true, title: '发运量'},
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
    SaleOrderDetail.search = function () {
        var queryData = {};


        table.reload(SaleOrderDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    SaleOrderDetail.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/saleOrderDetail/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    SaleOrderDetail.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/saleOrderDetail/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    SaleOrderDetail.exportExcel = function () {
        var checkRows = table.checkStatus(SaleOrderDetail.tableId);
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
    SaleOrderDetail.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/saleOrderDetail/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SaleOrderDetail.tableId);
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
        elem: '#' + SaleOrderDetail.tableId,
        url: Feng.ctxPath + '/saleOrderDetail/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SaleOrderDetail.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SaleOrderDetail.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    SaleOrderDetail.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        SaleOrderDetail.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SaleOrderDetail.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SaleOrderDetail.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            SaleOrderDetail.onDeleteItem(data);
        }
    });
});
