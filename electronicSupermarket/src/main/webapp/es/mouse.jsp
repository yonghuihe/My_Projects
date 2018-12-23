<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>歪秀购物</title>
	<meta name="keywords" content="手机版,购物网站,商城模板,整站下载" />
	<meta name="description" content="手机版购物网站商城模板整站下载，包含个人中心所有的页面，首页商品分类，商品详情，购物车，结算，地址，新增地址，所有相关的UI交互，以及登录注册页面，红包功能，分类，最新上架，团购，签到，订单管理。" />
	<link rel="stylesheet" type="text/css" href="/static/css/ui.css">
	<link href="favicon.ico" type="image/x-icon" rel="icon">
	<link href="iTunesArtwork@2x.png" sizes="114x114" rel="apple-touch-icon-precomposed">

	<script type="text/javascript" src="/static/js/pd/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/static/js/pd/aui-touchSlide.js"></script>
	<script src="/static/js/ba/aui-scroll.js" type="text/javascript" charset="utf-8"></script>
	<script src="/static/js/ba/aui-index.js" type="text/javascript" charset="utf-8"></script>
	<script src="/static/js/ba/aui-swipe.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<div class="aui-container">
	<div class="aui-page">
		<!-- 头部 begin-->
		<div class="header">
			<div class="aui-header-bg" style="background:#ff5a5f;"></div>
			<div class="toolbar statusbar-padding" style="min-height:50px">
				<button class="bar-button back-button"><i class="icon icon-sao"></i></button>
				<div class="header-title" style="height:50px; padding:0 50px">
					<div class="title aui-title-input"><input type="text" placeholder="秋季新品"></div>
				</div>
				<a href="news.jsp">
					<button class="icon aui-icon-mag"></button>
				</a>
			</div>
		</div>
		<div style="height:50px"></div>
		<!-- 头部 End-->
		<!-- 首页轮播 begin -->
		<div class="aui-banner-content">
			<div id="focus" class="focus">
				<div class="bd">
					<div class="tempWrap" style="overflow:hidden; position:relative;">
						<ul id="Gallery" class="gallery" style="width: 2250px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translate(-1125px, 0px) translateZ(0px);">
							<li style="display: table-cell; vertical-align: top; width: 375px;" >
								<a href="#"><img src="/static/js/pd/img/banner1.jpg"></a>
							</li>
							<li style="display: table-cell; vertical-align: top; width: 375px;">
								<a href="#"><img src="/static/js/pd/img/banner2.jpg"></a>
							</li>
							<li style="display: table-cell; vertical-align: top; width: 375px;" >
								<a href="#"><img src="/static/js/pd/img/banner1.jpg"></a>
							</li>
							<li style="display: table-cell; vertical-align: top; width: 375px;">
								<a href="#"><img src="/static/js/pd/img/banner1.jpg"></a>
							</li>
						</ul>
					</div>
				</div>
				<div class="hd">
					<ul>
						<li class="">1</li>
						<li class="">2</li>
						<li class="">3</li>
						<li class="">4</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 首页轮播 end -->
		<!-- 分类切换 begin -->
		<div class="" id="container" >
			<div id="main" style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
				<div id="scroller">
					<section class="slider" style="margin:0  auto; width:100%">
						<div class="swiper-container swiper-container2 swiper-container-horizontal">
							<div class="swiper-wrapper tuangouwidth" style="transition-duration: 0ms; transform: translate3d( 0px, 0px);">
								<div class="swiper-slide swiper-slide-duplicate " >
									<ul class="icon-list">
										<li class="icon">
											<a href="bag.html">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan1.png"></span>
												<span class="icon-desc">红包</span>
											</a>
										</li>
										<li class="icon">
											<a href="#">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan1.png"></span>
												<span class="icon-desc">签到</span>
											</a>
										</li>
										<li class="icon">
											<a href="new-pd.html">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan2.png"></span>
												<span class="icon-desc">新品</span>
											</a>
										</li>
										<li class="icon">
											<a href="#">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan3.png"></span>
												<span class="icon-desc">团购</span>
											</a>
										</li>
										<li class="icon">
											<a href="classs.html">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan4.png"></span>
												<span class="icon-desc">分类</span>
											</a>
										</li>
										<li class="icon">
											<a href="life.html">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan5.png"></span>
												<span class="icon-desc">生活</span>
											</a>
										</li>
										<li class="icon">
											<a href="#">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan6.png"></span>
												<span class="icon-desc">秀秀</span>
											</a>
										</li>
										<li class="icon">
											<a href="#">
												<span class="icon-circle"><img src="/static/js/ba/img/icon-tuan7.png"></span>
												<span class="icon-desc">达人</span>
											</a>
										</li>
									</ul>
								</div>
								<!--<div class="swiper-slide swiper-slide-duplicate " >-->
									<!--<ul class="icon-list">-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan.png"></span>-->
												<!--<span class="icon-desc">红包</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan1.png"></span>-->
												<!--<span class="icon-desc">签到</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan2.png"></span>-->
												<!--<span class="icon-desc">新品</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan3.png"></span>-->
												<!--<span class="icon-desc">团购</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan4.png"></span>-->
												<!--<span class="icon-desc">分类</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan5.png"></span>-->
												<!--<span class="icon-desc">生活</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan6.png"></span>-->
												<!--<span class="icon-desc">秀秀</span>-->
											<!--</a>-->
										<!--</li>-->
										<!--<li class="icon">-->
											<!--<a href="#">-->
												<!--<span class="icon-circle"><img src="js/ba/img/icon-tuan7.png"></span>-->
												<!--<span class="icon-desc">达人</span>-->
											<!--</a>-->
										<!--</li>-->
									<!--</ul>-->
								<!--</div>-->
							</div>
							<!--<div class="swiper-pagination swiper-pagination2 ">-->
								<!--<span class="swiper-pagination-bullet "></span>-->
								<!--<span class="swiper-pagination-bullet "></span>-->
							<!--</div>-->
						</div>
					</section>
				</div>
			</div>
		</div>
		<!-- 分类切换 end -->
		<div class="devider t-line"></div>
		<div class="b-line" style="position:relative"></div>
