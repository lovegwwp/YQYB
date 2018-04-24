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


	$('#start_time').click(function () {
		$('#laydate_table').show();
	});
	$('#end_time').click(function () {
		$('#laydate_table').show();
	});


	////日期控件/////上面///////////////////////





	//加载页面数据
	var hhrcwtjData = '';
	var hhrcwtjLsitData = '';
	$(".hhrcwtj_list").show();
	///日期查询
	getDayList();


	///日期查询
	$(".day_btn").click(function(){
		$(".hhrcwtj_list").show();
		//showHide();
		getDayList();

	});

	///报单券充值
	$(".bd_btn").click(function(){
		openPage("报单充值","jsp/hhrcwtjAdd.jsp");
	});

	///借贷充值
	$(".jd_btn").click(function(){
		openPage("借贷报单充值","jsp/hhrcwtjAdd.jsp?bz=99");
	});

	function openPage(title,content){
		var index = layui.layer.open({
			title : title,
			type : 2,
			area : ['500px' , '620px'],
			maxmin: true,
			content : content,
			success : function(layero, index){
				setTimeout(function(){
				},500)
			}
		})
	}

	function getDayList(){
		console.log($('#start_time').val());
		console.log($('#end_time').val());
		$.ajax({
			url : "ht/bill.action",
			data:{
				beginTime: $('#start_time').val(),
				endTime : $('#end_time').val(),
				tjType :$('#czId').val()
			},
			type : "get",
			dataType : "json",
			success : function(data){
				hhrcwtjLsitData = data;
				if(window.sessionStorage.getItem("addhhrcwtjList")){
					var addhhrcwtjList = window.sessionStorage.getItem("addhhrcwtjList");
					hhrcwtjLsitData = JSON.parse(addhhrcwtjList).concat(hhrcwtjLsitData);
				}
				//执行加载数据的方法
				hhrcwtjList();
				////加载统计
				$.ajax({
					url : "ht/total.action",
					data:{
						beginTime: $('#start_time').val(),
						endTime : $('#end_time').val(),
						tjType :$('#czId').val()

					},
					type : "get",
					dataType : "json",
					success : function(data){
						hhrcwtjData = data;
						if(window.sessionStorage.getItem("addhhrcwtj")){
							var addhhrcwtj = window.sessionStorage.getItem("addhhrcwtj");
							hhrcwtjData = JSON.parse(addhhrcwtj).concat(hhrcwtjData);
						}
						//统计
						hhrcwtj();
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
 

	function hhrcwtj(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrcwtjData.concat().splice(curr * nums - nums, nums);
			} else {
				currData = that.concat().splice(curr * nums - nums, nums);
			}
			if (currData.length != 0) {
				for (var i = 0; i < currData.length; i++) {
					dataHtml += '<tr>'
						+ '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
						+ '<td>' + currData[i].totald + '</td>'
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
			hhrcwtjData = that;
		}
		laypage({
			cont: "page1",
			pages: Math.ceil(hhrcwtjData.length / nums),
			jump: function (obj) {
				$(".hhrcwtj_content").html(renderDate(hhrcwtjData, obj.curr));
				$('.hhrcwtj_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


	function hhrcwtjList(that) {
		//渲染数据
		function renderDate(data, curr) {
			$('#czId').val()
			var dataHtml = '';
			if (!that) {
				currData = hhrcwtjLsitData.concat().splice(curr * nums - nums, nums);
			} else {
				currData = that.concat().splice(curr * nums - nums, nums);
			}
			if (currData.length != 0) {
				for (var i = 0; i < currData.length; i++) {
					dataHtml += '<tr>'
						+ '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
						+ '<td>' + currData[i].uUuid + '</td>'
						+ '<td>' + getSecoCate(currData[i].secoCate) + '</td>'
						+ '<td>' + currData[i].score + '</td>'
						+ '<td>' + currData[i].orderSn + '</td>'
					    + '<td class ="imgth"> <img style="'+'width:90px; height:90px"'+'src="'+ currData[i].detail +'"/></td>'
						+ '<td>' + currData[i].cjsj + '</td>'
						+ '<td>' + currData[i].zzCode + '</td></tr>';
					//if($('#czId').val()==3||$('#czId').val()==4){
						//dataHtml += '<td> <img style="'+'width:90px; height:90px"'+'src="'+ currData[i].detail +'"/></td></tr>';

					//}

				}
			} else {
				dataHtml = '<tr><td colspan="10">暂无数据</td></tr>';
			}
			return dataHtml;
		}
		showHide();
		//分页
		var nums = 10; //每页出现的数据量
		if (that) {
			hhrcwtjLsitData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrcwtjLsitData.length / nums),
			jump: function (obj) {
				$(".hhrcwtjlist_content").html(renderDate(hhrcwtjLsitData, obj.curr));
				$('.hhrcwtjlist_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}

	////1支付宝，2微信，3线下充值，4借贷充值'
	function getSecoCate(bz){
		if(bz=='1'){
			return '支付宝';
		}else if(bz=='2'){
			return '微信';
		}else if(bz=='3'){
			return '线下充值';
		}else if(bz=='4'){
			return '借贷充值';
		}else {
			return '未知';
		}

	}

	function showHide(){
		if($('#czId').val()==3||$('#czId').val()==4){
			$('.imgth').show();
		}else{
			$('.imgth').hide();
		}
	}



})

