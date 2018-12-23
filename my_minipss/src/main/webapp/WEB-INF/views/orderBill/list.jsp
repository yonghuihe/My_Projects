<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<script type="text/javascript" src="/js/system/orderBill.js"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
	<title>PSS-采购订单管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="orderBill" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间：
							<s:date name="queryObject.beginDate" format="yyyy-MM-dd" var="beginDate"/>
							<s:textfield class="ui_input_txt02 Wdate beginDate" name="queryObject.beginDate" value="%{#beginDate}"/>
							~
							<s:date name="queryObject.beginDate" format="yyyy-MM-dd" var="endDate"/>
							<s:textfield class="ui_input_txt02 Wdate endDate" name="queryObject.endDate" value="%{#endDate}"/>
							供应商：
							<s:select list="#suppliers" cssClass="ui_select01" name="queryObject.supplierId" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择部门---"/>
							状态：
							<s:select list="#{'-1':'--全部--','0':'--未审核--','1':'--已审核--'}" name="queryObject.status" cssClass="ui_select02"></s:select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 page_btn"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-href='<s:url action="orderBill_input" />'/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>订单编号</th>
							<th>业务时间</th>
							<th>供应商</th>
							<th>采购数量</th>
							<th>采购金额</th>
							<th>制单人</th>
							<th>审核人</th>
							<th>审核状态</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td><s:property value="#item.sn"/></td>
									<s:date name="#item.vdate" format="yyyy-MM-dd" var="formatDate"/>
									<td><s:property value="formatDate"/></td>
									<td><s:property value="#item.supplier.name"/></td>
									<td><s:property value="#item.totalNumber"/></td>
									<td><s:property value="#item.totalAmount"/></td>
									<td><s:property value="#item.inputUser.name"/></td>
									<td><s:property value="#item.auditor.name"/></td>
									<td>
										<s:if test="#item.status == 0">
											<font color="green">未审核</font>
										</s:if>
										<s:elseif test="#item.status == 1">
											<font color="red">已审核</font>
										</s:elseif>
									</td>
									<td>
										<s:if test="#item.status == 0">
											<s:url action="orderBill_audit" var="#auditUrl">
												<s:param name="orderBill.id" value="#item.id"/>
											</s:url>
											<a href="javascript:;" class="btn_audit" data-url='<s:property value="#auditUrl"/>'>审核</a>
											<s:a action="orderBill_input"><s:param name="orderBill.id" value="#item.id"/>编辑</s:a>
											<s:url action="orderBill_delete" var="#delUrl">
												<s:param name="orderBill.id" value="#item.id"/>
											</s:url>
											<a href="javascript:;" class="btn_delete" data-url='<s:property value="#delUrl"/>'>删除</a>
										</s:if>
										<s:elseif test="#item.status == 1">
											<s:a action="orderBill_view"><s:param name="orderBill.id" value="#item.id"/>查看</s:a>
										</s:elseif>
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
