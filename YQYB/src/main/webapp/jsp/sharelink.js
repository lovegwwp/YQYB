layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var sharelinkData = '';
	$.ajax({
		url : "getSharelink.action",
		type : "get",
		dataType : "json",
		success : function(data){
			sharelinkData = data;
			if(window.sessionStorage.getItem("addsharelink")){
				var addsharelink = window.sessionStorage.getItem("addsharelink");
				sharelinkData = JSON.parse(addsharelink).concat(sharelinkData);
			}
			//执行加载数据的方法
			sharelinkList();
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
	function Trim(str,is_global)
	{
		var result;
		result = str.replace(/(^\s+)|(\s+$)/g,"");
		if(is_global.toLowerCase()=="g")
		{
			result = result.replace(/\s/g,"");
		}
		return result;
	}

	var copytext = "";
	$("body").on("click",".sharelink_edit",function(){  //编辑
        var content =$(this).attr("data-content") ;
        var title =$(this).attr("data-title") ;
        var linkUrl =$(this).attr("data-linkUrl") ;
		var linkPic =$(this).attr("data-linkPic") ;
		var uid =$(this).attr("data-id") ;
        var index = layui.layer.open({
            title : "编辑信息",
            type : 2,
            area : ['800px' , '620px'],
            maxmin: true,
			// content:$('.copyTr'),
            content : "jsp/sharelink_edit.jsp?title="+title+"&uid="+uid+"&content="+content+"&linkUrl="+linkUrl+"&linkPic="+linkPic,
            success : function(layero, index){
                setTimeout(function(){
                },500)
            }
        })

	})


	function sharelinkList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = sharelinkData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr class="copyTr">'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			        /*+'<td>'+currData[i].roleId+'</td>'*/
					/*+'<td align="left">'+currData[i].roleId+'</td>'*/
			    /*	+'<td><a style="color:#1E9FFF;" target="_blank" href="'+currData[i].linksUrl+'">'+currData[i].linksUrl+'</a></td>'*/
			    	+'<td>'+currData[i].title+'</td>'
			    	+'<td class="copytext">'+currData[i].content+'</td>'
					+'<td> <img style="'+'width:90px; height:90px"'+'src="'+ currData[i].linkPic +'"/></td>'
					+'<td>'+currData[i].linkUrl+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini sharelink_edit" data-title="'+currData[i].title+'"  data-content="'+currData[i].content+'" data-linkUrl="'+currData[i].linkUrl+'"   data-linkPic="'+currData[i].linkPic+'"  data-id="'+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
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
			sharelinkData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(sharelinkData.length/nums),
			jump : function(obj){
				$(".sharelink_content").html(renderDate(sharelinkData,obj.curr));
				$('.sharelink_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
	
})

