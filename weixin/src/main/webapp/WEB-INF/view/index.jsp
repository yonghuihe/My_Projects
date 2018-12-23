<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
	<!-- 页面中引入JS文件 -->
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript">
		/* 通过config接口注入权限验证配置 */
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: 'wx8c5959ca0d5544a9', // 必填，公众号的唯一标识
		    timestamp: '1234567890', // 必填，生成签名的时间戳
		    nonceStr: 'company', // 必填，生成签名的随机串
		    signature: 'c99ac30722d64d4fc3af33f6051900f23c112990',// 必填，签名
		    jsApiList: ["onMenuShareTimeline"] // 必填，需要使用的JS接口列表
		});
		/* config验证通过之后才会执行ready方法 */
		wx.ready(function(){
	         //分享到朋友圈接口
	         wx.onMenuShareTimeline({
	             title: '首页', // 分享时的标题
	             link: 'http://23058o2g92.imwork.net/index.do', // 分享时的链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
	             imgUrl: 'http://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRt8Qia4lv7k3M9J1SKqKCImxJCt7j9rHYicKDI45jRPBxdzdyREWnk0ia0N5TMnMfth7SdxtzMvVgXg/0', // 分享时显示的图标
	             //用户确认分享后执行的回调函数
	             success: function () {
	                 alert("分享成功");
	             },
	             //用户取消分享后执行的回调函数
	             cancel: function () {
	                 alert("取消分享");
	             }
	         });
	     });
	</script>
</head>
<body>
	company微信公众号管理系统
	


</body>
</html>