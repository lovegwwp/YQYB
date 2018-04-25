layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var hhrinfoinfoData = '';
	$.ajax({
		url : "getUsers.action",
		data:{
		  account :''
		},
		type : "get",
		dataType : "json",
		success : function(data){
			hhrinfoinfoData = data;
			if(window.sessionStorage.getItem("addhhrinfoinfo")){
				var addhhrinfoinfo = window.sessionStorage.getItem("addhhrinfoinfo");
				hhrinfoinfoData = JSON.parse(addhhrinfoinfo).concat(hhrinfoinfoData);
			}
			//执行加载数据的方法
			hhrinfoinfoList();
		}
	})

	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "getUsers.action",
					data:{
						account :$(".search_input").val()
					},
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addhhrinfoinfo")){
							var addhhrinfoinfo = window.sessionStorage.getItem("addhhrinfoinfo");
							hhrinfoinfoData = JSON.parse(addhhrinfoinfo).concat(data);
						}else{
							hhrinfoinfoData = data;
						}
						for(var i=0;i<hhrinfoinfoData.length;i++){
							var hhrinfoinfoStr = hhrinfoinfoData[i];
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

		            		if(hhrinfoinfoStr.account.indexOf(selectStr) > -1){
								hhrinfoinfoStr["account"] = changeStr(hhrinfoinfoStr.account);
		            		}


		            		if( hhrinfoinfoStr.account.indexOf(selectStr)>-1){
		            			newArray.push(hhrinfoinfoStr);
		            		}
		            	}
						hhrinfoinfoData = newArray;
		            	hhrinfoinfoList(hhrinfoinfoData);
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

	/////1=使用，2=禁用
	$("body").on("click",".hhr_jjuse",function(){  //禁用
		var _this = $(this);
		console.log(_this[0]+"======");
		console.log(_this.attr("data-status")+"=========");
		if(_this.attr("data-status")!='1'){
			layer.alert("状态异常!");
			return false;
		}
		layer.confirm('确定禁用用户吗？',{icon:3, title:'提示信息'},function(index){
			ZtOption3(_this.attr("data-id"),'2','jyUsersByUuid.action');
			layer.close(index);
		});
	})

	/////1=使用，2=禁用
	$("body").on("click",".hhr_hfuse",function(){  ///通过
		var _this = $(this);
		if(_this.attr("data-status")!='2'){
			layer.alert("状态异常!");
			return false;
		}
		console.log(_this.attr("data-id"));
		layer.confirm('确定恢复用户使用吗？',{icon:3, title:'提示信息'},function(index){
			ZtOption3(_this.attr("data-id"),'1','jyUsersByUuid.action');
			layer.close(index);
		});
	})

    //可以转账
    $(".transer_btn").click(function(){
        var $checkbox = $('.hhrinfoinfo_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.hhrinfoinfo_list tbody input[type="checkbox"][name="checked"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定设置选中的信息？',{icon:3, title:'提示信息'},function(index){
                var index = layer.msg('设置中，请稍候',{icon: 16,time:false,shade:0.8});
                var strIds="";

                for(var j=0;j<$checked.length;j++){
                    var temp = $checked.eq(j).parents("tr").find(".hhr_hfuse").attr("data-id");
                    strIds =strIds + temp+",";////uuid的组合
                }
                ZtOption2(strIds,'transferOk.action');
                $('.hhrinfoinfo_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
                layer.close(index);

            })
        }else{
            layer.msg("请选择需要设置的内容");
        }
    })

    //不可以转账
    $(".noTranser_btn").click(function(){
        var $checkbox = $('.hhrinfoinfo_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.hhrinfoinfo_list tbody input[type="checkbox"][name="checked"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定设置选中的信息？',{icon:3, title:'提示信息'},function(index){
                var index = layer.msg('设置中，请稍候',{icon: 16,time:false,shade:0.8});
                var strIds="";
                for(var j=0;j<$checked.length;j++){
                    var temp = $checked.eq(j).parents("tr").find(".hhr_hfuse").attr("data-id");
                    strIds =strIds + temp+",";////uuid的组合
                    console.log(temp);
                    console.log(strIds);

                }
                ZtOption2(strIds,'transferNo.action');
                $('.hhrinfoinfo_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
                layer.close(index);

            })
        }else{
            layer.msg("请选择需要设置的内容");
        }
    })

	function hhrinfoinfoList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrinfoinfoData.concat().splice(curr * nums - nums, nums);
			} else {
				currData = that.concat().splice(curr * nums - nums, nums);
			}
			if (currData.length != 0) {
				for (var i = 0; i < currData.length; i++) {

					dataHtml += '<tr>'
						+ '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
						+ '<td>' + currData[i].account + '</td>'
						+ '<td>'+ getIsChuangke(currData[i].isChuangke)+'</td>'
						+ '<td>' + currData[i].bCode + '</td>'
						+ '<td>' + currData[i].totalPv + '</td>'
						+ '<td>' + currData[i].shoppingScore + '</td>'
						+ '<td>' + currData[i].cashScore + '</td>'
						+ '<td>' + currData[i].electScore + '</td>'
						+ '<td>' + currData[i].bdScore + '</td>'
						+ '<td>' + currData[i].borrow + '</td>'
						+ '<td>' + getTransfer(currData[i].isTransfer)+ '</td>'
						+ '<td>' + getBz(currData[i].status) + '</td>'
                        + '<td>'
						+  '<a class="layui-btn layui-btn-danger layui-btn-mini hhr_hfuse" data-id="'+currData[i].uuid+'" data-status="'+currData[i].status+'" ><i class="layui-icon">&#xe605;</i>使用</a>'
						+  '<a class="layui-btn layui-btn-danger layui-btn-mini hhr_jjuse" data-id="'+currData[i].uuid+'" data-status="'+currData[i].status+'"><i class="layui-icon">&#x1006;</i>禁用</a>'
                        + '</td>'
                        + '</tr>';
				}
			} else {
				dataHtml = '<tr><td colspan="13">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		if (that) {
			hhrinfoinfoData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrinfoinfoData.length / nums),
			jump: function (obj) {
				$(".hhrinfoinfo_content").html(renderDate(hhrinfoinfoData, obj.curr));
				$('.hhrinfoinfo_list thead input[type="checkbox"]').prop("checked", false);
				form.render();
			}
		})
	}
	function getIsChuangke(bz){
		if(bz=='2'){
			return '初级';
		}else if(bz=='3'){
			return '中级';
		}else if(bz=='4'){
			return '高级';
		}else {
			return '未知';
		}

	}
	function getIsSh(bz){
		if(bz=='0'){
			return '审核中';
		}else if(bz=='1'){
			return '通过';
		}else if(bz=='2'){
			return '拒绝';
		}else {
			return '未知';
		}

	}


	function getBz(bz){
		if(bz=='2'){
			return '禁用';
		}else if(bz=='1'){
			return '正常';
		}else {
			return '未知';
		}

	}

	function getTransfer(bz){
		if(bz=='1'){
			return '可转账';
		}else if(bz=='2'){
			return '不可转';
		}else {
			return '未知';
		}

	}


	///状态业务
	function  ZtOption2(ids,urls){
		$.ajax({
			url : urls,
			data:{
				uuid :ids
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

	function  ZtOption3(ids,bz,urls){
		$.ajax({
			url : urls,
			data:{
				uuid :ids,
				status :bz
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

