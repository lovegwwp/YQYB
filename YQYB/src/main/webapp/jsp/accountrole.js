layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var rolesData = '';
	$.ajax({
		url : "roleList.action",
		type : "get",
		dataType : "json",
		success : function(data){
			rolesData = data;
			if(window.sessionStorage.getItem("addroles")){
				var addroles = window.sessionStorage.getItem("addroles");
				rolesData = JSON.parse(addroles).concat(rolesData);
			}
			//执行加载数据的方法
			rolesList();
		}
	})

	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "roleList.action",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addroles")){
							var addroles = window.sessionStorage.getItem("addroles");
							rolesData = JSON.parse(addroles).concat(data);
						}else{
							rolesData = data;
						}
						for(var i=0;i<rolesData.length;i++){
							var rolesStr = rolesData[i];
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

		            		if(rolesStr.roleSign.indexOf(selectStr) > -1){
								rolesStr["roleSign"] = changeStr(rolesStr.roleSign);
		            		}

		            		if(rolesStr.description.indexOf(selectStr) > -1){
								rolesStr["description"] = changeStr(rolesStr.description);
		            		}
							if(rolesStr.roleSign.indexOf(selectStr)>-1 || rolesStr.description.indexOf(selectStr)>-1 ){
								newArray.push(rolesStr);
							}

		            	}
						rolesData = newArray;
		            	rolesList(rolesData);
					}
				})
            	
                layer.close(index);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加用户
	$(".rolesAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加角色",
			type : 2,
			area : ['400px' , '550px'],
			maxmin: true,
			content : "jsp/roleAdd.jsp",
			success : function(layero, index){
				setTimeout(function(){
				},500)
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			//layui.layer.full(index);
		})
		//layui.layer.full(index);
	})

	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.roles_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.roles_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                var strIds="";
	            	//删除数据
	            	for(var j=0;j<$checked.length;j++){
	            		//for(var i=0;i<rolesData.length;i++){
							//if(rolesData[i].id == $checked.eq(j).parents("tr").find(".roles_del").attr("data-id")){
								/*rolesData.splice(i,1);
								rolesList(rolesData);*/
								var temp = $checked.eq(j).parents("tr").find(".roles_del").attr("data-id");
                                strIds =strIds + temp+",";
                                console.log(temp);
                                console.log(strIds);
							//}
						//}
	            	}
	            	delteOption(strIds);
	            	$('.roles_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);

	        })
		}else{
			layer.msg("请选择需要删除的内容");
		}
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
 
	//操作
	$("body").on("click",".roles_edit",function(){  //编辑
        var roleSign =$(this).attr("data-roleSign") ;
        var description =$(this).attr("data-description") ;
        var uid =$(this).attr("data-id") ;
		console.log(roleSign);
        var index = layui.layer.open({
            title : "修改角色",
            type : 2,
            area : ['400px' , '550px'],
            maxmin: true,
            content : "jsp/roleAdd.jsp?roleSign="+roleSign+"&description="+description+"&uid="+uid,
            success : function(layero, index){
                setTimeout(function(){
                },500)
            }
        })

	})

	$("body").on("click",".roles_del",function(){  //删除
		var _this = $(this);
        layer.alert(_this.attr("data-id"));
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
		/*	//_this.parents("tr").remove();
			for(var i=0;i<rolesData.length;i++){
				if(rolesData[i].id == _this.attr("data-id")){
					rolesData.splice(i,1);
					rolesList(rolesData);
				}
			}*/
            delteOption(_this.attr("data-id"));
			layer.close(index);
		});
	})

	function rolesList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = rolesData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			        /*+'<td>'+currData[i].roleId+'</td>'*/
					/*+'<td align="left">'+currData[i].roleId+'</td>'*/
			    /*	+'<td><a style="color:#1E9FFF;" target="_blank" href="'+currData[i].linksUrl+'">'+currData[i].linksUrl+'</a></td>'*/
			    	+'<td>'+currData[i].roleSign+'</td>'
			    	+'<td>'+currData[i].description+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini roles_edit"   data-roleSign="'+currData[i].roleSign+'"  data-id="'+currData[i].id+'"  data-description="'+currData[i].description+'"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini roles_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
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
			rolesData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(rolesData.length/nums),
			jump : function(obj){
				$(".roles_content").html(renderDate(rolesData,obj.curr));
				$('.roles_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
	/////删除业务
	function  delteOption(ids){
        $.ajax({
            url : 'delRoles.action',
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

