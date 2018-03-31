var $form;
var form;
var $;
layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;



    //添加验证规则
    form.verify({
        oldPwd : function(value, item){
           /* if(value != "123456"){
                return "密码错误，请重新输入！";
            }*/
        },
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#newPwd2").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    //修改密码
    form.on("submit(changePwd)",function(data){
        var flag =false;
        console.log($("#oldPwd2").val()+"===>"+$("#newPwd2").val());
        $.ajax({
            async:true,
            url: "../upHtPwd.action" ,
            data:{
                oldPwd : $("#oldPwd2").val(),
                newPwd : $("#newPwd2").val(),
            },
            cache:false ,
            type: "post" ,
            dataType:"json" ,
            /* contentType:"application/json;charset=utf-8",  */
            success:function(result){
                layer.alert(result.message, function () {
                   layer.closeAll();
                    /*  //刷新父页面
                    parent.location.reload();*/
                });
                if(result.status=="true"){
                    flag = true;
                }
            } ,
            error:function(result){
                layer.alert(result.message);
            }
        });
        return flag;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})

