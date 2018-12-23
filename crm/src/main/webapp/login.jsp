<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>大地汽车保险系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 页面基本设置禁止随意更改 -->
<meta name="author" content="forework">
<meta name="format-detection" content="telephone=no">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="msapplication-tap-highlight" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link rel="stylesheet" type="text/css" href="/static/css/basic.css">
<link rel="stylesheet" type="text/css" href="/static/css/less.css">
<script type="text/javascript"
	src="/static/plugins/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		if (window != window.parent) {
			window.parent.location.href = "/login.jsp";
		}
	}
	function loginForm() {
		//发送ajax请求
		$.post("/login", $("#loginform").serialize(), function(data) {
			data = $.parseJSON(data);
			if (data.success) {
				window.location.href = "/index";
			} else {
				$.message.alert("提示消息", data.msg);
				resetForm();
			}
		})
	}
	function resetForm() {
		$("input[name]").val("");
	}
</script>
</head>
<body>
	<div class="login-top">
		<div style="height: 60px; background-color: white;">
			<div style="margin-left: 60px;">
				<img alt="" width="300px" height="60px"
					src="/static/images/logo.png">
			</div>
		</div>
		<form id="loginform" method="post">
			<div class="login-topBg">
				<div class="login-topBg1">
					<div class="login-topStyle">
						<div id="loginStyle" class="login-topStyle3"
							style="margin-top: 75px;">
							<h3>用户平台登录</h3>
							<div class="ui-form-item loginUsername">
								<input type="text" name="username" value="admin"
									placeholder="用户名">
							</div>
							<div class="loginPassword">
								<input type="password" name="password" value="5201314"
									placeholder="密码">
							</div>
							<a class="btnStyle btn-register" onclick="loginForm();">立即登录</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- <section class="container">
	<div class="login">
		<h1>用户登录</h1>
		<form method="post">
			<p>
				<input type="text" name="username" value="" placeholder="账号">
			</p>
			<p>
				<input type="password" name="password" value="" placeholder="密码">
			</p>
			<p class="submit">
				<input type="button" value="登录" onclick="submitForm()"> <input
					type="button" value="重置">
			</p>
		</form>
	</div>
	</section> -->
	<!-- <div style="text-align: center" class="login-help">
		<p>Copyright ©2018 公司</p>
	</div> -->
</body>
</html>