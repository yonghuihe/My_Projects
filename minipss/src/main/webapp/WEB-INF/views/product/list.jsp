<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/authority/commonAll.js"></script>
<script type="text/javascript" src="js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="js/common-page.js"></script>
<title>PSS-货品管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<%@include file="../common/common-msg.jsp" %>
	<s:form id="searchForm" action="product" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						品牌:<s:select list="#brands" cssClass="ui_select01" name="qo.brandId" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--"/>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_redirect" data-url='<s:url action="product_input" />'/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"></th>
							<th></th>
							<th>货品编号</th>
							<th>货品名称</th>
							<th>品牌</th>
							<th>默认销售价</th>
							<th>默认成本价</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td><img src="<s:property value='#item.smallPic' />" class="list_img"/></td>
									<td><s:property value="#item.sn"/></td>
									<td><s:property value="#item.name"/></td>
									<td><s:property value="#item.brand.name"/></td>
									<td><s:property value="#item.salePrice"/></td>
									<td><s:property value="#item.costPrice"/></td>
									<td>
										<s:a action="product_input"><s:param name="product.id" value="#item.id"/>编辑</s:a>
										<s:a action="product_delete"><s:param name="product.id" value="#item.id"/>删除</s:a>
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
