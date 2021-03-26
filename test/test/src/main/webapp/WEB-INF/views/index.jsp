<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理系统</title>
	<%@include file="common.jsp"%>
	<script type="text/javascript" src="/js/views/index.js"></script>
</head>
<body>
	<div id="cc" class="easyui-layout" fit="true" style="width:600px;height:400px;">   
	    <div data-options="region:'north'" style="height:100px;background:url('/img/banner-pic.gif') no-repeat;background-size:cover; ">
	    	<h1>小码哥客户关系管理系统</h1>
	    </div>   
	    <div data-options="region:'west'" style="width:150px;">
	    	<!-- 手风琴+菜单 -->
	    	<div class="easyui-accordion" fit="true">
	    		<div title="菜单">
	    			<!-- 使用树组件来定义菜单 -->
	    			<ul id="menuTree"></ul>
	    		</div>
	    		<div title="帮助"></div>
	    		<div title="简介"></div>
	    	</div>
	    </div>   
	    <div data-options="region:'center'" style="padding:5px;background:#eee;">
	    	<!-- 正文内容 -->
	    	<div id="myTabs" class="easyui-tabs" fit="true">
	    		<div title="欢迎页" closable="true">
	    			<center><h1>欢迎登陆系统</h1></center>
	    		</div>
	    	</div>
	    </div>   
	    <div data-options="region:'south'" style="height:30px;background:url('banner-pic.gif') no-repeat;background-size:cover; ">
	    	<center>版权信息</center>
	    </div>   
	</div> 
</body>
</html>