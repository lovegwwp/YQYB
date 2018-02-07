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
		<title>注册协议管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/fgn.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<!--引入引入kindeditor编辑器相关文件-->
	    <link rel="stylesheet" href="kindeditor-4.1.11/themes/default/default.css"/>
	    <script charset="utf-8" src="kindeditor-4.1.11/kindeditor-all.js"></script>
	    <script charset="utf-8" src="kindeditor-4.1.11/lang/zh_CN.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	/* 'multiimage', */
	      var editor;
	      window.editor
                = KindEditor.create('textarea[id="content"]', {
                items: ['bold', 'italic','underline','strikethrough','fontname', 'fontsize', 'forecolor', 'hilitecolor','image', 'link', 'unlink', 'source','plainpaste','hr'],
                filterMode : true,
                htmlTags:{
                    font : ['color', 'size'],
                    span : [
                        '.color', '.font-size', '.font-family','.font-weight', '.font-style', '.line-height', '.background', '.background-color', '.text-decoration'
                    ],
                    a : ['href', 'target', 'name', 'style'],
                    hr : ['class', '/'],
                    br : ['/'],
                    img : ['src', 'width', 'height', 'border', 'alt', 'title', 'align', 'style', '/'],
                    'p,h1,h2,h3,h4,h5,h6' : ['align', 'style'],
                    'strong,b,u,i,em' : []
                },
               uploadJson: 'jsp/upload_json.jsp',//指定上传图片的服务器端程序
               // uploadJson: 'addImg2.action',
                //fileManagerJson: '/images',//指定浏览远程图片的服务器端程序
                fileManagerJson : true,
                allowFileManager : true,
                formatUploadUrl : false,
                allowFileManager: true
            });
      
	  } );
    
      function reZcxyInfoFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchZcxyInfoFm").form("clear"); 
             $("#ZcxyInfoDg").datagrid({
	           url:"getZcxyInfo.action"      	     
	         }); 

        
        }
      function doZcxyInfoSearch(){
	      $("#ZcxyInfoDg").datagrid({
	           url:"getZcxyInfoBy.action",      
	           queryParams: {  
	              title: $("#title1").val()     
	          }  
	      });  
	   } 
    function addZcxyInfoWin(){
         setClear();
         $("#ZcxyInfoDg").datagrid("uncheckAll");         
         $( "#addZcxyInfo" ).window("open").window("setTitle", "新增");
         //$( "#addZcxyInfo" ).window("open"); 
      }
    function closeZcxyInfoWin(){
         $( "#addZcxyInfo" ).window("close");
         $("#ZcxyInfoFm").form("clear"); 
         editor.html("");
      }
      
   function closeZcxyInfoWinRefrsh(){
         $( "#addZcxyInfo" ).window("close");
         $("#ZcxyInfoFm").form("clear"); 
         editor.html("");
         reZcxyInfoFlash();
      }

    function saveZcxyInfo(){
	     var idStr = $("#id").val();
	     var title = $("#title").val();
	     var content = editor.html();
	      $.ajax({
                      url: "${pageContext.request.contextPath}/addZcxyInfo.action",
                      data: {
                          id:idStr,
                          title:title,
                          content:content
                      },
                      async: true,
                      type: "POST",
                      dataType: "json",
                      success: function (data) {
                          closeZcxyInfoWinRefrsh();
                          $.messager.alert(data.status, data.message); 
                            
                      },
                      error:function (error) {
                           $.messager.alert(data.status, data.message);   
                      }
           })
      
     }
     
    
    function deleteZcxyInfo() {
        var grid = $("#ZcxyInfoDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delZcxyInfo.action","确认删除所选数据吗？");
     }
    
    function editZcxyInfoWin(){
       var grid = $("#ZcxyInfoDg"); 
	   var fm = $("#ZcxyInfoFm");
	   var addWin = $( "#addZcxyInfo" );  
       var selectedRows = grid.datagrid("getSelections");
	   if (selectedRows.length != 1) {
	         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	         return;
	   }
	   var row = selectedRows[0];
	   addWin.window("open").window("setTitle", "编辑信息");
	   fm.form("load", row);
	   editor.html(row.content);
	 }
     
     function setClear(){
        $("#id").val("0");
     	$("#title").val("");
	    editor.html("");
     }
     
    </script>  
	<body class="easyui-layout">
		<table id="ZcxyInfoDg" title="协议列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getZcxyInfo.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>			
					<th field="title" width="150">标题</th>
<!-- 					<th field="subTitle" width="100">发布人</th>
					<th field="newPic" width="250">首页图片</th> -->
					<th field="content" width="750">协议内容</th>
<!-- 					<th field="cjsj" width="150">创建时间</th>	 -->		

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addZcxyInfoWin()">新增协议</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editZcxyInfoWin()">修改协议</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteZcxyInfo()">删除协议</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchZcxyInfoFm">  
					<span>标题:</span>
					<input id="title1" name="title1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doZcxyInfoSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reZcxyInfoFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addZcxyInfo" class="easyui-window" title="新增信息"  closed ="true" style="width:800px;height:500px;">
			 <form method="post" id="ZcxyInfoFm"  enctype="multipart/form-data" text-align:left>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>                               
                    <tr>
                        <td>标题：</td>
                        <td><input type="text" id="title" name="title"
                            class="easyui-validatebox" style="width:300px;height:20px;" required="true" />&nbsp;
                            <span style="color: red">*</span>
                        </td>
                    </tr>                     
                    <tr>
                       <td>内容：</td>
                        <td>                       
                        <textarea id="content" style="width:300px;height:300px;visibility:hidden;"></textarea>
                        </td>
                    </tr>                
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveZcxyInfo()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeZcxyInfoWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
	<style>
	  	#ZcxyInfoFm{
	  		margin:0 auto !important;
	  		width: 750px
	  	}
	  .datagrid-btable tr{height: 32px;}
	  	
	  </style>
  </body>

	
</html>
