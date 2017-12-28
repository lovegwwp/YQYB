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
		<title>提货点管理首页</title>		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reThdFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchThdFm").form("clear");  
            $("#ThdDg").datagrid({
 	           url:"thdSx.action"      	     
 	         }); 
            
        }
      function doThdSearch(){
	      $("#ThdDg").datagrid({
	           url:"thdCx.action",      
	           queryParams: {  
	        	   name: $("#name1").val(),       
	          }  
	      });  
	   } 
    function addThdWin(){
    	  $("#ThdDg").datagrid("uncheckAll"); 
    	  $("#id").val(0);
    	  $("#username").val("");
         $( "#addThd" ).window("open").window("setTitle", "新增"); 
      }
    function closeThdWin(){
         $( "#addThd" ).window("close"); 
      }

    function saveThd(){
	     var grid = $("#ThdDg"); 
	     var fm = $("#ThdFm");	   
	     var addWin = $( "#addThd" ); 	    
      	 commonSaveOperate(fm,"${pageContext.request.contextPath}/addThd.action",addWin,grid);  
      	 
     }
    
    function deleteThd() {
        var grid = $("#ThdDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delThd.action","确认删除所选数据吗？");
     }
    
    function editThdWin(){
	     var selectedRows = $("#ThdDg").datagrid("getSelections");
	     if (selectedRows.length != 1) {
	         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	         return;
	     }
	     var row = selectedRows[0];
	     var stype = row.status;
	     if(stype =='0'){
	     	 $.messager.alert("系统提示", "用户已禁用，不能修改！");
	         return;
	     }
	     $( "#addThd" ).window("open").window("setTitle", "编辑用户信息");
	     $("#ThdFm").form("load", row);
     }
    
    
    function gzThd() {
   	    var grid = $("#ThdDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/upThdZt.action?status=0","确认更改所选数据状态吗？");
   }
    
    function hfThd() {
   	    var grid = $("#ThdDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/upThdZt.action?status=1","确认更改所选数据状态吗？");
   }
     
    
    </script>  
	<body class="easyui-layout">
		<table id="ThdDg" title="提货点列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="thdSx.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->	
					<th field="tel" width="200">提货点账号</th>
					<th field="thName" width="200">提货点名称</th>				
					<th field="name" width="200">提货点联系人</th>
					<th field="telShow" width="200">联系电话</th>
					<!-- <th field="provinceId" width="100">省份</th> -->
				    <th field="province" width="100">省份</th>
					<th field="city" width="100">城市</th>
					<th field="area" width="100">区域</th>
					<th field="addr" width="200">提货地址</th> 
					<!-- <th field="cjsj" width="150">创建时间</th> -->
					<th field="xgsj" width="150">修改时间</th>
					<!-- <th field="dlsj" width="150">登录时间</th> -->
					<th field="status" width="100"  formatter="formatJy">是否禁用</th>

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addThdWin()">新增提货点</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editThdWin()">修改提货点</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteThd()">删除提货点</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="gzThd()">提货点禁用</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="hfThd()">提货点恢复</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchThdFm">  
					<span>提货点名称:</span>
					<input id="name1" name="name1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doThdSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reThdFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addThd" class="easyui-window" title="用户新增"  closed = "true" style="width:450px;height:70%;">
			 <form method="post" id="ThdFm" text-align:center>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr> 
                    <tr>
                        <td>提货点账号：</td>
                        <td><input type="text" id="tel" name="tel"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                         
                    <tr>
                        <td>联系人姓名：</td>
                        <td><input type="text" id="name" name="name"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>   
                    <tr>
                        <td>联系人电话：</td>
                        <td><input type="text" id="telShow" name="telShow"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr>
                        <td>提货点名称：</td>
                        <td><input type="text" id="thName" name="thName"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                   
                   <tr>
                       <td><span style="color: blue">提货点地址</span> </td>
                    </tr>                     
                      <tr> 
                        <td>省份：</td>
                        <td> 
                        <input id="provinceId" name="provinceId" class="easyui-combobox" data-options="    
					            valueField: 'id',    
						        textField: 'name',    
						        url: 'getBaCo.action?area='+'2',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getBaCo.action?area='+'3'+'&fid='+rec.id;
						            $('#cityId').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>城市：</td>
                        <td> 
                        <input id="cityId" name="cityId" class="easyui-combobox" data-options="    
					            valueField: 'id',    
						        textField: 'name',    
						        url: 'getBaCo.action?area='+'3',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getBaCo.action?area='+'4'+'&fid='+rec.id;
						            $('#areaId').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>区域：</td>
                        <td> 
                        <input id="areaId" name="areaId" class="easyui-combobox" data-options="    
					            valueField: 'id',    
						        textField: 'name',    
						        url: 'getBaCo.action?area='+'4',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getBaCo.action';    
						            $('#cc2').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr> 
                    <tr>
                        <td>详细地址：</td>
                        <td>
                          <textarea class="easyui-textbox" required="true" id="addr" name="addr" data-options="multiline:true" value="请填写" style="width:180px;height:100px;white-space:pre-wrap"></textarea>
                        </td>
                    </tr>       
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveThd()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeThdWin()">取消</a>
				</div>
			</form>
	  </div>	  
	 <style>
	  	#ThdFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
