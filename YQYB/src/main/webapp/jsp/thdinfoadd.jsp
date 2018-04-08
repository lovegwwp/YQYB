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
	<title>提货点添加</title>
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
			<label class="layui-form-label">用户ID</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input id" lay-verify="" placeholder="" value="0">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">提货点账号</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input tel" lay-verify="required" placeholder="请输入提货点账号">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">联系人姓名</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input name" lay-verify="required" placeholder="请输入联系人姓名">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">联系人电话</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input telShow" lay-verify="required" placeholder="请输入联系人电话">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">提货点名称</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input thName" lay-verify="required" placeholder="请输入提货点名称">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">省份</label>
			<div class="layui-input-inline" >
				<%--<input type="text" class="layui-input roleId" lay-verify="required" placeholder="请选择权限类别">--%>
				<select name="province" lay-verify="required" id="province" lay-search  lay-filter="provinceFilter">
					<option value=""></option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">城市</label>
			<div class="layui-input-inline" >
				<%--<input type="text" class="layui-input roleId" lay-verify="required" placeholder="请选择权限类别">--%>
				<select name="city" lay-verify="required" id="city" lay-search  lay-filter="cityFilter">
					<option value=""></option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">区域</label>
			<div class="layui-input-inline" >
				<%--<input type="text" class="layui-input roleId" lay-verify="required" placeholder="请选择权限类别">--%>
				<select name="area" lay-verify="required" id="area" lay-search  lay-filter="areaFilter">
					<option value=""></option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">详细地址</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text" class="layui-input addr" lay-verify="required" placeholder="请输入详细地址">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addthdinfo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript" src="thdinfoadd.js"></script>
</body>
</html>