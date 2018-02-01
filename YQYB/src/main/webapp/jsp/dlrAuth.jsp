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
		<title>代理人实名审核首页</title>		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function redlrAuthFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchdlrAuthFm").form("clear");  
            $("#dlrAuthDg").datagrid({
 	           url:"dlrAuthSx.action"      	     
 	         }); 
            
        }
      function dodlrAuthSearch(){
	      $("#dlrAuthDg").datagrid({
	           url:"dlrAuthCx.action",      
	           queryParams: {  
	        	   account: $("#account").val(),       
	          }  
	      });  
	   } 
   
    
    function agreedlrAuth() {
   	 var selectedRowsH = $("#dlrAuthDg").datagrid("getSelections");
     if (selectedRowsH.length != 1) {
         $.messager.alert("系统提示", "请选择一条数据！");
         return;
     }
     var row = selectedRowsH[0];
     var status = row.status;
     var uUuid = row.uUuid;
     if(status !='0'){
     	 $.messager.alert("系统提示", "提交状态异常！");
         return;
     }
   	    var grid = $("#dlrAuthDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/dlrAgree.action?uuid="+uUuid,"确认通过审核吗？");
   }
    
    function refusedlrAuth() {
   	 var selectedRowsH = $("#dlrAuthDg").datagrid("getSelections");
     if (selectedRowsH.length != 1) {
         $.messager.alert("系统提示", "请选择一条数据！");
         return;
     }
     var row = selectedRowsH[0];
     var status = row.status;
     var uUuid = row.uUuid;
     if(status !='0'){
     	 $.messager.alert("系统提示", "提交状态异常！");
         return;
     }
   	    var grid = $("#dlrAuthDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/dlrRefuse.action?uuid="+uUuid,"确认审核不通过吗？");
   }
     
    
    </script>  
	<body class="easyui-layout">
		<table id="dlrAuthDg" title="代理人列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="dlrAuthSx.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->	
					<th field="account" width="100">代理人账号</th>
					<th field="isChuangke" width="100"  formatter="formatDlr">代理等级</th>				
					<th field="realName" width="100">真实姓名</th>
					<th field="email" width="120">邮箱</th>
					<th field="idCard" width="180">身份证号码</th>
					<th field="cardPicture1" width="200" formatter="showImg">身份证照片</th> 
					<th field="cardPicture2" width="200" formatter="showImg">身份证照片</th> 
					<th field="cardPicture3" width="200" formatter="showImg">身份证照片</th> 
				<!-- 	<th field="validityDate" width="280">身份证有效期</th> 	 -->
					<th field="yxsj" width="100">身份证有效期</th> 												
					<th field="status" width="80"  formatter="formatSh">是否通过</th>
					<th field="cjsj" width="150">提交时间</th>		

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="agreedlrAuth()">审核通过</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="refusedlrAuth()">审核不通过</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchdlrAuthFm">  
					<span>用户账号:</span>
					<input id="account" name="account" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="dodlrAuthSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="redlrAuthFlash()">刷新</a>
				 </form>
			</div>
		</div>
     
	 <style>
	  	#dlrAuthFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
