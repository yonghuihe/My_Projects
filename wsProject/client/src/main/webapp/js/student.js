$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var studentDatagrid,studentDialog,studentForm;
	studentDatagrid = $("#student_datagrid");
	studentDialog = $("#student_dialog");
	studentForm = $("#student_form");
	/*
	 * 初始化数据表格 
	 */
	studentDatagrid.datagrid({
		url:"/student_list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#student_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	studentDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#student_dialog_bt'
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
				studentForm.form("clear");
				studentDialog.dialog("setTitle","新增");
				studentDialog.dialog("open");
			},
			edit:function(){
				var rowData = studentDatagrid.datagrid("getSelected");
				if(rowData){
					studentForm.form("clear");
					studentDialog.dialog("setTitle","编辑");
					studentDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					studentForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = studentDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/student_delete?studentId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										studentDatagrid.datagrid("reload");
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
				studentDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/student_update"
				}else{
					url = "/student_save";
				}
				studentForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								studentDialog.dialog("close");
								studentDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				studentDialog.dialog("close");
			}
	}
});
