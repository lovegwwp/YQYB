layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;


    $.ajax({
        url : '../roleList.action',
        type : 'POST',
        success : function(data) {
            var roles = eval(data);
            $(roles).each(
                function(index) {
                    var role = roles[index];
                    var optionStr = "";
                    optionStr += "<option value='" + role.id + "'>"
                        + role.roleSign + "</option>";
                    //alert(optionStr);
                    $("#uId").append(optionStr);
                    setValue();
                    layui.form().render('select','roleFilter');
                    //setValue();

                });
        },
        error : function(data) {
            alert('数据加载错误，请重试！');
        }
    });

	var urls ="../ht/topup.action";
	var bz = getUrlParams("bz");
	if(bz){
		urls ="../ht/borrow.action";
	}

    /////////提交//////////////
 	form.on("submit(addhhrcwtj)",function(data){
 		var flag =false;
		$.ajax({
			async:true,
			url: urls ,
			data:{
				payAmount : $(".payAmount").val(),
				uuid :$("#uId").val()
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


/*function getRoles(){
	$.ajax({
		url : '../roleList.action',
		type : 'POST',
		success : function(data) {
			var roles = eval(data);
			$(roles).each(
				function(index) {
					var role = roles[index];
					var optionStr = "";
					optionStr += "<option value='" + role.id + "'>"
						+ role.roleSign + "</option>";
					//alert(optionStr);
					$("#roleId").append(optionStr);
					layui.form().render('select','roleFilter');
				});
		},
		error : function(data) {
			alert('数据加载错误，请重试！');
		}
	});
}*/
