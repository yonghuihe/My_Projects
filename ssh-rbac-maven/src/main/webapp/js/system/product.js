$(function(){
	$('.fancybox').fancybox();
	
	if($("#editForm").size()>0){
		$("#editForm").validate({
			rules:{
				"product.name":{
					required:true
				},
				"product.salePrice":{
					required:true,
					number: true
				},
				"product.costPrice":{
					required:true,
					number: true
				},
				"pic":"accept"
			},
			messages:{
				"product.name":{
					required:"货品名不能为空!"
				},
				"product.salePrice":{
					required:"货品零售价不能为空！",
					number:"请输入正确的价格"
				},
				"product.costPrice":{
					required:"货品成本价不能为空！",
					number:"请输入正确的价格"
				},
				"pic":"请上传正确图片格式"
			}
		});
	}
});