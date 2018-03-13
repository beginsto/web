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

	<link href="${ctx }/css/signin/rule.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/signin/flexible.js"></script>
	<title>活动规则</title>
</head>
<body>
	<div class="all">
		<img src="${ctx }/images/signin/rule.png"/>
		<div class="word">
			<p class="stage"><span>1、打卡办法</span></p>
			<p>进入嘉兴移动官微公众号，回复“<span>打卡</span>”，获取活动页面，即可打卡。打卡后可查看当天<span>打卡排行榜</span>。分享邀请好友，好友参与打卡计划后，可接受好友的膜拜哦!</p>
			<p class="stage"><span>2、打卡时间</span></p>
			<p>5:00-8:00 为正常打卡时间，全额奖励当天流量</p>
			<p>8:00-10:00为迟到打卡时间，当天打卡流量减半</p>
			<p class="stage"><span>3、打卡规则（每三个21天计划为一期）</span></p>
			<p><span class="title">第一个21天打卡计划</span></p>
			<p>连续第N天打卡成功，当天奖励10*N流量。</p>
			<p>例如:</p>
			<p>连续第1天8点前打卡，当天奖励流量10M，累计10M。</p>
			<p>连续第2天8点前打卡，当天奖励流量20M，累计30M。</p>
			<p>连续第3天8点前打卡，当天奖励流量30M，累计60M。</p>
			<p>......</p>
			<p>连续第21天8点前打卡，当天奖励流量210M，累计2310M。</p>
			<p><span class="title1">第二个21天打卡计划</span></p>
			<p>第二轮21天打卡计划的第一天开始，每天固定奖励100M。</p>
			<p><span class="title1">第三个21天打卡计划</span></p>
			<p>第三轮21天打卡计划的第一天开始，每天固定奖励150M。</p>
			<p class="stage"><span>4、活动须知</span></p>
			<p>1）连续三轮21天打卡计划为一期活动，一期活动结束后（即第63天24点）所有流量清零，重新开始全新一期的打卡计划。</p>
			<p>2）某天8点至10点之间打卡，当天奖励为正常打卡流量的一半。</p>
			<p>3）中断打卡后，当天奖励流量重新从10M开始计算（即从第一天开始），总流量累计。</p>
			<p>4）所有流量奖励，从打卡计划的第22天0点开始兑换。</p>
			<p>&nbsp;</p>
		</div>
	</div>
</body>
</html>