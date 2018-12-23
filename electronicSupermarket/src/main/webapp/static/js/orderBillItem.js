$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var ordBillItemDatagrid,ordBillItemDialog,ordBillItemForm;
	ordBillItemDatagrid = $("#ordBillItem_datagrid");
	ordBillItemDialog = $("#ordBillItem_dialog");
	ordBillItemForm = $("#ordBillItem_form");
	/*
	 * 初始化数据表格 
	 */
	ordBillItemDatagrid.datagrid({
		url:"/ordBillItem_list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#ordBillItem_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	ordBillItemDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#ordBillItem_dialog_bt'
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
	$("input[data-con]").blur(function(){
		var cost=$("#costprice").val()!=null?$("#costprice").val():0;
		var number=$("#number").val()!=null?$("#number").val():0;
		$("#amount").val(cost*number);
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				ordBillItemForm.form("clear");
				ordBillItemDialog.dialog("setTitle","新增");
				ordBillItemDialog.dialog("open");
			},
			edit:function(){
				var rowData = ordBillItemDatagrid.datagrid("getSelected");
				if(rowData){
					ordBillItemForm.form("clear");
					ordBillItemDialog.dialog("setTitle","编辑");
					ordBillItemDialog.dialog("open");
					if(rowData.product)
						rowData["product.id"] = rowData.product.id;
					ordBillItemForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = ordBillItemDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/ordBillItem_delete?ordBillItemId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										ordBillItemDatagrid.datagrid("reload");
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
				ordBillItemDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/ordBillItem_update"
				}else{
					url = "/ordBillItem_save";
				}
				ordBillItemForm.form("submit",{
					url:url,
					onSubmit:function(param){
						param.amount=$("#amount").val();
					},
					success:function(data){
						console.log(data);
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								ordBillItemDialog.dialog("close");
								ordBillItemDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				ordBillItemDialog.dialog("close");
			}
	}
});
