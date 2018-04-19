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



	document.getElementById('month_time').onclick = function () {
		 mmonth.elem = this;
		 laydate(mmonth);
	 };

	$('#month_time').click(function () {
		$('#laydate_table').hide();
	});

	////日期控件/////上面///////////////////////

	//加载页面数据
	var hhrljtjhzData = '';
	///月查询
	getMonthList();


	///年月查询
	$(".month_btn").click(function(){
		getMonthList();
	});

	function getMonthList(){
		var month = $('#month_time').val();
		console.log(month);
		if(!month){
			var d="";
			var myDate = new Date();
			d = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
			var m = myDate.getMonth() + 1;
			if (m >= 1 && m <= 9) {
				m = "0" + m;
			}
			d= d+"-"+m; //获取当前月份(0-11,0代表1月)
			month =d;
		}
		$.ajax({
			url : "showScj/totalList.action",
			type : "get",
			data:{
				month: month
			},
			dataType : "json",
			success : function(data){
				console.log(data);
				hhrljtjhzData = data;
				if(window.sessionStorage.getItem("addhhrljtjhz")){
					var addhhrljtjhz = window.sessionStorage.getItem("addhhrljtjhz");
					hhrljtjhzData = JSON.parse(addhhrljtjhz).concat(hhrljtjhzData);
				}
				//执行加载数据的方法
				hhrljtjhzList();
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
 

	function hhrljtjhzList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrljtjhzData.concat().splice(curr * nums - nums, nums);
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
						+ '<td>' + currData[i].showTime + '</td>'
						+ '<td>' + currData[i].amount + '</td>'
						+ '<td>' + currData[i].cashScore + '</td>'
						+ '<td>' + currData[i].shoppingScore + '</td>'
						+ '<td>' + currData[i].elecScore + '</td>'
						+ '</tr>';
				}
			} else {
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if (that) {
			hhrljtjhzData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrljtjhzData.length / nums),
			jump: function (obj) {
				$(".hhrljtjhz_content").html(renderDate(hhrljtjhzData, obj.curr));
				$('.hhrljtjhz_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


})

