/**
 * 添加或者修改页面
 */
var PartnerGoodsInfoDlg = {
    data: {
        id: "",
        partnerCode: "",
        skuCode: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        yn: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','table','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var table = layui.table;

    var Goods = {
        tableId: "goodsTable"
    };
    /**
     * 初始化表格的列
     */
    Goods.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'skuCode', sort: true, title: '编码'},
            {field: 'goodsName', sort: true, title: '商品名称'},

            {
                field: 'categoryName', align: "center", title: '分类', templet: function (d) {

                    return d.categoryName+"--"+d.twoCategoryName;

                }
            },
            {
                field: 'status', align: "center", title: '状态', templet: function (d) {
                    if (d.status ===1) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            },
            {field: 'goodsModel', sort: true, title: '规格型号'},
            {field: 'goodsBrand', sort: true, title: '品牌'},
            {field: 'weight', sort: true, title: '重量'},
            {field: 'unitName', sort: true, title: '单位名称'},
            {
                field: 'isFresh', align: "center", title: '是否生鲜', templet: function (d) {
                    if (d.isFresh ===1) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            },
            {
                field: 'weighed', align: "center", title: '是否称重', templet: function (d) {
                    if (d.weighed ===1) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            },
            {
                field: 'productType', align: "center", title: '商品类型', templet: function (d) {
                    if (d.productType ===0) {
                        return "成品";
                    }
                    else if (d.productType ===1)
                    {
                        return "半成品";
                    }
                    else {
                        return "原料";
                    }
                }
            },
            {field: 'price', sort: true, title: '价格'},
            {field: 'taxRate', sort: true, title: '税率'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Goods.search = function () {
        var queryData = {};


        table.reload(Goods.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Goods.tableId,
        url: Feng.ctxPath + '/goods/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Goods.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Goods.search();
    });

    /**
     * 点击查询按钮
     */
    Goods.search = function () {
        var queryData = {};


        table.reload(Goods.tableId, {
            where: queryData, page: {curr: 1}
        });
    };



    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var checkRows = table.checkStatus(Goods.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择商品");
            return false;
        }
        else
        {
            let  partnerCode=$("#partnerCode").val();
           // alert(partnerCode);
            if (partnerCode=='')
            {
                Feng.error("请选择供应商");
                return false;
            }

            console.log(checkRows.data);
            let skuCodes='';

            $.each(checkRows.data, function (index, itemobj) {
                //alert(index + "....." + itemobj);
                skuCodes+=itemobj.skuCode+",";
            })

            // $("#skuCode").val(skuCodes);
            // alert( $("#skuCode").val());
            // var ajax = new $ax(Feng.ctxPath + "/partnerGoods/addItem", function (data) {
            //     Feng.success("添加成功！");
            //     window.location.href = Feng.ctxPath + '/partnerGoods'
            // }, function (data) {
            //     Feng.error("添加失败！" + data.responseJSON.message)
            // });
            // ajax.set(data.field);
            // ajax.start();

            var requestBody = new Map();
            requestBody.set("partnerCode", $("#partnerCode").val());
            requestBody.set("skuCode", skuCodes);

           // alert(JSON.stringify(requestBody));

            layui.jquery.ajax({
                url: Feng.ctxPath + "/partnerGoods/addItem",
                type: 'post',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: _mapToJson(requestBody),
                success: function (data) {

                    Feng.success("保存成功");
                    window.location.href = Feng.ctxPath + '/partnerGoods'
                },
                error: function (data) {
                    Feng.error("保存失败");
                }
            });

            return false;
        }




    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/partnerGoods'
    });


    function _strMapToObj(strMap) {
        let obj = Object.create(null);
        for (let [k, v] of strMap) {
            obj[k] = v;
        }
        return obj;
    }

    function _mapToJson(map) {
        return JSON.stringify(_strMapToObj(map));
    }
});