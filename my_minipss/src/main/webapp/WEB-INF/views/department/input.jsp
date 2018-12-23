<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>信息管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/jquery/jquery.validate.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/common_msg.jsp" %>
	<s:form name="editForm" action="department_save" method="post" id="editForm">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">部门编辑</span>
				<div id="page_close">
					<a>
						<img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
					<s:hidden name="department.id"/>
					<tr>
						<td class="ui_text_rt" width="140">部门名称</td>
						<td class="ui_text_lt">
							<s:textfield name="department.name" class="ui_input_txt02"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">部门代码</td>
						<td class="ui_text_lt">
							<s:textfield name="department.sn" class="ui_input_txt02"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;<input type="submit" value="确定编辑" class="ui_input_btn01"/>
							&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>