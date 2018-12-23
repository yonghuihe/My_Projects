//禁用在jQuery 1.4中，.param()会深度递归一个对象来满足现在脚本语言和框架，比如PHP， Ruby on Rails等。
//你可以通过jQuery.ajaxSettings.traditional = true; 来全局得禁用这个功能
jQuery.ajaxSettings.traditional = true;

/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
});