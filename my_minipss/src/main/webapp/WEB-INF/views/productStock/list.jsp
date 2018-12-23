<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".btn_search").click(function(){
				$("#searchForm").submit();
			})
		})
	</script>
	<title>PSS-商品库存管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="productStock" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							货品名称：
							<s:textfield class="ui_input_txt02" name="queryObject.keywords"/>
							仓库：
							<s:select list="#depots" cssClass="ui_select01" name="queryObject.depotId" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择仓库---"/>
							品牌：
							<s:select list="#brands" cssClass="ui_select01" name="queryObject.brandId" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择品牌---"/>
							阈值：
							<s:textfield class="ui_input_txt02" name="queryObject.stockLimit"/>
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
							<th width="30"></th>
							<th>编号</th>
							<th>货品</th>
							<th>仓库</th>
							<th>品牌</th>
							<th>库存价格</th>
							<th>库存数量</th>
							<th>库存总金额</th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td></td>
									<td><s:property value="#item.id"/></td>
									<td><s:property value="#item.product.name"/></td>
									<td><s:property value="#item.depot.name"/></td>
									<td><s:property value="#item.product.brand.name"/></td>
									<td><s:property value="#item.stockPrice"/></td>
									<td><s:property value="#item.stockNumber"/></td>
									<td><s:property value="#item.stockAmount"/></td>
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
