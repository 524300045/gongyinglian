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

            {field: 'id', hide: true, title: ''},
            {field: 'skuCode', sort: true, title: '编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},

            {
                field: 'categoryName', align: "center", title: '分类', templet: function (d) {

                        return d.categoryName+"--"+d.twoCategoryName;

                }
            },
            {
                field: 'status', align: "center", title: '状态', templet: function (d) {
                    if (d.status ===1) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            },
            {field: 'goodsModel', sort: true, title: '规格型号'},
            {field: 'goodsBrand', sort: true, title: '品牌'},
            {field: 'weight', sort: true, title: '重量'},
            {field: 'unitName', sort: true, title: '单位名称'},
            {
                field: 'isFresh', align: "center", title: '是否生鲜', templet: function (d) {
                    if (d.isFresh ===1) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            },
            {
                field: 'weighed', align: "center", title: '是否称重', templet: function (d) {
                    if (d.weighed ===1) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            },
            {
                field: 'productType', align: "center", title: '商品类型', templet: function (d) {
                    if (d.productType ===0) {
                        return "成品";
                    }
                    else if (d.productType ===1)
                    {
                        return "半成品";
                    }
                    else {
                        return "原料";
                    }
                }
            },
            {field: 'price', sort: true, title: '价格'},
            {field: 'taxRate', sort: true, title: '税率'},
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

    Goods.openAddGoods = function () {
        func.open({
            title: '添加商品',
            content: Feng.ctxPath + '/goods/add',
            tableId: Goods.tableId

        });
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

   // Goods.jumpAddPage();
        Goods.openAddGoods();

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
