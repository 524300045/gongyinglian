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
            {field: 'realityNum', sort: true, title: '出库量',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},

            {align: 'center', toolbar: '#tableBar', title: '操作'}
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

    // 工具条点击事件
    table.on('tool(' + DetailGoods.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            DetailGoods.onEditItem(data);
        }
    });


    DetailGoods.onEditItem = function (data) {

        console.log(data);
        var value=data.realityNum;

        if (value==""||isNaN(value))
        {
            layer.msg("出库数量只能输入数字!");
            return;
        }

        //表单提交事件

        var ajax = new $ax(Feng.ctxPath + "/backPartnerDetail/updateOutNum", function (data) {
            if (data.code!=200)
            {
                Feng.success(data.message);
                return false;
            }
            else
            {
                Feng.success("保存成功！");
                table.reload(DetailGoods.tableId);
            }


        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set("id",data.id);
        ajax.set("realityNum",value);
        ajax.start();





    };

});

