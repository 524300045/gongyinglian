layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 退供明细表管理
     */
    var BackPartnerDetail = {
        tableId: "backPartnerDetailTable"
    };

    /**
     * 初始化表格的列
     */
    BackPartnerDetail.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'backOrderNo', sort: true, title: '退供单号'},
            {field: 'skuCode', sort: true, title: 'sku编码'},
            {field: 'goodsName', sort: true, title: '名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'planNum', sort: true, title: '计划量'},
            {field: 'realityNum', sort: true, title: '出库量'},
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
    BackPartnerDetail.search = function () {
        var queryData = {};


        table.reload(BackPartnerDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    BackPartnerDetail.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/backPartnerDetail/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    BackPartnerDetail.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/backPartnerDetail/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    BackPartnerDetail.exportExcel = function () {
        var checkRows = table.checkStatus(BackPartnerDetail.tableId);
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
    BackPartnerDetail.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/backPartnerDetail/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(BackPartnerDetail.tableId);
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
        elem: '#' + BackPartnerDetail.tableId,
        url: Feng.ctxPath + '/backPartnerDetail/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: BackPartnerDetail.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        BackPartnerDetail.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    BackPartnerDetail.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        BackPartnerDetail.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + BackPartnerDetail.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            BackPartnerDetail.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            BackPartnerDetail.onDeleteItem(data);
        }
    });
});
