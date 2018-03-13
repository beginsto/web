<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<script type="text/javascript" src="${ctx }/js/common/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common/flexible.js"></script>
<script type="text/javascript" src="${ctx }/js/common/natification.js"></script>
<script type="text/javascript" src="${ctx }/js/common/sms/sms.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/css/common/notification.css">
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
		/*background: #DE0949;*/
		overflow: hidden;
	}
	input[type="button"], input[type="submit"], input[type="reset"] {

		-webkit-appearance: none;

	}
	.sms-tel{  font-size:.45rem;width:80%;margin-left: 7.5%;height: 1.1rem;border: 1px solid #9e9e9e;border-radius: .15rem;background: #222;padding-left: 5%;color:#fff}
	.sms-code{font-size:.45rem;margin-top:.4rem;width:40%;margin-left: 7.5%;height: 1.1rem;border: 1px solid #9e9e9e;border-radius: .15rem;background: #222;padding-left: 5%;color:#fff}
	.sms{ font-size:.45rem;margin-top:.4rem;width:35.5%;margin-left: 3.5%;height: 1.1rem;border: 1px solid #9e9e9e;border-radius: .15rem;background: #3698fb;text-align: center;color: #fff;}
	.sub{width:89%;height:1.56rem;margin-top:.4rem;border:none;margin-left:5.5%;background: url(${ctx }/images/vivo-x20/button.png);background-size: 100% 100%}
	p{width:100%;margin-top:.5rem;font-size:.4rem;color:#fff;text-align: center;}
	p span{color: #3698fb;font-size:.4rem;}

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
		height: .8rem;
		position: absolute;
		top: 3rem;
		margin-left: 3.2rem;
	}

</style>
<body>
<img width="100%" src="${ctx }/images/vivo-x20/bg.png" />
<div style="position: absolute;top: 6.3rem;width: 100%">
	<input class="sms-tel" type="" name="" placeholder="请输入您的手机号码">
	<input class="sms-code" s type="" name="" placeholder="输入验证码">
	<input class="sms" type="button" onclick="sms()" value="获取验证码" />
	<p >已有<span><fmt:formatNumber type='number' value='${ amount*3 / 2  + 1 }' maxFractionDigits="0"></fmt:formatNumber> </span>位用户预约！</p>
	<input type="button" class="sub" />
	<input type="hidden" id="validate_code">
	<img style="width:88%;margin-left: 6%;margin-top: .5rem" src="${ctx }/images/vivo-x20/rule.png" />
</div>
<div class="mask">
	<div class="tips">
		<img class="img" src="${ctx }/images/vivo-x20/success.png"   />
		<div  class="confirm" >

		</div>
	</div>
</div>
<div class="loader" ></div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	
	$('.loader').hide();

    $('.sub').bind('click',function () {
        var _notification_portal = $('.notification-portal');
        var sms_code = $('.sms-code').val();
        var code = $('#validate_code').val()
        if(sms_code != '' && code != '' && sms_code == code){
            $.ajax({
                url:'commit',
                type:'post',
                data:{tel:$('.sms-tel').val(),source:'vivox20'},
                dataType:'json',
                success:function (data) {
                    if (data.resultCode == '200'){
                        $('.mask img').attr("src","${ctx}/images/vivo-x20/success.png");
                        $('.mask').show();
                    }else if(data.resultCode == '303'){
                        $('.mask img').attr("src","${ctx}/images/vivo-x20/repeat.png");
                        $('.mask').show();
                    }else{
                        _notification_portal.modal({status:'failed',title:'请求失败-'+data.resultCode,msg:'小嘉身体不适,暂时不能为您服务了...'})
                    }

                }
            })

        }else{
            _notification_portal.modal({status:'failed',title:'请求失败',msg:'验证码有误...'})
        }
    })

	$('.confirm').bind('click',function () {
        $('.mask').hide();
    })
	
})
	
</script>