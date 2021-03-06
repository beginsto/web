<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
	<meta http-equiv="Content-type" content="text/html;charset=utf-8">
	<meta name="viewport"   content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no">
	<meta name="msapplication-tap-highlight" content="no"/>
	<meta name="format-detection" content="email=no"/>

	<link href="${ctx }/css/star-sign/test.css" rel="stylesheet"/>
	<script src="${ctx }/js/common/jquery-1.11.0.min.js"></script>
	<script src="${ctx }/js/star-sign/jQueryRotate.2.2.js"></script>
	<script src="${ctx }/js/star-sign/rem2.js"></script>
	<script src="${ctx }/js/star-sign/index.js"></script>
	<script src="${ctx }/js/star-sign/friend.js"></script>
	<title></title>

</head>
<body>
<div class="allf" style="background: url(${ctx }/images/star-sign/libra/friendback.png);background-size: cover">
	<input id="openid" value="${openid}" type="hidden" />
	<div class="f1">
		<input id="tel" name="tel" type="tel" maxlength="11" placeholder="               请输入号码" />
		<input id="num" name="num" type="text"  placeholder="   请输入验证码" />
		<span id="tnum">获取验证码</span>
		<image class="ljyz" src="${ctx }/images/star-sign/ziyuan2.png"/>
		<image id="fcj"class="cj" src="${ctx }/images/star-sign/cj.png"/>
		<!--<image class="cj1" src="${ctx }/images/star-sign/ziyuan6.png"/>-->
	</div>
	<div class="myyz">

		<p>你的好友已成功获得验证啦！</p>
		<span>&nbsp;</span>
		<p class="tp"> 赢得一次抽奖机会！</p>
		<image id="mycj0" class="cj0" src="${ctx }/images/star-sign/ziyuanright.png"/>
		<image id="mycj" class="cj" src="${ctx }/images/star-sign/cj.png"/>
		<!--<image class="cj1" src="${ctx }/images/star-sign/ziyuan6.png"/>-->
	</div>
</div>
<div class="ewm">
	<div class="ewm1">
		<image id="guan" src="${ctx }/images/star-sign/xx.png"/>
		<span id="kong">&nbsp;</span>
		<p class="tlt">参与流程</p>
		<p class="kong">&nbsp;</p>
		<p class="content">长按下方二维码识别进入平台并</p>
		<p class="content">回复暗号&nbsp;<span>天秤座</span>&nbsp;即可参与活动</p>
		<image id="ewm" class="ewmt" src="${ctx }/images/star-sign/ewm.png"/>
	</div>
</div>
<div class="fyzjg">
	<div class="fyzjg1">
		<p id="jieguo"></p>
		<!--<p id="jieguo1">身为处女座的你，已成功帮助你的好友<br>获得了奖励！</p>-->
		<!--<p id="jieguo1">被我识破啦！你可不是处女座！</p>-->
		<p id="jieguo1"></p>
	</div>
</div>
</body>

</html>
<script>
    var isHelp = '${isHelp}';
    if (isHelp == 'Y'){
        $('.f1').css('display','none');
        $('.myyz').css('display','block');
    }else{
        $('.myyz').css('display','none');
        $('.f1').css('display','block');

    }

</script>
