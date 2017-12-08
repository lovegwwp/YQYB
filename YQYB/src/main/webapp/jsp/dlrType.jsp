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
		<title>代言人管理首页</title>		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reDlrFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchDlrFm").form("clear");  
            $("#DlrDg").datagrid({
 	           url:"dlrTypeSx.action"      	     
 	         }); 
            
        }
      function doDlrSearch(){
    	/*   var myselect=document.getElementById("isChuangke");       //  获取下拉列表对象
          var myIndex=myselect.selectedIndex ;                        //  获取下拉列表当前索引       
          var taxId = myselect.options[myIndex].value;              //  获取当前索引对应的option选项value值
          var taxText = myselect.options[myIndex].text;  
          alert(myIndex);
    	  alert(taxId);//  */  //获取当前索引对应的option选项文本框内容值
    	  //alert($("#isChuangke1").val());
         
	      $("#DlrDg").datagrid({
	           url:"dlrTypeCx.action",      
	           queryParams: {  
	        	   isChuangke: $("#isChuangke1").val(),       
	          }  
	      });  
	   } 
 
     
    
    </script>  
	<body class="easyui-layout">
		<table id="DlrDg" title="代理人列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="dlrTypeSx.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th> 
				    <th field="isChuangke" width="100"  formatter="formatDlr">代理人等级</th>
					<th field="id" width="100">总人数</th>				
					<th field="cashScore" width="300">现金积分总额</th>
					<th field="shoppingScore" width="200">购物积分总额</th>				
					

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">			
		    <div style="padding:3px">
		        <form id="searchDlrFm">  
					<span>代理人等级:</span>
					<!-- <input id="isChuangke" name="isChuangke" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp; -->
					<!-- class="easyui-combobox"  -->
				    <select id="isChuangke1"   style="width:150px;height:24px;line-height:20px;border:1px solid #95b9e7">
				        <option value="2">初级代理人</option>
				        <option value="3">中级代理人</option>
				        <option value="4">高级代理人</option>				        
				    </select>&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doDlrSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reDlrFlash()">刷新</a>
				 </form>
			</div>
		</div>
      
	 <style>
	  	#DlrFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
