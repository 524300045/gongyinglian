/**
 * 添加或者修改页面
 */
var WarehouseInfoInfoDlg = {
    data: {
        id: "",
        warehouseCode: "",
        warehouseName: "",
        status: "",
        address: "",
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
        var ajax = new $ax(Feng.ctxPath + "/warehouseInfo/addItem", function (data) {
            Feng.success("添加成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/warehouseInfo'
    });

});