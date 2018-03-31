/**
 * grid的选择校验
 * 
 * @param {}
 *            grid
 */
function gridSelectedValid(grid) {
	var row = grid.datagrid("getSelected");
	if (row != null) {
		return row;
	} else {
		$.messager.alert("提示", "您尚未选择数据！");
		return false;
	}
}

/**
 * grid的勾选校验
 */
function gridCheckedValid(grid) {
	var rows = grid.datagrid("getChecked");
	if (rows.length > 0) {
		return $.map(rows, function(n) {
			return n.id;
		}).join(",");
	} else {
		$.messager.alert("提示", "您尚未勾选数据！");
		return false;
	}
}

/**
 * grid的勾选校验
 */
function gridCheckedValid2(grid) {
	var rows = grid.datagrid("getChecked");
	if (rows.length=1) {
		return $.map(rows, function(n) {
			return n.macId;
		}).join(",");
	} else if(rows.length > 1) {
		$.messager.alert("提示", "请勾选数据单挑数据！");
		return false;
	}else if(rows.length = 0) {
		$.messager.alert("提示", "您尚未勾选数据！");
		return false;
	}
}

/**
 * 
 * 取消订单分配
 */
function commonBatchQxFp(grid, url,str) {	
		var selectedRowsFp = grid.datagrid("getSelections");
	    if (selectedRowsFp.length != 1) {
	        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	        return;
	    }
	    var row = selectedRowsFp[0];
	    if (row.status!=2) {
	    	 $.messager.alert("系统提示", "当前订单状态异常！");
	         return;
		}
	$.messager.confirm("操作确认", str, function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					orderId : row.orderId,
					oid :row.oid,
					rid :row.rid
				},
				success : function(data) {
					/** 如果删除成功，刷新grid数据* */
					if (data.status) {
						$.messager.progress("close");
//						$("#" + modelName + "_grid").datagrid("reload");
						grid.datagrid("reload");
						$.messager.alert("系统提示：", "操作成功！");
					} else {
                        $.messager.alert("系统提示", "操作失败，请联系系统管理员！");
                    }
				}
			});
		}
	});
}


/**
 * 
 * 取消订单
 */
function commonBatchQx(grid, url,str) {	
		var selectedRowsFp = grid.datagrid("getSelections");
	    if (selectedRowsFp.length != 1) {
	        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	        return;
	    }
	    var row = selectedRowsFp[0];
	    ///只有尚未分配的订单才可以直接取消
	    if (row.status!=1) {
	    	 $.messager.alert("系统提示", "当前订单状态异常！");
	         return;
		}
	$.messager.confirm("操作确认", str, function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					orderId : row.orderId,
					oid :row.oid,					
				},
				success : function(data) {
					/** 如果删除成功，刷新grid数据* */
					if (data.status) {
						$.messager.progress("close");
//						$("#" + modelName + "_grid").datagrid("reload");
						grid.datagrid("reload");
						$.messager.alert("系统提示：", "操作成功！");
					} else {
                        $.messager.alert("系统提示", "操作失败，请联系系统管理员！");
                    }
				}
			});
		}
	});
}

/**
 * 通用删除/禁用
 * 支持批量操作
 */
function commonBatchOperate(grid, url,str) {
	var ids = gridCheckedValid(grid);
	if (!ids) {
		return;
	}
	//"您确定要删除这<font color=red>"
    //+ selectedRows.length + "</font>条数据吗？"
	$.messager.confirm("操作确认", str, function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					strIds : ids
				},
				success : function(data) {
					/** 如果删除成功，刷新grid数据* */
					if (data.status) {
						$.messager.progress("close");
//						$("#" + modelName + "_grid").datagrid("reload");
						grid.datagrid("reload");
						$.messager.alert("系统提示：", "操作成功！");
					} else {
                        $.messager.alert("系统提示", "操作失败，请联系系统管理员！");
                    }
				}
			});
		}
	});
}

/**
 * 通用删除/禁用
 * 支持批量操作
 */
