<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
<script type="text/javascript">
	function chooseB(){
		artDialog.data("product",'{id:"1",name"test"}')
		art.dialog.close();
	}
</script>
</head>
<body>
	测试页面：test2
	<a href="javascript:;" onclick="chooseB();">选择这条记录</a>
</body>
</html>