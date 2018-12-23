<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<title>PSS-SystemMenu管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="systemMenu" method="post">
		<s:hidden name="queryObject.parentId"/>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<%-- <div id="box_center">
							名称/编号<s:textfield class="ui_input_txt02" name="queryObject.keyword"></s:textfield>
						</div> --%>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 page_btn"/>
							<s:url action="systemMenu_input" var="system_url">
								<s:param name="queryObject.parentId" value="queryObject.parentId"/>
							</s:url>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" 
								data-href='<s:property value="#system_url"/>'/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<a href="systemMenu">根目录</a>
				<s:iterator value="#parentPath">
					><a href="systemMenu?queryObject.parentId=<s:property value="id"/>"><s:property value="name"/></a>
				</s:iterator>
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"></th>
							<th>编号</th>
							<th>菜单编码</th>
							<th>菜单名称</th>
							<th>父菜单</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td></td>
									<td><s:property value="#item.id"/></td>
									<td><s:property value="#item.sn"/></td>
									<td><s:property value="#item.name"/></td>
									<td>
										<s:if test="#item.parent == null">
											父菜单
										</s:if>
										<s:else>
											<s:property value="#item.parent.name"/>
										</s:else>
									</td>
									<td>
										<s:a action="systemMenu_input">
											<s:param name="systemMenu.id" value="#item.id"/>
											<s:param name="queryObject.parentId" value="queryObject.parentId"/>
											编辑</s:a>
										<s:url namespace="/" action="systemMenu_delete" var="delURL">
											<s:param name="systemMenu.id" value="#item.id"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url='<s:property value="#delURL"/>'>删除</a>
										<s:a action="systemMenu"><s:param name="queryObject.parentId" value="#item.id"/>管理下级</s:a>
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
