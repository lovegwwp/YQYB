layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;
	/*extend({
	 laydate1: "laydate"
	 }).*/

	////日期控件////////////////////////////
  var start = {
		istime: true,
		format: 'YYYY-MM-DD',
		max: '2099-01-01',
		istoday: true,
		choose: function (datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
		}
	};

	var end = {
		istime: true,
		format: 'YYYY-MM-DD',
		max: '2099-01-01',
		istoday: true,
		choose: function (datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};

	var mmonth = {
		istime: false,
		format: 'YYYY-MM',
		max: '2099-01',
		type:'month',
		//istoday: true,
		choose: function (datas) {
			//start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};

	document.getElementById('start_time').onclick = function () {
		start.elem = this;
		laydate(start);
	};
	document.getElementById('end_time').onclick = function () {
		end.elem = this;
		laydate(end);
	};

	document.getElementById('month_time').onclick = function () {
		 mmonth.elem = this;
		 laydate(mmonth);
	 };
	$('#start_time').click(function () {
		$('#laydate_table').show();
	});
	$('#end_time').click(function () {
		$('#laydate_table').show();
	});
	$('#month_time').click(function () {
		$('#laydate_table').hide();
	});

	////日期控件/////上面///////////////////////

	//加载页面数据
	var hhrgxjtjData = '';
	///当日查询
	getTodayList();

	///当日查询
	$(".today_btn").click(function(){
		getTodayList();
	});

	///本周查询
	$(".week_btn").click(function(){
		console.log(111);
		getWeekList();
	});

	///日期查询
	$(".day_btn").click(function(){
		getDayList();
	});

	///年月查询
	$(".month_btn").click(function(){
		getMonthList();
	});

	function getTodayList(){
		$.ajax({
			url : "showGxj/list.action",
			type : "get",
			dataType : "json",
			success : function(data){
				hhrgxjtjData = data;
				if(window.sessionStorage.getItem("addhhrgxjtj")){
					var addhhrgxjtj = window.sessionStorage.getItem("addhhrgxjtj");
					hhrgxjtjData = JSON.parse(addhhrgxjtj).concat(hhrgxjtjData);
				}
				//执行加载数据的方法
				hhrgxjtjList();
			}
		})
	}

	function getWeekList(){
		$.ajax({
			url : "showGxj/listByWek.action",
			type : "get",
			dataType : "json",
			success : function(data){
				hhrgxjtjData = data;
				if(window.sessionStorage.getItem("addhhrgxjtj")){
					var addhhrgxjtj = window.sessionStorage.getItem("addhhrgxjtj");
					hhrgxjtjData = JSON.parse(addhhrgxjtj).concat(hhrgxjtjData);
				}
				//执行加载数据的方法
				hhrgxjtjList();
			}
		})
	}

	function getDayList(){
		console.log($('#start_time').val());
		console.log($('#end_time').val());
		$.ajax({
			url : "showGxj/listByDay.action",
			data:{
				beginTime: $('#start_time').val(),
				endTime : $('#end_time').val()
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrgxjtjData = data;
				if(window.sessionStorage.getItem("addhhrgxjtj")){
					var addhhrgxjtj = window.sessionStorage.getItem("addhhrgxjtj");
					hhrgxjtjData = JSON.parse(addhhrgxjtj).concat(hhrgxjtjData);
				}
				//执行加载数据的方法
				hhrgxjtjList();
			}
		})
	}

	function getMonthList(){
		console.log($('#month_time').val());
		$.ajax({
			url : "showGxj/listByMonth.action",
			type : "get",
			data:{
				month: $('#month_time').val()
			},
			dataType : "json",
			success : function(data){
				hhrgxjtjData = data;
				if(window.sessionStorage.getItem("addhhrgxjtj")){
					var addhhrgxjtj = window.sessionStorage.getItem("addhhrgxjtj");
					hhrgxjtjData = JSON.parse(addhhrgxjtj).concat(hhrgxjtjData);
				}
				//执行加载数据的方法
				hhrgxjtjList();
			}
		})
	}
	//查询
	/*$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "hhrgxjtjList.action",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addhhrgxjtj")){
							var addhhrgxjtj = window.sessionStorage.getItem("addhhrgxjtj");
							hhrgxjtjData = JSON.parse(addhhrgxjtj).concat(data);
						}else{
							hhrgxjtjData = data;
						}
						for(var i=0;i<hhrgxjtjData.length;i++){
							var hhrgxjtjStr = hhrgxjtjData[i];
							var selectStr = $(".search_input").val();
		            		function changeStr(data){
		            			var dataStr = '';
		            			var showNum = data.split(eval("/"+selectStr+"/ig")).length - 1;
		            			if(showNum > 1){
									for (var j=0;j<showNum;j++) {
		            					dataStr += data.split(eval("/"+selectStr+"/ig"))[j] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>";
		            				}
		            				dataStr += data.split(eval("/"+selectStr+"/ig"))[showNum];
		            				return dataStr;
		            			}else{
		            				dataStr = data.split(eval("/"+selectStr+"/ig"))[0] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>" + data.split(eval("/"+selectStr+"/ig"))[1];
		            				return dataStr;
		            			}
		            		}
		            		//网站名称
		            		if(hhrgxjtjStr.username.indexOf(selectStr) > -1){
								hhrgxjtjStr["username"] = changeStr(hhrgxjtjStr.username);
		            		}

		            		if(hhrgxjtjStr.description.indexOf(selectStr) > -1){
								hhrgxjtjStr["description"] = changeStr(hhrgxjtjStr.description);
		            		}
		            		if(hhrgxjtjStr.username.indexOf(selectStr)>-1 || hhrgxjtjStr.description.indexOf(selectStr)>-1 ){
		            			newArray.push(hhrgxjtjStr);
		            		}
		            	}
						hhrgxjtjData = newArray;
		            	hhrgxjtjList(hhrgxjtjData);
					}
				})
            	
                layer.close(index);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

*/
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			//layui.layer.full(index);
		})
		//layui.layer.full(index);


	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		data.elem.checked;
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})
 

	function hhrgxjtjList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrgxjtjData.concat().splice(curr * nums - nums, nums);
			} else {
				currData = that.concat().splice(curr * nums - nums, nums);
			}
			if (currData.length != 0) {
				for (var i = 0; i < currData.length; i++) {
					dataHtml += '<tr>'
						+ '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
						/*+'<td>'+currData[i].roleId+'</td>'*/
						/*+'<td align="left">'+currData[i].roleId+'</td>'*/
						/*	+'<td><a style="color:#1E9FFF;" target="_blank" href="'+currData[i].linksUrl+'">'+currData[i].linksUrl+'</a></td>'*/
						+ '<td>' + currData[i].amount + '</td>'
						+ '<td>' + currData[i].cashScore + '</td>'
						+ '<td>' + currData[i].shoppingScore + '</td>'
						+ '<td>' + currData[i].elecScore + '</td>'
						+ '</tr>';
				}
			} else {
				dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if (that) {
			hhrgxjtjData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrgxjtjData.length / nums),
			jump: function (obj) {
				$(".hhrgxjtj_content").html(renderDate(hhrgxjtjData, obj.curr));
				$('.hhrgxjtj_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


})

