layui.use(['table', 'admin', 'ax', 'func','laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;
    /**
     * 销售订单明细管理
     */
    var SaleOrderDetail = {
        tableId: "detailTable"
    };

    //渲染时间选择框
    laydate.render({
        elem: '#deliveryDate'
    });



    var rowArr=new Array();
    /**
     * 初始化表格的列
     */
    SaleOrderDetail.initColumn = function () {
        return [[

            {field: 'skuCode', sort: false, title: '商品编码'},
            {field: 'goodsName', sort: false, title: '商品名称'},
            {field: 'goodsModel', sort: false, title: '规格'},
            {field: 'unitName', sort: false, title: '单位'},
            {field: 'planNum', sort: false, title: '数量',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {field: 'taxPrice', sort: false, title: '单价',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {field: 'taxRate', sort: false, title: '税率'},

            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SaleOrderDetail.search = function () {
        var queryData = {};


        table.reload(SaleOrderDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    $('#btnAdd').click(function () {

        layer.open({
            type: 2,
            title: '选择商品',
            shadeClose: true,
            closeBtn:2,
            area: ['1000px', '600px'],
            btn: ['确认选择', '关闭'],
            content: Feng.ctxPath + '/saleOrderDetail/addGoods',

            yes : function(index,layero) {


                var row = window["layui-layer-iframe" + index].callbackdata();
                if (row==undefined)
                {
                    layer.alert("请选择商品!");
                    return;
                }
               // layer.alert("get:"+row);

              //  var row = $(layero).find('iframe')[0].contentWindow.callbackdata();

              //  console.log(row)

               //  alert(row);

                if (rowArr.length>0)
                {
                    var  isexist=0;

                    for (var i = 0; i < rowArr.length; i++) {
                        if (rowArr[i].skuCode == row.skuCode) {
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
                        objDetail.skuCode=row.skuCode;
                        objDetail.goodsName=row.goodsName;
                        objDetail.goodsModel=row.goodsModel;
                        objDetail.unitName=row.unitName;
                        objDetail.planNum="";
                        objDetail.taxRate=row.taxRate
                        objDetail.taxPrice=row.price;
                        rowArr.push(objDetail);
                    }
                }
                else
                {
                    var objDetail=new Object();
                    objDetail.skuCode=row.skuCode;
                    objDetail.goodsName=row.goodsName;
                    objDetail.goodsModel=row.goodsModel;
                    objDetail.unitName=row.unitName;
                    objDetail.planNum="";
                    objDetail.taxRate=row.taxRate
                    objDetail.taxPrice=row.price;
                    rowArr.push(objDetail);
                }

                layer.close(index);
                table.reload("detailTable", {
                    data : rowArr,
                })


            }
        });
    });



    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    SaleOrderDetail.onDeleteItem = function (data) {
        var m=0;
        for (var i = 0; i < rowArr.length; i++) {
            if (rowArr[i].skuCode == data.skuCode)
            {
                m=i;
            }
        }

        rowArr.splice(m, 1);

        table.reload("detailTable", {
            data : rowArr,
        })
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SaleOrderDetail.tableId,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SaleOrderDetail.initColumn(),
        data:[]
    });




    // 工具条点击事件
    table.on('tool(' + SaleOrderDetail.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            SaleOrderDetail.onDeleteItem(data);
        }
    });

    table.on('edit(' + SaleOrderDetail.tableId + ')', function(obj){
        console.log(obj);

        if (obj.field =="planNum"){

            var value = obj.value;
            console.log(value);
            if (value==""||isNaN(value))
            {
                console.log("非数字");
                obj.data.planNum="";
                obj.update(obj.data);
                layer.msg("采购数量只能输入数字!");
                table.reload("detailTable");
            }
            else
            {
                for (var i=0;i<rowArr.length;i++)
                {
                    if (obj.data.skuCode==rowArr[i].skuCode)
                    {
                        rowArr[i].planNum=value;
                    }
                }
            }
        }
        if (obj.field =="taxPrice")
        {
            var value = obj.value;
            console.log(value);
            if (value==""||isNaN(value))
            {
                console.log("非数字");
                obj.data.planNum="";
                obj.update(obj.data);
                layer.msg("价格只能输入数字!");
                table.reload("detailTable", {
                    data : rowArr,
                })
            }
            else
            {
                for (var i=0;i<rowArr.length;i++)
                {
                    if (obj.data.skuCode==rowArr[i].skuCode)
                    {
                        rowArr[i].taxPrice=value;
                    }
                }
            }
        }
    })
});
