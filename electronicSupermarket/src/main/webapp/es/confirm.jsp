<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>歪秀购物</title>
	<link rel="stylesheet" type="text/css" href="/static/css/ui.css">
	<link href="favicon.ico" type="image/x-icon" rel="icon">
	<link href="iTunesArtwork@2x.png" sizes="114x114" rel="apple-touch-icon-precomposed">
	<script type="text/javascript" src="/static/js/toast/aui-toast.js"></script>
</head>
<body>

<div class="aui-container">
	<div class="aui-page">
		<div class="header">
			<div class="header-background"></div>
			<div class="toolbar statusbar-padding">
				<button class="bar-button back-button" onclick="history.go(-1);" dwz-event-on-click="click"><i class="icon icon-back"></i></button>
				<!--<a class="bar-button" data-href="home.html?dwz_callback=home_render" target="navTab" rel="home"><i class="icon icon-back"></i></a>-->
				<div class="header-title">
					<div class="title">确认订单</div>
				</div>
			</div>
		</div>
		<div style="height:50px"></div>

		<div class="aui-wei-cell">
			<ul>
				<li>
					<a href="adds.html">
						<div class="" style="padding-left:15px">
							<i class="aui-cell-i"></i>
						</div>
					<%--	<div>
							<div class="aui-add-cell"><span class="aui-add-fix">李明轩</span><span>18801066666</span></div>
							<div class=" aui-add-title" style="padding-bottom:10px">北京市海淀区学院路51号学而国际大厦1层</div>
						</div>--%>
					</a>
				</li>
			</ul>
			<div class="aui-s-border" style="margin-bottom:10px"></div>
		</div>


		<div class="main_con" style="margin-bottom:0;">

			<div class="main_con_goods">
				<ul>
					<li style="margin-bottom:0;">
						<section class="aui-crl" style="padding-left:15px">
							<img src="/static/images/img/k1.jpg">
						</section>
						<div style="width:auto; padding-left:10px">
							<h2>雷柏（Rapoo） V700S合金版 混光机械键盘 游戏键盘 背光键盘 电脑键盘 笔记本</h2>
							<p class="aui-o">颜色分类:黑色</p>
						<%--	<p class="money"><em class="aui-redd">90￥</em>
								<em style="color:#999; font-size:14px">x2</em>
							</p>--%>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="aui-list-cells">
			<a href="javascript:;" class="aui-list-cell">
				<div class="aui-list-cell-cn">配送方式</div>
				<div class="aui-list-cell-fr">包邮</div>
			</a>
			<a href="javascript:;" class="aui-list-cell">
				<div class="aui-list-cell-cn">运费险</div>
				<div class="aui-list-cell-fr">保险</div>
			</a>
			<a href="javascript:;" class="aui-list-cell">
				<div class="aui-list-cell-cn">发票抬头</div>
				<div class="aui-list-cell-fr">个人</div>
			</a>
		</div>

		<div class="devider b-line"></div>

		<div class="aui-btn-content">
<%--			<div class="aui-content aui-card">
				<ul class="aui-list-view">
					<li class="aui-list-view-cell aui-switch-body">
						<div class="aui-switch-title">优惠券可抵5元</div>
						<input type="checkbox" class="aui-switch aui-pull-right" checked="">
					</li>
					<li class="aui-list-view-cell aui-switch-body">
						<div class="aui-switch-title">红包</div>
						<input type="checkbox" class="aui-switch aui-pull-right">
					</li>
					<li class="aui-list-view-cell aui-switch-body">
						<div class="aui-switch-title">限时秒杀</div>
						<input type="checkbox" class="aui-switch aui-switch-success aui-pull-right" >
					</li>
				</ul>
			</div>--%>

			<script type="text/javascript">
				apiready = function () {
				}
			</script>
		</div>

		<div style="height:33px"></div>

		<!--内容信息 end-->

		<!--结算信息 start-->
		<div class="settlement t-line">
			<div class="settlement_left">
				<font class="zongji">总计：</font><font class="money">￥399.00</font><br />
				（共1件，不含运费）
			</div>
			<div class="settlement_right">
				<a href="wait.jsp">提交订单</a>
			</div>
		</div>
		<!--结算信息 end-->






	</div>
</div>


<%--<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>--%>
<%--<script type="text/javascript" src="js/car/car-js.js"></script>
<script type="text/javascript" src="js/car/car-mi.js"></script>--%>
</body>
</html>