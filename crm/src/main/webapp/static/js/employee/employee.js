$(function() {
	// 抽取变量
	var emp_dataGrid = $("#emp_dataGrid");
	var emp_form = $("#emp_form");
	var emp_dialog = $("#emp_dialog");
	var role_combobox = $('#role_combobox');
	var emp_import = $("#emp_import");
	var importForm = $("#importForm");

	emp_dataGrid.datagrid({
		fit : true,
		fitColumns : true,
		url : "/employee_list",
		rownumbers : true,
		pagination : true,
		striped : true,
		singleSelect : true,
		toolbar : "#emp_toolbar",

		columns : [ [ {
			field : "username",
			title : "用户名",
			width : 1,
			align : "center"
		}, {
			field : "realName",
			title : "真实名字",
			width : 1,
			align : "center"
		}, {
			field : "password",
			title : "密码",
			width : 1,
			align : "center"
		}, {
			field : "tel",
			title : "电话号码",
			width : 1,
			align : "center"
		}, {
			field : "email",
			title : "邮箱",
			width : 1,
			align : "center"
		}, {
			field : "inputTime",
			title : "入职时间",
			width : 1,
			align : "center"
		}, {
			field : "dept",
			title : "部门",
			width : 1,
			align : "center",
			formatter : deptFormatter
		}, {
			field : "state",
			title : "状态",
			width : 1,
			align : "center",
			formatter : stateFormatter
		}, {
			field : "admin",
			title : "是否是管理员",
			width : 1,
			align : "center",
			formatter : adminFormatter
		}, ] ],
		onClickRow : function(rowIndex, rowData) {
			if (!rowData.state) {
				$("#remove_btn").linkbutton("disable")
			} else {
				$("#remove_btn").linkbutton("enable")
			}
		}
	});

	function deptFormatter(value, row, index) {
		if (value) {
			return value.name
		} else {
			return ""
		}
	}
	function stateFormatter(value, row, index) {
		if (value) {
			return "<font color='green'>在职</font>"
		} else {
			return "<font color='red'>离职</font>"
		}
	}
	function adminFormatter(value, row, index) {
		if (value) {
			return "<font color='green'>是</font>"
		} else {
			return "<font color='red'>否</font>"
		}
	}

	emp_dialog.dialog({
		width : 350,
		height : 400,
		closed : true,
		buttons : "#emp_button"
	});
	emp_import.dialog({
		width : 500,
		height : 150,
		closed : true,
	});

	// 统一绑定事件
	$("a[data-cmd]").on("click", function() {
		// 获取当前按钮要执行的方法名
		var cmd = $(this).data("cmd");
		// 调用对应的方法
		methodObje[cmd]();
	})

	var methodObje = {
		// 新增
		add : function() {
			emp_form.form("clear");
			emp_dialog.dialog("setTitle", "新增员工");
			emp_dialog.dialog("open");

			// 显示密码信息框
			$("#passwordInfo").show();
		},

		// 编辑
		edit : function() {
			// 提示必须选中才可以编辑
			var selected = emp_dataGrid.datagrid("getSelected");
			if (selected == null) {
				$.messager.alert("温馨提示", "请选择需要编辑的数据", "info");
				return;
			}
			emp_form.form("clear");
			emp_dialog.dialog("setTitle", "编辑员工");
			emp_dialog.dialog("open");

			// 隐藏密码信息框
			$("#passwordInfo").hide();
			// 处理部门对象
			if (selected.dept) {
				selected["dept.id"] = selected.dept.id;
			}
			// 处理管理员下拉框（如果使用easyui1.3版本需要处理）
			selected["admin"] = selected["admin"] + "";

			// 回显角色，使用combobox的setValues方法，参数角色id集合，使用ajax从后台获取
			var data = {
				id : selected.id
			}
			$.post("/employee_getRoleIdsByEId", data, function(backData) {
				role_combobox.combobox('setValues', backData);
			});

			// 数据回显
			emp_form.form("load", selected);
		},

		// 离职
		remove : function() {
			// 提示必须选中才可以修改
			var selected = emp_dataGrid.datagrid("getSelected");
			if (selected == null) {
				$.messager.alert("温馨提示", "请选择需要编辑的数据", "info");
				return;
			}
			// 弹出确认框：提示用户确认
			$.messager.confirm('确认', '您确认将该用户设置为离职吗？', function(r) {
				if (r) {
					var data = {
						id : selected.id
					}
					$.post("/employee_remove", data, function(backData) {
						if (backData.success) {
							$.messager.alert("温馨提示", backData.msg, "info",
									function() {
										// 刷新datagrid
										emp_dataGrid.datagrid("reload");
									});
						} else {
							$.messager.alert("温馨提示", backData.msg, "error");
						}
					})
				}
			});
		},

		// 查询
		searchForm : function() {
			// 获取关键字
			var keyword = $("#emp_toolbar [name='keyword']").val();
			$('#emp_dataGrid').datagrid('load', {
				'keyword' : keyword
			});
		},

		// 查询全部
		searchAll : function() {
			// 手动 清空页面查询条件
			$("#emp_toolbar [name='keyword']").val('');
			// 查询全部，参数为{}
			emp_dataGrid.datagrid("load", {});
		},

		// 导出
		doExport : function() {
			window.location.href = "/employee_export"
		},

		// 导入
		doImport : function() {
			emp_import.dialog("setTitle", "导入员工");
			emp_import.dialog("open");
		},
		
		importButton : function(){
			importForm.form("submit",{
				success:function(data){
					// 返回的是字符串，必须要转成json才可以做判断
					data = JSON.parse(data);
					if (data.success == "false") {
						emp_import.dialog("close");
						$.messager.alert("温馨提示", data.msg, "error");
					} else {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 关闭dialog
							emp_import.dialog("close");
							// 刷新datagrid
							emp_dataGrid.datagrid("reload");
						});
					}
				}
			})
		},

		// 保存
		save : function() {
			// 判断是新增还是编辑（根据表单的隐藏属性：id来判断）
			var url = null;
			var id = $("#emp_form [name='id']").val();
			if (id) {
				url = "/employee_update";
			} else {
				url = "/employee_save";
			}

			emp_form.form("submit", {
				url : url,
				onSubmit : function(params) {
					// 获取所有选中的行
					var values = $("#role_combobox").combobox("getValues");
					// 遍历出每一个id
					for (var i = 0; i < values.length; i++) {
						params["roles[" + i + "].id"] = values[i];
					}
				},
				success : function(data) {
					// 返回的是字符串，必须要转成json才可以做判断
					data = JSON.parse(data);
					if (data.success == "false") {
						emp_dialog.dialog("close");
						$.messager.alert("温馨提示", data.msg, "error");
					} else {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 关闭dialog
							emp_dialog.dialog("close");
							// 刷新datagrid
							emp_dataGrid.datagrid("reload");
						});
					}
				}
			})
		},

		// 退出
		cancel : function() {
			// 关闭表单
			emp_dialog.dialog("close");
		}

	}
})
