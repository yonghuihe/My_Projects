$(function() {
	//抽取变量
	var role_datagrid = $("#role_datagrid");
	var role_dialog = $("#role_dialog");
	var role_form = $("#role_form");

	role_datagrid.datagrid({
		fit : true,
		fitColumns : true,
		url : "/role_list",
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : "#role_toolbar",
		columns : [ [ {
			field : "sn",
			width : 1,
			title : "编号"
		},{
			field : "name",
			width : 1,
			title : "名字"
		}] ]
	});

	role_dialog.dialog({
		width : 500,
		height : 400,
		buttons : '#role_buttons',
		closed : true
	});

	//方法统一管理
	var mehtodObj = {
		add : function() {
			//清空已有权限的数据
			$("#selfPermission").datagrid("loadData",[]);
			//清空表单内容
			role_form.form("clear");
			//设置标题
			role_dialog.dialog("setTitle", "新增角色");
			//打开弹出框
			role_dialog.dialog("open");
		},

		edit : function() {
			//判断是否选中数据
			var row = role_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			//清空表单内容
			role_form.form("clear");
			//设置标题
			role_dialog.dialog("setTitle", "编辑角色");
			
			//查询该角色拥有的权限
			var options = $("#selfPermission").datagrid("options");
			options.url = "/permission_getPermissionByRid";
			
			//重新加载已有权限数据
			$("#selfPermission").datagrid("load",{
				id:row.id
			});
			
			//回显表单数据
			role_form.form("load", row);

			//打开弹出框
			role_dialog.dialog("open");
		},

		cancel : function() {
			//关闭弹出框
			role_dialog.dialog("close");
		},

		reload : function() {
			//清空keyword输入框
			$("[name='keyword']").val("");

			role_datagrid.datagrid("load", {});
		},

		save : function() {
			var url;
			//获取隐藏域的id值
			var id = $("#role_dialog [name='id']").val();
			if (id) {
				url = "/role_update";
			} else {
				url = "/role_save";
			} 
			//提交表单
			role_form.form("submit", {
				url : url,
				onSubmit:function(param){
					//获取selfPermission中的所有数据
					var rows = $("#selfPermission").datagrid("getRows");
					for(var i=0;i<rows.length;i++){
						//permissions[1].id = 1
						param["permissions["+i+"].id"] = rows[i].id;
					}
				},
				success : function(data) {
					data = $.parseJSON(data);
					console.log(data)
					if (data.success) {
						//提示消息
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							//关闭弹出框
							role_dialog.dialog("close");
							//重新加载数据表格内容
							role_datagrid.datagrid("reload");
						});
					} else {
						$.messager.alert('温馨提示', data.msg, 'info');
					}
				}
			});
		},

		searchKey : function() {
			//通过load重新请求datagrid的url,并带上自定义的参数
			role_datagrid.datagrid("load", {
				keyword : $("[name='keyword']").val()
			});
		}
	}
	
	//按钮统一绑定事件
	$("a[data-cmd]").on("click",function(){
		//拿到当前的按钮需要执行的方法名称
		var cmd = $(this).data("cmd");
		//通过管理方法的对象来调用
		mehtodObj[cmd]();
	});
	
	$("#allPermission").datagrid({
		title:'所有权限',
		width:220,
		height:280,
		url:"/permission_list",
		fitColumns:true,
		singleSelect:true,
		columns:[[
		   {title:'权限名称',field:'name',width:1,align:'center'}       
		]],
		onDblClickRow:function(rowIndex, rowData){
			//获取已有权限的所有行数据
			var rows = $('#selfPermission').datagrid("getRows");
			console.log(rows)
			for(var i=0;i<rows.length;i++){
				//取出每行数据的id来进行判断
				if(rows[i].id == rowData.id){
					//如果已经存在就直接选中
					$('#selfPermission').datagrid("selectRow",i);
					return;
				}
			}
			//添加到已有权限中
			$('#selfPermission').datagrid('appendRow',rowData);
		}
	});
	
	$("#selfPermission").datagrid({
		title:'已有权限',
		width:220,
		height:280,
		fitColumns:true,
		singleSelect:true,
		columns:[[
			{title:'权限名称',field:'name',width:1,align:'center'}       
		]],
		onDblClickRow:function(rowIndex, rowData){
			$('#selfPermission').datagrid('deleteRow',rowIndex);
		}
	});
	
	

})
