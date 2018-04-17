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
				<label class="layui-form-label">充值方式:</label>
				<select name="czId" lay-verify="required" id="czId" lay-search  lay-filter="czFilter">
					<option value=""></option>
					<option value="1">支付宝</option>
					<option value="2">微信</option>
					<option value="3">线下充值</option>
					<option value="4">借贷充值</option>
				</select>
		   </div>&nbsp;&nbsp;
			<div class="layui-input-inline">
				<input type="text" name="start_time" class="layui-input" id="start_time"
					   placeholder="开始时间">
			</div>
			<span>——</span>
			<div class="layui-input-inline">
				<input type="text" name="end_time" class="layui-input" id="end_time"
					   placeholder="结束时间">
			</div>
			<a class="layui-btn day_btn">查询</a>
			<a class="layui-btn bd_btn">报单充值</a>
			<a class="layui-btn jd_btn">借贷充值</a>

		</div>

	</blockquote>
	<div class="layui-form hhrcwtj_list">
		<table class="layui-table">
			<colgroup>
				<col width="50">
				<col width="0">
				<col>
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose1" id="allChoose1"></th>
				<th>总额</th>
			</tr>
			</thead>
			<tbody class="hhrcwtj_content"></tbody>
		</table>
	</div>
	<div id="page1"></div>
	<p>详细列表</p>
	<div class="layui-form hhrcwtjlist_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="0">
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th>用户UUID</th>
					<th>充值方式</th>
					<th>充值数额</th>
					<th>订单号</th>
					<th>充值时间</th>
					<th>经手人</th>

				</tr> 
		    </thead>
		    <tbody class="hhrcwtjlist_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="jsp/hhrcwtj.js"></script>
</body>
</html>