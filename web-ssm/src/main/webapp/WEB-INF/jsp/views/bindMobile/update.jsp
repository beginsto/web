<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>重新登记手机号</title>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/bindMobile/h5.css">
	<link type="text/css" rel="stylesheet" href="${ctx }/css/bindMobile/phone_bind.css">
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?bb080db77c119be415284aede4c3ae00";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
	</script>
<head>
</head>
<body>
		<div class="result-tip">
				<p>你当前关联的信息如下</p>				
		</div>
	    	<ul class="info-box">
	    		<li>
	    			手机号码：<span class="success_phone">${mobile }</span>
	    		</li>
	    	</ul>  
		</div>
<div class="all">
	<div class="main">
	    <div class="item-box" style="display:block;">
	        <ul class="pb-form">
			        <div class="result-tip">
						<p>重新登记手机号码请输入如下信息</p>				
					</div>
	            <li class="li1">
	                <input type="text" id="mobile" class="h5-txt1 phone-txt" placeholder="请输入您的手机号码">                
	            </li>
	            <li class="li2">
	                <input type="text" name="YZM" id="YZM" class="h5-txt1 prove-txt" placeholder="请输入6位验证码">
	                <span class="h5-btn1 get-prove-btn" id="clickYZM" style="display:block; color:#fff;background:#73BF00;">获取验证码</span>
	                <input id="openid" type="hidden" value="${openid }">
	            </li>               
	            <li class="li3">
	                <span class="h5-btn2 sure-btn" id="tj">登记手机号码</span>
	            </li>
	        </ul>
	    </div>
	</div>
</div>


</body></html>
<script type="text/javascript">
    var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"
        + "wxyz0123456789+/" + "=";
    function encode64(input) {

        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;
        do {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
                + keyStr.charAt(enc3) + keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        } while (i < input.length);

        return output;
    }

    function LodaData(tel)
    {
        var date = new Date();
        var v_year=date.getFullYear();
        var v_month=date.getMonth()+1;
        if(parseInt(v_month)<10)
            v_month="0"+v_month;
        var v_day=date.getDate();
        if(v_day<10)
            v_day="0"+v_day;
        var v_hours=date.getHours();
        var v_minutes=date.getMinutes();
        var v_seconds=date.getSeconds();
        if(v_minutes<10)
            v_minutes="0"+v_minutes;
        if(v_seconds<10)
            v_seconds="0"+v_seconds;
        if(v_hours < 10)
            v_hours="0"+v_hours;
        var v_random=Math.floor(Math.random() * (200 - 1));
        var v_resul=v_month+""+v_year+""+v_day+""+v_random+""+v_seconds+""+v_hours+""+v_minutes;
        $.ajax({//
            type: "GET",
            url: "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?type=0&token="+encode64(encode64(encode64(v_resul)))+"&teleNum="+tel+"&sendType=1",
            dataType:'jsonp',
            jsonpCallback : "jsonpCallback",
            jsonp : "callback",
            processData: false,
            success: function(data) {
                insertData(tel,data.msg);
            }, error: function(XMLHttpRequest, textStatus, errorThrown)
            {
                //alert(XMLHttpRequest.status);
                //alert(XMLHttpRequest.readyState);
                //alert(textStatus);
            }
        });
    }

    function insertData(mobile,context) {
        $.ajax({
            type:'post',
            url:'sendMsg',
            dataType:'json',
            data:{mobile:mobile,context:context},
            success:function (data) {
                if(data.result == 'success')
                    $("#prove_alert").show();
                else
                    alert("短信通道被挤爆啦...请稍等片刻在尝试!");
            }
        });
    }

    function setDisable () {
        var count = 60;
        var countdown = setInterval(CountDown, 1000);
        function CountDown() {
            $("#clickYZM").attr("disabled", true).css('background','#ccc');

            $("#clickYZM").text( count + "秒");
            if (count == 0) {
                $("#clickYZM").text("重新获取").removeAttr("disabled").css('background','#73BF00');
                clearInterval(countdown);
            }
            count--;
        }
    }

    $("#clickYZM").click(function(){
        if($(this).css('backgroundColor') == 'rgb(204, 204, 204)'){//使倒计时失效
            return;
        }
        var mobile = $("#mobile").val();
        var reg = /^1[3|4|5|8|7|9][0-9]\d{8}$/;
        //发送验证码,第一步验证手机号码
        if (!reg.test(mobile)) {
            alert("手机号码格式不正确");
            return false;
        }else {
            setDisable();
            //insertData('15905836605','547898');
            LodaData(mobile);
        }
    });

    $("#tj").click(function(){
        var mobile = $("#mobile").val();
        var YZM = $("#YZM").val();
        var openid = $("#openid").val()
        var reg = /^1[3|4|5|8|7|9][0-9]\d{8}$/;

        if (!reg.test(mobile)){
            alert("手机号码格式不正确");
            return false;
        }
        if (YZM ==""||mobile=="") {
            alert("请填写完整信息");
            return false;
        }
        $.ajax({
            type:'post',
            url:'validate',
            dataType:'json',
            data:{tel:mobile,context:YZM},
            success:function (data) {
                if(data.result == 'success')
                    bangding(mobile,openid);
                else if (data.result == 'failed')
                    alert("验证码有误...");
                else
                    alert("系统异常...");
            }
        });

    });

    function bangding(mobile,openid){
        $.ajax({
            type:'post',
            url:'bind',
            dataType:'json',
            data:{
                phone:mobile,
                openid:openid
            },
            success:function (data) {
                console.log(data.result);
                if(data.result == 'success')
                    window.location.href = "success?mobile="+mobile;
                //else if (data.result == 'null-param')
                //alert("验证码有误...");
                //else if (data.result == 'null-openid')
                //alert("验证码有误...");
                else
                    alert("系统异常...");
            }
        });
    };
    $('.alert-btn').click(function () {
        $("#prove_alert").hide();
    })


</script>
