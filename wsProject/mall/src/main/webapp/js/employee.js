$(function() {
	//抽取变量
	var emp_datagrid = $("#emp_datagrid");
	var emp_dialog = $("#emp_dialog");
	var emp_form = $("#emp_form");

	emp_datagrid.datagrid({
		fit : true,
		fitColumns : true,
		url : "/employee_list",
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : "#emp_toolbar",
		columns : [ [ {
			field : "username",
			width : 1,
			title : "用户名"
		}, {
			field : "realname",
			width : 1,
			title : "真实名字"
		}, {
			field : "tel",
			width : 1,
			title : "手机号码"
		}, {
			field : "email",
			width : 1,
			title : "邮箱"
		}, {
			field : "dept",
			width : 1,
			title : "部门",
			formatter : deptformatter
		}, {
			field : "inputtime",
			width : 1,
			title : "入职时间"
		}, {
			field : "state",
			width : 1,
			title : "状态",
			formatter : stateformatter
		}, {
			field : "admin",
			width : 1,
			title : "是否管理员",
			formatter : adminFormatter
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			//判断状态是否是离职状态,如果是就禁用离职按钮
			if (!rowData.state) {
				$("#removeBtn").linkbutton("disable");
			} else {
				$("#removeBtn").linkbutton("enable");
			}

		}
	});

	function stateformatter(value, row, index) {
		return value ? "在职" : "<font color='red'>离职</font>";
	}
	function adminFormatter(value, row, index) {
		return value ? "是" : "否";
	}
	function deptformatter(value, row, index) {
		return value ? value.name : "";
	}

	emp_dialog.dialog({
		width : 290,
		height : 290,
		buttons : '#emp_buttons',
		closed : true
	});

/*	var obj = {
		showName : function() {
		}
	};
	obj.showName();
	obj["showName"]();*/

	//方法统一管理
	var mehtodObj = {
		add : function() {
			//清空表单内容
			emp_form.form("clear");
			//设置标题
			emp_dialog.dialog("setTitle", "新增员工");
			//打开弹出框
			emp_dialog.dialog("open");
		},

		edit : function() {
			//判断是否选中数据
			var row = emp_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}

			//清空表单内容
			emp_form.form("clear");
			//设置标题
			emp_dialog.dialog("setTitle", "编辑员工");

			//部门数据处理(基于同名原则来做回显,所以必须要有[dept.id]属性)
			if (row.dept) {
				row["dept.id"] = row.dept.id;
			}
			//是否管理员数据处理
			row["admin"] = row.admin + "";

			console.log(row)
			//回显表单数据
			emp_form.form("load", row);

			//回显角色数据
			$.get("/getRidByEid?id="+row.id,function(data){
				console.log(data);
				$("#roleCombo").combobox("setValues",data);
			});
			
			/*发送同步请求(使用这种方式,就会先执行完请求的所有操作,再去打开弹出框)
			 var html = $.ajax({
			  url: "some.php",
			  async: false
			 }).responseText;*/
			 
			//打开弹出框
			emp_dialog.dialog("open");
		},

		cancel : function() {
			//关闭弹出框
			emp_dialog.dialog("close");
		},

		reload : function() {
			//清空keyword输入框
			$("[name='keyword']").val("");

			emp_datagrid.datagrid("load", {});
		},

		save : function() {
			var url;
			//获取隐藏域的id值
			var id = $("#emp_dialog [name='id']").val();
			if (id) {
				url = "/employee_update";
			} else {
				url = "/employee_save";
			}

			//提交表单
			emp_form.form("submit", {
				url : url,
				onSubmit:function(param){
					//获取角色下拉框选中的所有值
					var values = $("#roleCombo").combobox("getValues");
					for(var i=0;i<values.length;i++){
						param["roles["+i+"].id"] = values[i];
					}
				},
				success : function(data) {
					data = $.parseJSON(data);
					console.log(data)
					if (data.success) {
						//提示消息
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							//关闭弹出框
							emp_dialog.dialog("close");
							//重新加载数据表格内容
							emp_datagrid.datagrid("reload");
						});
					} else {
						$.messager.alert('温馨提示', data.msg, 'info');
					}
				}
			});
		},

		//离职按钮的事件
		updateState : function() {
			//判断是否选中数据
			var row = emp_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}

			$.get("/employee_updateState?id=" + row.id, function(data) {
				if (data.success) {
					//提示消息
					$.messager.alert('温馨提示', data.msg, 'info', function() {
						//重新加载数据表格内容
						emp_datagrid.datagrid("reload");
					});
				} else {
					$.messager.alert('温馨提示', data.msg, 'info');
				}
			});
		},

		searchKey : function() {
			//通过load重新请求datagrid的url,并带上自定义的参数
			emp_datagrid.datagrid("load", {
				keyword : $("[name='keyword']").val()
			});
		},
		
		exportFile:function(){
			window.location.href = "/employee_export"
		}
	}
	
	//按钮统一绑定事件
	$("a[data-cmd]").on("click",function(){
		//拿到当前的按钮需要执行的方法名称
		var cmd = $(this).data("cmd");
		//通过管理方法的对象来调用
		mehtodObj[cmd]();
	});
	
	$("#file_dialog").dialog({
		width:200,
		height:200,
		title:"导入文件"
	})
	
	
	

})
