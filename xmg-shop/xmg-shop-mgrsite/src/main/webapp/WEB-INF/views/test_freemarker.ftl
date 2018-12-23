<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小码商城管理平台</title>
</head>
<body>
	<#macro testMacro num>
	     <h1>小码商城管理平台${num}</h1>
	 </#macro>    
	 
	<#list 0..4 as number> 
	<@testMacro number/> 
	</#list>
</body>
</html>

