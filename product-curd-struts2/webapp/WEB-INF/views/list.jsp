<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:debug/>
<s:a href="/product/product_edit">新增</s:a>
	<table width="80%" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<th>编号</th>
			<th>商品名称</th>
			<th>供应商</th>
			<th>品牌</th>
			<th>零售价</th>
			<th>成本价</th>
			<th>折扣</th>
			<th>商品分类</th>
			<th>操作</th>
		</tr>
		<s:iterator value="#products">
			<tr>
				<td><s:property value="id"/></td>
				<td><s:property value="productName"/></td>
				<td><s:property value="supplier"/></td>
				<td><s:property value="brand"/></td>
				<td><s:property value="salePrice"/></td>
				<td><s:property value="costPrice"/></td>
				<td><s:property value="cutoff"/></td>
				<td><s:property value="dir.id"/></td>
				<td align="center">
					<!-- Struts2标签不能嵌套 -->
					<a href="/product/product_edit?product.id=<s:property value="id"/>">编辑</a>	|
				 	<a href="/product/product_delete?product.id=<s:property value="id"/>">删除</a></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>