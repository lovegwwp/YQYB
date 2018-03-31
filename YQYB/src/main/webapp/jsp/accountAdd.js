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
 	var editIndex = layedit.build('accounts_content');
	//getRoles();///动态加载select
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
                    setValue();
                    layui.form().render('select','roleFilter');
                    //setValue();

                });
        },
        error : function(data) {
            alert('数据加载错误，请重试！');
        }
    });

    ////////用户修改/页面回显//////////////
    function setValue(){
        var roleid = getUrlParams("roleName");
        var username = getUrlParams("username");
        var uid = getUrlParams("uid");
        if(uid){
            $(".username").val(username);
            $(".id").val(uid);
            $("#roleId").val(roleid);
           /* console.log(roleid+"w");
            console.log($("#roleId").val());*/

        }

    }

    /////////提交//////////////
 	form.on("submit(addAccounts)",function(data){
 		var flag =false;
		//alert($(".username").val());
		//alert(JSON.stringify(data));
		//alert(JSON.stringify(data.field));
		$.ajax({
			async:true,
			url: "../addAccount.action" ,
			data:{
				username : $(".username").val(),
				roleId :$("#roleId").val(),
				id :$(".id").val()
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
