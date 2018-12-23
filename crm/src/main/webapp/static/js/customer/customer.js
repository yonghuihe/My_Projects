$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var customerDatagrid,customerDialog,customerForm;
	customerDatagrid = $("#customer_datagrid");
	customerDialog = $("#customer_dialog");
	customerForm = $("#customer_form");
	/*
	 * 初始化数据表格 
	 */
	customerDatagrid.datagrid({
		url:"/customer_list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#customer_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	customerDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#customer_dialog_bt'
	});
	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				customerForm.form("clear");
				customerDialog.dialog("setTitle","新增");
				customerDialog.dialog("open");
			},
			edit:function(){
				var rowData = customerDatagrid.datagrid("getSelected");
				if(rowData){
					customerForm.form("clear");
					customerDialog.dialog("setTitle","编辑");
					customerDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					customerForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = customerDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/customer_delete?customerId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										customerDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的数据!","warining");
				}
			},
			reload:function(){
				customerDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/customer_update"
				}else{
					url = "/customer_save";
				}
				customerForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								customerDialog.dialog("close");
								customerDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				customerDialog.dialog("close");
			}
	}
});
