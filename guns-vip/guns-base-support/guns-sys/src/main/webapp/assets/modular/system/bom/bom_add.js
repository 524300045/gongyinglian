/**
 * 添加或者修改页面
 */
var BackPartnerDetailInfoDlg = {
    data: {
        id: "",
        backOrderNo: "",
        skuCode: "",
        goodsName: "",
        goodsModel: "",
        unitName: "",
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

layui.use(['form', 'admin','table', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var table = layui.table;
    var laydate = layui.laydate;

    var rowArr=new Array();

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
            {field: 'goodsName', sort: true, title: '商品名称', templet: function (d) {

                   return d.goodsName+"("+d.skuCode+")"
                }},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'proportion',title: '数量', minWidth:100, align:"left",edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;',event:"dataCol"},
            {field: 'childGoodsName', sort: true, title: '组件名称', templet: function (d) {
                    return d.childGoodsName+"("+d.childSkuCode+")"
                }},
            {field: 'childUnitName', sort: true, title: '单位'},
            {align: 'center', toolbar: '#detailTableBar', title: '操作'}
        ]];
    };

    var data=new Array();

    table.on('row(goodsTable)', function(obj){
        var data = obj.data;
        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        var queryData = {};
        queryData['skuCode'] =data.skuCode;
        table.reload(DetailTable.tableId, {
            where: queryData, page: {curr: 1},
            url: Feng.ctxPath + '/bom/childBomList'
        });


    });


    /**
     * 点击查询按钮
     */
    GoodsTable.search = function () {
        var queryData = {};
        queryData['goodsName'] = $("#goodsName").val();
        table.reload(GoodsTable.tableId, {
            where: queryData, page: {curr: 1},
            url: Feng.ctxPath + '/goods/list'
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






    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    GoodsTable.onAddGoods = function (data) {
        console.log(data);

        var skuCode=data.skuCode;
        console.log(skuCode);
        layer.open({
            type: 2,
            title: '选择商品',
            shadeClose: true,
            closeBtn:2,
            area: ['1000px', '600px'],
            btn: ['确认选择', '关闭'],
            content: Feng.ctxPath + '/bom/addGoods',

            yes : function(index,layero) {


                var row = window["layui-layer-iframe" + index].callbackdata();
                if (row==undefined)
                {
                    layer.alert("请选择商品!");
                    return;
                }

                //插入数据
                var ajax = new $ax(Feng.ctxPath + "/bom/addItem", function (data) {

                    if (data.code==200)
                    {
                        Feng.success("操作成功！");

                        var queryData = {};
                        queryData['skuCode'] =skuCode;
                        table.reload(DetailTable.tableId, {
                            where: queryData, page: {curr: 1},
                            url: Feng.ctxPath + '/bom/childBomList'
                        });

                        layer.close(index);
                    }
                    else
                    {
                        Feng.error("操作失败！" + data.message)
                    }

                }, function (data) {
                    Feng.error("操作失败！" + data.responseJSON.message)
                });
                ajax.set("skuCode",data.skuCode);
                ajax.set("goodsName",data.goodsName);
                ajax.set("childSkuCode",row.skuCode);
                ajax.set("childGoodsName",row.goodsName);
                ajax.set("proportion",1);
                ajax.start();

            }
        });


    };



    DetailTable.onDelete = function (data) {
        console.log(data);
        var skuCode=data.skuCode;
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/bom/delete", function (data) {

                if (data.code == 200) {
                    Feng.success("操作成功！");
                    var queryData = {};
                    queryData['skuCode'] = skuCode;
                    table.reload(DetailTable.tableId, {
                        where: queryData, page: {curr: 1},
                        url: Feng.ctxPath + '/bom/childBomList'
                    });
                } else {
                    Feng.error("操作失败！" + data.message)
                }

            }, function (data) {
                Feng.error("操作失败！" + data.responseJSON.message)
            });
            ajax.set("id", data.id);
            ajax.start();
        }
        Feng.confirm("确定删除?", operation);
    };


    DetailTable.onUpdate = function (data) {
        console.log(data);
        var skuCode=data.skuCode;
        var ajax = new $ax(Feng.ctxPath + "/bom/editItem", function (data) {

            if (data.code==200)
            {
                Feng.success("操作成功！");

            }
            else
            {
                Feng.error("操作失败！" + data.message)
            }

        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.set("id",data.id);
        ajax.set("proportion",data.proportion);
        ajax.start();
    };

    //表单提交事件


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
        if (layEvent === 'edit') {
            DetailTable.onUpdate(data);
        }
    });

    table.on('edit(' + DetailTable.tableId + ')', function(obj){
        console.log(obj);
        //console.log(obj.event)
        if (obj.field =="proportion"){

            var value = obj.value;
            console.log(value);
            if (value==""||isNaN(value))
            {
                console.log("非数字");
                obj.data.proportion="";
                obj.update(obj.data);

                table.reload("detailTable");

                layer.msg("数量只能输入数字!");
            }
            else
            {

            }
        }

    })




});