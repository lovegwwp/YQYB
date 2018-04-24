layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var zjzlpwData = '';


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

	//查询市场
	$(".search_btn").click(function(){
		var  sc = $("#scId").val();
		if(!sc){
			layer.alert("请选择市场!");
			return;
		}
		$.ajax({
				url : "jrc/user/list.action",
				data:{
					zjUid:sc
				},
				type : "get",
				dataType : "json",
				success : function(data){
					if(window.sessionStorage.getItem("addzjzlpw")){
						var addzjzlpw = window.sessionStorage.getItem("addzjzlpw");
						zjzlpwData = JSON.parse(addzjzlpw).concat(data);
					}else{
						zjzlpwData = data;
					}
					zjzlpwList(zjzlpwData);
				}
			});
			//layer.close(index);
	})


	//模糊查询
	$(".like_btn").click(function(){
		var  sc = $("#scId").val();
		if(!sc){
			layer.alert("请选择市场!");
			return;
		}
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "jrc/user/list.action",
					data:{
						zjUid:sc
					},
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addzjzlpw")){
							var addzjzlpw = window.sessionStorage.getItem("addzjzlpw");
							zjzlpwData = JSON.parse(addzjzlpw).concat(data);
						}else{
							zjzlpwData = data;
						}
						for(var i=0;i<zjzlpwData.length;i++){
							var zjzlpwStr = zjzlpwData[i];
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

		            		if(zjzlpwStr.uAccount.indexOf(selectStr) > -1){
								zjzlpwStr["uAccount"] = changeStr(zjzlpwStr.uAccount);
		            		}
		            		if(zjzlpwStr.uAccount.indexOf(selectStr)>-1 ){
		            			newArray.push(zjzlpwStr);
		            		}
		            	}
						zjzlpwData = newArray;
		            	zjzlpwList(zjzlpwData);
					}
				})
            	
                layer.close(index);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加用户
	$(".zjzlpwAdd_btn").click(function(){
		var zjName = $("#scId").find("option:selected").text();
		var zjUid = $("#scId").val();
		if(!zjUid){
			layer.alert("请选择市场！");
			return;
		}
		var index = layui.layer.open({
			title : "添加市场人员",
			type : 2,
			area : ['400px' , '420px'],
			maxmin: true,
			content : "jsp/zjzlpwAdd.jsp?zjUid="+zjUid+"&zjName="+zjName,
			success : function(layero, index){
				setTimeout(function(){
				},500)
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			//layui.layer.full(index);
		})
	})


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
 


	$("body").on("click",".zjzlpw_del",function(){  //删除
		var _this = $(this);
        layer.alert(_this.attr("data-id"));
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
        delteOption(_this.attr("data-id"));
		ayer.close(index);
		});
	})

	function zjzlpwList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = zjzlpwData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+'<td>'+currData[i].uId+'</td>'
			    	+'<td>'+currData[i].uAccount+'</td>'
			    	+'<td>'+currData[i].parentId+'</td>'
					+'<td>'+getPart(currData[i].depart)+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini zjzlpw_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if(that){
			zjzlpwData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(zjzlpwData.length/nums),
			jump : function(obj){
				$(".zjzlpw_content").html(renderDate(zjzlpwData,obj.curr));
				$('.zjzlpw_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}


	function getPart(bz){
		if(bz=='0'){
			return '顶层';
		}else if(bz=='1'){
			return '市场A';
		}else if(bz=='2'){
			return '市场B';
		}else {
			return '未知';
		}

	}

	/////删除业务
	function  delteOption(ids){
        $.ajax({
            url : 'jrc/deleteJrc.action',
            data:{
                strIds :ids
            },
            type : 'POST',
            success:function(result){
                layer.alert(result.message, function () {
                    //刷新父页面
                    parent.location.reload();
                });
            } ,
            error:function(result){
                layer.alert(result.message, function () {
                    //刷新父页面
                    parent.location.reload();
                });
            }
        });
    }
})

