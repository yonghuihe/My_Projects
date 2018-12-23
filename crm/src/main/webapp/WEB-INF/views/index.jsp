<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理</title>
	<%@ include file="/WEB-INF/views/common/common.jsp" %>
	<link rel="stylesheet" type="text/css" href="/static/css/index/public.css">
	<link rel="stylesheet" type="text/css" href="/static/css/index/reset.css">
	<script type="text/javascript" src="/static/js/index.js"></script>
</head>
<body>
	<div class="public-header-warrp">
		<div class="public-header">
			<div class="content">
				<img src="/static/images/logo.png"
					style="width: 300px; heigth: 60px;">
				<div class="public-header-admin fr">
					<p class="admin-name">
						<font color="green"><shiro:principal property="username" />您好!</font>
					</p>
					<div class="public-header-fun fr">
						<a href="logout" class="public-header-loginout">安全退出</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="MyLayout" class="easyui-layout" data-options="fit:true,noheader:true">
		<div data-options="region:'north',split:true" style="height: 70px;"></div>
	    <div data-options="region:'west',split:true,noheader:true" style="width:200px;">
	    	<div id="MyMenu" class="easyui-accordion" data-options="fit:true">   
			    <div title="菜单" data-options="selected:true,iconCls:'icon-save'" style="overflow:auto;padding:10px;">
			    	<ul id="MyTree" class="easyui-tree" ></ul>
			    </div>   
			    <div title="帮助文档" data-options="iconCls:'icon-reload'" style="padding:10px;"></div>   
			</div>  
	    </div>   
	    <div data-options="region:'center'" style="padding:5px;background:#eee;">
	    	<div id="MyTabs" class="easyui-tabs" fit=true></div>
	    </div>
	    <div data-options="region:'south',split:true" 
	    	style="height:30px;background-image: url('/static/images/banner-pic.gif');background-repeat: no-repeat;background-size:cover;">
	    	<div align="center">版权所有，侵权不究！</div>
	    </div>   
	    
	</div>
</body>
</html>