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

    GoodsTable.initColumn = function () {
        return [[

            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };


    DetailTable.initColumn = function () {
        return [[

            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    var data=new Array();


    //渲染时间选择框
    laydate.render({
        elem: '#arrivalDate'
    });



    var goodsTableResult = table.render({
        elem: '#' + GoodsTable.tableId,
        page: false,
        height: "full-158",
        cellMinWidth: 100,
        cols: GoodsTable.initColumn(),
        data:[]
    });

    // 渲染表格
    var detailTableResult = table.render({
        elem: '#' + DetailTable.tableId,
        page: false,
        height: "full-158",
        cellMinWidth: 100,
        cols: DetailTable.initColumn(),
        data:data
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
            btn: ['确认选择', '关闭'],
        content: Feng.ctxPath + '/pmsOrderPurchaseDetail/selectGoods?formName='+ formName + "&formId=" + formId,
            // success: function(layero, index){
            //     console.log("1");
            //     var body = layer.getChildFrame('body', index);
            //     var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //     console.log(body.html()) //得到iframe页的body内容
            //
            // }
            // end: function () {
            //     alert(2);
            //     console.log(PmsOrderPurchaseDetailInfoDlg.data.goodsName)
            //     var body = layer.getChildFrame('body', index);
            //     var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //     console.log(body.html()) //得到iframe页的body内容
            // }
            yes : function(index,layero) {
                // alert(1111);
                // $(layero).find("input").each(function(i, v) {
                //     alert($(v).text());
                // });
                // layer.close(index);
                 var row = $(layero).find('iframe')[0].contentWindow.callbackdata();
               // layer.alert("get:"+row);
                // var row = $(layero).find("layui-layer-iframe")[0].contentWindow.callbackdata();
                //可以使用ajax请求对数据进行进一步处理
                console.log(row)
                data.push(row);
                layer.close(index);
                alert(data.length);

                table.reload("detailTable", {
                    data : data,
                })
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



});