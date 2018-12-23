/**
 * 页面加载完成,处理通用的分页
 */
$(function(){
	
	//注册跳转页面
	$(".btn_page").click(function(){
		$("input[name='qo.currentPage']").val($(this).data("page")||$("input[name='qo.currentPage']").val());
		$("#searchForm").submit();
	});
	
	$("[name='qo.pageSize']").change(function(){
		$("#searchForm").submit();
	});
	
	$(".btn_redirect").click(function(){
		window.location.href=$(this).data("url");
	});
	
})