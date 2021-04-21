layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 车间管理
     */
    var Workshop = {
        tableId: "workshopTable"
    };

    /**
     * 初始化表格的列
     */
    Workshop.initColumn = function () {
        return [[

            {field: 'id', hide: true, title: ''},
            {field: 'shopCode', sort: true, title: '车间编码'},
            {field: 'shopName', sort: true, title: '车间名称 '},

            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Workshop.search = function () {
        var queryData = {};


        table.reload(Workshop.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    Workshop.openAddInfo = function () {
        func.open({
            height: 300,
            title: '添加车间',
            content: Feng.ctxPath + '/workshop/add',
            tableId: Workshop.tableId
        });
    };
    Workshop.onEditInfo = function (data) {
        func.open({
            height: 470,
            title: '编辑仓库',
            content: Feng.ctxPath + "/workshop/edit?id=" + data.id,
            tableId: Workshop.tableId
        });
    };


    /**
     * 导出excel按钮
     */
    Workshop.exportExcel = function () {
        var checkRows = table.checkStatus(Workshop.tableId);
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
    Workshop.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/workshop/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Workshop.tableId);
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
        elem: '#' + Workshop.tableId,
        url: Feng.ctxPath + '/workshop/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Workshop.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Workshop.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

        Workshop.openAddInfo();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Workshop.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Workshop.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Workshop.onEditInfo(data);
        } else if (layEvent === 'delete') {
            Workshop.onDeleteItem(data);
        }
    });
});
