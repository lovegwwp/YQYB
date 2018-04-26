layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var hhrshinfoData = '';
	$.ajax({
		url : "hhrshCx.action",
		type : "get",
		dataType : "json",
		success : function(data){
			hhrshinfoData = data;
			if(window.sessionStorage.getItem("addhhrshinfo")){
				var addhhrshinfo = window.sessionStorage.getItem("addhhrshinfo");
				hhrshinfoData = JSON.parse(addhhrshinfo).concat(hhrshinfoData);
			}
			//执行加载数据的方法
			hhrshinfoList();
		}
	})

	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            	$.ajax({
					url : "hhrshCx.action",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addhhrshinfo")){
							var addhhrshinfo = window.sessionStorage.getItem("addhhrshinfo");
							hhrshinfoData = JSON.parse(addhhrshinfo).concat(data);
						}else{
							hhrshinfoData = data;
						}
						for(var i=0;i<hhrshinfoData.length;i++){
							var hhrshinfoStr = hhrshinfoData[i];
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

		            		if(hhrshinfoStr.account.indexOf(selectStr) > -1){
								hhrshinfoStr["account"] = changeStr(hhrshinfoStr.account);
		            		}

		            		if(hhrshinfoStr.realName.indexOf(selectStr) > -1){
								hhrshinfoStr["realName"] = changeStr(hhrshinfoStr.realName);
		            		}
							if(hhrshinfoStr.email.indexOf(selectStr) > -1){
								hhrshinfoStr["email"] = changeStr(hhrshinfoStr.email);
							}
							if(hhrshinfoStr.idCard.indexOf(selectStr) > -1){
								hhrshinfoStr["idCard"] = changeStr(hhrshinfoStr.idCard);
							}

		            		if(hhrshinfoStr.realName.indexOf(selectStr)>-1 || hhrshinfoStr.account.indexOf(selectStr)>-1|| hhrshinfoStr.email.indexOf(selectStr)>-1|| hhrshinfoStr.idCard.indexOf(selectStr)>-1){
		            			newArray.push(hhrshinfoStr);
		            		}
		            	}
						hhrshinfoData = newArray;
		            	hhrshinfoList(hhrshinfoData);
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

	$("body").on("click",".hhr_jj",function(){  //禁用
		///0审核中，1=通过2=拒绝
		var _this = $(this);
		console.log(_this[0]+"======");
		console.log(_this.attr("data-status")+"=========");
		if(_this.attr("data-status")=='1'){
			layer.alert("该合伙人已通过审核!");
			return false;
		}else if(_this.attr("data-status")=='2'){
			layer.alert("该合伙人已被拒绝过审!");
			return false;
		}
		// layer.alert(_this.attr("data-id"));
		layer.confirm('确定拒绝吗？',{icon:3, title:'提示信息'},function(index){
			ZtOption2(_this.attr("data-id"),'dlrRefuse.action');
			layer.close(index);
		});
	})

	///通过
	$("body").on("click",".hhr_tg",function(){  ///0审核中，1=通过2=拒绝
		var _this = $(this);
		if(_this.attr("data-status")=='1'){
			layer.alert("该合伙人已通过审核!");
			return false;
		}else if(_this.attr("data-status")=='2'){
			layer.alert("该合伙人已被拒绝过审!");
			return false;
		}
		console.log(_this.attr("data-id"));
		// layer.alert(_this.attr("data-id"));
		layer.confirm('确定通过吗？',{icon:3, title:'提示信息'},function(index){
			/*	//_this.parents("tr").remove();
			 for(var i=0;i<thdinfoData.length;i++){
			 if(thdinfoData[i].id == _this.attr("data-id")){
			 thdinfoData.splice(i,1);
			 thdinfoList(thdinfoData);
			 }
			 }*/
			ZtOption2(_this.attr("data-id"),'dlrAgree.action');
			layer.close(index);
		});
	})

    //可以转账
    $(".transer_btn").click(function(){
        var $checkbox = $('.hhrshinfo_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.hhrshinfo_list tbody input[type="checkbox"][name="checked"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定设置选中的信息？',{icon:3, title:'提示信息'},function(index){
                var index = layer.msg('设置中，请稍候',{icon: 16,time:false,shade:0.8});
                var strIds="";

                for(var j=0;j<$checked.length;j++){
                    var temp = $checked.eq(j).parents("tr").find(".hhr_tg").attr("data-id");
                    strIds =strIds + temp+",";////uuid的组合
                }
                ZtOption2(strIds,'transferOk.action');
                $('.hhrshinfo_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
                layer.close(index);

            })
        }else{
            layer.msg("请选择需要设置的内容");
        }
    })

    //不可以转账
    $(".noTranser_btn").click(function(){
        var $checkbox = $('.hhrshinfo_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.hhrshinfo_list tbody input[type="checkbox"][name="checked"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定设置选中的信息？',{icon:3, title:'提示信息'},function(index){
                var index = layer.msg('设置中，请稍候',{icon: 16,time:false,shade:0.8});
                var strIds="";
                for(var j=0;j<$checked.length;j++){
                    var temp = $checked.eq(j).parents("tr").find(".hhr_tg").attr("data-id");
                    strIds =strIds + temp+",";////uuid的组合
                    console.log(temp);
                    console.log(strIds);

                }
                ZtOption2(strIds,'transferNo.action');
                $('.hhrshinfo_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
                layer.close(index);

            })
        }else{
            layer.msg("请选择需要设置的内容");
        }
    })

	function hhrshinfoList(that) {
		//渲染数据
		function renderDate(data, curr) {
			var dataHtml = '';
			if (!that) {
				currData = hhrshinfoData.concat().splice(curr * nums - nums, nums);
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
						//+ '<td>' + currData[i].isChuangke + '</td>'
						+ '<td>' + currData[i].account + '</td>'
						+ '<td>'+ getIsChuangke(currData[i].isChuangke)+'</td>'
						+ '<td>' + currData[i].realName + '</td>'
					/*	+ '<td>' + currData[i].email + '</td>'*/
						+ '<td>' + currData[i].idCard + '</td>'
						+ '<td>' + currData[i].yxsj + '</td>'
						+ '<td> <img style="'+'width:90px; height:90px"'+'src="'+ currData[i].cardPicture1 +'"/></td>'
                        + '<td> <img style="'+'width:90px; height:90px"'+'src="'+ currData[i].cardPicture2 +'"/></td>'
                        + '<td> <img style="'+'width:90px; height:90px"'+'src="'+ currData[i].cardPicture3 +'"/></td>'
				   /*	+ '<td>' + currData[i].cardPicture2 + '</td>'
						+ '<td>' + currData[i].cardPicture3 + '</td>'*/
						+ '<td>' + getIsPay(currData[i].bIsPay)+ '</td>'
						+ '<td>' + getIsSh(currData[i].status) + '</td>'
						+ '<td>' + currData[i].cjsj + '</td>'
                        + '<td>'
						+  '<a class="layui-btn layui-btn-danger layui-btn-mini hhr_tg" data-id="'+currData[i].uUuid+'" data-status="'+currData[i].status+'" ><i class="layui-icon">&#xe605;</i>通过</a>'
						+  '<a class="layui-btn layui-btn-danger layui-btn-mini hhr_jj" data-id="'+currData[i].uUuid+'" data-status="'+currData[i].status+'"><i class="layui-icon">&#x1006;</i>拒绝</a>'
                        + '</td>'
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
			hhrshinfoData = that;
		}
		laypage({
			cont: "page",
			pages: Math.ceil(hhrshinfoData.length / nums),
			jump: function (obj) {
				$(".hhrshinfo_content").html(renderDate(hhrshinfoData, obj.curr));
				$('.hhrshinfo_list thead input[type="checkbox"]').prop("checked", false);
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
	function getIsPay(bz){
		if(bz=='0'){
			return '未付钱';
		}else if(bz=='1'){
			return '已付钱';
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

})

