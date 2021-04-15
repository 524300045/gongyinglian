layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 库存管理
     */
    var WareStock = {
        tableId: "wareStockTable"
    };

    /**
     * 初始化表格的列
     */
    WareStock.initColumn = function () {
        return [[

            {field: 'warehouseCode', sort: true, title: '仓库编码'},
            {field: 'warehouseName', sort: true, title: '仓库名称'},
            {field: 'skuCode', sort: true, title: '商品编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'goodsModel', sort: true, title: '规格'},
            {field: 'unitName', sort: true, title: '单位'},
            {field: 'realStock', sort: true, title: '库存'},
            {field: 'quantity', sort: true, title: '当前库存',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}

        ]];
    };

    /**
     * 点击查询按钮
     */
    WareStock.search = function () {
        var queryData = {};


        table.reload(WareStock.tableId, {
            where: queryData, page: {curr: 1}
        });
    };



    /**
     * 导出excel按钮
     */
    WareStock.exportExcel = function () {
        var checkRows = table.checkStatus(WareStock.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WareStock.tableId,
        url: Feng.ctxPath + '/wareStock/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WareStock.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WareStock.search();
    });



    // 导出excel
    $('#btnExp').click(function () {
        WareStock.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WareStock.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WareStock.onEditItem(data);
        }
    });


    WareStock.onEditItem = function (data) {

        console.log(data);
        var value=data.quantity;

        if (value==""||isNaN(value))
        {
            layer.msg("库存数量只能输入数字!");
            return;
        }

        //表单提交事件

        var ajax = new $ax(Feng.ctxPath + "/wareStock/updateStock", function (data) {
            if (data.code!=200)
            {
                Feng.success(data.message);
                return false;
            }
            else
            {
                Feng.success("保存成功！");
                table.reload(WareStock.tableId);
            }


        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set("id",data.id);
        ajax.set("realStock",value);
        ajax.start();





    };
});
