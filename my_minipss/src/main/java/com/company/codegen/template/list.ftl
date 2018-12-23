<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<title>PSS-${className}管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="${className?uncap_first}" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-href='<s:url action="${className?uncap_first}_input" />'/> 
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
							<#list propNames as prop>							
							<th>${prop}</th>
							</#list>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td></td>
									<td><s:property value="#item.id"/></td>
									<#list propNames as prop>
									<td><s:property value="#item.${prop}"/></td>
									</#list>
									<td>
										<s:a action="${className?uncap_first}_input"><s:param name="${className?uncap_first}.id" value="#item.id"/>编辑</s:a>
										<s:url action="${className?uncap_first}_delete" var="#delUrl">
											<s:param name="${className?uncap_first}.id" value="#item.id"/>
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
