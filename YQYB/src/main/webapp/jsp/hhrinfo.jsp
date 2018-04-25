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
		    	<input type="text" value="" placeholder="请输入合伙人账号" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
        <div class="layui-inline">
            <a class="layui-btn transer_btn" style="background-color:#5FB878">可以转账</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn noTranser_btn" style="background-color:#5FB878">禁止转账</a>
        </div>
		<%--<div class="layui-inline">
			<a class="layui-btn jy_btn" style="background-color:#5FB878">账号禁用</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn hf_btn" style="background-color:#5FB878">恢复使用</a>
		</div>--%>
		<div class="layui-inline">
			<div class="layui-form-mid layui-word-aux">可查询字段[合伙人账号]</div>
		</div>
	</blockquote>
	<div class="layui-form hhrinfoinfo_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="130">
				<col width="100">
				<col width="130">
				<col width="100">
				<col width="100">
				<col width="100">
				<col width="100">
				<col width="100">
				<col width="100">
				<col >
				<col>
				<col>
				<col>
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th>合伙人账号</th>
					<th>等级</th>
					<th>推荐码</th>
					<th>分红权</th>
					<th>消费券</th>
					<th>股券</th>
					<th>电子券</th>
					<th>报单券</th>
					<th>借贷金额</th>
					<th>是否可以转账</th>
					<th>使用状态</th>
                    <th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="hhrinfoinfo_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="jsp/hhrinfo.js"></script>
</body>
</html>