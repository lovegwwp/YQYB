layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
		$ = layui.jquery;

	//加载页面数据
	var hhrcwjdtjinfoData = '';
	$.ajax({
		url : "ht/total.action",
		data:{
			beginTime:'' ,
			endTime : '',
			tjType :'4'
		},
		type : "get",
		dataType : "json",
		success : function(data){
			hhrcwjdtjinfoData = data;
			if(window.sessionStorage.getItem("addhhrcwjdtjinfo")){
				var addhhrcwjdtjinfo = window.sessionStorage.getItem("addhhrcwjdtjinfo");
				hhrcwjdtjinfoData = JSON.parse(addhhrcwjdtjinfo).concat(hhrcwjdtjinfoData);
			}
			//执行加载数据的方法
			hhrcwjdtjinfoList();
		}
	})

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
 

	function hhrcwjdtjinfoList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrcwjdtjinfoData.concat().splice(curr * nums - nums, nums);
			} else {
				currData = that.concat().splice(curr * nums - nums, nums);
			}
			if (currData.length != 0) {
				for (var i = 0; i < currData.length; i++) {

					dataHtml += '<tr>'
						+ '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
						+ '<td>' + currData[i].totald + '</td>'
						+ '<td>' + currData[i].totalc + '</td>'
						+ '<td>' + currData[i].totale + '</td>'
						+ '</tr>';
				}
			} else {
				dataHtml = '<tr><td colspan="4">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if (that) {
			hhrcwjdtjinfoData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrcwjdtjinfoData.length / nums),
			jump: function (obj) {
				$(".hhrcwjdtjinfo_content").html(renderDate(hhrcwjdtjinfoData, obj.curr));
				$('.hhrcwjdtjinfo_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


})

