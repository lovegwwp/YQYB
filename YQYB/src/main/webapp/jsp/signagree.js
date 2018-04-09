layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var signagreeData = '';
	$.ajax({
		url : "getSignagree.action",
		type : "get",
		dataType : "json",
		success : function(data){
			signagreeData = data;
			if(window.sessionStorage.getItem("addsignagree")){
				var addsignagree = window.sessionStorage.getItem("addsignagree");
				signagreeData = JSON.parse(addsignagree).concat(signagreeData);
			}
			//执行加载数据的方法
			signagreeList();
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
	$("body").on("click",".signagree_edit",function(){  //编辑
       // var content =$(this).attr("data-content") ;
		copytext = $(this).parents(".copyTr").find(".copytext").html();
		copytext = copytext.replace(/(\n)+|(\r\n)+/g, "");
		localStorage.setItem('copytext', JSON.stringify(copytext));
        var title =$(this).attr("data-title") ;
		console.log(title);
        var uid =$(this).attr("data-id") ;
        var index = layui.layer.open({
            title : "编辑信息",
            type : 2,
            area : ['800px' , '620px'],
            maxmin: true,
			// content:$('.copyTr'),
            content : "jsp/signagree_edit.jsp?title="+title+"&uid="+uid,
            success : function(layero, index){
                setTimeout(function(){
                },500)
            }
        })

	})
	function getMyHtml(){
		return copytext;
	}

	function signagreeList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = signagreeData.concat().splice(curr*nums-nums, nums);
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
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini signagree_edit" data-title="'+currData[i].title+'"   data-id="'+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
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
			signagreeData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(signagreeData.length/nums),
			jump : function(obj){
				$(".signagree_content").html(renderDate(signagreeData,obj.curr));
				$('.signagree_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
	
})

