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

	<link href="http://jxyd2.1860.cn:9090/jxwx_static/mro/css/index.css" rel="stylesheet"/>
	<link href="http://jxyd2.1860.cn:9090/jxwx_static/JUI/css/loading.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://jxyd2.1860.cn:9090/jxwx_static/JUI/js/flexible.js"></script>
	<script src="http://jxyd2.1860.cn:9090/jxwx_static/JUI/js/loading.js"></script>
	<script src="${ctx }/js/mro/index.js"></script>
	<script src="http://jxyd2.1860.cn:9090/jxwx_static/JUI/js/amount.js"></script>

	<title>最多跑一次</title>
</head>
<body>
	<div class="all">
		<img class="head" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/head.png"/>
		<img class="banner" src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/banner.png"/>
		<div class="qiehuan">
		</div>
		<div class="page-1">
		<p class="pa"></p>
		<div class="nhq da">
			<div class="turnT">
				<ul id="nhqT">

				</ul>
			</div>
			<div class="piaoDiv">
				<input hidden="hidden" id="openid" value="${openid }"/>
				<input hidden="hidden" id="issue"  />
				<div class="piaoNum">当前票数：<span id="numN"></span>票</div>
				<div id="getN" class="isGet">立即投票</div>
			</div>
			<div class="hui"></div>
			<p class="df">请为我们的服务打分：</p>
			<div class="pjDiv">
				<div class="myN"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/hmy.png"></div>
				<div class="jbmyN"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/hjbmy.png"></div>
				<div class="bmyN"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/hbmy.png"></div>
				<div class="wytcN"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/wytc.png"></div>
			</div>
			<div class="spM spN">
				<span class="sp1 red"></span>
				<span class="sp2"></span>
			</div>
		</div>
		<div class="nhqX">
			<p class="p1">【</p>
			<img class="nhq-1" />
			<p class="p2"></p>
			<img class="nhq-2" />
			<p class="p3"></p>
		</div>
		</div>
	</div>
	<div class="ruleDiv">
		<img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/rule.png"/>
		<div class="rule">
			<p>活动时间：<br>&nbsp;即日起-2018年1月31日 </p>
			<p>活动对象：<br>全体嘉兴市民</p>
			<p>活动规则：<br>请在页面上给喜欢的单位投票哦，如果你有意见或者建议也可向我们吐槽。本次投票活动每期投放六个单位，投票持续2天，您可选择其中一个为其投票助力哦~</p>
		</div>
		<span class="closeRule"></span>
	</div>
	<div class="rs">
		<img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/false.png"/>
		<span class="closeRs"></span>
		<span class="fow"></span>
	</div>
	<img class="ruleBtn" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/ruleBtn.png">
	<div class="forw">
		<img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/mro/faword.png"/>
		<span class="guan"></span>
	</div>
	<div class="tcRs">
		<div class="div1">
			<div>
				<p></p>
			</div>			
		</div>
	</div>
</body>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="${ctx }/js/common/wechat/wx_api.js"></script>
<script type="text/javascript">
	wx.ready(function(){
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: '嘉兴市“最多跑一次”向您报告！请您打分！', // 分享标题
            link: 'http://jxyd.1860.cn/main/mro/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://jxyd.1860.cn/main/images/mro/mro_share.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        //分享给朋友
        wx.onMenuShareAppMessage({
            title: '嘉兴市“最多跑一次”向您报告！请您打分！', // 分享标题', // 分享标题
            desc: '我正在参加嘉兴市“最多跑一次”投票活动，你也一起加入吧！', // 分享描述
            link: 'http://jxyd.1860.cn/main/mro/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://jxyd.1860.cn/main/images/mro/mro_share.png', // 分享图标
            //type: '', // 分享类型,music、video或link，不填默认为link
            //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
                //alert("分享成功")
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
                //alert("取消分享");
            }
        });
    });
</script>
</html>