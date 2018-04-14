layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

	getRoles();

  function getZj(){
	$.ajax({
		url : '../jrc/getRecord.action',
		type : 'POST',
		success : function(data) {
			var zjUids = eval(data);
			$(zjUids).each(
				function(index) {
					var zj = zjUids[index];
					var optionStr = "";
					optionStr += "<option value='" + zj.uId + "'>"
						+ zj.uAccount + "</option>";
					$("#zjUid").append(optionStr);
					layui.form().render('select','zjFilter');
					///助理select
					//getRoles();
                    ////编辑回显
					setValue();
					//layui.form().render('select','uIdFilter');

				});
		},
		error : function(data) {
			alert('数据加载错误，请重试！');
		}
	});
  }
	var urls="../zl/insertZl.action";//新增
	var uuid = getUrlParams("uuid");
	if(uuid){
		urls="../zl/updateZl.action";//修改
	}

    ////////用户修改/页面回显//////////////
    function setValue(){
        var uId = getUrlParams("uId");
        var zjName = getUrlParams("zjName");
        var uuid = getUrlParams("uuid");
		var zjUid = getUrlParams("zjUid");
        if(uuid){
            $(".zjName").val(zjName);
            $(".id").val(uuid);
            $("#uId").val(uId);
			$("#zjUid").val(zjUid);
        }

    }

	function getRoles(){
		$.ajax({
			url : '../getaccountsZl.action',
			type : 'POST',
			success : function(data) {
				var zls = eval(data);
				$(zls).each(
					function(index) {
						var aaa = zls[index];
						var optionStr1 = "";
						optionStr1 += "<option value='" + aaa.id + "'>"
							+ aaa.username + "</option>";
						console.log(optionStr1);
						$("#uId").append(optionStr1);
						layui.form().render('select','uIdFilter');
                       ////总监select
						getZj()
					});
			},
			error : function(data) {
				alert('数据加载错误，请重试！');
			}
		});
	}


	/////////提交//////////////
 	form.on("submit(addzlfpsc)",function(data){
 		var flag =false;
		var zjCode = $("#zjUid").find("option:selected").text();
		//alert(zjCode);\
		console.log($("#roleId").val());
		$.ajax({
			async:true,
			url: urls ,
			data:{
				zjName : $(".zjName").val(),
				uId :$("#uId").val(),
				id :$(".id").val(),
				zjCode :zjCode,
				zjUid:$("#zjUid").val()

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
