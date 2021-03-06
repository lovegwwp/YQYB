    var menuIds;
	$(function() {
		getMenuTreeData();
	});

	function getAllSelectNodes() {
		var ref = $('#menuTree').jstree(true); // 获得整个树

		menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
		console.log(menuIds);
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
				"three_state" : true,
			},
			"plugins" : [ "wholerow", "checkbox" ]
		});
		//$('#menuTree').jstree("open_all");

	}

	function save() {
		$('#menuIds').val(menuIds);
		var role = $('#signupForm').serialize();
		$.ajax({
			cache : true,
			type : "POST",
			url : "/sys/role/save",
			data : role, // 你的formid

			async : false,
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

					parent.layer.close(index);

				} else {
					parent.layer.msg(data.msg);
				}
			}
		});
	}




