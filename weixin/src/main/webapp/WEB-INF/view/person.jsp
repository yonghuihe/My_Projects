<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<!-- 引入 WeUI -->
    <link rel="stylesheet" href="/css/weui.min.css">
    <link rel="stylesheet" href="/css/jquery-weui.min.css">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/jquery-weui.min.js"></script>
	<style>	
		.weui-cells{font-size: 15px}
		.head{
			text-align: center;
			height: 45px;
			line-height: 45px
			box-shadow: 0 1px 1px #e4e4e4;
			background: #fff;
			position: fixed;
			left: 0;
			top: 0;
			z-index: 100;
			width: 100%; 
		}
		.head .operation{
			position: absolute;
			right: 0;
			top: 0;
			width: 60px;
			height: 40px;
			color: #00d26d;
		}
		.tx{
 			width: 60px;
			height: 55px;
			border-radius:50%;
		}
	</style>
</head>
<body>
	<div class="head" >
		个人信息<a href="编辑个人信息.html" class="operation">编辑</a>
	</div>
	<div class="weui-cells">
		<div class="weui-cell ">
			<div class="weui-cell__bd">头像</div>
			<div class="weui-cell__price"><img class="tx" alt="" src="${headimgurl}"></div>
		</div>
	</div>
	<div class="weui-cells mt5">
		<div class="weui-cell ">
			<div class="weui-cell__bd">昵称</div>
			<div class="weui-cell__price">${nickname}</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd"> 国家</div>
			<div class="weui-cell__price">${country}</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">所在城市</div>
			<div class="">${province} ${city}</div>
		</div>
		<div class="weui-cell weui-cell_access">
			<div class="weui-cell__bd">姓别</div>
			<div class="weui-cell__price">
				<c:choose>
				    <c:when test="${sex==2}"> 女</c:when>
				    <c:when test="${sex==1}"> 男</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>