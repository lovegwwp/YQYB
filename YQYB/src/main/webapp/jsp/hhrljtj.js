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
	var hhrljtjData = '';
	var hhrljtjLsitData = '';
	$(".hhrljtj_list").show();
	///昨日查询
	getYestodayList();

	///昨日查询
	$(".today_btn").click(function(){
		$(".hhrljtj_list").show();
		getYestodayList();
	});

	///本周查询
	$(".week_btn").click(function(){
		$(".hhrljtj_list").show();
		getWeekList();
	});

	///日期查询
	$(".day_btn").click(function(){
		$(".hhrljtj_list").show();
		getDayList();
	});

	///年月查询
	$(".month_btn").click(function(){
		$(".hhrljtj_list").show();
		getMonthList();
	});

	///历史累计查询
	$(".history_btn").click(function(){
		$(".hhrljtj_list").hide();
		getHistoryList();
	});


	function getYestodayList(){
		$.ajax({
			url : "showScj/list.action",
			data:{
				zjUid:"",
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjList")){
					var addhhrljtjList = window.sessionStorage.getItem("addhhrljtjList");
					hhrljtjLsitData = JSON.parse(addhhrljtjList).concat(hhrljtjLsitData);
				}
				//执行加载数据的方法
				hhrljtjList();
				////加载统计
				$.ajax({
					url : "showScj/info.action",
					type : "get",
					dataType : "json",
					success : function(data){
						hhrljtjData = data;
						if(window.sessionStorage.getItem("addhhrljtj")){
							var addhhrljtj = window.sessionStorage.getItem("addhhrljtj");
							hhrljtjData = JSON.parse(addhhrljtj).concat(hhrljtjData);
						}

						//统计
						//hhrljtj();
					}
				})
			}
		})
	}

	function getHistoryList(){
		$.ajax({
			url : "showScj/total.action",
			data:{
				zjUid:"",
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjList")){
					var addhhrljtjList = window.sessionStorage.getItem("addhhrljtjList");
					hhrljtjLsitData = JSON.parse(addhhrljtjList).concat(hhrljtjLsitData);
				}
				//执行加载数据的方法
				hhrljtjList();
				//统计
				hhrljtj();
			}
		})
	}

	function getWeekList(){
		$.ajax({
			url : "showScj/listByWek.action",
			data:{
				zjUid:"",
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjList")){
					var addhhrljtjList = window.sessionStorage.getItem("addhhrljtjList");
					hhrljtjLsitData = JSON.parse(addhhrljtjList).concat(hhrljtjLsitData);
				}
				//执行加载数据的方法
				hhrljtjList();
				////加载统计
				$.ajax({
					url : "showScj/wekInfo.action",
					type : "get",
					dataType : "json",
					success : function(data){
						hhrljtjData = data;
						if(window.sessionStorage.getItem("addhhrljtj")){
							var addhhrljtj = window.sessionStorage.getItem("addhhrljtj");
							hhrljtjData = JSON.parse(addhhrljtj).concat(hhrljtjData);
						}
						//统计
						hhrljtj();
					}
				})
			}
		})
	}

	function getDayList(){
		console.log($('#start_time').val());
		console.log($('#end_time').val());
		$.ajax({
			url : "showScj/listByDay.action",
			data:{
				beginTime: $('#start_time').val(),
				endTime : $('#end_time').val(),
				zjUid:""
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjList")){
					var addhhrljtjList = window.sessionStorage.getItem("addhhrljtjList");
					hhrljtjLsitData = JSON.parse(addhhrljtjList).concat(hhrljtjLsitData);
				}
				//执行加载数据的方法
				hhrljtjList();
				////加载统计
				$.ajax({
					url : "showScj/dayInfo.action",
					data:{
						beginTime: $('#start_time').val(),
						endTime : $('#end_time').val()
					},
					type : "get",
					dataType : "json",
					success : function(data){
						hhrljtjData = data;
						if(window.sessionStorage.getItem("addhhrljtj")){
							var addhhrljtj = window.sessionStorage.getItem("addhhrljtj");
							hhrljtjData = JSON.parse(addhhrljtj).concat(hhrljtjData);
						}
						//统计
						hhrljtj();
					}
				})
			}
		})
	}

	function getMonthList(){
		console.log($('#month_time').val());
		$.ajax({
			url : "showScj/listByMonth.action",
			type : "get",
			data:{
				month: $('#month_time').val(),
				zjUid:""
			},
			dataType : "json",
			success : function(data){
				hhrljtjLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjList")){
					var addhhrljtjList = window.sessionStorage.getItem("addhhrljtjList");
					hhrljtjLsitData = JSON.parse(addhhrljtjList).concat(hhrljtjLsitData);
				}
				//执行加载数据的方法
				hhrljtjList();
				////加载统计
				$.ajax({
					url : "showScj/monthInfo.action",
					type : "get",
					data:{
						month: $('#month_time').val()
					},
					dataType : "json",
					success : function(data){
						hhrljtjData = data;
						if(window.sessionStorage.getItem("addhhrljtj")){
							var addhhrljtj = window.sessionStorage.getItem("addhhrljtj");
							hhrljtjData = JSON.parse(addhhrljtj).concat(hhrljtjData);
						}
						//统计
						hhrljtj();
					}
				})
			}
		})
	}

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
 

	function hhrljtj(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrljtjData.concat().splice(curr * nums - nums, nums);
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
			hhrljtjData = that;
		}
		laypage({
			cont: "page1",
			pages: Math.ceil(hhrljtjData.length / nums),
			jump: function (obj) {
				$(".hhrljtj_content").html(renderDate(hhrljtjData, obj.curr));
				$('.hhrljtj_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


	function hhrljtjList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrljtjLsitData.concat().splice(curr * nums - nums, nums);
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
						+ '<td>' + currData[i].uId + '</td>'
						+ '<td>' + currData[i].uName + '</td>'
						+ '<td>' + currData[i].pv + '</td>'
						+ '<td>' + currData[i].aId + '</td>'
						+ '<td>' + currData[i].aName + '</td>'
						+ '<td>' + currData[i].aPv + '</td>'
						+ '<td>' + currData[i].bId + '</td>'
						+ '<td>' + currData[i].bName + '</td>'
						+ '<td>' + currData[i].bPv + '</td>'
						+ '</tr>';
				}
			} else {
				dataHtml = '<tr><td colspan="10">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if (that) {
			hhrljtjLsitData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrljtjLsitData.length / nums),
			jump: function (obj) {
				$(".hhrljtjlist_content").html(renderDate(hhrljtjLsitData, obj.curr));
				$('.hhrljtjlist_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


})

