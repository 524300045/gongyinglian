layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 客退单管理
     */
    var CustomerBackOrder = {
        tableId: "customerBackOrderTable"
    };

    /**
     * 初始化表格的列
     */
    CustomerBackOrder.initColumn = function () {
        return [[

            {field: 'id', hide: true, title: ''},
            {align: 'center',width: 200,  title: '操作'
                , templet: function (d)
                {
                    if (d.orderState ===0) {
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">审核</a>' +
                            '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">取消</a>'+
                            '<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>'
                            ;
                    }
                    return "<a class=\"layui-btn layui-btn-warm layui-btn-xs\" lay-event=\"view\">查看</a> ";
                }},
            {field: 'customerBackOrderNo', sort: true, title: '客退单号'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'orderNo', sort: true, title: '销售单号'},
            {field: 'orderState', sort: true, title: '状态', templet: function (d)
                {
                    if (d.orderState ===0) {
                        return "新建";
                    }
                    else  if (d.orderState ===-10)
                    {
                        return "已取消";
                    }
                    else  if (d.orderState ===10)
                    {
                        return "已审核";
                    }
                    else  if (d.orderState ===20)
                    {
                        return "入库中";
                    }
                    else  if (d.orderState ===30)
                    {
                        return "已完成";
                    }
                    else
                    {
                        return  "";
                    }
                }},
            {field: 'receiverName', sort: true, title: '收货人姓名'},
            {field: 'receiverPhone', sort: true, title: '电话'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'auditUser', sort: true, title: '审核人'},
            {field: 'auditTime', sort: true, title: '审核时间'},
            {field: 'cancelUser', sort: true, title: '取消人'},
            {field: 'cancelTime', sort: true, title: '取消时间'},
            {field: 'finishTime', sort: true, title: '完成时间'},
            {field: 'finishUser', sort: true, title: '完成人'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateUser', sort: true, title: '更新人'},
            {field: 'updateTime', sort: true, title: '更新时间'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CustomerBackOrder.search = function () {
        var queryData = {};
        queryData['customerBackOrderNo'] = $("#customerBackOrderNo").val();
        queryData['receiverName'] =$("#receiverName").val();

        table.reload(CustomerBackOrder.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    CustomerBackOrder.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/customerBackOrder/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    CustomerBackOrder.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/customerBackOrder/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    CustomerBackOrder.exportExcel = function () {
        var checkRows = table.checkStatus(CustomerBackOrder.tableId);
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
    CustomerBackOrder.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/customerBackOrder/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CustomerBackOrder.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    CustomerBackOrder.onAuditItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/customerBackOrder/audit", function (data) {
                Feng.success("审核成功!");
                table.reload(CustomerBackOrder.tableId);
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否审核?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CustomerBackOrder.tableId,
        url: Feng.ctxPath + '/customerBackOrder/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CustomerBackOrder.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CustomerBackOrder.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    CustomerBackOrder.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        CustomerBackOrder.exportExcel();
    });

    CustomerBackOrder.onViewDetail = function (data) {
        layer.open({
            type: 2,
            title: '查看客退明细',
            shadeClose: true,
            area: ['1000px', '600px'],
            content: Feng.ctxPath + '/customerBackOrderDetail/viewDetail?orderNo='+data.orderNo,
            end: function () {

            }


        });
    };

    // 工具条点击事件
    table.on('tool(' + CustomerBackOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CustomerBackOrder.onAuditItem(data);
        } else if (layEvent === 'delete') {
            CustomerBackOrder.onDeleteItem(data);
        }
        else if (layEvent === 'view') {
            CustomerBackOrder.onViewDetail(data);
        }
    });
});
