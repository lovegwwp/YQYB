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
		<title>市场奖人员管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reScjRyFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchscjRyFm").form("clear");  
           // $("#scjRyDg").datagrid("reload");  
             $("#scjRyDg").datagrid({
	           url:"jrc/user/list.action"      	     
	         });         
        }
      function doScjRySearch(){
	      $("#scjRyDg").datagrid({
	           url:"jrc/listByAccount.action",      
	           queryParams: {  
	        	   account: $("#account1").val() 	            
	          }  
	      });  
	   } 
    function addScjRyWin(){
    	setClear();
    	$("#ppp").show();
    	var selectedRows2 = $("#scjRyDg").datagrid("getSelections");
    	if (selectedRows2.length==1) {
    		var ppAccount = selectedRows2[0].uAccount;  
    		$("#pAccount").val(ppAccount);
		}
    		
    	$("#id").val("0");       
        $( "#addScjRy" ).window("open").window("setTitle", "新增");
      }
    function closeScjRyWin(){
         $( "#addScjRy" ).window("close");
         $("#scjRyFm").form("clear"); 
      }

    function saveScjRy(){
	     var grid = $("#scjRyDg"); 
	     var fm = $("#scjRyFm");	   
	     var addWin = $( "#addScjRy" );  
      	 commonSaveOperate(fm,"${pageContext.request.contextPath}/jrc/addJRecord.action",addWin,grid); 
      	 setClear();     	     
     }
    
    function deleteScjRy() {
        var grid = $("#scjRyDg"); 
        var selectedRows = $("#scjRyDg").datagrid("getSelections");
	    if (selectedRows.length != 1) {
	         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	         return;
	    }
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/jrc/deleteJrc.action","确认删除所选数据吗？");
     	reScjRyFlash();
     }
    
    function editScjRyWin(){
       $("#ppp").hide();
       $("#pAccount").val("0");
       var grid = $("#scjRyDg"); 
	   var fm = $("#scjRyFm");
	   var addWin = $( "#addScjRy" );  
       openEditWin(grid,addWin,fm);
     }
     
     function setClear(){
     	$("#depart").val("");
	    $("#pAccount").val("");
	    $("#uAccount").val("");
	    $("#id").val("0");
     }
    
    </script>  
	<body class="easyui-layout">
		<table id="scjRyDg" title="常量列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="jrc/user/list.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<th field="uId" width="50">用户ID</th>
					<th field="uName" width="120">真实姓名</th>
					<th field="uAccount" width="150">账号</th>
					<th field="parentId" width="50">父级ID</th>
					<th field="depart" width="100" formatter="formatDepart">市场分布</th>
				<!-- 	<th field="pv" width="80">禁用状态</th>
					<th field="jyPv" width="80">禁用状态</th>
					<th field="status" width="80">禁用状态</th>
					<th field="created" width="80">禁用状态</th>
					<th field="modifyTime" width="80">禁用状态</th> -->

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addScjRyWin()">新增市场</a>
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editScjRyWin()">修改市场</a> -->
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteScjRy()">删除市场</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchscjRyFm">  
					<span>账户:</span>
					<input id="account1" name="account1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doScjRySearch()">搜索</a>&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reScjRyFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addScjRy" class="easyui-window" title="新增市场人员"  closed = "true" style="width:400px;height:250px;">
			 <form method="post" id="scjRyFm" text-align:center>
                <table cellspacing="8px;"> 
                  <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>
                                                    
                     <tr id="ppp">
                        <td>父级账号：</td>
                        <td><input type="text" id="pAccount" name="pAccount"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>  
                     <tr>
                        <td>本级账号：</td>
                        <td><input type="text" id="uAccount" name="uAccount"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                 
                    <tr>
                        <td>市场分布：</td>
                        <td>
                       <!--  <input type="text" id="depart" name="depart"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span> -->
	                        <select id="depart"  name="depart" required="true"  style="width:170px;height:21px;line-height:18px;border:1px solid #95b9e7">
						        <option value="0">顶层</option>
						        <option value="1">市场A</option>		
						        <option value="2">市场B</option>			       			        
					        </select>&nbsp;<span style="color: red">*</span>
                        </td>                        
                    </tr>  
                                               
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveScjRy()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeScjRyWin()">取消</a>
				</div>
			</form>
	  </div>	  
	<style>
	  	#scjRyFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  	  
	   .datagrid-btable tr{height: 32px;}	
	  </style>
  </body>

	
</html>
