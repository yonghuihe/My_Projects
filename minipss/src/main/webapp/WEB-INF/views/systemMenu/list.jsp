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
<title>PSS-系统菜单管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<%@include file="../common/common-msg.jsp" %>
	<s:form id="searchForm" action="systemMenu" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
							<s:url action="systemMenu_input" var="url"><s:param name="systemMenu.parent.id" value="systemMenu.id" /></s:url>
							<input type="button" value="新增" class="ui_input_btn01 btn_redirect" data-url='<s:property value="#url"/>'/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					当前目录 : <s:a action="systemMenu">根目录</s:a>
					<s:iterator value="systemMenu.allParents">
					> <s:a action="systemMenu"><s:param name="qo.parentId" value="id" /><s:property value="name" /></s:a>
					</s:iterator>
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>编号</th>
							<th>父级菜单</th>
							<th>菜单名称</th>
							<th>URL</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td><s:property value="#item.sn"/></td>
									<td><s:property value="#item.parentDisplay"/></td>
									<td><s:property value="#item.name"/></td>
									<td><s:property value="#item.action"/></td>
									<td>
										<s:a action="systemMenu_input"><s:param name="systemMenu.id" value="#item.id"/>编辑</s:a>
										<s:a action="systemMenu_delete"><s:param name="systemMenu.id" value="#item.id"/>删除</s:a>
										<s:a action="systemMenu"><s:param name="qo.parentId" value="#item.id"/>查看下级菜单</s:a>
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
