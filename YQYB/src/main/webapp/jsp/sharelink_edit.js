layui.config({
	base : "js/"
}).use(['form','upload','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		upload = layui.upload ,// 获取upload模块
		laydate = layui.laydate,
		$ = layui.jquery;

	upload({
		url : '../upLoadImg.action',
		ext : 'png|jpg|jpeg',
		title : '请选择图片',
		before : function(input) {
			// 返回的参数item，即为当前的input DOM对象
			console.log('图片上传中');
		},
		success : function(res) {
			$("#linkPic").attr('src',res.message);
			//layer.msg(res.msg);
		}
	});

	setValue();

    ////////用户修改/页面回显//////////////
    function setValue(){
        var title = getUrlParams("title");
		var content = getUrlParams("content");
		var linkUrl = getUrlParams("linkUrl");
		var linkPic = getUrlParams("linkPic");
        var uid = getUrlParams("uid");
        if(uid){
            $(".title").val(title);
			$(".content").val(content);
			$(".linkUrl").val(linkUrl);
			$("#linkPic").attr('src',linkPic);
            $(".id").val(uid);
        }
		layui.form().render();

    }

    /////////提交//////////////
 	form.on("submit(addsharelink)",function(data){
 		var flag =false;
		$.ajax({
			async:true,
			url: "../addShareLink.action" ,
			data:{
				title : $(".title").val(),
				content : $(".content").val(),
				linkUrl : $(".linkUrl").val(),
				linkPic : $("#linkPic").attr('src'),
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



