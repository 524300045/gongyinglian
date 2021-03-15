layui.use(['table', 'admin', 'ax','ztree', 'func', 'tree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var tree = layui.tree;
    var $ZTree = layui.ztree;

    /**
     * 分类表管理
     */
    var Category = {
        tableId: "categoryTable"
    };

    /**
     * 初始化表格的列
     */
    Category.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'categoryCode', sort: true, title: '编码'},
            {field: 'categoryName', sort: true, title: '分类名称'},
            {field: 'parentCode', sort: true, title: '上级ID'},
            {field: 'priority', sort: true, title: '优先级'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Category.search = function () {
        var queryData = {};


        table.reload(Category.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 左侧树加载
     */
    Category.loadCategoryTree = function () {
        var ajax = new $ax(Feng.ctxPath + "/category/layuiTree", function (data) {
            tree.render({
                elem: '#categoryTree',
                data: data,
                click: Category.onClickDept,
                onlyIconControl: true
            });
        }, function (data) {
        });
        ajax.start();
    };

    /**
     * 跳转到添加页面
     */
    Category.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/category/add'
    };

    Category.openAddCategory = function () {
        func.open({
            height: 530,
            title: '添加分类',
            content: Feng.ctxPath + '/category/add',
            tableId: Category.tableId,
            endCallback: function () {
                Category.loadCategoryTree();
            }
        });
    };

    /**
     * 点击编辑部门
     *
     * @param data 点击按钮时候的行数据
     */
    Category.onEditCategory = function (data) {
        func.open({
            height: 530,
            title: '编辑分类',
            content: Feng.ctxPath + "/category/edit?id=" + data.id,
            tableId: Category.tableId,
            endCallback: function () {
                Category.loadCategoryTree();
            }
        });
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Category.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/category/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Category.exportExcel = function () {
        var checkRows = table.checkStatus(Category.tableId);
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
    Category.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/category/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Category.tableId);
                Category.loadCategoryTree();
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
        elem: '#' + Category.tableId,
        url: Feng.ctxPath + '/category/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Category.initColumn()
    });

    //初始化左侧部门树
    Category.loadCategoryTree();

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Category.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

   // Category.jumpAddPage();
        Category.openAddCategory();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Category.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Category.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
          //  Category.jumpEditPage(data);
            Category.onEditCategory(data);
        } else if (layEvent === 'delete') {
            Category.onDeleteItem(data);
        }
    });
});

$(function () {
    var panehHidden = false;
    if ($(this).width() < 769) {
        panehHidden = true;
    }
    $('#myContiner').layout({initClosed: panehHidden, west__size: 260});
});
