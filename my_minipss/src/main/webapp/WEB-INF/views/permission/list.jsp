<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/jquery/jquery.validate.js"></script>
	<script type="text/javascript" src="/js/system/permission.js"></script>
	<title>PSS-账户管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>
	<s:form id="searchForm" action="permission" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="加载权限" class="ui_input_btn01 loadpermission_btn" data-href='<s:url action="permission_reload" />'/>
							<input type="button" value="新增" disabled class="ui_input_btn01 btn_input" data-href=<s:url value="permission_input"/>>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<!-- <th width="30"><input type="checkbox" id="all" /></th> -->
							<th>编号</th>
							<th>权限表达式</th>
							<th>权限名称</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="rs">
								<tr>
									<%-- <td><input type="checkbox" name="IDCheck" id='<s:property value="#rs.id"/>' class="acb" /></td> --%>
									<td><s:property value="#rs.id"/></td>
									<td><s:property value="#rs.expression"/></td>
									<td><s:property value="#rs.name"/></td>
									<td>
										<%-- <s:a action="permission_input"><s:param name="permission.id" value="#rs.id"/>编辑</s:a> --%>
										<s:a action="permission_delete"><s:param name="permission.id" value="#rs.id"/>删除</s:a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="../../views/common/common_page.jsp" %>
			</div>
		</div>
	</s:form>
</body>
</html>
