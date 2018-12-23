$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var messageHandlingDatagrid,messageHandlingDialog,messageHandlingForm;
	messageHandlingDatagrid = $("#messageHandling_datagrid");
	messageHandlingDialog = $("#messageHandling_dialog");
	messageHandlingForm = $("#messageHandling_form");
	/*
	 * 初始化数据表格 
	 */
	messageHandlingDatagrid.datagrid({
		url:"/messageHandling_list.do",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#messageHandling_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	messageHandlingDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#messageHandling_dialog_bt'
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
				messageHandlingForm.form("clear");
				messageHandlingDialog.dialog("setTitle","新增");
				messageHandlingDialog.dialog("open");
			},
			edit:function(){
				var rowData = messageHandlingDatagrid.datagrid("getSelected");
				if(rowData){
					messageHandlingForm.form("clear");
					messageHandlingDialog.dialog("setTitle","编辑");
					messageHandlingDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					messageHandlingForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = messageHandlingDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/messageHandling_delete.do?messageHandlingId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										messageHandlingDatagrid.datagrid("reload");
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
				messageHandlingDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/messageHandling_update.do"
				}else{
					url = "/messageHandling_save.do";
				}
				messageHandlingForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								messageHandlingDialog.dialog("close");
								messageHandlingDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				messageHandlingDialog.dialog("close");
			}
	}
});
