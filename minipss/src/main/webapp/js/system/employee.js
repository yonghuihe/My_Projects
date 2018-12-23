$(function(){
	$("#all").click(function(){
		var checked=this.checked;
		$("input[name=IDCheck]").prop("checked",checked);
	});
	
	$(".btn_batchdelete").click(function(){
		//得到所有选中的checkbox;
		//从这些checkbox中得到对应的id值;
		//把这些id值放到一个数组里面;
		var ids=$.map($("input[name=IDCheck]:checked"),function(item){
			return $(item).data("eid");
		});
		//使用ajax提交
		if(ids.length<=0){
			art.dialog("请选择要批量删除的员工!",function(){});
		}else{
			art.dialog({
				title:"确认",
				content:"你是否确认要删除选中的员工",
				icon:"question",
				ok:function(){
					var dialog=art.dialog({title:"请稍等"});
					$.ajax({
						dataType:"json",
						url:"employee_batchDelete.action",
						data:{"ids":ids},
						success:function(){
							dialog.title("提示信息").content("批量删除成功").button({
								name:"确定",
								callback:function(){
									window.location.reload();
								}
							});
						}
					});
				},
				cancel:true
			});
		}
	});
	
	//前段页面验证
	if($("#editForm").size()>0){
		$("#editForm").validate({
			rules:{
				"employee.name":{
					required:true,
					minlength: 6
				},
				"employee.password":{
					required:true,
					minlength: 4
				},
				"repassword":{
					required:true,
					equalTo:"#password"
				},
				"employee.email":"email",
				"employee.age":{
					digits:true,
					range: [18, 60]
				}
			},
			messages:{
				"employee.name":{
					required:"请输入用户名",
					minlength:"用户名最少为{0}位"
				},
				"employee.password":{
					required:"请输入密码",
					minlength: "密码长度至少为{0}位"
				},
				"repassword":{
					required:"请输入验证密码",
					equalTo:"验证密码和密码不一致"
				},
				"employee.email":"请输入正确的Email格式",
				"employee.age":{
					digits:"请输入正确的年龄",
					range: "年龄的范围在{0}~{1}之间"
				}
			}
		});
	}
	
	
	//为选择按钮添加事件
	$(".select").click(function(){
		$(".all_roles option:selected").appendTo($(".selected_roles"));
	})
	
	//全选
	$(".selectAll").click(function(){
		$(".all_roles option").appendTo($(".selected_roles"));
	})
	
	//反选
	$(".deselect").click(function(){
		$(".selected_roles option:selected").appendTo($(".all_roles"));
	})
	
	//全反选
	$(".deselectAll").click(function(){
		$(".selected_roles option").appendTo($(".all_roles"));
	})
	
	//提交表单的时候,把选择的权限列表中所有的权限全部选中;
	$("#editForm").submit(function(){
		$(".selected_roles option").prop("selected",true);
	});
	
	//在编辑的时候把已经选中的权限从全部权限列表中去掉;
	if($(".selected_roles option").size()>0){
		var ids=$.map($(".selected_roles option"),function(item){
			return item.value;
		});
		
		$(".all_roles option").filter(function(){
			return $.inArray($(this).val(),ids)>=0;
		}).remove();
	}
	
});