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
	<title>合伙人审核</title>
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
		    	<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
        <div class="layui-inline">
            <a class="layui-btn transer_btn" style="background-color:#5FB878">可以转账</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn noTranser_btn" style="background-color:#5FB878">禁止转账</a>
        </div>
		<div class="layui-inline">
			<div class="layui-form-mid layui-word-aux">可查询字段[合伙人账号],[真实姓名],[邮箱],[身份证号]</div>
		</div>
	</blockquote>
	<div class="layui-form hhrshinfo_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="120">
				<col width="100">
				<col>
				<col width="180">
				<col width="100">
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
			<%--	<col>--%>
				<col>
				<col>
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<%--<th>权限ID</th>--%>
					<%--<th style="text-align:left;">用户权限</th>--%>
					<th>合伙人账号</th>
					<th>合伙人等级</th>
					<th>真实姓名</th>
					<%--<th>邮箱</th>--%>
					<th>证件号</th>
					<th>证件有效期</th>
					<th>证件图片</th>
					<th>证件图片</th>
					<th>证件图片</th>
					<th>是否交钱</th>
					<th>是否通过</th>
					<th>提交时间</th>
                    <th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="hhrshinfo_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="jsp/hhrsh.js"></script>
</body>
</html>