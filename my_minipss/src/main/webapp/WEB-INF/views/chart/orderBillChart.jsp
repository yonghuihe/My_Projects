<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
			$("[name='queryObject.groupType']").change(function(){
				$("#searchForm").submit();
			})
			$(".btn_search").click(function(){
				$("#searchForm").submit();
			})
		})
	</script>
	<title>PSS-订货报表管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="chart_orderBillChart" method="post">
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
							货品名称：
							<s:textfield class="ui_input_txt02" name="queryObject.keywords"/>
							供应商：
							<s:select list="#suppliers" cssClass="ui_select01" name="queryObject.supplierId" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择供应商---"/>
							品牌：
							<s:select list="#brands" cssClass="ui_select01" name="queryObject.brandId" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择品牌---"/>
							分组：
							<s:select name="queryObject.groupType" cssClass="ui_select01" list="#groupByValues"  listValue="value"></s:select>
							<%-- <s:select name="queryObject.groupType" cssClass="ui_select01" list="#{'PRODUCT':'货品名称','BRAND':'品牌','ORDERMAN':'订货人员','SUPPLIER':'供应商','MONTH':'订货日期(月)','DAY':'订货日期(日)'}" ></s:select> --%>
							<%-- <select name="queryObject.groupType">
								<option value="PRODUCT">货品名称</option>
								<option value="BRAND">品牌</option>
								<option value="ORDERMAN">订货人员</option>
								<option value="SUPPLIER">供应商</option>
								<option value="MONTH">订货日期(月)</option>
								<option value="DAY">订货日期(日)</option>
							</select> --%>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_search"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>分组类型</th>
							<th>订货数量</th>
							<th>订货金额</th>
						</tr>
						<tbody>
							<s:iterator value="#result" var="item">
								<tr>
									<td><s:property value="#item.groupByType"/></td>
									<td><s:property value="#item.totalNumber"/></td>
									<td><s:property value="#item.totalAmount"/></td>
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
