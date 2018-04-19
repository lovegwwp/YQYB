layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;


	var urls="../jrc/addJRecord.action";//新增
	var zjUid = getUrlParams("zjUid");
	var zjName = getUrlParams("zjName");
	$(".zjUid").val(zjUid);
	$(".zjName").val(zjName);

	/////////提交//////////////
 	form.on("submit(addzjzlpw)",function(data){
 		var flag =false;
		//var zjCode = $("#zjUid").find("option:selected").text();
		console.log($(".id").val());
		$.ajax({
			async:true,
			url: urls ,
			data:{
				pAccount : $(".pAccount").val(),
				uAccount :$(".uAccount").val(),
				id :$(".id").val(),
				depart :$("#depart").val(),
				zjUid:$(".zjUid").val()

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
