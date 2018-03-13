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
	<meta name="msapplication-tap-highlight" content="no"/>
	<meta name="format-detection" content="email=no"/>

	<link href="${ctx }/css/star-sign/libra/index.css" rel="stylesheet"/>
	<script src="${ctx }/js/common/jquery-1.11.0.min.js"></script>
	<script src="${ctx }/js/star-sign/libra/rem2.js"></script>
	<script src="${ctx }/js/star-sign/libra/index.js"></script>
	<script type="text/javascript" src="${ctx }/js/common/Data/amount.js"></script>
	<style>
		img{
			height: auto;
		}
	</style>
	<title></title>
</head>
<body id="body">
<input type="hidden" id="openid" value="${openid }" />
<input type="hidden" id="nickName"  />
	<div class="all">
		<div class="head"><img src="${ctx }/images/star-sign/libra/head.png"></div>
		<div class="context"><img src="${ctx }/images/star-sign/libra/context.png"></div>
		<div class="gift"><img src="${ctx }/images/star-sign/libra/gift.png"><span id="mFive" class="M5"></span><span id="mSix" class="M6"></span></div>
		<div class="footer"><img id="footer" src="${ctx }/images/star-sign/libra/footer.png"></div>
	</div>
	<div class="big">
		<div class="bigOne"><!--改 加div-->
			<div class="rule">
				<p class="p1">本期活动时间：2017/9/23-2017/10/23</p>
				<p class="p2">活动对象:嘉兴移动官微绑定用户且手机号码实名登记时间符合每月星座日期规定</p>
				<p class="p3">活动规则:</p>
				<p class="pN">1)在嘉兴移动官微回复"天秤座"，获取活动链接；</p>
				<p class="pN">2)本月是天秤座的用户，可以直接领取奖励，100%中奖，每人只可参与一次抽奖；</p>
				<p class="pN">非天秤座的用户通过邀请通道成功邀请天秤座好友参与活动的，也可获得一次奖励(每期活动仅限一次)；每个天秤座好友只可帮忙验证5次；</p>
				<p class="p2">本期活动奖励：</p>
				<p class="pN">500M流量券</p>
				<p class="pN">600M流量券</p>
				<p class="pN">两类流量券各5000份，共计10000份流量券</p>
				<p ><span class="sp">奖品数量有限,先到先得</span></p>
				<p class="p2">奖励发放规则：</p>
				<p class="pN">流量券奖励将在中奖后的15个工作日内为您安排赠送，所有流量券都将赠送至手机营业厅App内，请您务必下载并安装手机营业厅客户端(流量券有效期为90日，请注意及时查收并兑换)</p>
			</div>
			<div class="ruleBtn"><img src="${ctx }/images/star-sign/libra/rule.png"></div>
		</div>
	</div>
		<!-- 绑定 -->
		<div class="yanzheng">
			<div class=yanzheng1>
				<img src="${ctx }/images/star-sign/libra/yanzheng.png"/>
				<span>一键绑定手机号码，轻松拿奖励</span>
				<a class="now" href="http://jxyd.1860.cn/app/bindMobile/index?openid=${openid }">立即绑定</a>
			</div>
		</div>
		<div class="bangding">
			<image class="noo" id="noo" src="${ctx }/images/star-sign/libra/bdcg.png"/>
		</div>
		<!-- 绑定结束 -->
		<div class="gift1">
			<image id="giftall" src=""/>
			<span class="closeGift"></span>
		</div>
		<div class="nog">
			<div class="nogift">
				<img src="${ctx }/images/star-sign/libra/yanzheng.png">
				<div class="ll">
					<p class="pS">领取失败</p>
					<p class="pS1">每人只可选择一个礼包哟！</p>
				</div>
			</div> 
		</div>
		<div class="noTpz">
			<img src="${ctx }/images/star-sign/libra/yanzheng.png"/>
			<div class="llT" style="margin: 0;    padding: 0 .6rem;">
				<p class="pT" style="font-size: .4rem">验证失败</p>
				<p class="pT1" style="font-size: .3rem">您可不是天秤座，但是您也有机会拿奖哦！</p>
				<p  id="forward" style="    font-size: .3rem;background: #2C479F;width: 40%;height: .6rem;margin-left: 30%;margin-top: .1rem;margin: top;line-height: .6rem;border-radius: .1rem;">立即前往</p>
			</div>
		</div>
<script>
$("#forward").click(function () {
	window.location.href="forward.jsp_Scorpio"
})
</script>
</body>
</html>