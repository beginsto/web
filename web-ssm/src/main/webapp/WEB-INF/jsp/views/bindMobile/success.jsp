<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>登记成功</title>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/bindMobile/h5.css">
	<link type="text/css" rel="stylesheet" href="${ctx }/css/bindMobile/phone_bind.css">
	<head>
	</head>
	<body>
		<%
		 String mobile = request.getParameter("mobile");
		%>
	
	<div class="main success_main" style="display:block;">
		<div class="cricle-blue">
			<img src="${ctx }/images/bindMobile/success.png" width="50%">
		</div>
		<p class="pop-title">登记手机号成功！<br>你当前关联的信息如下 </p>
		<ul class="phone-msg">
			<li>手机号码：<span id="phoneNum">${mobile }</span></li>
		</ul>
	</div>
	</body>
	</html>
<script>
    if(localStorage.from&&localStorage.from=='light_handprint'){
        //隐藏微信右上角按钮
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady(){
            WeixinJSBridge.call('hideOptionMenu');
        });
    }
</script>

