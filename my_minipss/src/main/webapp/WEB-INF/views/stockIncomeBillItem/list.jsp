<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<title>PSS-StockIncomeBillItem管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="stockIncomeBillItem" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-href='<s:url action="stockIncomeBillItem_input" />'/> 
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
							<th>stockIncomeBill</th>
							<th>remark</th>
							<th>product</th>
							<th>number</th>
							<th>costPrice</th>
							<th>amount</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td></td>
									<td><s:property value="#item.id"/></td>
									<td><s:property value="#item.stockIncomeBill"/></td>
									<td><s:property value="#item.remark"/></td>
									<td><s:property value="#item.product"/></td>
									<td><s:property value="#item.number"/></td>
									<td><s:property value="#item.costPrice"/></td>
									<td><s:property value="#item.amount"/></td>
									<td>
										<s:a action="stockIncomeBillItem_input"><s:param name="stockIncomeBillItem.id" value="#item.id"/>编辑</s:a>
										<s:url action="stockIncomeBillItem_delete" var="#delUrl">
											<s:param name="stockIncomeBillItem.id" value="#item.id"/>
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
