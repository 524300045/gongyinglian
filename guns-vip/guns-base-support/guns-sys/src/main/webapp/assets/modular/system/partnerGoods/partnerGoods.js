layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 供应商商品表管理
     */
    var PartnerGoods = {
        tableId: "partnerGoodsTable"
    };

    /**
     * 初始化表格的列
     */
    PartnerGoods.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'partnerCode', sort: true, title: '供应商编码'},
            {field: 'skuCode', sort: true, title: '商品编码'},
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
    PartnerGoods.search = function () {
        var queryData = {};


        table.reload(PartnerGoods.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    PartnerGoods.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/partnerGoods/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    PartnerGoods.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/partnerGoods/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    PartnerGoods.exportExcel = function () {
        var checkRows = table.checkStatus(PartnerGoods.tableId);
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
    PartnerGoods.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/partnerGoods/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PartnerGoods.tableId);
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
        elem: '#' + PartnerGoods.tableId,
        url: Feng.ctxPath + '/partnerGoods/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: PartnerGoods.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PartnerGoods.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    PartnerGoods.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        PartnerGoods.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PartnerGoods.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PartnerGoods.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            PartnerGoods.onDeleteItem(data);
        }
    });
});
