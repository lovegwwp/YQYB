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
	<title>文字编辑</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:80%;" enctype="multipart/form-data" >
		<div class="layui-form-item" style="display:none">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input id" lay-verify="" placeholder="" value="0">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">标题</label>
			<div class="layui-input-block">
				<%--<input type="tel" class="layui-input linksUrl" lay-verify="required|url" placeholder="请输入用户名称">--%>
				<input type="text"  class="layui-input title" lay-verify="required"  placeholder="请输入标题">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">内容</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input content" lay-verify="required" placeholder="请输入内容">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">链接</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input linkUrl" lay-verify="required" placeholder="请输入链接">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">图片上传</label>
			<div class="layui-input-block">
				<input id="imgFile" type="file" name="imgFile" lay-type="file"  class="layui-upload-file" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">预览</label>
			<div class="layui-input-block">
				<img  id="linkPic" style="width:90px; height:90px" src=""/>
			</div>
		</div>
	
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addsharelink">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript" src="sharelink_edit.js"></script>
	<!--引入引入kindeditor编辑器相关文件-->
	<link rel="stylesheet" href="../kindeditor-4.1.11/themes/default/default.css"/>
	<script charset="utf-8" src="../kindeditor-4.1.11/kindeditor-all.js"></script>
	<script charset="utf-8" src="../kindeditor-4.1.11/lang/zh-CN.js"></script>
</body>
</html>