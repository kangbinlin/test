<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myFn" uri="http://www.520it.com/crm/permission" %>
<html>
<head>
    <title>员工管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
<table id="emp_datagrid"></table>
<!-- 数据表格的顶部按钮-->
<div id="emp_datagrid_tb">
    <div>
        <c:if test="${myFn:checkPermission('哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈')}">
            <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        </c:if>
            <a id="emp_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <c:if test="${myFn:checkPermission('com._520it.crm.web.controller.EmployeeController:delete')}">
            <a id="emp_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">离职</a>
        </c:if>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        关键字查询：<input name="keyword"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="find">搜索</a>
    </div>
</div>
<!-- 对话框 -->
<div id="emp_dialog">
    <form id="emp_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id">
            <tr>
                <td>账号</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text" name="realname"/></td>
            </tr>
            <tr>
                <td>联系方式</td>
                <td><input type="text" name="tel"/></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>部门</td>
                <td>
                   <input type="text" name="dept.id" class="easyui-combobox"
                          data-options="valueField:'id', textField:'name', url:'/department_queryForEmployee'">
                </td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td><input type="text" name="inputtime" class="easyui-datebox"/></td>
            </tr>
            <tr>
                <td>角色</td>
                <td>
                    <input id="emp_roles" type="text" class="easyui-combobox"
                           data-options="valueField:'id', textField:'name', url:'/role_queryForEmployee', multiple:true">
                </td>
            </tr>
        </table>
    </form>
</div>
<!--对话框底部按钮-->
<div id="emp_diglog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
