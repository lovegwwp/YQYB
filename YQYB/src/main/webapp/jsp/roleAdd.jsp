<%@ page session="false" language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户添加</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:80%;">
		<div class="layui-form-item" style="display:none">
			<label class="layui-form-label">角色ID</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input id" lay-verify="" placeholder="" value="0">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input roleSign" lay-verify="required" placeholder="请输入角色名称">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input description" lay-verify="required" placeholder="请输入角色描述">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">权限列表</label>
			<div class="layui-input-block">
				<div id="menuTree"></div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addroles">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<%--<script type="text/javascript" src="../js/jstree.js"></script>--%>
	<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
	<link rel="stylesheet" href="../css/dist/themes/default/style.min.css"  />
	<script type="text/javascript" src="roleAdd.js"></script>
	<%--<script type="text/javascript" src="treeAdd.js"></script>--%>
</body>
</html>