$(document).ready(function(){
	$(".page_btn").click(function(){
		$("[name='queryObject.currentPage']").val($(this).attr("data-page") || $("[name='queryObject.currentPage']").val());
		$("#searchForm").submit();
	});
	$("[name='queryObject.pageSize']").change(function(){
		$("#searchForm").submit();
	});
	//给新增按钮添加点击事件
	$(".btn_input").click(function(){
		/**
		 * 这种方式也可以：
		 * 	window.location.href = $(this).data("href");
		 */
		window.location.href = $(this).attr("data-href");
	})
	
});