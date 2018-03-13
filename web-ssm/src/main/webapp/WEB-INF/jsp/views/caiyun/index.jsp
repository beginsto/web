<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-type" content="text/html;charset=utf-8">
<meta name="viewport"   content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<meta name="format-detection" content="telephone=no">
<meta name="msapplication-tap-highlight" content="no"/>
<meta name="format-detection" content="email=no"/>
<script src="${ctx }/js/common/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/caiyun/natification.js"></script>
<script src="${ctx }/js/star-sign/rem2.js"></script>
<script src="${ctx }/js/caiyun/sms.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/css/caiyun/notification.css">
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
		font-size: .33rem;
		font-family:-apple-system,BlinkMacSystemFont,PingFang SC,Hiragino Sans GB,Segoe UI,Microsoft YaHei,Roboto,Oxygen,Ubuntu,Cantarell,Fira Sans,Droid Sans,wenquanyi micro hei,Helvetica Neue,Helvetica,Arial,sans-serif;
		font-weight: 300;
		font-style: normal;
	}
	body{
		background: #DE0949;
	}
	input[type="button"], input[type="submit"], input[type="reset"] {

		-webkit-appearance: none;

	}
	.mask{
		width:100%;
		z-index: 10;
		height:100%;
		position: absolute;
		top:0;
		background: rgba(0, 0, 0, 0.44);
		display: none;
	}
	.mask .tips{
		position: fixed;
		top: 40%;
	}

	.mask .img{

		width:70%;
		margin-left: 15%;
	}

	.mask .confirm{
		width: 40%;
		height: .5rem;
		position: absolute;
		top: 2.1rem;
		margin-left: 2.2rem;
	}
	

</style>
<title>集团彩云</title>

</head>
<body >
<div>
	<img style="width: 100%" src="${ctx}/images/caiyun/bg.png" />
	<div style="position: absolute;top: 4.4rem;width: 100%;">
		<input class="sms-tel" type="text" style="width:80%;margin-left: 7.5%;padding-left: 5%;height: .8rem;" name="" placeholder="请输入手机号码">
		<input class="sms-code"type="text" style="width:40%;margin-left: 7.5%;margin-top:.5rem; padding-left: 5%;height: .8rem;" placeholder="请输入验证码" /><input class="sms" type="button" onclick="sms()" style="width:35%;margin-left: 4%;height: .91rem;background: #736ffb;border:none;color:#fff;text-align: center" value="获取验证码" />
		<input type="button" class="sub" style="width: 85%;height:.91rem;background: #736ffb;margin: .5rem 7.5%;text-align: center;color: #fff;border:none;" value="立即登记" />
		<input type="hidden" id="validate_code">
	</div>
	<div class="mask">
		<div class="tips">
			<img class="img"    />
			<div  class="confirm" >

			</div>
		</div>
	</div>
	<div class="notification-portal"></div>
	<div class="loader" ></div>
</div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	console.log("ready:"+1);
	setTimeout(function(){
		//$('.loader').show();
	},2000);

	$('.sub').bind('click',function () {
		var sms_code = $('.sms-code').val();
		var code = $('#validate_code').val()
		if(sms_code != '' && code != '' && sms_code == code){
		    $.ajax({
				url:'commit',
				type:'post',
				data:{tel:$('.sms-tel').val(),platform:'caiyun_${platform}'},
				dataType:'json',
				success:function (data) {
					if (data.resultCode == '200'){
						$('.mask img').attr("src","${ctx}/images/caiyun/success.png");
						$('.mask').show();
                        jump();
					}else if(data.resultCode == '303'){
                        $('.mask img').attr("src","${ctx}/images/caiyun/repeat.png");
                        $('.mask').show();
                        jump();
					}else{
                        createNotification('failed','请求失败-'+data.resultCode,'小嘉身体不适,暂时不能为您服务了...')
                    }

                }
			})

		}else{
            createNotification('failed','请求失败','验证码有误...')
		}
    })
	
})

function jump() {
	setTimeout(function () {
		window.location.href="https://jituancaiyun.com/d/";
    },2000)
}

function createNotification(s,t,m){
    var $notification_portal=$('.notification-portal');
    $notification_portal.html(
        '<div class="notification notification-'+ s +'">'+
        '<div class="notification-context">'+
        '<h3 >'+t+'</h3>'+m+
        '</div>'+
        '<div id="close" class="notification-close">关闭</div>'+
        '</div>'
    );
    var $notification = $('.notification');
    $notification.animate({top:'0'},300);
    $('.notification-close').bind(
        "click",
        function(){
            $notification.animate({top:'-60px'},300);
            //$notification_portal.html("");
        });
}
	
</script>