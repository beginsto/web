<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
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
	<title>摩羯座</title>
</head>
<body>
	<div class="base-page">
		<div class="all">
			<img class="forward" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/forward.png"/>
			<span class="forwardBtn"></span>
		</div>
		<div class="wardDiv">
			<img class="ward" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/ward.png"/>
			<img class="iKnow" src="http://jxyd2.1860.cn:9090/jxwx_static/img/star-sign/Capricornus/iKnow.png"/>
		</div>
	</div>
	<script>
		window.onload = function(){
			$(".forwardBtn").bind("click",function(){
				$(".wardDiv").show();
			});
			$(".iKnow").bind("click",function(){
				$(".wardDiv").hide();
			});
		}
	</script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/common/wechat/wx_api.js"></script>
	<script type="text/javascript">

        wx.ready(function(){
            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: '真爱粉们快来领取话费流量，就等你来领！抢到就是赚到！', // 分享标题
                link: 'http://jxyd.1860.cn/main/star-sign/friend?openid=${openid }', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: 'http://jxyd.1860.cn/main/images/star-sign/share_1.png', // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });

            //分享给朋友
            wx.onMenuShareAppMessage({
                title: '真爱粉们快来领取话费流量，就等你来领！抢到就是赚到！', // 分享标题
                desc: '你的好友${nickName }正在疯抢流量券！离成功就差0.01米，你赶紧来帮TA吧！', // 分享描述
                link: 'http://jxyd.1860.cn/main/star-sign/friend?openid=${openid }', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: 'http://jxyd.1860.cn/main/images/star-sign/share_1.png', // 分享图标
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
</body>
</html>