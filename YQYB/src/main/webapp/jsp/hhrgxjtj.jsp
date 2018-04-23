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
			<div class="layui-input-inline">
				<input type="text" name="start_time" class="layui-input" id="start_time"
					   placeholder="开始时间">
			</div>
			<span>——</span>
			<div class="layui-input-inline">
				<input type="text" name="end_time" class="layui-input" id="end_time"
					   placeholder="结束时间">
			</div>
			<a class="layui-btn day_btn">日期查询</a>
		</div>
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" name="month_time" class="layui-input" id="month_time"
					   placeholder="选择月份">
			</div>
			<a class="layui-btn month_btn">按月查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn today_btn">昨日查询</a>
			<a class="layui-btn week_btn">本周查询</a>
		</div>
	</blockquote>
	<div class="layui-form hhrgxjtj_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="0">
				<%--<col >--%>
				<col>
				<col>
				<col>
				<col>
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<%--<th>权限ID</th>--%>
					<%--<th style="text-align:left;">用户权限</th>--%>
					<th>实发总额</th>
					<th>实发股券</th>
					<th>实发电子券</th>
					<th>实发商城消费券</th>
				</tr> 
		    </thead>
		    <tbody class="hhrgxjtj_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<%--<script type="text/javascript" src="laydate/laydate.js"></script>--%>
	<script type="text/javascript" src="jsp/hhrgxjtj.js"></script>
	<%--<script type="text/javascript" src="js/fgn.js"></script>--%>
</body>
</html>