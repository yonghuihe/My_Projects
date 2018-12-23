<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/authority/commonAll.js"></script>
<script type="text/javascript" src="js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="js/common-page.js"></script>
<script type="text/javascript">
$(function(){
	$(".loadpermission_btn").click(function(){
		art.dialog({
			title:"确认",
			content:"重新加载权限会消耗一定的系统性能,是否立刻执行?",
			icon:"question",
			ok:function(){
				var dialog=art.dialog({title:"请等待"});
				$.ajax({
					dataType:"json",
					type:"post",
					url:"permission_reload.action",
					success:function(){
						dialog.title("提示").content("加载完成!").button({
							name:"确认",
							callback:function(){
								window.location.reload();
							}
						});
					}
				});
			},
			cancel:true
		});
	});
});
</script>
<title>PSS-权限管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<%@include file="../common/common-msg.jsp" %>
	<s:form id="searchForm" action="permission" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
							<input type="button" value="加载权限" class="ui_input_btn01 loadpermission_btn"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"></th>
							<th>编号</th>
							<th>权限表达式</th>
							<th>权限名称</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td></td>
									<td><s:property value="#item.id"/></td>
									<td><s:property value="#item.expression"/></td>
									<td><s:property value="#item.name"/></td>
									<td>
										<s:a action="permission_delete"><s:param name="permission.id" value="#item.id"/>删除</s:a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="../common/common-page.jsp" %>
			</div>
		</div>
	</s:form>
</body>
</html>
