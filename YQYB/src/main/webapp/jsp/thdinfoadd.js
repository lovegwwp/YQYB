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
 	//var editIndex = layedit.build('thdinfo_content');
    ///select加载
    getProvince('../getBaCo.action?area='+'2');
	setValue();

    ///省份下拉监听
    form.on('select(provinceFilter)', function(data){
        var  province =data.value;
        console.log($("#province").val());
        getCity('../getBaCo.action?area='+'3&fid='+province);
    });
    ///城市下拉监听
    form.on('select(cityFilter)', function(data){
        if(!$("#province").val()){
            layer.alert("请选择省份");
        }
        var  city =data.value;
        getArea('../getBaCo.action?area='+'4&fid='+city);
    });

    ///城市下拉监听
    form.on('select(areaFilter)', function(data){
        if(!$("#province").val()){
            layer.alert("请选择省份");
        }
        if(!$("#city").val()){
            layer.alert("请选择城市");
        }
    });


    function getSelectOption(urld,Render){
        var defer = $.Deferred();
        $.ajax({
            url : urld,
            type : 'POST',
            success : function(data) {
                loadSelect(data,Render);
            },
            error : function(data) {
                alert('数据加载错误，请重试！');
            }
        });
        return defer.promise();
    }

    function getSelectData(urld,isRender){
        $.ajax({
            url : urld,
            type : 'POST',
            success : function(data) {
                var roles = eval(data);
                $(roles).each(
                    function(index) {
                        var role = roles[index];
                        var optionStr = "";
                        optionStr += "<option value='" + role.id + "'>"
                            + role.name + "</option>";
                        var uid = getUrlParams("uid");
                        if(isRender=='2'){
                            $("#province").append(optionStr);
                            if(uid){
                                $("#province").val(getUrlParams("provinceId"));
                            }
                            layui.form().render('select','provinceFilter');
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
            },
            error : function(data) {
                alert('数据加载错误，请重试！');
            }
        });
        return defer.promise();
    }


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
            getProvince('../getBaCo.action?area='+'2');
            getCity('../getBaCo.action?area='+'3&fid='+ getUrlParams("provinceId"));
            getArea('../getBaCo.action?area='+'4&fid='+ getUrlParams("cityId"));
        }
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
					$("#province").append(optionStr);
					if(uid){
						$("#province").val(getUrlParams("provinceId"));
					}
					layui.form().render('select','provinceFilter');
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

	function getProvince(urls){
        $.ajax({
            url : urls,
            type : 'POST',
            success : function(data) {
                var roles = eval(data);
                $(roles).each(
                    function(index) {
                        var role = roles[index];
                        var optionStr = "";
                        optionStr += "<option value='" + role.id + "'>"
                            + role.name + "</option>";
                        var uid = getUrlParams("uid");
                        $("#province").append(optionStr);
                        if(uid){
                            $("#province").val(getUrlParams("provinceId"));
                        }
                        layui.form().render('select','provinceFilter');
                    });
            },
            error : function(data) {
                alert('数据加载错误，请重试！');
            }
        });


    }

    function getCity(urls){
        $("#city").html('');
        $.ajax({
            url : urls,
            type : 'POST',
            success : function(data) {
                var roles = eval(data);
                $(roles).each(
                    function(index) {
                        var role = roles[index];
                        var optionStr = "";
                        optionStr += "<option value='" + role.id + "'>"
                            + role.name + "</option>";
                        var uid = getUrlParams("uid");
                        $("#city").append(optionStr);
                        if(uid){
                            $("#city").val(getUrlParams("cityId"));
                        }
                        layui.form().render('select','cityFilter');
                    });
            },
            error : function(data) {
                alert('数据加载错误，请重试！');
            }
        });


    }

    function getArea(urls){
        $("#area").html('');
        $.ajax({
            url : urls,
            type : 'POST',
            success : function(data) {
                var roles = eval(data);
                $(roles).each(
                    function(index) {
                        var role = roles[index];
                        var optionStr = "";
                        optionStr += "<option value='" + role.id + "'>"
                            + role.name + "</option>";
                        var uid = getUrlParams("uid");
                        $("#area").append(optionStr);
                        if(uid){
                            $("#area").val(getUrlParams("areaId"));
                        }
                        layui.form().render('select','areaFilter');
                    });
            },
            error : function(data) {
                alert('数据加载错误，请重试！');
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


