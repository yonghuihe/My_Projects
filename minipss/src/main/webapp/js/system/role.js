$(function(){
	
	//为选择按钮添加事件
	$(".select").click(function(){
		$(".all_permissions option:selected").appendTo($(".selected_permissions"));
	})
	
	//全选
	$(".selectAll").click(function(){
		$(".all_permissions option").appendTo($(".selected_permissions"));
	})
	
	//反选
	$(".deselect").click(function(){
		$(".selected_permissions option:selected").appendTo($(".all_permissions"));
	})
	
	//全反选
	$(".deselectAll").click(function(){
		$(".selected_permissions option").appendTo($(".all_permissions"));
	})
	
	//在编辑的时候把已经选中的权限从全部权限列表中去掉;
	if($(".selected_permissions option").size()>0){
		var ids=$.map($(".selected_permissions option"),function(item){
			return item.value;
		});
		
		$(".all_permissions option").filter(function(){
			return $.inArray($(this).val(),ids)>=0;
		}).remove();
	}
	
	//为选择按钮添加事件
	$(".mselect").click(function(){
		$(".all_menus option:selected").appendTo($(".selected_menus"));
	})
	
	//全选
	$(".mselectAll").click(function(){
		$(".all_menus option").appendTo($(".selected_menus"));
	})
	
	//反选
	$(".mdeselect").click(function(){
		$(".selected_menus option:selected").appendTo($(".all_menus"));
	})
	
	//全反选
	$(".mdeselectAll").click(function(){
		$(".selected_menus option").appendTo($(".all_menus"));
	})
	
	//在编辑的时候把已经选中的权限从全部权限列表中去掉;
	if($(".selected_menus option").size()>0){
		var ids=$.map($(".selected_menus option"),function(item){
			return item.value;
		});
		
		$(".all_menus option").filter(function(){
			return $.inArray($(this).val(),ids)>=0;
		}).remove();
	}
	
	//提交表单的时候,把选择的权限列表中所有的权限全部选中;
	$("#editForm").submit(function(){
		$(".selected_permissions option").prop("selected",true);
		$(".selected_menus option").prop("selected",true);
	});
});