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
	<title>分配市场</title>
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
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input id" lay-verify="" placeholder="" value="0">
			</div>
		</div>
		<div class="layui-form-item" style="display:none">
			<label class="layui-form-label">zjUid</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input zjUid" lay-verify="" placeholder="" value="0">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">市场名称</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input layui-disabled zjName" lay-verify="required" placeholder="请输入市场名称">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">父级推荐码</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input pAccount" lay-verify="required" placeholder="请输入父级推荐码">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">本级推荐码</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input uAccount" lay-verify="required" placeholder="请输入本级推荐码">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">市场分布</label>
			<div class="layui-input-inline" >
				<select name="depart" lay-verify="required" id="depart" lay-search  lay-filter="zjFilter">
					<%--<option value="0">顶层</option>--%>
					<option value="1">市场A</option>
					<option value="2">市场B</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addzjzlpw">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript" src="zjzlpwAdd.js"></script>
</body>
</html>