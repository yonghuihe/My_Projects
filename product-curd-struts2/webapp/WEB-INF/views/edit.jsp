<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:debug/>
	<s:form namespace="/product" action="product_saveOrUpdate" method="post" theme="simple">
		<s:hidden name="product.id"/>
		商品名称：<s:textfield name="product.productName"/><br/>
		供&nbsp;&nbsp;应&nbsp;商：<s:textfield name="product.supplier"/><br/>
		品&emsp;&emsp;牌：<s:textfield name="product.brand"/><br/>
		零&nbsp;&nbsp;售&nbsp;价：<s:textfield name="product.salePrice"/><br/>
		进&nbsp;&nbsp;货&nbsp;价：<s:textfield name="product.costPrice"/><br/>
		折&emsp;&emsp;扣：<s:textfield name="product.cutoff"/><br/>
		商品分类：<s:textfield name="product.dir.id"/><br/>
		<s:submit value="提交"/>
	</s:form>
</body>
</html>