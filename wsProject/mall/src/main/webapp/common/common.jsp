<link rel="stylesheet" type="text/css" href="/jquery-easyui/themes/default/easyui.css"><!-- 全局样式 -->   
<link rel="stylesheet" type="text/css" href="/jquery-easyui/themes/icon.css"><!-- 图标样式 -->
<script type="text/javascript" src="/jquery-easyui/jquery.min.js"></script><!-- jquery库 -->
<script type="text/javascript" src="/jquery-easyui/jquery.easyui.min.js"></script> <!-- easyui核心文件 -->
<script type="text/javascript" src="/jquery-easyui/locale/easyui-lang-zh_CN.js"> </script>
<script type="text/javascript" src="/js/base.js"> </script><!-- easyui补丁包 -->
<script type="text/javascript">
$(function(){
	window.onload = numberInputPlaceholder();
});

function numberInputPlaceholder(){
	$(".easyui-combobox").each(function(i){
		var span = $(this).siblings("span")[i];
		var targetInput = $(span).find("input:first");
		if(targetInput){
		$(targetInput).attr("placeholder", $(this).attr("placeholder"));
		}

	});
}

</script>
<script type="text/javascript">
		function setCookie(){
			console.log(userCookie)
			if(userCookie.success){
				document.cookie = "name="+userCookie.name;
				document.cookie = "token="+userCookie.token;
			}else{
				window.location.href = "/login.jsp";
			}
		}
</script>

<script type="text/javascript" src="http://login.520it.com:8082/checkLogin_api"></script>
