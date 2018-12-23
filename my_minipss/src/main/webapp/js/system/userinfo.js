$(function() {
	$(".batchdelete-btn").click(function() {
		// 查看是否有选中的行
		var selected = $("input[name='IDCheck']:checked");
		var batchDeleteUrl = $(this).data("url");
		if (selected.length <= 0) {
			art.dialog({
				title : "提示信息",
				content : "请选择要删除的数据",
				ok : function() {
				}
			});
			showDialog("提示信息", "请选择要删除的数据");
		} else {
			var ids = [];
			// 如果有选中的行，把选中的行的id保存起来
			$("input[name='IDCheck']:checked").each(function(index, item) {
				ids[index] = $(item).val();
			});
			// 在删除之前先让操作者确认是否要删除用户
			art.dialog({
				title : "提示信息",
				content : "是否确认要删除选中的员工信息？",
				icon : "question",
				ok : function() {
					// 发送ajax请求，把ids发送给后台
					$.ajax({
						dataType : 'json',
						type : "POST",
						url : batchDeleteUrl,
						data : $.param({
							"ids" : ids
						}, true),
						success : function(response) {
							art.dialog({icon:'succeed', title:'提示', drag:false, resize:false, content:'批量删除成功', 
				        		ok:true,close:function(){window.location.reload()}});
						}
					});
				},
				cancel : true
			})
		};
	});
	
	if($("#editForm").size()>0){
		$("#editForm").validate({
			rules:{
				"employee.name":{
					required:true,
					minlength: 4
				},
				"employee.password":{
					required:true,
					minlength: 4
				},
				repassword:{
					equalTo: "#password"
				},
				"employee.email":"email",
				"employee.age":{
					digits: true,
					range: [18, 60]
				}
			},
			messages:{
				"employee.name":{
					required:"用户名不能为空!",
					minlength:"用户名长度至少4位"
				},
				"employee.password":{
					required:"密码不能为空!",
					minlength:"密码长度至少4位"
				},
				repassword:"两次输入的密码不一致",
				"employee.email":"请输入正确格式的EMAIL",
				"employee.age":{
					digits:"请输入合法的年龄",
					range:"年龄范围在18到60岁"
				}
			}
		});
	}
	
	$("#select").click(function(){
		$(".all_roles option:selected").appendTo($(".selected_roles"));
	})
	$("#selectAll").click(function(){
		$(".all_roles option").appendTo($(".selected_roles"));
	})
	$("#deselect").click(function(){
		$(".selected_roles option:selected").appendTo($(".all_roles"));
	})
	$("#deselectAll").click(function(){
		$(".selected_roles option").appendTo($(".all_roles"));
	})
	
	$("#pselect").click(function(){
		$(".all_permissions option:selected").appendTo($(".selected_permissions"));
	})
	$("#pselectAll").click(function(){
		$(".all_permissions option").appendTo($(".selected_permissions"));
	})
	$("#pdeselect").click(function(){
		$(".selected_permissions option:selected").appendTo($(".all_permissions"));
	})
	$("#pdeselectAll").click(function(){
		$(".selected_permissions option").appendTo($(".all_permissions"));
	})

	$("#mselect").click(function(){
		$(".all_systemMenus option:selected").appendTo($(".selected_systemMenus"));
	})
	$("#mselectAll").click(function(){
		$(".all_systemMenus option").appendTo($(".selected_systemMenus"));
	})
	$("#mdeselect").click(function(){
		$(".selected_systemMenus option:selected").appendTo($(".all_systemMenus"));
	})
	$("#mdeselectAll").click(function(){
		$(".selected_systemMenus option").appendTo($(".all_systemMenus"));
	})
	
	// 添加全选方法：
	$("#all").click(function() {
		var checked = this.checked;
		$("input[name='IDCheck']").each(function() {
			$(this).prop("checked", checked);
		});
	});

	//提交表单的时候，需要把已经选中的权限列表中的所有项全部选中
	$("#editForm").submit(function(){
		if($(".selected_permissions option:selected").size()!=$(".selected_permissions option")){
			$(".selected_permissions option").prop("selected",true);
		}
		if($(".selected_roles option:selected").size()!=$(".selected_roles option")){
			$(".selected_roles option").prop("selected",true);
		}
		if($(".selected_systemMenus option:selected").size()!=$(".selected_systemMenus option")){
			$(".selected_systemMenus option").prop("selected",true);
		}
	});
	
	if($(".selected_permissions option").size()>0){
		var arr=$.map($(".selected_permissions option"),function(option){
			return $(option).attr("value");
		});
		$(".all_permissions option").filter(function(index){
			return $.inArray($(this).attr("value"),arr)>=0;
		}).remove();
	}
	if($(".selected_roles option").size()>0){
		var arr=$.map($(".selected_roles option"),function(option){
			return $(option).attr("value");
		});
		$(".all_roles option").filter(function(index){
			return $.inArray($(this).attr("value"),arr)>=0;
		}).remove();
	}
	if($(".selected_systemMenus option").size()>0){
		var arr=$.map($(".selected_systemMenus option"),function(option){
			return $(option).attr("value");
		});
		$(".all_systemMenus option").filter(function(index){
			return $.inArray($(this).attr("value"),arr)>=0;
		}).remove();
	}
});