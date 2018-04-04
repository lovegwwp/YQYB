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
 	var editIndex = layedit.build('dtjjbl_content');
	setValue();
    ////////用户修改/页面回显//////////////
    function setValue(){
        var bz_id = getUrlParams("bz_id");
		var bz_value = getUrlParams("bz_value");
        var bz_info = getUrlParams("bz_info");
        var uid = getUrlParams("uid");
		console.log(bz_id +"===>"+ bz_value +"====>"+bz_info+"====>"+uid);
        if(uid){
            $(".bz_id").val(bz_id);
			$(".bz_value").val(bz_value);
			$(".bz_info").val(bz_info);
            $(".id").val(uid);
        }
		layui.form().render();

    }

    /////////提交//////////////
 	form.on("submit(adddtjjbl)",function(data){
 		var flag =false;
		$.ajax({
			async:true,
			url: "../adddtjjbl.action" ,
			data:{
				bz_value : $(".bz_value").val(),
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



