layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 采购订单表管理
     */
    var PmsOrderPurchase = {
        tableId: "pmsOrderPurchaseTable"
    };

    /**
     * 初始化表格的列
     */
    PmsOrderPurchase.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'orderNo', sort: true, title: '采购订单编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'partnerName', sort: true, title: '供应商名称'},
            {field: 'orderState', sort: true, title: '状态', templet: function (d) {
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
                        return "收货中";
                    }
                    else  if (d.orderState ===30)
                    {
                        return "收货完成";
                    }
                    else {
                        return "";
                    }
                }
        },
            {field: 'arrivalDate', sort: true, title: '到货日期'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center',  title: '操作', templet: function (d)
                {
                    if (d.orderState ===0) {
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">审核</a>' +
                            '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">取消</a>'+
                            '<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="view">查看</a>'
                            ;
                    }
                    return "<a class=\"layui-btn layui-btn-warm layui-btn-xs\" lay-event=\"view\">查看</a> ";
                }
            }
        ]];
    };

    /**
     * 点击查询按钮
     */
    PmsOrderPurchase.search = function () {
        var queryData = {};

        queryData['orderNo'] =$("#orderNo").val();
        queryData['partnerCode'] = $("#partnerCode").val();


        table.reload(PmsOrderPurchase.tableId, {
            where: queryData, page: {curr: 1}
        });
    };






    PmsOrderPurchase.onAuditItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/pmsOrderPurchase/audit", function (data) {
                Feng.success("审核成功!");
                table.reload(PmsOrderPurchase.tableId);
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("orderNo", data.orderNo);
            ajax.start();
        };
        Feng.confirm("确定审核?", operation);
    };


    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    PmsOrderPurchase.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/pmsOrderPurchase/cancel", function (data) {
                Feng.success("取消成功!");
                table.reload(PmsOrderPurchase.tableId);
            }, function (data) {
                Feng.error("取消失败!" + data.responseJSON.message + "!");
            });
            ajax.set("orderNo", data.orderNo);
            ajax.start();
        };
        Feng.confirm("是否取消?", operation);
    };


    PmsOrderPurchase.onViewDetail = function (data) {
        layer.open({
            type: 2,
            title: '查看明细',
            shadeClose: true,
            area: ['1000px', '600px'],
            content: Feng.ctxPath + '/pmsOrderPurchaseDetail/viewDetail?orderNo='+data.orderNo,
            end: function () {

            }


        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + PmsOrderPurchase.tableId,
        url: Feng.ctxPath + '/pmsOrderPurchase/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: PmsOrderPurchase.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PmsOrderPurchase.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    PmsOrderPurchase.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        PmsOrderPurchase.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PmsOrderPurchase.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
           // PmsOrderPurchase.jumpEditPage(data);
            PmsOrderPurchase.onAuditItem(data);
        } else if (layEvent === 'delete') {
            PmsOrderPurchase.onDeleteItem(data);
        }
        else if (layEvent === 'view') {
            PmsOrderPurchase.onViewDetail(data);
        }
    });
});
