<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<link href="/js/fancyBox/source/jquery.fancybox.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/common_page.js"></script>
	<script type="text/javascript" src="/js/fancyBox/source/jquery.fancybox.pack.js"></script>
	<title>PSS-商品管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
	<script type="text/javascript">
		$(function(){
			$(".fancyBox").fancybox();
		})
	</script>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="product" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-href='<s:url action="product_input" />'/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"></th>
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>货品品牌</th>
							<th>成本价</th>
							<th>销售价</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.result" var="item">
								<tr>
									<td></td>
									<td>
										<a class="fancyBox" href='<s:property value="#item.imagePath"/>' title='<s:property value="#item.name"/>'>
											<img alt="" src='<s:property value="#item.smallImagePath"/>' class="list_img">
										</a>
									</td>
									<td><s:property value="#item.name"/></td>
									<td><s:property value="#item.sn"/></td>
									<td><s:property value="#item.brand.name"/></td>
									<td><s:property value="#item.costPrice"/></td>
									<td><s:property value="#item.salePrice"/></td>
									<td>
										<s:a action="product_input"><s:param name="product.id" value="#item.id"/>编辑</s:a>
										<s:url action="product_delete" var="delUrl">
											<s:param name="product.id" value="#item.id"/>
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
