<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-type" content="text/html;charset=utf-8">
	<meta name="viewport"   content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no">
	<meta name="msapplication-tap-highlight" content="no"/>
	<meta name="format-detection" content="email=no"/>

	<link href="${ctx }/css/signin/getday.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/signin/flexible.js"></script>
	<script src="${ctx }/js/signin/getday.js"></script>
	<title>签到天数</title>
</head>
<body>
<input type="hidden" id="openid" value="${openid }"  />
	<div class="all">
		<div class="head"><img src="${ctx }/images/signin/head.png"/><p>2017/10/09开始</p></div>
		<div class="numPeople">
			<p><span id="howP">23938</span>人已参加</p><div class="div1"></div>
			<ul class="oneUl">
			</ul>
			<ul class="twoUl">
			</ul>
			<div class="div2"></div>
		</div>
		<div class="ruleI">
			<p>1、每天早上5:00-8:00打卡，全额流量(10M);8:00-10:00打卡，流量减半(5M)</p>
			<p>2、连续第N天打卡，当天奖励2*N流量。连续打卡超过21天，每天固定奖励50M流量。打卡中断后，再次打卡奖励流量重新从10M开始计算，总积分累计。</p>
			<p>3、累计流量在活动进行一轮后(即21天)可在流量商城进行兑换，所有累计流量将于第63天24点清零。</p>
			<span id="ruleD">打卡规则详情</span>
			<span id="invite">邀请好友</span>
			<span id="mall">兑换商城</span>
		</div>
		<div class="dayList">
		<div class="div3"></div>
		<p class="title">日程列表</p>
		<ul id="dayListWord">
			<!-- <li><div class="aready"><span>01</span><p class="p1">09月07日&nbsp;5:00</p><p class="p2">21天早起达人计划&nbsp;第1天</p><a id="res" href="#">打卡成功</a></div></li>
			<li><div class="getStart"><span>02</span><p class="p1">09月07日&nbsp;5:00</p><p class="p2">21天早起达人计划&nbsp;第2天</p><a id="res" href="#">18小时后开始</a></div></li>
			<li><div class="miss"><span>03</span><p class="p1">09月07日&nbsp;5:00</p><p class="p2">21天早起达人计划&nbsp;第3天</p><a id="res" href="#">已错过</a></div></li>
			<li><div class="noStart"><span>04</span><p class="p1">09月07日&nbsp;5:00</p><p class="p2">21天早起达人计划&nbsp;第4天</p><a id="res" href="#">未开始</a></div></li> -->
		</ul>
		<div class="div4"></div>
		</div>
	</div>
	<div class="hide"></div>
	<div class="zf">
		<img src="${ctx }/images/signin/zf.png"/>
		<span id="close"></span>
	</div>
</body>
</html>