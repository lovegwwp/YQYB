layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var zlfpscData = '';
	$.ajax({
		url : "zl/selectAllZl.action",
		type : "get",
		dataType : "json",
		success : function(data){
			zlfpscData = data;
			if(window.sessionStorage.getItem("addzlfpsc")){
				var addzlfpsc = window.sessionStorage.getItem("addzlfpsc");
				zlfpscData = JSON.parse(addzlfpsc).concat(zlfpscData);
			}
			//执行加载数据的方法
			zlfpscList();
		}
	})

	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "zl/selectAllZl.action",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addzlfpsc")){
							var addzlfpsc = window.sessionStorage.getItem("addzlfpsc");
							zlfpscData = JSON.parse(addzlfpsc).concat(data);
						}else{
							zlfpscData = data;
						}
						for(var i=0;i<zlfpscData.length;i++){
							var zlfpscStr = zlfpscData[i];
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
		            		if(zlfpscStr.zjCode.indexOf(selectStr) > -1){
								zlfpscStr["zjCode"] = changeStr(zlfpscStr.zjCode);
		            		}

		            		if(zlfpscStr.zjName.indexOf(selectStr) > -1){
								zlfpscStr["zjName"] = changeStr(zlfpscStr.zjName);
		            		}
		            		if(zlfpscStr.zjCode.indexOf(selectStr)>-1 || zlfpscStr.zjName.indexOf(selectStr)>-1 ){
		            			newArray.push(zlfpscStr);
		            		}
		            	}
						zlfpscData = newArray;
		            	zlfpscList(zlfpscData);
					}
				})
            	
                layer.close(index);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加市场助理
	$(".zlfpscAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加市场助理",
			type : 2,
			area : ['400px' , '320px'],
			maxmin: true,
			content : "jsp/zlfpscAdd.jsp",
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
	$("body").on("click",".zlfpsc_edit",function(){  //编辑
        var zjCode =$(this).attr("data-zjCode") ;
		var zjName =$(this).attr("data-zjName") ;
        var zjUid =$(this).attr("data-zjUid") ;
		var uuid =$(this).attr("data-id") ;
        var uId =$(this).attr("data-uId") ;
		console.log("1111"+uuid);
        var index = layui.layer.open({
            title : "修改市场助理",
            type : 2,
            area : ['400px' , '320px'],
            maxmin: true,
            content : "jsp/zlfpscAdd.jsp?uId="+uId+"&zjUid="+zjUid+"&zjCode="+zjCode+"&zjName="+zjName+"&uuid="+uuid,
            success : function(layero, index){
                setTimeout(function(){
                },500)
            }
        })

	})

	$("body").on("click",".zlfpsc_del",function(){  //删除
		var _this = $(this);
        layer.alert(_this.attr("data-id"));
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
		/*	//_this.parents("tr").remove();
			for(var i=0;i<zlfpscData.length;i++){
				if(zlfpscData[i].id == _this.attr("data-id")){
					zlfpscData.splice(i,1);
					zlfpscList(zlfpscData);
				}
			}*/
		console.log(_this.attr("data-id"));
            delteOption(_this.attr("data-id"));
			layer.close(index);
		});
	})

	function zlfpscList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = zlfpscData.concat().splice(curr*nums-nums, nums);
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
			    	+'<td>'+currData[i].uId+'</td>'
			    	+'<td>'+currData[i].zjUid+'</td>'
			    	+'<td>'+currData[i].zjCode+'</td>'
					+'<td>'+currData[i].zjName+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini zlfpsc_edit" data-uId="'+currData[i].uId+'" data-zjUid="'+currData[i].zjUid+'"   data-zjCode="'+currData[i].zjCode+'"   data-zjName="'+currData[i].zjName+'"   data-id="'+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini zlfpsc_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="6">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if(that){
			zlfpscData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(zlfpscData.length/nums),
			jump : function(obj){
				$(".zlfpsc_content").html(renderDate(zlfpscData,obj.curr));
				$('.zlfpsc_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
	/////删除业务
	function  delteOption(ids){
        $.ajax({
            url : 'zl/deleteZl.action',
            data:{
				id :ids
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

