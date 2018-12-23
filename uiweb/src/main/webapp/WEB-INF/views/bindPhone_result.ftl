<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>蓝源Eloan-P2P平台</title>
		<#include "common/links-tpl.ftl" />
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
		<script type="text/javascript">
			$(function(){
				//点击"修改手机号码"，弹出模态框
				$("#goto_bindphone").click(function(){
					//电话号码回显
					$("#phoneNumber").val("${userinfo.phoneNumber}");
					$("#bindPhoneModal").modal('show');
				});
				
				//给"发送验证码"添加点击事件，如果电话号码不为空，则禁用"发送验证码"按钮，发送ajax请求
				$("#sendVerifyCode").click(function(){
					var npn = $("#newPhoneNumber");
					var _me = $(this);
					if(npn.val()){
						_me.attr("disabled","disabled");
						$.ajax({
							dataType:"json",
							type:"post",
							url:"/sendVerifyCode.do",
							data:{
								phoneNumber:npn.val()
							},
							success:function(data){
								if(data.success){
									var time = 180;
									var timer = window.setInterval(function(){
										time--;
										if(time >= 0){
											_me.html(time+"秒后发送");
										}else{
											_me.html("重新发送验证码");
											_me.attr("disabled",false);
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
				})
				
				
				//绑定手机：找到按钮，添加点击事件，使用ajax提交表单，如果成功，弹出确认框，提示绑定成功，重新加载页面，否则popup绑定手机失败
				$("#bindPhone").click(function(){
					$("#bindForm").ajaxSubmit(function(data){
						if(data.success){
							$.messager.confirm("提示","手机绑定成功",function(){
								window.location.reload();
							})
						}else{
							$.messager.popup("绑定手机失败");
						}
					})
				})
				
			})
		</script>
	</head>
	<body>
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
							手机认证
						</div>
						<div class="panel-body">
							<h4 class="text-success ">你已经通过手机认证</h4>
							<hr />
							<table style="width: 100%;height: 50px;">
								<tr>
									<td><span>手机号码： ${userinfo.phoneNumber}</span></td>
									<td><a id="goto_bindphone" href="javascript:;">修改手机号码</a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>	
		
		<#if userinfo.isBindPhone>
		<div class="modal fade" id="bindPhoneModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="exampleModalLabel">修改绑定的手机号码</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal" id="bindForm" method="post" action="/bindPhone.do">
		        	<div class="form-group">
					    <label for="phoneNumber" class="col-sm-3 control-label">原手机号码:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" readonly="readonly"/>
					    </div>
					</div>
		        	<div class="form-group">
					    <label for="newPhoneNumber" class="col-sm-3 control-label">新手机号码:</label>
					    <div class="col-sm-4">
						      <input type="text" class="form-control" id="newPhoneNumber" name="newPhoneNumber"/>
						      <button id="sendVerifyCode" class="btn btn-primary" type="button" >发送验证码</button>
					    </div>
					</div>
					<div class="form-group">
					    <label for="verifyCode" class="col-sm-3 control-label">手机验证码:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="verifyCode" name="verifyCode" />
					    </div>
					</div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" id="bindPhone">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
		</#if>	
		
		<#include "common/footer-tpl.ftl" />
	</body>
</html>