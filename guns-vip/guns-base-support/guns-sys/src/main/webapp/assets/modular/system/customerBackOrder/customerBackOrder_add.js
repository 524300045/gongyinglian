layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 销售订单表管理
     */
    var SaleOrder = {
        tableId: "customerBackOrderTable"
    };

    /**
     * 初始化表格的列
     */
    SaleOrder.initColumn = function () {
        return [[

            {field: 'id', hide: true, title: ''},
            {align: 'center',width: 200,  title: '操作'
                , templet: function (d)
                {
                    return "<a class=\"layui-btn layui-btn-warm layui-btn-xs\" lay-event=\"view\">客退</a> ";
                }},
            {field: 'orderNo', sort: true, title: '订单'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
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
                    else  if (d.orderState ===30)
                    {
                        return "已发运";
                    }
                    else
                    {
                        return  "";
                    }
                }},
            {field: 'totalAmount', sort: true, title: '金额'},
            {field: 'receiverName', sort: true, title: '收货人'},
            {field: 'receiverPhone', sort: true, title: '电话'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'deliveryDate', sort: true, title: '发货日期'},
            {field: 'deliveryTime', sort: true, title: '发运时间'},
            {field: 'deliveryUser', sort: true, title: '发运人'},
            {field: 'remark', sort: true, title: '备注'}

        ]];
    };

    /**
     * 点击查询按钮
     */
    SaleOrder.search = function () {
        var queryData = {};
        queryData['orderNo'] = $("#orderNo").val();
        queryData['receiverName'] =$("#receiverName").val();

        table.reload(SaleOrder.tableId, {
            where: queryData, page: {curr: 1}
        });
    };




    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SaleOrder.tableId,
        url: Feng.ctxPath + '/saleOrder/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SaleOrder.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SaleOrder.search();
    });


    SaleOrder.onViewDetail = function (data) {
        layer.open({
            type: 2,
            title: '订单明细',
            shadeClose: true,
            area: ['1000px', '600px'],
            content: Feng.ctxPath + '/customerBackOrderDetail/saleOrderDetail?orderNo='+data.orderNo,
            end: function () {

            }


        });
    };




    // 工具条点击事件
    table.on('tool(' + SaleOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

       if (layEvent === 'view') {
            SaleOrder.onViewDetail(data);
        }
    });
});
