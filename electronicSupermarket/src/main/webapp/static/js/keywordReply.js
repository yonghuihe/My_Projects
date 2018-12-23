$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var keywordReplyDatagrid,keywordReplyDialog,keywordReplyForm;
	keywordReplyDatagrid = $("#keywordReply_datagrid");
	keywordReplyDialog = $("#keywordReply_dialog");
	keywordReplyForm = $("#keywordReply_form");
	/*
	 * 初始化数据表格 
	 */
	keywordReplyDatagrid.datagrid({
		url:"/keywordReply_list.do",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#keywordReply_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	keywordReplyDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#keywordReply_dialog_bt'
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
				keywordReplyForm.form("clear");
				keywordReplyDialog.dialog("setTitle","新增");
				keywordReplyDialog.dialog("open");
			},
			edit:function(){
				var rowData = keywordReplyDatagrid.datagrid("getSelected");
				if(rowData){
					keywordReplyForm.form("clear");
					keywordReplyDialog.dialog("setTitle","编辑");
					keywordReplyDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					keywordReplyForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = keywordReplyDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/keywordReply_delete.do?keywordReplyId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										keywordReplyDatagrid.datagrid("reload");
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
				keywordReplyDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/keywordReply_update.do"
				}else{
					url = "/keywordReply_save.do";
				}
				keywordReplyForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								keywordReplyDialog.dialog("close");
								keywordReplyDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				keywordReplyDialog.dialog("close");
			}
	}
});
