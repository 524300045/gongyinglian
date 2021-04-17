layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * bom表管理
     */
    var Bom = {
        tableId: "bomTable"
    };

    /**
     * 初始化表格的列
     */
    Bom.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'skuCode', sort: true, title: '编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'childSkuCode', sort: true, title: '组件SKU'},
            {field: 'childGoodsName', sort: true, title: '组件商品名称'},
            {field: 'proportion', sort: true, title: '数量'},
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
    Bom.search = function () {
        var queryData = {};


        table.reload(Bom.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Bom.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/bom/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Bom.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/bom/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Bom.exportExcel = function () {
        var checkRows = table.checkStatus(Bom.tableId);
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
    Bom.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/bom/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Bom.tableId);
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
        elem: '#' + Bom.tableId,
        url: Feng.ctxPath + '/bom/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Bom.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Bom.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Bom.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Bom.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Bom.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Bom.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Bom.onDeleteItem(data);
        }
    });
});
