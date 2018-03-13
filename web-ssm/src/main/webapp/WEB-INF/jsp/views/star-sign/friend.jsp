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

	<link href="${ctx }/css/star-sign/Capricornus/friend.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/common/flexible.js"></script>
	<script src="${ctx }/js/star-sign/friend.js"></script>
	<title>摩羯座</title>
</head>
<body>
	<div class="base-page">
		<input id="openid" value="${openid}" type="hidden" />
		<div class="all">
			<img class="friend" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/friend.png"/>
			<div class="friendLoginDiv fDiv">
				<div class="context">
					<img class="friendHead" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/friendHead.png"/>
					<input type="tel" name="tel" id="tel" class="tel" placeholder="请输入手机号码">
					<input type="num" name="num" id="num" class="num" placeholder="请输入验证码">
					<span class="code" id="code">获取验证码</span>
					<span class="login">立即验证</span>
					<span class="iWant">我也要参加</span>
				</div>
			</div>
			<div class="friendLoginDiv fDiv-1">
				<div class="context">
					<img class="friendHead" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/friendHead.png"/>
					<img class="dh" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/ziyuanright.png"/>
					<p class="headP">你的好友已成功帮你获得验证啦！</p>
					<p class="tp">赶快去抽奖吧！</p>
					<span class="iWant">我也要参加</span>
				</div>
			</div>
			<img class="footer" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/friendFooter.png"/>
			<span id="iWant"></span>
		</div>
		<div class="iWantDiv">
			<img class="iDo" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/iDo.png"/>
			<img class="ewm" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/ewm.png"/>
			<img class="close" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/close.png"/>
		</div>
	</div>
	<script>
        var isHelp = '${isHelp}';
        if (isHelp == 'Y'){
            $(".fDiv").hide();
            $(".fDiv-1").show();
        }

        $("#iWant").bind("click",function(){
            $(".iWantDiv").show();
        });
        $(".close").bind("click",function(){
            $(".iWantDiv").hide();
        });
	</script>
</body>
</html>