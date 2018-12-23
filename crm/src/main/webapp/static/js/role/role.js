$(function() {
	// 抽取变量
	var role_dataGrid = $("#role_dataGrid");
	var role_form = $("#role_form");
	var role_dialog = $("#role_dialog");
	var all_permission = $("#all_permission");
	var self_permission = $("#self_permission");

	role_dataGrid.datagrid({
		fit : true,
		fitColumns : true,
		url : "/role_list",
		rownumbers : true,
		pagination : true,
		striped : true,
		singleSelect : true,
		toolbar : "#role_toolbar",

		columns : [ [ {
			field : "sn",
			title : "角色编号",
			width : 1,
			align : "center"
		}, {
			field : "name",
			title : "角色名字",
			width : 1,
			align : "center"
		} ] ]
	});
	// 对话框初始化
	role_dialog.dialog({
		resizable : true,
		width : 600,
		height : 500,
		closed : true,
		buttons : "#role_button"
	});

	// 所有权限
	all_permission.datagrid({
		width : 230,
		height : 300,
		fitColumns : true,
		url : "/permission_list",
		title : "所有权限",
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		columns : [ [ {
			field : "name",
			title : "权限名称",
			width : 1,
			align : "center"
		} ] ],
		// 点击时触发
		onClickRow : function(rowIndex, rowData) {
			// 防止重复添加相同的数据
			// 获取已有权限中所有的行
			var rows = self_permission.datagrid("getRows");
			var rowId;
			// 遍历得到所有的行，进行判断
			for (var i = 0; i < rows.length; i++) {
				rowId = rows[i].id;
				// 已有权限中已经存在该权限
				if (rowData.id == rowId) {
					// 选中该权限,selectRow方法参数，索引从0开始
					self_permission.datagrid("selectRow", rowId - 1);
					return;
				}
			}
			// 将点击的行(rowData)添加到已有权限中
			self_permission.datagrid('appendRow', rowData);
		},
		toolbar : "#loadAllPermissions"

	});

	// 已选权限
	self_permission.datagrid({
		width : 230,
		height : 300,
		fitColumns : true,
		title : "已有权限",
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		columns : [ [ {
			field : "name",
			title : "权限名称",
			width : 1,
			align : "center"
		} ] ],
		// 点击时触发
		onClickRow : function(rowIndex, rowData) {
			// 将点击的行(rowData)从已有权限中删除
			self_permission.datagrid('deleteRow', rowIndex);
		},
		toolbar : "#removeSelfPermissions"
	});

	// 统一绑定事件
	$("a[data-cmd]").on("click", function() {
		// 获取当前按钮要执行的方法名
		var cmd = $(this).data("cmd");
		// 调用对应的方法
		methodObject[cmd]();
	})

	var methodObject = {
		// 添加
		add : function() {
			role_form.form("clear");
			role_dialog.dialog("setTitle", "添加角色");
			role_dialog.dialog("open");

			// 清空已有权限
			methodObject["removeSelfPermissions"]();
		},

		// 编辑
		edit : function() {
			// 提示必须选中才可以编辑
			var selected = role_dataGrid.datagrid("getSelected");
			if (selected == null) {
				$.messager.alert("温馨提示", "请选择需要编辑的数据", "info");
				return;
			}
			role_form.form("clear");
			role_dialog.dialog("setTitle", "编辑角色");
			role_dialog.dialog("open");

			// 表单回显
			role_form.form("load", selected);
			// 已有权限回显
			// 获取属性对象,给属性对象添加url，并带上角色id
			var options = self_permission.datagrid("options");
			options.url = "/permission_getPermissionsByRId?rId=" + selected.id;
			// 重新发送加载数据
			self_permission.datagrid("load");
		},

		// 删除
		remove : function() {
			// 提示必须选中才可以修改
			var selected = role_dataGrid.datagrid("getSelected");
			if (selected == null) {
				$.messager.alert("温馨提示", "请选择需要删除的角色", "info");
				return;
			}
			// 弹出确认框：提示用户确认
			$.messager.confirm('确认', '您确认将该角色删除吗？', function(r) {
				if (r) {
					var data = {
						id : selected.id
					}
					$.post("/role_remove", data, function(backData) {
						if (backData.success) {
							$.messager.alert("温馨提示", backData.msg, "info",
									function() {
										// 刷新datagrid
										role_dataGrid.datagrid("reload");
									});
						} else {
							$.messager.alert("温馨提示", backData.msg, "error");
						}
					})
				}
			});
		},

		// 刷新
		searchAll : function() {
			// 参数为{}
			role_dataGrid.datagrid("load", {});
		},

		// 保存
		save : function() {
			// 判断是添加还是编辑（根据表单的隐藏属性：id来判断）
			var url = null;
			var id = $("#role_form [name='id']").val();
			if (id) {
				url = "/role_update";
			} else {
				url = "/role_save";
			}
			$("#role_form").form("submit", {
				url : url,
				// 往param对象中添加集合需要的属性,保存角色的时候需要保存权限，我们可以将权限的id封装到对应的权限上
				onSubmit : function(param) {
					// 获取已有权限中所有的数据
					var rows = self_permission.datagrid("getRows");
					var row;
					// 遍历，将每个权限的id设置到对应的权限中
					for (var i = 0; i < rows.length; i++) {
						row = rows[i];
						param["permissions[" + i + "].id"] = row.id;
					}
				},
				success : function(data) {
					// 返回的是字符串，必须要转成json才可以做判断
					data = $.parseJSON(data);
					if (data.success == "false") {
						$.messager.alert("温馨提示", data.msg, "error");
					} else {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 关闭dialog
							role_dialog.dialog("close");
							// 刷新datagrid
							role_dataGrid.datagrid("reload");
						});
					}
				}
			})
		},

		// 退出
		cancel : function() {
			// 关闭表单
			role_dialog.dialog("close");
		},

		// 加载所有权限
		loadAllPermissions : function() {
			$.post("/permission_list", function(backData) {
				if (backData) {
					$.messager.alert("温馨提示", "加载成功", "info", function() {
						// 刷新datagrid
						role_dataGrid.datagrid("reload");
					});
				} else {
					$.messager.alert("温馨提示", "加载失败", "error");
				}
			})
		},

		// 清空已有权限
		removeSelfPermissions : function() {
			self_permission.datagrid("loadData", {
				rows : []
			});
		}

	}
})
