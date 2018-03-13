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

	<link href="${ctx }/css/signin/active.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/signin/flexible.js"></script>
	<script src="${ctx }/js/signin/active.js"></script>
	<title>21天早起达人</title>
</head>
<body>
<input type="hidden" id="openid" value="${openid }"  />
	<div class="all">
		<div class="head">
		<img class="hd" src="${ctx }/images/signin/activehead.png"/>
		<span><img class="nd" src=""/></span>
		<p>微信昵称</p>
		</div>
		<div class="context">
			<p class="p1">21天早起达人计划&nbsp;&nbsp;&nbsp;第<span>10</span>天</p>
			<p class="p2">时间:&nbsp;&nbsp;09月09日 5:00-10:00</p>
		</div>
		<div class="list">
		<div class="div2"></div>
		<p>共<span id="rens">192903</span>人参与日程</p>
		<ul class="oneUl">
				
			</ul>
		<div class="div3"></div>
		</div>
		<div class="ruleI">
			<p>1、每天早上5:00-8:00打卡，全额流量(10M);8:00-10:00打卡，流量减半(5M)</p>
			<p>2、连续第N天打卡，当天奖励2*N流量。连续打卡超过21天，每天固定奖励50M流量。打卡中断后，再次打卡奖励流量重新从10M开始计算，总积分累计。</p>
			<p>3、累计流量在活动进行一轮后(即21天)可在流量商城进行兑换，所有累计流量将于第63天24点清零。</p>
		</div>
		<div class="footer">
			<span id="rule" class="span1">打卡规则详情</span>
			<span id="nyYn" class="span2">我要打卡</span>
		</div>
	</div>
	<div class="hide"></div>
	<div class="dk">
		<img src="${ctx }/images/signin/dk.png"/>
		<p id="num">10</p>
		<p id="num1">今天是您打卡的第10天</p>
		<span id="closeDk"></span>
	</div>
</body>
</html>
<script>
</script>