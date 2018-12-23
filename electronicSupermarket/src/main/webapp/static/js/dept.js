$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var deptDatagrid,deptDialog,deptForm;
	deptDatagrid = $("#dept_datagrid");
	deptDialog = $("#dept_dialog");
	deptForm = $("#dept_form");
	/*
	 * 初始化数据表格 
	 */
	deptDatagrid.datagrid({
		url:"/dept_list.do",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#dept_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	deptDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#dept_dialog_bt'
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
				deptForm.form("clear");
				deptDialog.dialog("setTitle","新增");
				deptDialog.dialog("open");
			},
			edit:function(){
				var rowData = deptDatagrid.datagrid("getSelected");
				if(rowData){
					deptForm.form("clear");
					deptDialog.dialog("setTitle","编辑");
					deptDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					deptForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = deptDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/dept_delete.do?deptId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										deptDatagrid.datagrid("reload");
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
				deptDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/dept_update.do"
				}else{
					url = "/dept_save.do";
				}
				deptForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								deptDialog.dialog("close");
								deptDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				deptDialog.dialog("close");
			}
	}
});
