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
	//console.log(parent);
	//console.log(parent.getMyHtml());
 	var editIndex = layedit.build('issues_content');
	getkindeditor();
	setValue();

    ////////用户修改/页面回显//////////////
    function setValue(){
        var title = getUrlParams("title");
		var content = JSON.parse(localStorage.getItem('copytext'));
		//content = content.replace(/(\n)+|(\r\n)+/g, "");
        var uid = getUrlParams("uid");
		editor.html("");
        if(uid){
            $(".title").val(title);
			//editor.html();
			editor.html(content);
            $(".id").val(uid);
        }
		layui.form().render();

    }

    /////////提交//////////////
 	form.on("submit(addissues)",function(data){
 		var flag =false;
		$.ajax({
			async:true,
			url: "../addCjwtInfo.action" ,
			data:{
				title : $(".title").val(),
				content : editor.html(),
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

  function getkindeditor(){
	var editor;
	window.editor
		= KindEditor.create('textarea[id="content"]', {
		items: ['bold', 'italic','underline','strikethrough','fontname', 'fontsize', 'forecolor', 'hilitecolor','image', 'link', 'unlink', 'source','plainpaste','hr'],
		filterMode : true,
		htmlTags:{
			font : ['color', 'size'],
			span : [
				'.color', '.font-size', '.font-family','.font-weight', '.font-style', '.line-height', '.background', '.background-color', '.text-decoration'
			],
			a : ['href', 'target', 'name', 'style'],
			hr : ['class', '/'],
			br : ['/'],
			img : ['src', 'width', 'height', 'border', 'alt', 'title', 'align', 'style', '/'],
			'p,h1,h2,h3,h4,h5,h6' : ['align', 'style'],
			'strong,b,u,i,em' : []
		},
		uploadJson: 'jsp/upload_json.jsp',//指定上传图片的服务器端程序
		// uploadJson: 'addImg2.action',
		//fileManagerJson: '/images',//指定浏览远程图片的服务器端程序
		fileManagerJson : true,
		allowFileManager : true,
		formatUploadUrl : false,
		allowFileManager: true
	});
}

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



