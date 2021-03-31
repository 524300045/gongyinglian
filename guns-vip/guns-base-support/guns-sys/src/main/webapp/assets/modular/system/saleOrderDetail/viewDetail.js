layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 商品表管理
     */
    var DetailGoods = {
        tableId: "detailTable"
    };

    /**
     * 初始化表格的列
     */
    DetailGoods.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'orderNo', sort: true, title: '订单号'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},

            {field: 'planNum', sort: true, title: '下单量'},
            {field: 'deliveryNum', sort: true, title: '发货量'},
            {field: 'taxRate', sort: true, title: '税率'},
            {field: 'taxPrice', sort: true, title: '单价'}
        ]];
    };


var orderNo=Feng.getUrlParam("orderNo");
console.log(orderNo)


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + DetailGoods.tableId,
        url: Feng.ctxPath + '/saleOrderDetail/detailList?orderNo='+orderNo,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: DetailGoods.initColumn()
    });



});

