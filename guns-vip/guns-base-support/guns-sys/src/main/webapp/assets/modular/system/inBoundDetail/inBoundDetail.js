layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 入库单明细表管理
     */
    var InBoundDetail = {
        tableId: "inBoundDetailTable"
    };

    /**
     * 初始化表格的列
     */
    InBoundDetail.initColumn = function () {
        return [[


            {field: 'inboundTaskCode', sort: true, title: '入库单号'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'orderNo', sort: true, title: '采购订单'},
            {field: 'partnerName', sort: true, title: '供应商名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'realityNum', sort: true, title: '入库数量'},
            {field: 'taxPrice', sort: true, title: '单价'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'}

        ]];
    };

    /**
     * 点击查询按钮
     */
    InBoundDetail.search = function () {
        var queryData = {};
        queryData['orderNo'] =$("#orderNo").val();
        queryData['partnerCode'] = $("#partnerCode").val();
        queryData['goodsName'] =$("#goodsName").val();

        table.reload(InBoundDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };



    /**
     * 导出excel按钮
     */
    InBoundDetail.exportExcel = function () {
        var checkRows = table.checkStatus(InBoundDetail.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + InBoundDetail.tableId,
        url: Feng.ctxPath + '/inBoundDetail/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InBoundDetail.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InBoundDetail.search();
    });



    // 导出excel
    $('#btnExp').click(function () {
        InBoundDetail.exportExcel();
    });


});