<!--		<div class="b-line">
			<a class="home-inform aui-home-inform" data-href="home-slogan.html" target="navView" rel="slogan">
				<i class="name icon-inform"></i>
				<span style="font-size:14px; padding-left:5px">APP新版本上线换一种方式购物</span>
			</a>
		</div>-->
		<!--<div class="my-car-thumbnail"><img src="/static/images/img/banner-car.jpg"></div>-->

		<div class="aui-title-h">
			<h2>大牌热卖</h2>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items">
		<span>
			<a href="page.jsp"><img src="/static/images/img/b3.jpg"></a>
		</span>
				<a href="#" class="aui-flex-box">新款上市</a>
			</div>
			<div class="aui-flex-item aui-flex-items">
		<span>
			<a href="page.jsp"><img src="/static/images/img/b4.jpg"></a>
		</span>
				<a href="page.jsp" class="aui-flex-box">最火产品</a>

			</div>
			<div class="aui-flex-item aui-flex-items">
		<span>
			<a href="page.jsp"><img src="/static/images/img/k3.jpg"></a>
		</span>
				<a href="#" class="aui-flex-box">热潮时代</a>
			</div>
		</div>
<%--		<div class="aui-title-h">
			<h2>新品必购</h2>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="/static/images/img/xiao1.jpg">
		</span>
			</div>
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="/static/images/img/xiao2.jpg">
		</span>

			</div>
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="/static/images/img/xiao3.jpg">
		</span>

			</div>
			<div class="aui-flex-item aui-flex-items1">
		<span>
			<img src="/static/images/img/xiao34.jpg">
		</span>

			</div>
		</div>--%>
		<div class="aui-title-h">
			<h2>新品上架</h2>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page.jsp"><img src="/static/images/img/k1.jpg"></a>
		</span>
				<a href="/es/page.jsp" class="aui-flex-box">
					<h2>雷柏（Rapoo） V700S合金版 混光机械键盘 游戏键盘 背光键盘 电脑键盘 笔记本 </h2>
					<em>￥99.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page1.jsp"><img src="/static/images/img/k2.jpg"></a>
		</span>
				<a href="page1.jsp" class="aui-flex-box">
					<h2>ikbc c87 樱桃轴机械键盘 87键原厂Cherry轴 黑色 红轴 </h2>
					<em>￥199.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page2.jsp"><img src="/static/images/img/b3.jpg"></a>
		</span>
				<a href="page2.jsp" class="aui-flex-box">
					<h2>罗技（Logitech）G502 炫光自适应游戏鼠标 RGB鼠标 </h2>
					<em>￥299.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page3.jsp"><img src="/static/images/img/k5.jpg"></a>
		</span>
				<a href="page3.jsp" class="aui-flex-box">
					<h2>
						罗技（Logitech）K120键盘 </h2>
					<em>￥399.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page4.jsp"><img src="/static/images/img/b5.jpg"></a>
		</span>
				<a href="page4.jsp" class="aui-flex-box">
					<h2>虎猫 X8牧马人RGB炫光LOL竞技守望先锋专业CF游戏鼠标办公有线USB笔记本台式 </h2>
					<em>￥499.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page8.jsp"><img src="/static/images/img/b6.jpg"></a>
		</span>
				<a href="page8.jsp" class="aui-flex-box">
					<h2>达尔优（dare-u） G60 牧马人游戏鼠标 四色呼吸灯变换 有线鼠标 </h2>
					<em>￥168.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page5.jsp"><img src="/static/images/img/b4.jpg"></a>
		</span>
				<a href="page5.jsp" class="aui-flex-box">
					<h2>罗技（Logitech）G102 游戏鼠标 6000DPI RGB鼠标 黑色 </h2>
					<em>￥256.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page6.jsp"><img src="/static/images/img/b7.jpg"></a>
		</span>
				<a href="page6.jsp" class="aui-flex-box">
					<h2>雷蛇（Razer）DeathAdder 炼狱蝰蛇3500DPI 三色灯光版 3键有线游戏鼠标 黑色 </h2>
					<em>￥398.00</em>
				</a>
			</div>
		</div>
		<div class="aui-flex">
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page7.jsp"><img src="/static/images/img/k4.jpg"></a>
		</span>
				<a href="page7.jsp" class="aui-flex-box">
					<h2>达尔优（dare-u）机械师合金版 108键背光机械键盘 青轴 黑金色 </h2>
					<em>￥178.00</em>
				</a>
			</div>
			<div class="aui-flex-item aui-flex-items1 aui-flex-items2">
		<span>
			<a href="/es/page9.jsp"><img src="/static/images/img/k3.jpg"></a>
		</span>
				<a href="/es/page9.jsp" class="aui-flex-box">
					<h2>ikbc c104 樱桃轴机械键盘 104键原厂Cherry轴 白色 红轴 </h2>
					<em>￥988.00</em>
				</a>
			</div>
		</div>
		<div style="height:44px"></div>
		<div class="tab-bar tab-bottom">
			<a class="tab-button active" href="/index.do"><i class="tab-button-icon icon icon-home"></i><span class="tab-button-txt">首页</span></a>
			<%--<a class="tab-button cached" href="life.html"><i class="tab-button-icon icon icon-exhibition" ></i><span class="tab-button-txt">生活</span></a>--%>
			<%--<a class="tab-button cached" href="classs.html"><i class="tab-button-icon icon icon-service" ></i><span class="tab-button-txt">分类</span></a>--%>
<%--			<a class="tab-button cached" href="car.html"><i class="tab-button-icon icon icon-car"></i><span class="tab-button-txt">购物车</span></a>--%>
			<a class="tab-button cached" href="/es/me.jsp"><i class="tab-button-icon icon icon-my" ></i><span class="tab-button-txt">我的</span></a>
		</div>
	</div>
</div>


<script>
	/*banner首页轮播*/
	TouchSlide({
		slideCell : "#focus",
		titCell : ".hd ul", // 开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell : ".bd ul",
		effect : "leftLoop",
		autoPlay : true, // 自动播放
		autoPage : true, // 自动分页
		delayTime: 200, // 毫秒；切换效果持续时间（执行一次效果用多少毫秒）
		interTime: 5000, // 毫秒；自动运行间隔（隔多少毫秒后执行下一个效果）
		switchLoad : "_src" // 切换加载，真实图片路径为"_src"
	});
</script>


</body>
</html>