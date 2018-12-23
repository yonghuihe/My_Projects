<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝源Eloan-P2P平台->邮件激活</title>
	<#include "common/links-tpl.ftl" />
	<link type="text/css" rel="stylesheet" href="/css/account.css" />
	<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery-validation/localization/messages_zh.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
	<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>
	
	<style type="text/css">
		.el-login-form{
			width:600px; 
			margin-left:auto;
			margin-right:auto;
			margin-top: 20px;
		}
		.el-login-form .form-control{
			width: 220px;
			display: inline;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			//给"修改邮箱"添加点击事件，弹出模态框
			$("#goto_bindEmail").click(function(){
				//邮箱回显
				$("#email").val("${(userinfo.email)!''}")
				//弹出模态框
				$("#bindEmailModal").modal('show');
			});
			//给"发送验证码"绑定点击事件（如果邮箱不为空，发送成功后禁用按钮，设置倒计时）
			$("#sendVerifyCode").click(function(){
				var newEmail = $("#newEmail");
				var _me = $(this);
				if(newEmail){
					_me.attr("disabled","disabled");
					$.ajax({
						dataType:"json",
						type:"post",
						url:"/sendVerifyCode.do",
						data:{
							phoneNumber:${(userinfo.phoneNumber)!''}
						},
						success:function(data){
							if(data.success){
								var time = 180;
								var timer = window.setInterval(function(){
									time--;
									if(time > 0){
										_me.html(time+"秒后重新发送");
									}else{
										_me.html("重新发送验证码");
										_me.attr("disabled","disabled");
										window.clearInterval(timer);
									}
								},1000)
							}else{
								_me.attr("disabled",false);
								$.messager.alert("提示",data.msg);							
							}
						}
					})
				}
			});
			
			
			//表单提交
			$("#bindEmail").click(function(){
				if(validateForm().form()){
					$("#bindForm").ajaxSubmit(function(data){
						if(data.success){
							$.messager.confirm("提示","修改成功",function(){
								window.location.reload();
							});
						}else{
							$.messager.alert("提示",data.msg);
						}
					})
				}
			})
		})
		
		//表单验证
		function validateForm(){
			return $("#bindForm").validate({
				rules:{
					"newEmail":{
						required:true,
						email:true
					},
					"confirmEmail":{
						equalTo:"#newEmail"
					}
				},
				messages:{
					"newEmail":{
						required: "邮箱必填",
						email:"请输入正确的邮箱格式",
						equalTo:"#email"
					},
					"confirmEmail":{
						equalTo:"两次输入的邮箱不一致"
					}
				},
				///校验失败的样式
				errorClass:"text-danger"
			});
		}
		//注册表单验证
		$(validform());
		
	</script>

</head>
<body>
	<#if !(userinfo??)>
		<!-- 网页头信息 -->
		<div class="el-header" >
			<div class="container" style="position: relative;">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">首页</a></li>
					<li><a href="/login.html">登录</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
			</div>
		</div>
		
		<!-- 网页导航 --> 
		<div class="navbar navbar-default el-navbar">
			<div class="container">
				<div class="navbar-header">
					<a href=""><img alt="Brand" src="/images/logo.png"></a>
					<span class="el-page-title">邮件激活结果</span>
				</div>
			</div>
		</div>
		
		<!-- 网页内容 --> 
		<div class="container"  style="min-height: 343px;">
			<!--发送验证邮件的结果页面-->
			<h1>
				<#if success>
					您的邮件已经成功激活,请登录系统查看!
				<#else>
					您的邮件激活失败,失败原因是:${msg}!
				</#if>
			</h1>
		</div>
		
	<#else>
		<!--查看已绑定邮件的页面-->
		<!-- 网页顶部导航 -->
		<#include "common/head-tpl.ftl" />
		
		<#assign currentNav="account" />
		<!-- 网页导航 -->
		<#include "common/navbar-tpl.ftl" />
		
		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#include "common/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							邮箱认证
						</div>
						<div class="panel-body">
							<h4 class="text-success ">你已经通过邮箱认证</h4>
							<hr />
							<table style="width: 100%;height: 50px;">
								<tr>
									<td><span>邮箱： ${userinfo.email}</span></td>
									<td><a id="goto_bindEmail" href="javascript:;">修改邮箱</a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</#if>
	
	<#if (userinfo.isBindEmail)??>
		<div class="modal fade" id="bindEmailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="exampleModalLabel">修改绑定的邮箱</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal" id="bindForm" method="post" action="/bindEmail.do">
		        	<div class="form-group">
					    <label for="email" class="col-sm-3 control-label">原绑定邮箱:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="email" name="email" readonly="readonly"/>
					    </div>
					</div>
		        	<div class="form-group">
					    <label for="newEmail" class="col-sm-3 control-label">新绑定邮箱:</label>
					    <div class="col-sm-4">
						      <input type="text" autocomplete="off" class="form-control" id="newEmail" name="newEmail"/>
					    </div>
					</div>
		        	<div class="form-group">
					    <label for="confirmEmail" class="col-sm-3 control-label">确认新绑定邮箱:</label>
					    <div class="col-sm-4">
						      <input type="text" autocomplete="off" class="form-control" id="confirmEmail"/>
						      <p class="help-block">请再次填写邮箱</p>
					    </div>
					</div>
		        	<div class="form-group">
					    <label for="verifyCode" class="col-sm-3 control-label">手机验证码:</label>
					    <div class="col-sm-4">
						      <input type="text" class="form-control" id="verifyCode" name="verifyCode"/>
						      <button id="sendVerifyCode" class="btn btn-primary" type="button" >发送验证码</button>
					    </div>
					</div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" id="bindEmail">确认修改</button>
		      </div>
		    </div>
		  </div>
		</div>
	</#if>	
		
	<#include "common/footer-tpl.ftl" />
</body>
</html>