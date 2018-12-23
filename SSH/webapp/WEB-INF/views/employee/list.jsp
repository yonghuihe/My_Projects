<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>薪资</th>
			<th>入职日期</th>
		</tr>
		<s:iterator value="#list" status="num">
			<tr>
				<td><s:property value="#num.count"/></td>
				<td><s:property value="name"/></td>
				<td><s:property value="salary"/></td>
				<td><s:property value="hiredate"/></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>