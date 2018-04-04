layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var accountlogData = '';
	$.ajax({
		url : "accountlogList.action",
		type : "get",
		dataType : "json",
		success : function(data){
			accountlogData = data;
			if(window.sessionStorage.getItem("addaccountlog")){
				var addaccountlog = window.sessionStorage.getItem("addaccountlog");
				accountlogData = JSON.parse(addaccountlog).concat(accountlogData);
			}
			//执行加载数据的方法
			accountlogList();
		}
	})

	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "accountlogList.action",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addaccountlog")){
							var addaccountlog = window.sessionStorage.getItem("addaccountlog");
							accountlogData = JSON.parse(addaccountlog).concat(data);
						}else{
							accountlogData = data;
						}
						for(var i=0;i<accountlogData.length;i++){
							var accountlogStr = accountlogData[i];
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
		            		if(accountlogStr.username.indexOf(selectStr) > -1){
								accountlogStr["username"] = changeStr(accountlogStr.username);
		            		}

		            		if(accountlogStr.description.indexOf(selectStr) > -1){
								accountlogStr["description"] = changeStr(accountlogStr.description);
		            		}
		            		if(accountlogStr.username.indexOf(selectStr)>-1 || accountlogStr.description.indexOf(selectStr)>-1 ){
		            			newArray.push(accountlogStr);
		            		}
		            	}
						accountlogData = newArray;
		            	accountlogList(accountlogData);
					}
				})
            	
                layer.close(index);
		}else{
			layer.msg("请输入需要查询的内容");
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
 

	function accountlogList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = accountlogData.concat().splice(curr * nums - nums, nums);
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
						+ '<td>' + currData[i].username + '</td>'
						+ '<td>' + currData[i].description + '</td>'
						+ '<td>' + currData[i].cjsj + '</td>'
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
			accountlogData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(accountlogData.length / nums),
			jump: function (obj) {
				$(".accountlog_content").html(renderDate(accountlogData, obj.curr));
				$('.accountlog_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}


})

