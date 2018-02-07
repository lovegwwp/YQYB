<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<meta charset="UTF-8">
		<title>分享管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/fgn.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>

	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reYhfxInfoFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchYhfxInfoFm").form("clear"); 
             $("#YhfxInfoDg").datagrid({
	           url:"getYhfxInfo.action"      	     
	         }); 

        
        }
      function doYhfxInfoSearch(){
	      $("#YhfxInfoDg").datagrid({
	           url:"getYhfxInfoBy.action",      
	           queryParams: {  
	              title: $("#titles1").val(), 
	              content: $("#content1").val(),  	            
	          }  
	      });  
	   } 
    function addYhfxInfoWin(){
         setClear();
         $("#YhfxInfoDg").datagrid("uncheckAll");         
         $( "#addYhfxInfo" ).window("open").window("setTitle", "新增");
         //$( "#addYhfxInfo" ).window("open"); 
      }
    function closeYhfxInfoWin(){
         $( "#addYhfxInfo" ).window("close");
         $("#YhfxInfoFm").form("clear"); 
      }

    function saveYhfxInfo(){
	     var grid = $("#YhfxInfoDg"); 
	     var fm = $("#YhfxInfoFm");	   
	     var addWin = $( "#addYhfxInfo" );
	     //var fValue = $("#myFile").val(); 
	     //var fValue = $("#pics1").val();
	     var fValue = $("#pics2").filebox('getValue');
	     //alert($("#pics1").filebox('getValue'));
 	     /* fm.form("submit", {
	    	 type: "post",
	    	 url: "${pageContext.request.contextPath}/addYhfxInfo.action",
	    	 success: function(data) {
	    		 
	    	 }
	     });  */
	    if(checkFileExt(fValue)){
	    	 commonSaveUpload(fm,"${pageContext.request.contextPath}/addYhfxInfo.action",addWin,grid);  
      	 }else{
      	 	$.messager.alert("系统提示", "上传文件类型不合法！");
      	 }        
     }
     
    
    function deleteYhfxInfo() {
        var grid = $("#YhfxInfoDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delYhfxInfo.action","确认删除所选数据吗？");
     }
    
    function editYhfxInfoWin(){
       var grid = $("#YhfxInfoDg"); 
	   var fm = $("#YhfxInfoFm");
	   var addWin = $( "#addYhfxInfo" );  
       openEditWin(grid,addWin,fm);
       /* var editWin = addWin;
	   var selectedRows = grid.datagrid("getSelections");
	     if (selectedRows.length != 1) {
	         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	         return;
	     }
	     var row = selectedRows[0];
	     editWin.window("open").window("setTitle", "编辑信息");
	     fm.form("load", row);
	     $('#pics').attr('src',row.pics1); */
	     
     }
     
     function setClear(){
        $("#id").val("0");
     	$("#pics").val("");
	    $("#contents").val("请填写");
	    $("#titles").val("");
     }
     
    </script>  
	<body class="easyui-layout">
		<table id="YhfxInfoDg" title="分享表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getYhfxInfo.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->				
					<th field="title" width="150">分享标题</th>
					<!-- <th field="byteSize" width="100">图片大小</th> -->
					<th field="content" width="350">分享内容</th>
					<th field="linkPic" width="200" formatter ="showImg">分享图片</th>
					<th field="linkUrl" width="250" >分享链接</th>
				<!-- 	<th field="xgsj" width="150">修改时间</th> -->

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addYhfxInfoWin()">新增分享</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editYhfxInfoWin()">修改分享</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteYhfxInfo()">删除分享</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchYhfxInfoFm">  
					<span>标题:</span>
					<input id="titles1" name="titles1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<span>内容:</span>
					<input id="content1" name="content1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doYhfxInfoSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reYhfxInfoFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addYhfxInfo" class="easyui-window" title="新增分享"  closed = "true" style="width:500px;height:400px;">
			 <form method="post" id="YhfxInfoFm"  enctype="multipart/form-data" text-align:left>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>                            
                    <tr>
                        <td>标题：</td>
                        <td><input type="text" id="title" name="title"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                     <tr>
                        <td>分享链接：</td>
                        <td><input type="text" id="linkUrl" name="linkUrl"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td>分享图片</td>
                        <td>
                        	<input id="pics2" name="pics2" class="easyui-filebox"  style="width:200px;"/>&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                 
            
                    <tr>
                       <td>内容：</td>
                        <td><textarea class="easyui-textbox" required="true" id="content" name="content" data-options="multiline:true" value="请填写" style="width:300px;height:100px;white-space:pre-wrap"></textarea>
                        </td>
                    </tr>                
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveYhfxInfo()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeYhfxInfoWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
	<style>
	  	#YhfxInfoFm{
	  		margin:0 auto !important;
	  		width: 400px
	  	}
	  .datagrid-btable tr{height: 32px;}
	  	
	  </style>
  </body>

	
</html>
