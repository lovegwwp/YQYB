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


	//////可选择市场
	$.ajax({
		url : 'zl/selectZl.action',
		type : 'POST',
		success : function(data) {
			var scs = eval(data);
			$(scs).each(
				function(index) {
					var sc = scs[index];
					var optionStr = "";
					optionStr += "<option value='" + sc.zjUid + "'>"
						+ sc.zjName + "</option>";
					//alert(optionStr);
					$("#scId").append(optionStr);
					layui.form().render('select','scFilter');
					//setValue();

				});
		},
		error : function(data) {
			alert('数据加载错误，请重试！');
		}
	});

	////日期控件/////上面///////////////////////

	//加载页面数据
	var hhrljtjzjzlData = '';
	var hhrljtjzjzlLsitData = '';
	///昨日查询
	///getYestodayList();

	///昨日查询
	$(".today_btn").click(function(){
		getYestodayList();
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

	function getYestodayList(){
		var  sc = $("#scId").val();
		if(!sc){
			layer.alert("请选择市场!");
			return;
		}
		$.ajax({
			url : "showScj/list.action",
			data:{
				zjUid:sc,
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjzjzlLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjzjzlList")){
					var addhhrljtjzjzlList = window.sessionStorage.getItem("addhhrljtjzjzlList");
					hhrljtjzjzlLsitData = JSON.parse(addhhrljtjzjzlList).concat(hhrljtjzjzlLsitData);
				}
				//执行加载数据的方法
				hhrljtjzjzlList();

			}
		})
	}

	function getWeekList(){
		var  sc = $("#scId").val();
		if(!sc){
			layer.alert("请选择市场!");
			return;
		}
		$.ajax({
			url : "showScj/listByWek.action",
			data:{
				zjUid:sc,
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjzjzlLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjzjzlList")){
					var addhhrljtjzjzlList = window.sessionStorage.getItem("addhhrljtjzjzlList");
					hhrljtjzjzlLsitData = JSON.parse(addhhrljtjzjzlList).concat(hhrljtjzjzlLsitData);
				}
				//执行加载数据的方法
				hhrljtjzjzlList();

			}
		})
	}

	function getDayList(){
		var  sc = $("#scId").val();
		if(!sc){
			layer.alert("请选择市场!");
			return;
		}
		$.ajax({
			url : "showScj/listByDay.action",
			data:{
				beginTime: $('#start_time').val(),
				endTime : $('#end_time').val(),
				zjUid:sc
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrljtjzjzlLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjzjzlList")){
					var addhhrljtjzjzlList = window.sessionStorage.getItem("addhhrljtjzjzlList");
					hhrljtjzjzlLsitData = JSON.parse(addhhrljtjzjzlList).concat(hhrljtjzjzlLsitData);
				}
				//执行加载数据的方法
				hhrljtjzjzlList();

			}
		})
	}

	function getMonthList(){
		console.log($('#month_time').val());
		var  sc = $("#scId").val();
		if(!sc){
			layer.alert("请选择市场!");
			return;
		}
		$.ajax({
			url : "showScj/listByMonth.action",
			type : "get",
			data:{
				month: $('#month_time').val(),
				zjUid:sc
			},
			dataType : "json",
			success : function(data){
				hhrljtjzjzlLsitData = data;
				if(window.sessionStorage.getItem("addhhrljtjzjzlList")){
					var addhhrljtjzjzlList = window.sessionStorage.getItem("addhhrljtjzjzlList");
					hhrljtjzjzlLsitData = JSON.parse(addhhrljtjzjzlList).concat(hhrljtjzjzlLsitData);
				}
				//执行加载数据的方法
				hhrljtjzjzlList();

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


	function hhrljtjzjzlList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrljtjzjzlLsitData.concat().splice(curr * nums - nums, nums);
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
			hhrljtjzjzlLsitData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrljtjzjzlLsitData.length / nums),
			jump: function (obj) {
				$(".hhrljtjzjzllist_content").html(renderDate(hhrljtjzjzlLsitData, obj.curr));
				$('.hhrljtjzjzllist_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


})

