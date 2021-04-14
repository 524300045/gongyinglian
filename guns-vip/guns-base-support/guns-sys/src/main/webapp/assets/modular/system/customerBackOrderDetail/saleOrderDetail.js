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
            {field: 'deliveryNum', sort: true, title: '发货量'},
            {field: 'num', sort: true, title: '客退量',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {field: 'remark', sort: true, title: '原因',edit: 'text',style: 'outline: 1px solid #e6e6e6;outline-offset: -5px;'},
            {field: 'taxRate', sort: true, title: '税率'},
            {field: 'taxPrice', sort: true, title: '单价'}
        ]];
    };


var orderNo=Feng.getUrlParam("orderNo");
console.log(orderNo)

    $('#btnAdd').click(function (){

        var rowData=new Array();

        var  tableDatas=table.cache[DetailGoods.tableId];
        var msg='';
        for (var i=0;i<tableDatas.length;i++)
        {
            var skuCode=tableDatas[i].skuCode;

            console.log(tableDatas[i].num);
            if (tableDatas[i].num!=""&&tableDatas[i].num!=undefined)
            {
                if (isNaN(tableDatas[i].num))
                {
                    msg+=skuCode+"数量只能输入数字,";
                }
                else
                {
                    tableDatas[i].planNum=tableDatas[i].num;
                    if (parseFloat(tableDatas[i].planNum)>parseFloat(tableDatas[i].deliveryNum))
                    {
                        msg+=skuCode+"客退量不能大于发运量,";
                    }
                    rowData.push(tableDatas[i]);
                }
            }
        }
        if (msg!="")
        {
            Feng.error(msg);
            return;
        }

        if (rowData.length==0)
        {
            Feng.error("没有要客退的商品");
            return;
        }
        console.log(rowData)
        var ajax = new $ax(Feng.ctxPath + "/customerBackOrder/addItemDetail", function (data) {
            if (data.code==200)
            {
                Feng.success("创建成功,请到【客退单查询】中查看！");
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
            else
            {
                Feng.error("添加失败！" + data.message)
            }
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });

        ajax.set("orderNo",orderNo);
        ajax.set("detailStr",JSON.stringify(rowData));
        ajax.start();

    });

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

