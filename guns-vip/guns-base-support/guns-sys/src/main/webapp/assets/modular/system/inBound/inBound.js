layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 入库单管理
     */
    var InBound = {
        tableId: "inBoundTable"
    };

    /**
     * 初始化表格的列
     */
    InBound.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'inboundTaskCode', sort: true, title: '入库单号'},
            {field: 'orderNo', sort: true, title: '采购订单编码'},
            {field: 'partnerCode', sort: true, title: '供应商编码'},
            {field: 'partnerName', sort: true, title: '供应商名称'},
            {field: 'orderType', sort: true, title: '单据类型0:采购入库'},
            {field: 'orderState', sort: true, title: '状态'},
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
    InBound.search = function () {
        var queryData = {};


        table.reload(InBound.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    InBound.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/inBound/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    InBound.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/inBound/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    InBound.exportExcel = function () {
        var checkRows = table.checkStatus(InBound.tableId);
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
    InBound.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/inBound/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(InBound.tableId);
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
        elem: '#' + InBound.tableId,
        url: Feng.ctxPath + '/inBound/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InBound.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InBound.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    InBound.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        InBound.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + InBound.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            InBound.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            InBound.onDeleteItem(data);
        }
    });
});
