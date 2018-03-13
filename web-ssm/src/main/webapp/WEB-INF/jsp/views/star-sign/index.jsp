<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html;charset=utf-8">
	<meta name="viewport"   content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no">
	<meta name="format-detection" content="email=no"/>
	<meta name="msapplication-tap-highlight" content="no"/>

	<link href="${ctx }/css/star-sign/Capricornus/index.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/common/flexible.js"></script>
	<script src="${ctx }/js/star-sign/Capricornus/index.js"></script>
	<srcipt src="${ctx }/js/common/Data/amount.js"></srcipt>
	<title>摩羯座</title>
</head>
<body>
	<div class="base-page">
		<div class="all">
			<img class="background" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/all_01.png"/>
			<div class="btnDiv">
				<span class="ruleBtn">活动规则</span>
				<span class="myGiftBtn">我的奖品</span>
				<span class="iNoBtn">不是摩羯座？点这里</span>
			</div>
			<div class="zhiZhenDiv">
				<img class="btn" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/btn.png"/>
			</div>
		</div>
		<div class="ruleDiv">
			<div class="color-ground">
				<div class="context">
					<p class="p1"><span class="color">本期活动时间:</span>2017/12/22-2018/1/19</p>
					<p><span class="color">活动对象:</span><br>嘉兴移动官微绑定用户且手机号码实名登记时间符合每月星座日期规定。</p>
					<p><span class="color">活动规则:</span><br>1）在嘉兴移动官微回复“摩羯座”，获取活动链接；<br> 2）本月是摩羯座的用户，可以直接抽取奖励，100%中奖，每人只可参与一次抽奖； 非摩羯座的用户通过邀请通道成功邀请摩羯座好友参与活动的，也可获得一次奖励（每期活动仅限一次）；每位摩羯座好友只可帮忙验证5次。</p>
					<p><span class="color">本期活动奖励:</span><br>1元话费（中奖概率20%）、2元话费（中奖概率10%) 3元话费（中奖概率4%）、5元话费（中奖概率1%）100M流量券（中奖概率25%）、200M流量券（中奖概率20%）、300M流量券（中奖概率15%）、 500M流量券（中奖概率5%），共计20000份奖励。奖品数量有限，先到先得。</p>
					<p class="p2"><span class="color">奖励发放规则:</span><br>话费和流量券奖励将在中奖后的15个工作日内完成赠送。话费奖励直接赠送至用户馈赠金账户。流量奖励以流量券形式赠送至用户手机营业厅账户。需下载浙江移动手机营业厅客户端兑换使用。(流量券兑换流量为省内2/3/4G流量，流量券有效期90天，逾期作废。兑换流量当月有效，次月不结转)</p>
				</div>
			</div>
			<img class="ruleHead" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/ruleHead.png"/>
			<img class="closeRule" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/close.png"/>
		</div>
		<div class="getGiftDiv">
			<img class="gift" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/gift.png"/>
			<img class="closeGift" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/close.png"/>
			<div class="giftContext">
				<p><span>5元话费</span>到手，恭喜您呀！</p>
			</div>
		</div>
		<div class="isGiftDiv">
			<img class="isGift" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/isGift.png"/>
			<img class="closeThis" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/close.png"/>
		</div>
		<div class="myGiftDiv">
			<div class="color-ground-2">
				<div class="context-2">
					<img src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/ll.png"/>
					<!-- <p class="rGiftP"><span class="rGiftSpan">3</span>元</p> -->
				</div>
			</div>
			<img class="myGiftHead" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/myGiftHead.png"/>
			<img class="giftFoter" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/giftFoter.png"/>
			<img class="closeMyGift" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/close.png"/>
		</div>
		<div class="loginDiv">
			<div class="color-ground-1" style="top:4rem;height: 3.5rem">
				<div class="context-1">
					<img class="loginHead" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/loginHead.png"/>
					<!--<input type="tel" name="tel" class="tel" placeholder="请输入手机号码">
					<input type="num" name="num" class="num" placeholder="请输入验证码">
					<span class="code" id="code">获取验证码</span>-->
					<span class="login">立即验证</span>
				</div>
			</div>
		</div>
		<div class="successDiv">
			<img src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/success.png"/>
			<div class="successContext">
				<p>感谢您关注嘉兴移动官方微信平台，<br><span id="mm">3秒后</span>为您跳转至活动页面...</p>
			</div>
		</div>
		<div class="falseDiv">
			<img class="false" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/false.png"/>
			<img class="closeFalse" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/close.png"/>
		</div>
		<input type="hidden" id="openid" value="${openid }" />
	</div>
</body>
</html>