layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	var editIndex = layedit.build('thdinfo_content');
	setValue();
	/*getSelect('../getBaCo.action?area='+'2',"2");
	getSelect('../getBaCo.action?area='+'3',"3");
	getSelect('../getBaCo.action?area='+'4',"4");*/
/*
   $.ajax({
        url : '../getBaCo.action?area='+'2',
        type : 'POST',
        success : function(data) {
            var roles = eval(data);
            $(roles).each(
                function(index) {
                    var role = roles[index];
                    var optionStr = "";
                    optionStr += "<option value='" + role.id + "'>"
                        + role.name + "</option>";
                    //alert(optionStr);
                    $("#province").append(optionStr);
					setSelect();
                    layui.form().render('select','provinceFilter');
                    //setValue();

                });
        },
        error : function(data) {
            alert('数据加载错误，请重试！');
        }
    });
	$.ajax({
		url : '../getBaCo.action?area='+'3',
		type : 'POST',
		success : function(data) {
			var roles = eval(data);
			$(roles).each(
				function(index) {
					var role = roles[index];
					var optionStr = "";
					optionStr += "<option value='" + role.id + "'>"
						+ role.name + "</option>";
					//alert(optionStr);
					$("#city").append(optionStr);
					setSelect();
					layui.form().render('select','cityFilter');
					//setValue();

				});
		},
		error : function(data) {
			alert('数据加载错误，请重试！');
		}
	});
*/
	//初始化  全部咨询/第一页
	/*$.when(getSelect('../getBaCo.action?area='+'2',"2")).done(function (data) {
		loadSelect(data,"2");
		console.log(1111);
		$.when(getSelect('../getBaCo.action?area='+'3',"3")).done(function (data) {
			loadSelect(data,"3");
			console.log(333);*/
			$.when(getSelect('../getBaCo.action?area='+'4',"4")).done(function (data) {
				loadSelect(data,"4");
				console.log(1444);
			});
	/*	});
	});*/







    ////////用户修改/页面回显//////////////
    function setValue(){
        var uid = getUrlParams("uid");
        if(uid){
            $(".thName").val(getUrlParams("thName"));
			$(".telShow").val(getUrlParams("telShow"));
			$(".name").val(getUrlParams("name"));
			$(".tel").val(getUrlParams("tel"));
			$(".addr").val(getUrlParams("addr"));
            $(".id").val(uid);
        }
    }

    function getSelect(urld,Render){
		var defer = $.Deferred();
		$.ajax({
			url : urld,
			type : 'POST',
			success : function(data) {
				console.log(data);
				defer.resolve(data);
			},
			error : function(data) {
				alert('数据加载错误，请重试！');
			}
		});
		return defer.promise();
	}

	function  loadSelect(data,isRender){
		var roles = eval(data);
		$(roles).each(
			function(index) {
				var role = roles[index];
				var optionStr = "";
				optionStr += "<option value='" + role.id + "'>"
					+ role.name + "</option>";
				var uid = getUrlParams("uid");
				if(isRender=='2'){
					$("#area").append(optionStr);
					if(uid){
						$("#area").val(getUrlParams("areaId"));
					}
					layui.form().render('select','areaFilter');
				}else if(isRender=='3'){
					$("#city").append(optionStr);
					if(uid){
						$("#city").val(getUrlParams("cityId"));
					}
					layui.form().render('select','cityFilter');
				}else if(isRender=='4'){
					$("#area").append(optionStr);
					if(uid){
						$("#area").val(getUrlParams("areaId"));
					}
					layui.form().render('select','areaFilter');
				}

			});

	}

    /////////提交//////////////
 	form.on("submit(addthdinfo)",function(data){
 		var flag =false;
		$.ajax({
			async:true,
			url: "../addThd.action" ,
			data:{
				id :$(".id").val(),
				thName :$(".thName").val(),
				telShow: $(".telShow").val(),
			    name: $(".name").val(),
				tel:$(".tel").val(),
				addr:$(".addr").val(),
				cityId:$("#city").val(),
				areaId:$("#area").val(),
				provinceId:$("#province").val()
			},
			cache:false ,
			type: "post" ,
			dataType:"json" ,
			/* contentType:"application/json;charset=utf-8",  */
			success:function(result){
				layer.alert(result.message, function () {
					layer.closeAll("iframe");
					//刷新父页面
					parent.location.reload();
				});
				if(result.status=="true"){
					flag = true;
				}
			} ,
			error:function(result){
				layer.alert(result.message);
			}
		});
 		return flag;
 	})
	
})

function getUrlParams(key) {
    var args = {};
   // var theRequest=window.location.search;
    //alert(theRequest);//?roleName=admin&username=ggg&uid=21
    var pairs = window.location.search.substring(1).split('&');
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('=');
        if (pos === -1) {
            continue;
        }
        args[pairs[i].substring(0, pos)] = decodeURIComponent(pairs[i].substring(pos + 1));
    }
   // console.log(args);
    return args[key];
}


