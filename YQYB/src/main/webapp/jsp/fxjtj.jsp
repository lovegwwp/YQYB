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
		<title>分销奖统计首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	
	  
	 $(function() {
		 		

		   $('#attYearMonth').datebox({
		       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
		       onShowPanel: function () {
		          //触发click事件弹出月份层
		          span.trigger('click'); 
		          if (!tds)
		            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
		            setTimeout(function() { 
		                tds = p.find('div.calendar-menu-month-inner td');
		                tds.click(function(e) {
		                   //禁止冒泡执行easyui给月份绑定的事件
		                   e.stopPropagation(); 
		                   //得到年份
		                   var year = /\d{4}/.exec(span.html())[0] ,
		                   //月份
		                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
		                   month = parseInt($(this).attr('abbr'), 10);  

		         //隐藏日期对象                     
		         $('#attYearMonth').datebox('hidePanel') 
		           //设置日期的值
		           .datebox('setValue', year + '-' + month); 
		                        });
		                    }, 0);
		            },
		            //配置parser，返回选择的日期
		            parser: function (s) {
		                if (!s) return new Date();
		                var arr = s.split('-');
		                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		            },
		            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
		            formatter: function (d) { 
		                var currentMonth = (d.getMonth()+1);
		                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
		                return d.getFullYear() + '-' + currentMonthStr; 
		            }
		        });

		        //日期选择对象
		        var p = $('#attYearMonth').datebox('panel'), 
		        //日期选择对象中月份
		        tds = false, 
		        //显示月份层的触发控件
		        span = p.find('span.calendar-text'); 
		        var curr_time = new Date();

		        //设置当前年月
		        $("#attYearMonth").datebox("setValue", myformatter(curr_time));
		        
		       
	        
		});
	 
	//格式化日期
	 function myformatter(date) {
	     //获取年份
	     var y = date.getFullYear();
	     //获取月份
	     var m = date.getMonth() + 1;
	     return y + '-' + m;
	 }
	

	
   $.fn.datebox.defaults.formatter = function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			m = m < 10 ? ("0" + m) : m;
			var d = date.getDate();
			d= d < 10 ? ("0" + d) : d;
			return y+'-'+m+'-'+d;
		}  
   
   $.fn.datebox.defaults.parser =  function (date) {
	   	if (!date) return new Date();
	    var arr = date.split('-');
	    return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1 , parseInt(arr[2], 10));
} 
 /*   
   $('#kssj').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	    parser: function (date) {
	    	if (!date) return new Date();
            var arr = date.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1 , parseInt(arr[2], 10));
        } 
	   });

 */

    
      function reFxjtjFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchFxjtjFm").form("clear");  
           // $("#FxjtjDg").datagrid("reload");  
            $("#FxjtjDg").datagrid({
 	           url:"showFxj/list.action"      	     
 	         }); 
        
        }
      function doFxjtjWeekSearch(){
	      $("#FxjtjDg").datagrid({
	           url:"showFxj/listByWek.action",      
	           queryParams: {  	             
	          }  
	      });  
	   } 

      function doFxjtjSearch(){
    	  alert($("#kssj").datebox("getValue"));
	      $("#FxjtjDg").datagrid({
	           url:"showFxj/listByDay.action",      
	           queryParams: {  	             	
	              beginTime: $("#kssj").datebox("getValue"), 
	              endTime: $("#jssj").datebox("getValue"), 
	          }  
	      });  
	   } 

      function doFxjtjMonthSearch(){
    	 // alert($("#attYearMonth").datebox("getValue"));
	      $("#FxjtjDg").datagrid({
	           url:"showFxj/listByMonth.action",      
	           queryParams: {  	         
	              month: $("#attYearMonth").datebox("getValue"), 		             
	          }  
	      });  
	   } 



 
    </script>  
	<body class="easyui-layout">
		<table id="FxjtjDg" title="分销奖统计列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="showFxj/list.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<th field="amount" width="250">推荐总额</th>
					<th field="cashScore" width="200">现金积分</th>
					<th field="shoppingScore" width="200">购物积分</th>
					
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">			
		    <div style="padding:3px">
		        <form id="searchFxjtjFm">  									   
					<span>开始时间:</span>  <!-- formatter ="myformatter2"  -->
					<input  id="kssj"  type= "text" class= "easyui-datebox" > </input>&nbsp;&nbsp;  								 
					<span>结束时间:</span>
					<input  id="jssj"  type= "text" class= "easyui-datebox" > </input>&nbsp;&nbsp; 								
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doFxjtjSearch()">搜索</a>&nbsp;&nbsp;	
					<span>月份选择:</span>&nbsp;&nbsp;
					<input  id="attYearMonth"  type= "text" class= "easyui-datebox" > </input>&nbsp;&nbsp;
					<!-- <input id="month1" name="month1" style="line-height:18px;border:1px solid #95b9e7" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCoCl.action?bz_type='+'month_type',
						        method:'get',  
                                panelHeight:'auto'
                              "/>&nbsp;&nbsp;&nbsp;&nbsp;  -->
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doFxjtjMonthSearch()">按月查询</a>&nbsp;&nbsp;	
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doFxjtjWeekSearch()">本周查询</a>&nbsp;&nbsp;								
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reFxjtjFlash()">刷新(昨日)</a>
				 </form>
			</div>
		</div>       
	
  </body>

	
</html>