function commonAuth(grid, url,str) {
	var ids = gridCheckedValid(grid);
	if (!ids) {
		return;
	}
	//"您确定要删除这<font color=red>"
    //+ selectedRows.length + "</font>条数据吗？"
	$.messager.confirm("操作确认", str, function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					id : ids
				},
				success : function(data) {
					/** 如果删除成功，刷新grid数据* */
					if (data.status) {
						$.messager.progress("close");
//						$("#" + modelName + "_grid").datagrid("reload");
						grid.datagrid("reload");
						$.messager.alert("系统提示：", "操作成功！");
					} else {
                        $.messager.alert("系统提示", "操作失败，请联系系统管理员！");
                    }
				}
			});
		}
	});
}


/**
 * 通用==修改状态
 * 支持批量操作
 */
function commonBatchOperate2(grid, url,str) {
	var ids = gridCheckedValid2(grid);
	if (!ids) {
		return;
	}
	//"您确定要删除这<font color=red>"
    //+ selectedRows.length + "</font>条数据吗？"
	$.messager.confirm("操作确认", str, function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					macIds : ids
				},
				success : function(data) {
					/** 如果删除成功，刷新grid数据* */
					if (data.status) {
						$.messager.progress("close");
//						$("#" + modelName + "_grid").datagrid("reload");
						grid.datagrid("reload");
						$.messager.alert("系统提示：", "操作成功！");
					} else {
                        $.messager.alert("系统提示", "操作失败，请联系系统管理员！");
                    }
				}
			});
		}
	});
}



/**
 * 通用保存
 *
 */
function commonSaveOperate(fm,url,addWin,grid) {
	var data = fm.serialize();
	//console.log(data);
    if(fm.form("validate")){ 
          $.ajax({  
                  type: "post" ,  
                  url: url ,   
                  cache:false ,  
                  data:data ,  
                  dataType:"json" , 
                  /* contentType:"application/json;charset=utf-8",  */
                  success:function(result){  
                      //1 关闭窗口  
                	  addWin.window("close"); 
                      //2刷新datagrid   
                	  grid.datagrid("reload");  
                	  setFmValue() ;
                      //3 提示信息
                      $.messager.alert(result.status, result.message);  
                    /*  / $.messager.show({  
                          title:result.status ,   
                          msg:result.message  
                      }); */  
                  } ,  
                  error:function(result){                             
                      $.messager.alert(result.status, result.message);   
                  }  
              });  
          } else {  
              $.messager.alert("系统提示", "数据填写不完整，请注意！");
          }  
	
}

/**
 *  上传文件保存
 *
 */
function commonSaveUpload(fm,url,addWin,grid) {
	//var data = fm.serialize();
	//console.log(data);
    if(fm.form("validate")){ 
    	fm.form("submit", {
	    	 type: "post",
	    	 url: url,
	    	 success: function(result) {
	    	  var result = eval('(' + result + ')');  
	    		//1 关闭窗口  
           	  addWin.window("close"); 
                 //2刷新datagrid   
           	  grid.datagrid("reload");  
                 //3 提示信息
              $.messager.alert(result.status, result.message);  
             } ,  
             error:function(result){ 
            	 var result = eval('(' + result + ')');  
                 $.messager.alert(result.status, result.message);   
             }  
	     });
    }else {  
              $.messager.alert("系统提示", "数据填写不完整，请注意！");
          }  
	
}

function openEditWin(grid,editWin,fm){
	 var selectedRows = grid.datagrid("getSelections");
     if (selectedRows.length != 1) {
         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
         return;
     }
     var row = selectedRows[0];
     editWin.window("open").window("setTitle", "编辑信息");
     fm.form("load", row);
}
function openEditWin2(grid,editWin,fm){
	 var selectedRows = grid.datagrid("getSelections");
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];
    if (row.status!=1) {
    	 $.messager.alert("系统提示", "当前订单状态异常！");
         return;
	}
    editWin.window("open").window("setTitle", "编辑信息");
    fm.form("load", row);
}

function openEditWin3(grid,editWin,fm){
	 var selectedRows = grid.datagrid("getSelections");
   if (selectedRows.length != 1) {
       $.messager.alert("系统提示", "请选择一条要编辑的数据！");
       return;
   }
   var row = selectedRows[0];
   if (row.status!=2) {
   	 $.messager.alert("系统提示", "当前订单状态异常！");
        return;
	}
   editWin.window("open").window("setTitle", "编辑信息");
   fm.form("load", row);
}

