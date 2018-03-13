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

	<link href="${ctx }/css/signin/rank.css" rel="stylesheet"/>
	<link href="${ctx }/css/common/dropload/dropload.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/signin/flexible.js"></script>
	<script src="${ctx}/js/common/dropload/dropload.min.js"></script>
	<script src="${ctx }/js/signin/rank.js"></script>
	<title>签到排行</title>
</head>
<body>
<input type="hidden" id="openid" value="${openid }"  />
<div class="all">
	<div class="head">
		<span id="flow">总流量:10M</span>
		<div class="count">
			<ul class="ulTitle">
				<li>打卡时间</li>
				<li>持续打卡天数</li>
				<li>今日流量数</li>
			</ul>
			<ul id="myflow">
				<li id="myflowTime">8:00</li>
				<li id="myflowDay">1天</li>
				<li id="myflowNum">10M</li>
			</ul>
		</div>
		<span class="nd"><img src=""/></span>
	</div>
	<div class="mid">
		<p>共有13213人参加早起计划</p>
		<span id="inviteF">邀请好友来PK</span>
	</div>
	<div class="context">
		<div class="title">
			<span id="rankFriend" class="active">好友排行</span>
			<span class="zw"></span>
			<span id="rankAll">总排名</span>
			<span class="zw"></span>
			<span id="rankPop">人气排行</span>
		</div>
		<!-- 好友排行 -->
		<div class="content">
			<div class="contentHead">
				<span><img /></span>
				<p class="wName"></p>
				<p class="todayDk"></p>
			</div>
			<div class="contentList">
				<div class="div1"></div>
				<div class="list"></div>
				<ul id="contentListUl">

				</ul>
				<!--<span class="allF"></span>-->
			</div>
		</div>
		<!-- 好友排行结束 -->
		<!-- 总排行 -->
		<div class="contentRank">
			<div class="rankHead">
				<span class="rankNum"></span>
				<span class="rankTxiang"><img src=""/></span>
				<p class="rankName"></p>
				<p class="rankDk"></p>
			</div>
			<div class="rankList">
				<div class="div1"></div>
				<ul id="rankPhUl">

				</ul>
			</div>
		</div>
		<!-- 总排行结束 -->
		<!-- 人气排行 -->
		<div class="humanQi">
			<div class="humanQiHead">
				<span><img src=""/></span>
				<p class="qiName"></p>
				<p class="humanQiNum">3人膜拜</p>
			</div>
			<div class="humanList">
				<div class="div1">&nbsp;</div>
				<ul id="humanRankUl">

				</ul>
			</div>
		</div>
	</div>
</div>
<div class="hide"></div>
<div class="zf">
	<img src="${ctx }/images/signin/zf.png"/>
	<span id="close"></span>
</div>
</body>
</html>

<script>

</script>
