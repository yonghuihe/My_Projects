$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var defaultReplyDatagrid,defaultReplyDialog,defaultReplyForm;
	defaultReplyDatagrid = $("#defaultReply_datagrid");
	defaultReplyDialog = $("#defaultReply_dialog");
	defaultReplyForm = $("#defaultReply_form");
	/*
	 * 初始化数据表格 
	 */
	defaultReplyDatagrid.datagrid({
		url:"/defaultReply_list.do",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#defaultReply_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	defaultReplyDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#defaultReply_dialog_bt'
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
				defaultReplyForm.form("clear");
				defaultReplyDialog.dialog("setTitle","新增");
				defaultReplyDialog.dialog("open");
			},
			edit:function(){
				var rowData = defaultReplyDatagrid.datagrid("getSelected");
				if(rowData){
					defaultReplyForm.form("clear");
					defaultReplyDialog.dialog("setTitle","编辑");
					defaultReplyDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					defaultReplyForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = defaultReplyDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/defaultReply_delete.do?defaultReplyId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										defaultReplyDatagrid.datagrid("reload");
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
				defaultReplyDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/defaultReply_update.do"
				}else{
					url = "/defaultReply_save.do";
				}
				defaultReplyForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								defaultReplyDialog.dialog("close");
								defaultReplyDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				defaultReplyDialog.dialog("close");
			}
	}
});
