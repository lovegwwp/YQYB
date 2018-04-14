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
		<div class="layui-form-item">
			<label class="layui-form-label">充值用户</label>
			<div class="layui-input-inline" >
				<%--<input type="text" class="layui-input roleId" lay-verify="required" placeholder="请选择权限类别">--%>
				<select name="uId" lay-verify="required" id="uId" lay-search  lay-filter="userFilter">
					<option value=""></option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">充值金额</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input payAmount" lay-verify="required|number" placeholder="请输入充值金额">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addhhrcwtj">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript" src="hhrcwtjAdd.js"></script>
</body>
</html>