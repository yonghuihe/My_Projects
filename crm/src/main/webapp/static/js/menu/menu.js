$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var menuDatagrid,menuDialog,menuForm;
	menuDatagrid = $("#menu_datagrid");
	menuDialog = $("#menu_dialog");
	menuForm = $("#menu_form");
	/*
	 * 初始化数据表格 
	 */
	menuDatagrid.datagrid({
		url:"/menu_list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#menu_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	menuDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#menu_dialog_bt'
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
				menuForm.form("clear");
				menuDialog.dialog("setTitle","新增");
				menuDialog.dialog("open");
			},
			edit:function(){
				var rowData = menuDatagrid.datagrid("getSelected");
				if(rowData){
					menuForm.form("clear");
					menuDialog.dialog("setTitle","编辑");
					menuDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					menuForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = menuDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/menu_delete?menuId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										menuDatagrid.datagrid("reload");
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
				menuDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/menu_update"
				}else{
					url = "/menu_save";
				}
				menuForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								menuDialog.dialog("close");
								menuDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				menuDialog.dialog("close");
			}
	}
});
