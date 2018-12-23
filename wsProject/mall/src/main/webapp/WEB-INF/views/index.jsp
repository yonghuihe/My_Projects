<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" src="/js/index.js"></script>


</head>
<body>
	<div class="easyui-layout" data-options="fit:true">   
	    <div data-options="region:'north',split:false" style="height:80px;">
	    	<h1 align="center">小码哥员工管理系统</h1>
	    </div>   
	    <div data-options="region:'south',split:false" style="height:50px;">
	    	版权声明
	    </div>   
	    <div data-options="region:'west',split:true" style="width:150px;">
	    	<div class="easyui-accordion" data-options="fit:true" >
	    		<div title="菜单">
	    			<ul id="myTree">
	    			</ul>
	    		</div>
	    		<div title="公司简介">
	    		</div>
	    	</div>
	    </div>   
	    <div data-options="region:'center'" style="padding:5px;">
	    	<div id="myTabs" >
	    		<div title="主页">
	    			欢迎进入主页
	    		</div>
	    	</div>
	    </div>   
	</div>  


</body>
</html>