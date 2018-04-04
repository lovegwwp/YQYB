layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var cjblData = '';
	$.ajax({
		url : "getcjbl.action",
		type : "get",
		dataType : "json",
		success : function(data){
			cjblData = data;
			if(window.sessionStorage.getItem("addcjbl")){
				var addcjbl = window.sessionStorage.getItem("addcjbl");
				cjblData = JSON.parse(addcjbl).concat(cjblData);
			}
			//执行加载数据的方法
			cjblList();
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
	$("body").on("click",".cjbl_edit",function(){  //编辑
        var bz_id =$(this).attr("data-bz_id") ;
        var bz_value =$(this).attr("data-bz_value") ;
		var bz_info =$(this).attr("data-bz_info") ;
        var uid =$(this).attr("data-id") ;
        var index = layui.layer.open({
            title : "修改数值",
            type : 2,
            area : ['400px' , '320px'],
            maxmin: true,
            content : "jsp/cjbl_edit.jsp?bz_id="+bz_id+"&bz_value="+bz_value+"&uid="+uid+"&bz_info="+bz_info,
            success : function(layero, index){
                setTimeout(function(){
                },500)
            }
        })

	})

	

	function cjblList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = cjblData.concat().splice(curr*nums-nums, nums);
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
			    	+'<td>'+currData[i].bz_id+'</td>'
			    	+'<td>'+currData[i].bz_value+'</td>'
			    	+'<td>'+currData[i].bz_info+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini cjbl_edit" data-bz_id="'+currData[i].bz_id+'" data-bz_value="'+currData[i].bz_value+'" data-bz_info="'+currData[i].bz_info+'"  data-id="'+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
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
			cjblData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(cjblData.length/nums),
			jump : function(obj){
				$(".cjbl_content").html(renderDate(cjblData,obj.curr));
				$('.cjbl_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
	
})

