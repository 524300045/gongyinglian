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
            {align: 'center', toolbar: '#goodsTableBar', title: '操作'}
        ]];
    };


    DetailTable.initColumn = function () {
        return [[
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'planNum',title: '采购数量', minWidth:100, align:"left",edit: 'number',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {field: 'taxPrice',title: '价格', minWidth:100, align:"left",edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {field: 'unitName', sort: true, title: '单位'},
            {align: 'center', toolbar: '#detailTableBar', title: '操作'}
        ]];
    };

    var data=new Array();
    var rowData=new Array();

    //渲染时间选择框
    laydate.render({
        elem: '#arrivalDate'
    });


    /**
     * 点击查询按钮
     */
    GoodsTable.search = function () {
        var queryData = {};

        if ($("#partnerCode").val()=="")
        {
            Feng.error("请选择供应商");
            return;
        }
        queryData['partnerCode'] = $("#partnerCode").val();
        queryData['goodsName'] = $("#goodsName").val();
        table.reload(GoodsTable.tableId, {
            where: queryData, page: {curr: 1},
            url: Feng.ctxPath + '/pmsOrderPurchaseDetail/partnergoodslist'
        });
    };

    // 搜索按钮点击事件
    $('#btnQuery').click(function () {
        GoodsTable.search();
    });


    var goodsTableResult = table.render({
        elem: '#' + GoodsTable.tableId,
        page: true,
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


    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    GoodsTable.onAddGoods = function (data) {
        console.log(data);

        if (rowData.length>0)
        {
            var  isexist=0;

            for (var i = 0; i < rowData.length; i++) {
                if (rowData[i].skuCode == data.skuCode) {
                    isexist=1;
                }
            }
            if (isexist==1)
            {
                layer.msg("请勿重复添加！", {icon : 0,time : 1500});
            }
            else
            {
                var objDetail=new Object();
                objDetail.skuCode=data.skuCode;
                objDetail.goodsName=data.goodsName;
                objDetail.goodsModel=data.goodsModel;
                objDetail.unitName=data.unitName;
                objDetail.planNum="";
                objDetail.taxPrice="";
                rowData.push(objDetail);
            }
        }
        else
        {
            var objDetail=new Object();
            objDetail.skuCode=data.skuCode;
            objDetail.goodsName=data.goodsName;
            objDetail.goodsModel=data.goodsModel;
            objDetail.unitName=data.unitName;
            objDetail.planNum="";
            objDetail.taxPrice="";
            rowData.push(objDetail);
        }


        table.reload("detailTable", {
            data : rowData,
        })
    };



    DetailTable.onDelete = function (data) {
        console.log(data);
        var m=0;
        for (var i = 0; i < rowData.length; i++) {
            if (rowData[i].skuCode == data.skuCode)
            {
                m=i;
            }
        }

        rowData.splice(m, 1);

        table.reload("detailTable", {
            data : rowData,
        })
    };

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {

        var  tableDatas=table.cache[DetailTable.tableId];
        console.log(tableDatas);
        if (tableDatas.length==0)
        {
            Feng.error("请选择商品");
            return;
        }

        if ($("#partnerCode").val()=="")
        {
            Feng.error("请选择供应商");
            return;
        }
        if ($("#warehouseCode").val()=="")
        {
            Feng.error("请选择仓库");
            return;
        }

        if ($("#arrivalDate").val()=="")
        {
            Feng.error("请选择到货日期");
            return;
        }

        var msg='';
        for (var i=0;i<tableDatas.length;i++)
        {
            if (tableDatas[i].planNum==""||tableDatas[i].taxPrice=="")
            {
                msg=msg+tableDatas[i].skuCode+"未填写采购数量和价格,<br/>";
            }
        }
        if (msg!="")
        {
            Feng.error(msg);
            return;
        }

        var ajax = new $ax(Feng.ctxPath + "/pmsOrderPurchaseDetail/addItemDetail", function (data) {
            if (data.code==200)
            {
                Feng.success("添加成功！");
            }
            else
            {
                Feng.error("添加失败！" + data.message)
            }
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });

        ajax.set("partnerCode",$("#partnerCode").val());
        ajax.set("partnerName",$("#partnerCode").find("option:selected").text());
        ajax.set("warehouseCode",$("#warehouseCode").val());
        ajax.set("warehouseName",$("#warehouseCode").find("option:selected").text());
        ajax.set("arrivalDate",$("#arrivalDate").val());
        ajax.set("detailStr",JSON.stringify(tableDatas));
        ajax.set("remark",$("#remark").val());
        ajax.start();

        return false;
    });

    table.on('tool(' + GoodsTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            GoodsTable.onAddGoods(data);
        }
    });

    table.on('tool(' + DetailTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'delete') {
            DetailTable.onDelete(data);
        }
    });

    table.on('tool(' + DetailTable.tableId + ')', function(obj){
        console.log(obj);
        if (obj.event =="dataCol"){
            //dataCol=obj;
            // $("table input").attr("type","number");
            // $("table input").attr("step","0.1");
            console.log(obj);
        }
    })


});