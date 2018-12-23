$(function() {
	// 抽取变量
	var permission_dataGrid = $("#permission_dataGrid");

	permission_dataGrid.datagrid({
		fit : true,
		fitColumns : true,
		url : "/permission_list",
		rownumbers : true,
		pagination : true,
		striped : true,
		singleSelect : true,
		toolbar : "#permission_toolbar",

		columns : [ [ {
			field : "name",
			title : "权限名称",
			width : 1,
			align : "center"
		}, {
			field : "resource",
			title : "权限表达式",
			width : 1,
			align : "center"
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			if (!rowData.state) {
				$("#remove_btn").linkbutton("disable")
			} else {
				$("#remove_btn").linkbutton("enable")
			}
		}
	});

	// 统一绑定事件
	$("a[data-cmd]").on("click", function() {
		// 获取当前按钮要执行的方法名
		var cmd = $(this).data("cmd");
		// 调用对应的方法
		methodObj[cmd]();
	})

	var methodObj = {
		// 关键字查询
		searchForm : function() {
			// 获取关键字
			var keyword = $("#permission_toolbar [name='keyword']").val();
			if (keyword == null || keyword == "") {
				$.messager.alert("温馨提示", "请输入要查询的关键字：'权限名称' 或 '权限表达式'", "info");
			}
			permission_dataGrid.datagrid('load', {
				'keyword' : keyword
			});
		},

		// 查询全部
		searchAll : function() {
			// 手动 清空页面查询条件
			$("#permission_toolbar [name='keyword']").val('');
			// 查询全部，参数为{}
			permission_dataGrid.datagrid("load", {});
		},
		
		// 重新加载权限
		searchPermissions : function() {
			$.post("/permission_load", function(backData) {
				alert(backData.success)
				alert(backData.msg)
				/*if (backData.success) {
					$.messager.alert("温馨提示", backData.msg, "info",function(){
						// 刷新datagrid
						permission_dataGrid.datagrid("reload");
						console.log(backData.success);
					});
				} else {
					$.messager.alert("温馨提示", backData.msg, "error");
				}*/
			})
		}
	}
})