function openEditWin4(grid,editWin,fm){
	 var selectedRows = grid.datagrid("getSelections");
  if (selectedRows.length != 1) {
      $.messager.alert("系统提示", "请选择一条要编辑的数据！");
      return;
  }
  var row = selectedRows[0];
  if (row.status=='0') {
  	 $.messager.alert("系统提示", "当前用户等级异常！");
       return;
  }
  if (row.isAuth!='1') {
	  	 $.messager.alert("系统提示", "当前用户尚未认证！");
	       return;
  }
  editWin.window("open").window("setTitle", "编辑信息");
  fm.form("load", row);
}

//设置
function setFmValue(){
    $("#account").val(""); 
    $("#uname").val(""); 
    $("#job").val(""); 
    $("#age").val("");
    $("#abstracts").val("请填写"); 
    $("#zd").val("请填写"); 
    $("#skills").val("请填写");
    $("#emails").val("");
 	$("#bz_type").val("");
    $("#bz_id").val("");
    $("#bz_value").val("");
    $("#pid").val("");
    $("#macId").val("");
    $("#talkTime").val("");
}  

   //文件后缀名校验
 function checkFileExt(filename)
  {
     var flag = false; //状态
     var arr = ["jpg","png","gif","ico","mp4","wmv","Ogg","MPGE4","mp3","MP3"];
     //取出上传文件的扩展名
     var index = filename.lastIndexOf(".");
     var ext = filename.substr(index+1);
     //循环比较
     for(var i=0;i<arr.length;i++)
     {
      if(ext == arr[i])
      {
       flag = true; //一旦找到合适的，立即退出循环
       break;
      }
     }
  return flag;
}
 
 
 //文件后缀名校验
 function checkFileExt3(filename)
  {
     var flag = false; //状态
     var arr = ["war","WAR","apk","APK","ipa","IPA"];
     //取出上传文件的扩展名
     var index = filename.lastIndexOf(".");
     var ext = filename.substr(index+1);
     //循环比较
     for(var i=0;i<arr.length;i++)
     {
      if(ext == arr[i])
      {
       flag = true; //一旦找到合适的，立即退出循环
       break;
      }
     }
  return flag;
}
 
 
 //视频后缀名校验
 function checkFileExt2(filename)
  {
     var flag = false; //状态
     var arr = ["mp4","wmv","Ogg","MPGE4"];
     //取出上传文件的扩展名
     var index = filename.lastIndexOf(".");
     var ext = filename.substr(index+1);
     //循环比较
     for(var i=0;i<arr.length;i++)
     {
      if(ext == arr[i])
      {
       flag = true; //一旦找到合适的，立即退出循环
       break;
      }
     }
  return flag;
}
 
 
 
	 function formatNumShow(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "管理员";  
           }else{  
               return "普通用户";  
           }  
         }  
 		 
	 }

	 
	 function formatXtgxType(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "安卓";  
           }else{  
               return "IOS";  
           }  
         }  
 		 
	 }
	 
	 
	 function formatJy(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "正常";  
           }else{  
               return "禁用";  
           }  
         }  
 		 
 }
	 
	 function formatReportresult(value,row,index){
	 		if(value==null){  
	             return "";  
	         }else{  
	           if(value==1){  
	               return "情况属实";  
	           }else  if(value==2){  
	               return "不属实";  
	           }else  if(value==0){  
	               return "未处理";  
	           }   
	         }  
	 		 
		 }
	 function formatCzzt(value,row,index){
	 		if(value==null){  
	             return "";  
	         }else{  
	           if(value==0){  
	               return "待提现";  
	           }else  if(value==1){  
	               return "已支付";  
	           }else  if(value==2){  
	               return "未支付";  
	           }   
	         }  
	 		 
		 }
	  
	 function formatSh(value,row,index){
	 		if(value==null){  
	             return "";  
	         }else{  
	           if(value==1){  
	               return "审核中";  
	           }else if(value==2){  
	               return "通过";  
	           }else if(value==3){  
	               return "未通过";  
	           }    
	        }  
	 		 
	 }
	 
	function formatShelve(value,row,index){
	 		if(value==null){  
	             return "";  
	         }else{  
	           if(value==1){  
	               return "上架";  
	           }else if(value==0){  
	               return "下架";  
	           }  
	        }  
	 		 
	 }
	 
	 function formatNumSex(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "女";  
           }else{  
               return "男";  
           }  
         }  
 		 
  }
	function formatNumState(value,row,index){
	 		if(value==null){  
	             return "";  
	         }else{  
	           if(value==1){  
	               return "单身";  
	           }else if(value==2){  
	               return "未婚";  
	           }else if(value==3){  
	               return "已婚";  
	           }   
	         }  
	 		 
	 } 
	function formatStatus(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "普通用户";  
           }else if(value==2){  
               return "陪玩用户";  
           }else if(value==3){  
               return "明星用户";  
           }else if(value==0){  
             return "禁用";  
           }
         }  
 		 
 }  
	
	function formatSource(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "微信授权";  
           }else if(value==2){  
               return "QQ授权";  
           }else if(value==0){  
             return "直接注册";  
           }
         }  
 		 
 }  
	
	function formatAuth(value,row,index){
 		if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "已认证";  
           }else if(value==0){  
             return "未认证";  
           }
         }  
 		 
 }  
		//上分订单状态表0未支付，1已支付，2已接单，3完成，4订单取消
