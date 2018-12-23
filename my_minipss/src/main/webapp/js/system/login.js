$(document).ready(function() {
	$("#login_sub").click(function() {
		$("#submitForm").submit();
	});
	$('#pwd').keypress(enterLogin).keydown(enterLogin);
	$("#submitForm").validate({
		errorClass: "error",
		errorElement: "span",
		rules:{
			userName:"required",
			password:"required",
		},
		messages:{
			userName:"请输入用户名",
			password:"请输入密码"
		}
	})
});

function enterLogin(e) { // 传入 event
	var e = e || window.event;
	if (e.keyCode == 13) {
		$("#submitForm").submit();
	}
}