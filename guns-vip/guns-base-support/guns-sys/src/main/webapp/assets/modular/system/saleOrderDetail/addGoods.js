
var selectData;
layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 商品表管理
     */
    var SelectGoods = {
        tableId: "goodsTable"
    };


    /**
     * 初始化表格的列
     */
    SelectGoods.initColumn = function () {
        return [[
            {type:'radio'},
            {field: 'id', hide: true, title: ''},
            {field: 'skuCode', sort: true, title: '编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {
                field: 'categoryName', align: "center", title: '分类', templet: function (d) {

                        return d.categoryName+"--"+d.twoCategoryName;

                }
            },

            {field: 'goodsModel', sort: true, title: '规格型号'},
            {field: 'unitName', sort: true, title: '单位名称'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'taxRate', sort: true, title: '税率'},
           // {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SelectGoods.search = function () {
        var queryData = {};


        table.reload(Goods.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    table.on('row(goodsTable)',function(obj){
        var data = obj.data;
        selectData = data;
        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        //选中radio样式
        obj.tr.find('i[class="layui-anim layui-icon"]').trigger("click");
    });


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SelectGoods.tableId,
        url: Feng.ctxPath + '/saleOrderDetail/goodslist',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SelectGoods.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Goods.search();
    });

    SelectGoods.onEditCategory = function (data) {
        console.log(data);
        var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };



});

var callbackdata = function () {
    console.log(selectData);
    return selectData;
}



