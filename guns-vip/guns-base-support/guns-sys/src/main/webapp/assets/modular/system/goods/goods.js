layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 商品表管理
     */
    var Goods = {
        tableId: "goodsTable"
    };

    /**
     * 初始化表格的列
     */
    Goods.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'skuCode', sort: true, title: '编码'},
            {field: 'goodsName', sort: true, title: '分类名称'},
            {field: 'categoryCode', sort: true, title: '一级分类编码'},
            {field: 'categoryName', sort: true, title: '一级分类名称'},
            {field: 'twoCategoryCode', sort: true, title: '二级分类编码'},
            {field: 'twoCategoryName', sort: true, title: '二级分类名称'},
            {field: 'status', sort: true, title: '0:停用 1:启用'},
            {field: 'goodsModel', sort: true, title: '规格型号'},
            {field: 'goodsBrand', sort: true, title: '品牌'},
            {field: 'weight', sort: true, title: '重量'},
            {field: 'unitName', sort: true, title: '单位名称'},
            {field: 'unitCode', sort: true, title: '单位编码'},
            {field: 'isFresh', sort: true, title: '是否生鲜 0:否 1:是'},
            {field: 'weighed', sort: true, title: '是否称重 0:否 1:是'},
            {field: 'productType', sort: true, title: '商品类型 0:成品 1：半成品 2：原料'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'taxRate', sort: true, title: '税率'},
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
    Goods.search = function () {
        var queryData = {};


        table.reload(Goods.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Goods.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/goods/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Goods.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/goods/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Goods.exportExcel = function () {
        var checkRows = table.checkStatus(Goods.tableId);
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
    Goods.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Goods.tableId);
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
        elem: '#' + Goods.tableId,
        url: Feng.ctxPath + '/goods/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Goods.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Goods.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Goods.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Goods.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Goods.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Goods.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Goods.onDeleteItem(data);
        }
    });
});
