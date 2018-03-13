<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
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
	<script src="${ctx }/js/star-sign/libra/rem1.js"></script>
	<title></title>
	<script type="text/javascript">
		window.onload = function(){
			$(".zt").bind("click",function(){
				$(".fg").show();
			});
			$("#close").bind("click",function(){
				$(".fg").hide();
			});
		}
	</script>
</head>
<body id="al">
	<div class="fall">
		<img class="zt" src="${ctx }/images/star-sign/libra/zt.png"/><!--改-->
		<span class="tz"></span>
		<img class="step_three" style="width: 96%;margin-left: 2%;position: absolute;top: 12rem;" src="${ctx }/images/star-sign/libra/step_three.png" />
	</div>
	<div class="fg" >
		<img src="${ctx }/images/star-sign/libra/share.png">
		<p class="p1">召集天秤座好友完成验证</p>
		<p class="p2">即可获得一个礼包哦！</p>
		<span id="close"></span>
	</div>
</body>
</html>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="${ctx }/js/common/wechat/wx_api.js"></script>
<script type="text/javascript">

    wx.ready(function(){
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: '选择困难大户天秤座，白送的流量券就在你面前，还在纠结啥！行动起来！！！', // 分享标题
            link: 'http://jxyd.1860.cn/main/star-sign/friend?openid=${openid }', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://jxyd.1860.cn/main/images/star-sign/share_2.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        //分享给朋友
        wx.onMenuShareAppMessage({
            title: '选择困难大户天秤座，白送的流量券就在你面前，还在纠结啥！行动起来！！！', // 分享标题
            desc: '你的好友${nickName }正在疯抢流量券！离成功就差0.01米，你赶紧来帮TA吧！', // 分享描述
            link: 'http://jxyd.1860.cn/main/star-sign/friend?openid=${openid }', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://jxyd.1860.cn/main/images/star-sign/share_2.png', // 分享图标
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