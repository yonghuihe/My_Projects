$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var orderBillDatagrid,orderBillDialog,orderBillForm;
	orderBillDatagrid = $("#orderBill_datagrid");
	orderBillDialog = $("#orderBill_dialog");
	orderBillForm = $("#orderBill_form");
	/*
	 * 初始化数据表格 
	 */
	orderBillDatagrid.datagrid({
		url:"/orderBill_list.do",
		fit:true,
		rownumbers:true,
		columns:[[
			{field:'sn',title:'订单编号',width:100,},
			{field:'product',title:'货品名称',width:100,formatter: function(value,row,index){
				if (value){
					return value.productName;
				} else {
					return '';
				}
			}
			},
			{field:'price',title:'单价',width:100,align:'right'},
			{field:'totalNumber',title:'数量',width:100,align:'right'},
			{field:'totalAmount',title:'订单总金额',width:100,align:'right'},
			{field:'status',title:'订单状态',width:100,align:'right'},
			{field:'vdate',title:'下单时间',width:100,align:'right'},
			{field:'user',title:'收货人',width:100,align:'right',formatter: function(value,row,index){
				if (value){
					console.log(value.nickname)
					return value.nickname;
				} else {
					return '';
				}
			}
			},
	]],
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#orderBill_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	orderBillDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#orderBill_dialog_bt'
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
				orderBillForm.form("clear");
				orderBillDialog.dialog("setTitle","新增");
				orderBillDialog.dialog("open");
			},
			edit:function(){
				var rowData = orderBillDatagrid.datagrid("getSelected");
				if(rowData){
					orderBillForm.form("clear");
					orderBillDialog.dialog("setTitle","编辑");
					orderBillDialog.dialog("open");
					console.log(rowData)
					if(rowData.product)
						rowData["product.id"] = rowData.product.id;
					if(rowData.user)
						rowData["user.id"] = rowData.user.id;
					console.log(rowData.product.id)
					orderBillForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = orderBillDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/orderBill_delete?orderBillId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										orderBillDatagrid.datagrid("reload");
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
				orderBillDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/orderBill_update"
				}else{
					url = "/orderBill_save";
				}
				orderBillForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								orderBillDialog.dialog("close");
								orderBillDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				orderBillDialog.dialog("close");
			}
	}
});
