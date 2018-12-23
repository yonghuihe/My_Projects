<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>PSS-账户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/system/userinfo.js"></script>
	<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>
	<s:form id="searchForm" action="employee" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<s:textfield class="ui_input_txt02" name="queryObject.keyword"/>
							所属部门
							<s:select list="#depts" cssClass="ui_select01" name="queryObject.deptId" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择部门---"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 page_btn"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-href=<s:url value="employee_input"/>>
							<input type="button" value="批量删除" class="ui_input_btn01 batchdelete-btn" data-href=<s:url value="employee_batchDelete"/>>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th>角色</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="rs">
								<tr>
									<td><input type="checkbox" name="IDCheck" value='<s:property value="#rs.id"/>' class="acb" /></td>
									<td><s:property value="#rs.id"/></td>
									<td><s:property value="#rs.name"/></td>
									<td><s:property value="#rs.email"/></td>
									<td><s:property value="#rs.age"/></td>
									<td><s:property value="#rs.dept.name"/></td>
									<td><s:property value="#rs.roleNames"/></td>
									<td>
										<s:a action="employee_input"><s:param name="employee.id" value="#rs.id"/>编辑</s:a>
										<s:url action="employee_delete" var="delUrl">
											<s:param name="employee.id" value="#rs.id"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url='<s:property value="#delUrl"/>'>删除</a>
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