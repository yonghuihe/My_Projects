$(function() {
	// 抽取变量
	var dept_dataGrid = $("#dept_dataGrid");
	var dept_form = $("#dept_form");
	var dept_dialog = $("#dept_dialog");
	var role_combobox = $('#role_combobox');
	var dept_import = $("#dept_import");
	var importForm = $("#importForm");

	dept_dataGrid.datagrid({
		fit : true,
		fitColumns : true,
		url : "/department_list",
		rownumbers : true,
		pagination : true,
		striped : true,
		singleSelect : true,
		toolbar : "#dept_toolbar",

		columns : [ [ {
			field : "name",
			title : "部门名称",
			width : 1,
			align : "center"
		}, {
			field : "sn",
			title : "部门编号",
			width : 1,
			align : "center"
		}, {
			field : "manager",
			title : "部门经理",
			width : 1,
			align : "center",
			formatter:empFormatter
		}, {
			field : "parent",
			title : "上级部门",
			width : 1,
			align : "center",
			formatter : deptFormatter
		}, {
			field : "state",
			title : "状态",
			width : 1,
			align : "center",
			formatter : stateFormatter
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			if (!rowData.state) {
				$("#remove_btn").linkbutton("disable");
				$("#edit_btn").linkbutton("disable");
			} else {
				$("#remove_btn").linkbutton("enable")
				$("#edit_btn").linkbutton("enable")
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
	function empFormatter(value, row, index) {
		if (value) {
			return value.username
		} else {
			return ""
		}
	}
	function stateFormatter(value, row, index) {
		if (value) {
			return "<font color='green'>正常</font>"
		} else {
			return "<font color='red'>停用</font>"
		}
	}
	function adminFormatter(value, row, index) {
		if (value) {
			return "<font color='green'>是</font>"
		} else {
			return "<font color='red'>否</font>"
		}
	}

	dept_dialog.dialog({
		width : 350,
		height : 300,
		closed : true,
		buttons : "#dept_button"
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
			dept_form.form("clear");
			dept_dialog.dialog("setTitle", "新增部门");
			dept_dialog.dialog("open");
		},

		// 编辑
		edit : function() {
			// 提示必须选中才可以编辑
			var selected = dept_dataGrid.datagrid("getSelected");
			if (selected == null) {
				$.messager.alert("温馨提示", "请选择需要编辑的数据", "info");
				return;
			}
			dept_form.form("clear");
			dept_dialog.dialog("setTitle", "编辑部门");
			dept_dialog.dialog("open");

			console.log(selected)
			
			// 处理部门对象
			if (selected.dept) {
				selected["dept.id"] = selected.dept.id;
			}
			// 处理状态下拉框（如果使用easyui1.3版本需要处理）
			//selected["state"] = selected["state"] + "";


			// 数据回显
			dept_form.form("load", selected);
		},

		// 离职
		remove : function() {
			// 提示必须选中才可以修改
			var selected = dept_dataGrid.datagrid("getSelected");
			if (selected == null) {
				$.messager.alert("温馨提示", "请选择需要编辑的数据", "info");
				return;
			}
			// 弹出确认框：提示用户确认
			$.messager.confirm('确认', '您确定要停用吗？', function(r) {
				if (r) {
					var data = {
						id : selected.id
					}
					$.post("/department_remove", data, function(backData) {
						if (backData.success) {
							$.messager.alert("温馨提示", backData.msg, "info",
									function() {
										// 刷新datagrid
										dept_dataGrid.datagrid("reload");
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
			var keyword = $("#dept_toolbar [name='keyword']").val();
			$('#dept_dataGrid').datagrid('load', {
				'keyword' : keyword
			});
		},

		// 查询全部
		searchAll : function() {
			// 手动 清空页面查询条件
			$("#dept_toolbar [name='keyword']").val('');
			// 查询全部，参数为{}
			dept_dataGrid.datagrid("load", {});
		},

		importButton : function(){
			importForm.form("submit",{
				success:function(data){
					// 返回的是字符串，必须要转成json才可以做判断
					data = JSON.parse(data);
					if (data.success == "false") {
						dept_import.dialog("close");
						$.messager.alert("温馨提示", data.msg, "error");
					} else {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 关闭dialog
							dept_import.dialog("close");
							// 刷新datagrid
							dept_dataGrid.datagrid("reload");
						});
					}
				}
			})
		},

		// 保存
		save : function() {
			// 判断是新增还是编辑（根据表单的隐藏属性：id来判断）
			var url = null;
			var id = $("#dept_form [name='id']").val();
			if (id) {
				url = "/department_update";
			} else {
				url = "/department_save";
			}

			dept_form.form("submit", {
				url : url,
				success : function(data) {
					// 返回的是字符串，必须要转成json才可以做判断
					data = JSON.parse(data);
					if (data.success == "false") {
						dept_dialog.dialog("close");
						$.messager.alert("温馨提示", data.msg, "error");
					} else {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 关闭dialog
							dept_dialog.dialog("close");
							// 刷新datagrid
							dept_dataGrid.datagrid("reload");
						});
					}
				}
			})
		},

		// 退出
		cancel : function() {
			// 关闭表单
			dept_dialog.dialog("close");
		}

	}
})
