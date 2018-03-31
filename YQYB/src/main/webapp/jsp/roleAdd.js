layui.config({
	base : "js/"
}).extend({
	jstree: "jstree"
}).use(['form','layer','jquery','layedit','laydate', 'jstree'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery,
		jstree = layui.jstree;

	///////////////////////////////////////////////////
	var menuIds;
	/*$(function() {*/
		getMenuTreeData();
/*	});*/

	function getAllSelectNodes() {
		var ref = $('#menuTree').jstree(true); // 获得整个树

		menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
		//console.log(menuIds);
		$("#menuTree").find(".jstree-undetermined").each(function(i, element) {
			menuIds.push($(element).closest('.jstree-node').attr("id"));
		});
		console.log(menuIds);
	}
	function getMenuTreeData() {
		$.ajax({
			type : "GET",
			url : "../getMenuTree.action",
			success : function(menuTree) {
				loadMenuTree(menuTree);
			}
		});
	}
	function loadMenuTree(menuTree) {
		$('#menuTree').jstree({
			'core' : {
				'data' : menuTree,
			},
			"checkbox" : {
				// "three_state" : true,
			},
			"plugins" : [ "wholerow", "checkbox" ]
		});
		$('#menuTree').jstree("open_all");

	}
	///////////////////////////////////////////////////


	//创建一个编辑器
 	var editIndex = layedit.build('roles_content');

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
 	form.on("submit(addroles)",function(data){
 		var flag =false;
		//alert($(".username").val());
		//alert(JSON.stringify(data));
		//alert(JSON.stringify(data.field));
		getAllSelectNodes();
		if(!menuIds||menuIds.length==0||menuIds==null||menuIds=="undefined"){
			layer.msg("请勾选权限菜单！");
			//return flag;
		}
		$.ajax({
			async:true,
			url: "../addRoles.action" ,
			data:{
				description : $(".description").val(),
				roleSign : $(".roleSign").val(),
				strIds :menuIds.toString(),
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




