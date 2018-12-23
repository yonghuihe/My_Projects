<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet"
	type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/authority/commonAll.js"></script>
<script type="text/javascript" src="js/common-page.js"></script>
<script type="text/javascript" src="js/system/employee.js"></script>
<script type="text/javascript" src="js/plugins/artDialog/artDialog.js?skin=blue"></script>

<title>PSS-账户管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<%@include file="../common/common-msg.jsp" %>
	<s:form action="employee" id="searchForm" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱 <s:textfield cssClass="ui_input_txt02" name="qo.keyword" /> 
							所属部门
							<s:select list="#depts" cssClass="ui_select01" name="qo.deptId" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" /> 
							<input type="button" value="新增" class="ui_input_btn01 btn_redirect" data-url='<s:url action="employee_input" />'/>
							<input type="button" value="批量删除" class="ui_input_btn01 btn_batchdelete" />
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
							<s:iterator value="#result.result">
							<tr>
								<td><input type="checkbox" name="IDCheck" class="acb" data-eid="${id}" autocomplete="off"/></td>
								<td><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="age" /></td>
								<td><s:property value="dept.name" /></td>
								<td><s:property value="roleNames" /></td>
								<td>
									<s:a action="employee_input"><s:param name="employee.id" value="id" />编辑</s:a>
									<s:a action="employee_delete"><s:param name="employee.id" value="id"/>删除</s:a>
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
