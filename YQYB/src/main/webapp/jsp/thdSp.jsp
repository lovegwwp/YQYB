<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";  
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>提货商品详情首页</title>		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reThSpFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchThSpFm").form("clear");  
            $("#ThSpDg").datagrid({
 	           url:"thdSpSx.action"      	     
 	         }); 
            
        }
      function doThSpSearch(){
	      $("#ThSpDg").datagrid({
	           url:"thdSpCx.action",      
	           queryParams: {  
	        	   thName: $("#name1").val(),       
	          }  
	      });  
	   } 

    </script>  
	<body class="easyui-layout">
		<table id="ThSpDg" title="提货点列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="thdSpSx.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->	
					<th field="orderSn" width="200">订单号</th>					
					<th field="thr" width="150">提货人</th>
					<th field="tel" width="200">提货人联系电话</th>		
					<th field="thSp" width="200">提货商品</th>
					<th field="price" width="100">商品价格</th>	
					<th field="thNum" width="100">提货数量</th>					
				    <th field="thName" width="300">提货点名称</th>				
					<th field="name" width="200">提货点联系人</th>
					<!-- <th field="status" width="100"  formatter="formatJy">是否禁用</th> -->

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">			
		    <div style="padding:3px">
		        <form id="searchThSpFm">  
					<span>提货点名称:</span>
					<input id="name1" name="name1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doThSpSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reThSpFlash()">刷新</a>
				 </form>
			</div>
		</div>
      
	 <style>
	  	#ThSpFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
