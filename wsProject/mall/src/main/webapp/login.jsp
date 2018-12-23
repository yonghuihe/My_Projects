<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小码哥客户关系管理系统mall</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="/jquery-easyui/jquery.min.js"></script> 
<script type="text/javascript">

	function login(){
		//提交表单
		$.post("/login",$("form").serialize(),function(data){
			if(data.success){
				window.location.href = "/index";
			}else{
				alert(data.msg);
			}
		},"json");
	}

</script>
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>LOGIN.520IT.COM用户登录</h1>
      <form method="post">
        <p><input type="text" name="username" value="admin" placeholder="账号"></p>
        <p><input type="password" name="password" value="123" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="login()">
        	<input type="button" value="重置" onclick="reset()">
        </p>
      </form>
    </div>
  </section>
  </body>
</html>