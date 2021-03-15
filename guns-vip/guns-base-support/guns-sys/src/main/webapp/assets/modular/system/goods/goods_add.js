/**
 * 添加或者修改页面
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
























































    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/goods/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/goods'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/goods'
    });

});