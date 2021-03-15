/**
 * 添加或者修改页面
 */
var CategoryInfoDlg = {
    data: {
        pid: "",
        pName: ""

    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;




    $('#pName').click(function () {
        var formName = encodeURIComponent("parent.CategoryInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.CategoryInfoDlg.data.pid");
        var treeUrl = encodeURIComponent("/category/tree");

        layer.open({
            type: 2,
            title: '父级分类',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#parentCode").val(CategoryInfoDlg.data.pid);
                $("#pName").val(CategoryInfoDlg.data.pName);
            }
        });
    });


















    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/category/addItem", function (data) {
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
        window.location.href = Feng.ctxPath + '/category'
    });

});