function formatOrderSfStatus(value,row,index){
		 if(value==null){  
             return "";  
         }else{  
           if(value==0){  
               return "未支付";  
           }else if(value==1){  
               return "已支付";  
           }else if(value==2){  
               return "已接单";  
           }else if(value==3){  
               return "已完成";  
           }else if(value==4){  
               return "取消订单";  
           }      
         }  
	 }
	 
	 
	//支付类型
	 function formatZfType(value,row,index){
		 if(value==null){  
             return "";  
         }else{  
           if(value==1){  
               return "支付宝";  
           }else if(value==2){  
               return "微信";  
           }else if(value==3){  
               return "其他";  
           }    
         }  
	 }
	 

	
	 
	//供使用者调用  
	 function trim(s){  
	     return trimRight(trimLeft(s));  
	 }  
	 //去掉左边的空白  
	 function trimLeft(s){  
	     if(s == null) {  
	         return "";  
	     }  
	     var whitespace = new String(" \t\n\r");  
	     var str = new String(s);  
	     if (whitespace.indexOf(str.charAt(0)) != -1) {  
	         var j=0, i = str.length;  
	         while (j < i && whitespace.indexOf(str.charAt(j)) != -1){  
	             j++;  
	         }  
	         str = str.substring(j, i);  
	     }  
	     return str;  
	 }  

	 //去掉右边的空白 www.2cto.com   
	 function trimRight(s){  
	     if(s == null) return "";  
	     var whitespace = new String(" \t\n\r");  
	     var str = new String(s);  
	     if (whitespace.indexOf(str.charAt(str.length-1)) != -1){  
	         var i = str.length - 1;  
	         while (i >= 0 && whitespace.indexOf(str.charAt(i)) != -1){  
	            i--;  
	         }  
	         str = str.substring(0, i+1);  
	     }  
	     return str;  
	 } 
	 
  function myTrim(str){
	  if (str=="") {
		return ""
	  }else{
	  return  trimRight(trimLeft(str));
	  }
  }
	 
	 
	 
function showImg(value,row){
			// var imgUrlStr='http://121.40.29.64:8081/';
			//var imgUrlStr='http://192.168.0.28:8080/';
			 //value = imgUrlStr +value;
			 var width = 100;
			 var height = 80;
			 if (value=="http://121.40.29.64:8081/"||value=="http://121.40.29.64:8081/BACONHT/null"||value=="http://121.40.29.64:8081/null"||value =="null"||typeof(value) =="null"||typeof(value) == "undefined") {
				 value ="http://121.40.29.64:8081/BACONHT/img/404.png";
			}
			 return '<img  src='+value+' '+' width ='+width+' height='+height+ '/>';
			} 
		 

