layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;
    /**
     * 供应商表管理
     */
    var Partner = {
        tableId: "partnerTable"
    };

    /**
     * 初始化表格的列
     */
    Partner.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'partnerCode', sort: true, title: '供应商编码'},
            {field: 'partnerName', sort: true, title: '供应商名称'},
            {field: 'status', sort: true, title: '状态',templet: '#statusTpl'},
            {field: 'email', sort: true, title: '邮箱'},
            {field: 'phone', sort: true, title: '电话'},
            {field: 'contacts', sort: true, title: '联系人'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateUser', sort: true, title: '更新人'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Partner.search = function () {
        var queryData = {};


        table.reload(Partner.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Partner.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/partner/add'
    };

    Partner.openAddPartner = function () {
        func.open({
            title: '添加供应商',
            content: Feng.ctxPath + '/partner/add',
            tableId: Partner.tableId
        });
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Partner.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/partner/edit?id=' + data.id
    };

    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    Partner.onEditPartner = function (data) {
        func.open({

            title: '修改供应商',
            content: Feng.ctxPath + "/partner/edit?id=" + data.id,
            tableId: Partner.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    Partner.exportExcel = function () {
        var checkRows = table.checkStatus(Partner.tableId);
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
    Partner.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/partner/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Partner.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 修改职位状态
     */
    Partner.changeStatus = function (id, checked) {
        var ajax = new $ax(Feng.ctxPath + "/partner/changeStatus", function (data) {
            Feng.success("修改成功!");
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message);
            table.reload(Partner.tableId);
        });
        ajax.set("id", id);
        ajax.set("status", checked);
        ajax.start();
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Partner.tableId,
        url: Feng.ctxPath + '/partner/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Partner.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Partner.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    //Partner.jumpAddPage();
        Partner.openAddPartner();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Partner.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Partner.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
          //  Partner.jumpEditPage(data);
            Partner.onEditPartner(data);
        } else if (layEvent === 'delete') {
            Partner.onDeleteItem(data);
        }
    });

    // 修改user状态
    form.on('switch(status)', function (obj) {

        var positionId = obj.elem.value;
       // var checked = obj.elem.checked ? true : false;
        var checked = obj.elem.checked ? 1 : 0;
        Partner.changeStatus(positionId, checked);
    });
});
