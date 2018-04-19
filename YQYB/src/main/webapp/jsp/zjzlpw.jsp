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
	<title>用户列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	<link rel="stylesheet" href="css/news.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
			<label class="layui-form-label">市场(必选)</label>
			<div class="layui-input-inline">
				<select name="scId"  id="scId" lay-search  lay-filter="scFilter">
					<option value=""></option>
				</select>
			</div>
		    <a class="layui-btn search_btn">加载市场</a>
		</div>
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
			</div>
			<a class="layui-btn like_btn">模糊查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn zjzlpwAdd_btn" style="background-color:#5FB878">添加市场</a>
		</div>
	</blockquote>
	<div class="layui-form zjzlpw_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="0">
				<col>
				<col>
				<col>
				<col>
				<col width="13%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th>用户ID</th>
					<th>用户code</th>
					<th>上层ID</th>
					<th>市场分布</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="zjzlpw_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="jsp/zjzlpw.js"></script>
</body>
</html>