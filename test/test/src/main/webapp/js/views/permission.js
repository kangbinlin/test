$(function () {
    // 抽取变量：这个js文件中出现多少次${"#....}，JS引擎就会去对应的页面做多少次元素查找。现在这样集中整合后，每种选择器只做一次，后面都是直接用变量
    var permissionDatagrid, permissionDatagridEditAndDel, permissionDiglog, permissionForm;
    permissionDatagrid = $("#permission_datagrid");
    permissionDatagridEditAndDel = $("#permission_datagrid_del,#permission_datagrid_edit");
    permissionDiglog = $("#permission_dialog");
    permissionForm = $("#permission_form");

    permissionDatagrid.datagrid({
        url: "/permission_list",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#permission_datagrid_tb',
        pageList: [1, 5, 10, 20],
        // onClickRow: function (rowIndex, rowData) {
        //     if (rowData.state) {
        //         permissionDatagridEditAndDel.linkbutton("enable");
        //     } else {
        //         permissionDatagridEditAndDel.linkbutton("disable");
        //     }
        // },
        columns: [
            [
                {field: "name", title: "权限名称", width: 1, align: 'center'},
                {field: "resource", title: "权限url", width: 1, align: 'center'},
            ]
        ]
    });

    // 主页面一加载就先做好这个弹窗，只不过由于closed: true，所以一开始是隐藏的
    permissionDiglog.dialog({
        width: 300,
        height: 300,
        buttons: '#permission_diglog_bt',
        closed: true
    });


    // 统一管理方法
    var cmdObj = {

        add: function () {
            permissionDiglog.dialog("open");
            permissionDiglog.dialog("setTitle", "新增");
            permissionForm.form("clear");
        },

        save: function () {

            var idVal = $("#permission_form [name='id']").val();
            var url;

            // 如果有id，说明是编辑；否则，是保存。
            if (idVal) {
                url = "/permission_update";
            } else {
                url = "/permission_save";
            }

            // 发送异步请求
            permissionForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            // 关闭对话框
                            permissionDiglog.dialog("close");
                            // 刷新数据表格（其实就是调用最上面的那个list方法）
                            permissionDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info")
                    }
                }
            });
        },

        // 注意，这个方法其实是跳转页面（弹窗）。真正修改后的数据，还是要通过上面的save()提交到后台
        edit: function () {
            // 获取到选中的数据（一整行，也就是一个Employee对象信息）
            var rowData = permissionDatagrid.datagrid("getSelected");
            // 这里rowData不是boolean，但是只要非false都是true。感觉这样写其实不好，最好是判断是否为空啥的。
            if (rowData) {
                permissionDiglog.dialog("open");
                permissionDiglog.dialog("setTitle", "编辑");
                permissionForm.form("clear");
                permissionForm.form("load", rowData);//把rowData加载到弹窗显示
            } else {
                $.messager.alert("温馨提示", "请选中一条需要编辑的数据", "info");
            }
        },


        del: function () {
            // 获取到选中的数据
            var rowData = permissionDatagrid.datagrid("getSelected");
            if (rowData) {
                // 刷新数据表格
                $.messager.confirm("温馨提示", "您确定要删除这条数据吗？", function (yes) {
                    if (yes) {
                        $.get("/permission_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    // 刷新表格数据
                                    permissionDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json");
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要离职的员工", "info");
            }
        },

        reload: function () {
            permissionDatagrid.datagrid("reload");
        },

        cancel: function () {
            permissionDiglog.dialog("close");
        },

        find: function () {
            var keyword = $("[name='keyword']").val();
            permissionDatagrid.datagrid("load", {
                keyword: keyword
            });

        }
    };


    // 对按钮进行统一的监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });


    //允许用户通过enter键查询
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            //如果按的是enter键，调用find()
            cmdObj.find();
        }
    });

});


