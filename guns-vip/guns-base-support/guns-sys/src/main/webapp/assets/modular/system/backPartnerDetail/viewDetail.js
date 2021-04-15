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
            {field: 'backOrderNo', sort: true, title: '单号'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'planNum', sort: true, title: '下单量'},
            {field: 'realityNum', sort: true, title: '出库量'}
        ]];
    };


var orderNo=Feng.getUrlParam("orderNo");
console.log(orderNo)


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + DetailGoods.tableId,
        url: Feng.ctxPath + '/backPartnerDetail/list?orderNo='+orderNo,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: DetailGoods.initColumn()
    });





});

