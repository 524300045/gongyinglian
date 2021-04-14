layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;
    /**
     * 采购订单明细表管理
     */
    var CustomerBackDetailTable = {
        tableId: "customerBackDetailTable"
    };

    /**
     * 初始化表格的列
     */
    CustomerBackDetailTable.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'customerBackOrderNo', sort: true, title: '客退单号'},
            {field: 'receiverName', sort: true, title: '客户名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'planNum', sort: true, title: '计划量'},
            {field: 'realityNum', sort: true, title: '已入库'},
            {field: 'num', sort: true, title: '入库数量',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CustomerBackDetailTable.search = function () {
        var queryData = {};


        table.reload(CustomerBackDetailTable.tableId, {
            where: queryData, page: {curr: 1}
        });
    };







    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CustomerBackDetailTable.tableId,
        url: Feng.ctxPath + '/customerBackOrderDetail/detailInboundPageList',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CustomerBackDetailTable.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CustomerBackDetailTable.search();
    });

    CustomerBackDetailTable.onEditItem = function (data) {

        console.log(data);
        var value=data.num;

        if (value==""||isNaN(value))
        {
            layer.msg("入库数量只能输入数字!");
             return;
        }

        //表单提交事件

            var ajax = new $ax(Feng.ctxPath + "/customerBackOrderDetail/inbound", function (data) {
                if (data.code!=200)
                {
                    Feng.success(data.message);
                    return false;
                }
                else
                {
                    Feng.success("入库成功！");
                    table.reload(CustomerBackDetailTable.tableId);
                }


            }, function (data) {
                Feng.error("更新失败！" + data.responseJSON.message)
            });
            ajax.set("id",data.id);
            ajax.set("num",value);
            ajax.start();

    };


    // 工具条点击事件
    table.on('tool(' + CustomerBackDetailTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CustomerBackDetailTable.onEditItem(data);
        }
    });
});
