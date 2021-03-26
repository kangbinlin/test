<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
<table id="role_datagrid"></table>
<!-- 数据表格的顶部按钮-->
<div id="role_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="role_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="role_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        关键字查询：<input name="keyword"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="find">搜索</a>
    </div>
</div>
<!-- 对话框 -->
<div id="role_dialog">
    <form id="role_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id">
            <tr>
                <td>角色名称<input type="text" name="name"/></td>
                <td>角色编号<input type="text" name="sn"/></td>
            </tr>
            <tr>
                <td><table id="allPermissions"></table></td>
                <td><table id="selfPermissions"></table></td>
            </tr>
        </table>
    </form>
</div>
<!--对话框底部按钮-->
<div id="role_diglog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
