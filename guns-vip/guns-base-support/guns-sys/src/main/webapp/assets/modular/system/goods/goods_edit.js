/**
 * 详情对话框
 */
var GoodsInfoDlg = {
    data: {
        id: "",
        skuCode: "",
        goodsName: "",
        categoryCode: "",
        categoryName: "",
        twoCategoryCode: "",
        twoCategoryName: "",
        status: "",
        goodsModel: "",
        goodsBrand: "",
        weight: "",
        unitName: "",
        unitCode: "",
        isFresh: "",
        weighed: "",
        productType: "",
        price: "",
        taxRate: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        yn: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //选择分类
    $('#twoCategoryName').click(function () {
        var formName = encodeURIComponent("parent.GoodsInfoDlg.data.twoCategoryName");
        var formId = encodeURIComponent("parent.GoodsInfoDlg.data.twoCategoryCode");
        var treeUrl = encodeURIComponent("/category/tree");

        layer.open({
            type: 2,
            title: '部门选择',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                console.log(GoodsInfoDlg.data.categoryName)
                $("#twoCategoryCode").val(GoodsInfoDlg.data.twoCategoryCode);
                $("#twoCategoryName").val(GoodsInfoDlg.data.twoCategoryName);
            }
        });
    });

    var ajax = new $ax(Feng.ctxPath + "/dict/listDictsByCode", function (data) {

        for (var i = 0; i < data.data.length; i++) {
            var name = data.data[i].name;
            var code = data.data[i].code;
            console.log(name+"--"+code)
        }
        $.each(data.data, function (index, value) {
            // console.log(value.department_id);
            $('#unitCode').append(new Option(value.name,value.code));// 下拉菜单里添加元素
        });
        form.render("select");

    }, function (data) {
    });
    ajax.set("dictTypeCode", "unit");
    ajax.start();


    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/goods/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('goodsForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/goods/editItem", function (data) {

            if (data.code!="200")
            {
                Feng.error("更新失败！" + data.message)
            }
            else
            {

                Feng.success("更新成功！");
                admin.putTempData('formOk', true);

                //关掉对话框
                admin.closeThisDialog();
            }


        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/goods'
    });
});