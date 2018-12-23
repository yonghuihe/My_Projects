<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>信息管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form name="editForm" action="${className?uncap_first}_save" method="post" id="editForm">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">${className}编辑</span>
				<div id="page_close">
					<a>
						<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
					<s:hidden name="${className?uncap_first}.id" />
					<#list propNames as prop>
					<tr>
						<td class="ui_text_rt" width="140">${prop}</td>
						<td class="ui_text_lt">
							<s:textfield name="${className?uncap_first}.${prop}" cssClass="ui_input_txt02"/>
						</td>
					</tr>
					</#list>
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