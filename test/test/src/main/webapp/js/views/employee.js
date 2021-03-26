$(function () {
    // 抽取变量：这个js文件中出现多少次${"#....}，JS引擎就会去对应的页面做多少次元素查找。现在这样集中整合后，每种选择器只做一次，后面都是直接用变量
    var empDatagrid, empDatagridEditAndDel, empDiglog, empForm;
    empDatagrid = $("#emp_datagrid");
    empDatagridEditAndDel = $("#emp_datagrid_del,#emp_datagrid_edit");
    empDiglog = $("#emp_dialog");
    empForm = $("#emp_form");

    empDatagrid.datagrid({
        url: "/employee_list",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#emp_datagrid_tb',
        pageList: [1, 5, 10, 20],
        onClickRow: function (rowIndex, rowData) {
            if (rowData.state) {
                empDatagridEditAndDel.linkbutton("enable");
            } else {
                empDatagridEditAndDel.linkbutton("disable");
            }
        },
        columns: [
            [
                {field: "username", title: "用户名", width: 1, align: 'center'},
                {field: "realname", title: "真实姓名", width: 1, align: 'center'},
                {field: "tel", title: "电话", width: 1, align: 'center'},
                {field: "email", title: "邮箱", width: 1, align: 'center'},
                {field: "dept", title: "部门", width: 1, align: 'center', formatter: deptFormatter},
                {field: "inputtime", title: "入职时间", width: 1, align: 'center'},
                {field: "state", title: "状态", width: 1, align: 'center', formatter: stateFormatter},
                {field: "admin", title: "是否超级管理员", width: 1, align: 'center', formatter: adminFormatter}
            ]
        ]
    });

    // 主页面一加载就先做好这个弹窗，只不过由于closed: true，所以一开始是隐藏的
    empDiglog.dialog({
        width: 300,
        height: 300,
        buttons: '#emp_diglog_bt',
        closed: true
    });


    // 统一管理方法
    var cmdObj = {

        add: function () {
            empDiglog.dialog("open");
            empDiglog.dialog("setTitle", "新增");
            empForm.form("clear");
        },

        save: function () {

            var idVal = $("#emp_form [name='id']").val();
            var url;

            // 如果有id，说明是编辑；否则，是保存。
            if (idVal) {
                url = "/employee_update";
            } else {
                url = "/employee_save";
            }

            // 发送异步请求
            empForm.form("submit", {
                url: url,
                onSubmit: function (param) {
                    // 1.获取选择的角色ids
                    var ids = $("#emp_roles").combobox("getValues");
                    // 2.把这些id塞到参数中
                    for (var i = 0; i < ids.length; i++) {
                        param["roles["+i+"].id"] = ids[i];
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            // 关闭对话框
                            empDiglog.dialog("close");
                            // 刷新数据表格（其实就是调用最上面的那个list方法）
                            empDatagrid.datagrid("reload");
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
            var rowData = empDatagrid.datagrid("getSelected");
            // 这里rowData不是boolean，但是只要非false都是true。感觉这样写其实不好，最好是判断是否为空啥的。
            if (rowData) {
                empDiglog.dialog("open");
                empDiglog.dialog("setTitle", "编辑");
                empForm.form("clear");

                // 处理特殊属性：给rowData对象添加一个属性（Java是不能动态添加属性的)
                if (rowData.dept) {
                    rowData["dept.id"] = rowData.dept.id;
                }

                // 发动一个同步请求到后台查询员工对应的角色
                var html = $.ajax({
                    url:"/role_queryByEid?eid="+rowData.id,
                    async:false // 必须是同步请求，否则后台数据还没返回，前台就忙着设置，会是空！
                }).responseText;
                html = $.parseJSON(html);
                // 回显角色
                $("#emp_roles").combobox("setValues", html);

                empForm.form("load", rowData);//把rowData加载到弹窗显示
            } else {
                $.messager.alert("温馨提示", "请选中一条需要编辑的数据", "info");
            }
        },


        del: function () {
            // 获取到选中的数据
            var rowData = empDatagrid.datagrid("getSelected");
            if (rowData) {
                // 刷新数据表格
                $.messager.confirm("温馨提示", "您确定要删除这条数据吗？", function (yes) {
                    if (yes) {
                        $.get("/employee_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    // 刷新表格数据
                                    empDatagrid.datagrid("reload");
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
            empDatagrid.datagrid("reload");
        },

        cancel: function () {
            empDiglog.dialog("close");
        },

        find: function () {
            var keyword = $("[name='keyword']").val();
            empDatagrid.datagrid("load", {
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

function deptFormatter(value, record, index) {
    return value ? value.name : value;
}

function stateFormatter(value, record, index) {
    return value ? "<font color='green'>正常</font>" : "<font color='red'>离职</font>";
}

function adminFormatter(value, record, index) {
    return value ? "是" : "否";
}

