<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	wx.config({
		debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId : 'wx8c5959ca0d5544a9', // 必填，公众号的唯一标识
		timestamp : '1612151231', // 必填，生成签名的时间戳
		nonceStr : 'fsdfsdgsfs', // 必填，生成签名的随机串
		signature : '3e50c79ef502c325f72762812d5228e727f09298',// 必填，签名
		jsApiList : ['onMenuShareTimeline','hideAllNonBaseMenuItem']// 必填，需要使用的JS接口列表
	});
	wx.ready(function(){
		wx.hideAllNonBaseMenuItem();
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	    wx.onMenuShareTimeline({
		    title: '新的标题', // 分享标题
		    link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: '', // 分享图标
		    success: function () {
		    	// 用户点击了分享后执行的回调函数
		    	console.log("分享成功");
		    	window.location.href="http://www.baidu.com";
			},
			cancel:function(){
		    	console.log("取消成功");
			}
		})
	})
</script>
</head>
<body>company微信公众号管理系统


</body>
</html>