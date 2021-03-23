/**
 * 添加或者修改页面
 */
var PmsOrderPurchaseDetailInfoDlg = {
    data: {
        id: "",
        orderNo: "",
        skuCode: "",
        goodsName: "",
        goodsModel: "",
        unitName: "",
        isFresh: "",
        planNum: "",
        realityNum: "",
        taxRate: "",
        taxPrice: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        yn: ""
    }
};

layui.use(['form', 'admin','table','ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var table = layui.table;

    var GoodsTable = {
        tableId: "goodsTable"
    };

    var DetailTable = {
        tableId: "detailTable"
    };

    /**
     * 初始化表格的列
     */
    GoodsTable.initColumn = function () {
        return [[

            {field: 'skuCode', sort: true, title: '仓库编码'},
            {field: 'goodsName', sort: true, title: '仓库名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    DetailTable.initColumn = function () {
        return [[

            {field: 'skuCode', sort: true, title: '仓库编码'},
            {field: 'goodsName', sort: true, title: '仓库名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    //渲染时间选择框
    laydate.render({
        elem: '#arrivalDate'
    });

// 渲染表格
    var goodsTableResult = table.render({
        elem: '#' + GoodsTable.tableId,
        url: Feng.ctxPath + '/pmsOrderPurchaseDetail/list',
        page: false,
        height: "full-158",
        cellMinWidth: 100,
        cols: GoodsTable.initColumn()
    });

    // 渲染表格
    var detailTableResult = table.render({
        elem: '#' + DetailTable.tableId,
        page: false,
        height: "full-158",
        cellMinWidth: 100,
        cols: DetailTable.initColumn()
    });


    $('#btnGoods').click(function () {
        var formName = encodeURIComponent("parent.PmsOrderPurchaseDetailInfoDlg.data.goodsName");
        var formId = encodeURIComponent("parent.PmsOrderPurchaseDetailInfoDlg.data.twoCategoryCode");

        layer.open({
            type: 2,
            title: '选择商品',
            shadeClose: true,
            closeBtn:2,
            area: ['1000px', '600px'],
            content: Feng.ctxPath + '/pmsOrderPurchaseDetail/selectGoods?formName='+ formName + "&formId=" + formId,
            // success: function(layero, index){
            //     console.log("1");
            //     var body = layer.getChildFrame('body', index);
            //     var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //     console.log(body.html()) //得到iframe页的body内容
            //
            // }
            end: function () {
                console.log(PmsOrderPurchaseDetailInfoDlg.data.goodsName)
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                console.log(body.html()) //得到iframe页的body内容
            }
        });
    });


    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/pmsOrderPurchaseDetail/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/pmsOrderPurchaseDetail'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/pmsOrderPurchaseDetail'
    });

});