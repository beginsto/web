<%--
  Created by IntelliJ IDEA.
  User: begins
  Date: 17/9/4
  Time: 上午9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no"/>
    <meta name="format-detection" content="email=no"/>
    <script src="${ctx }/js/common/jquery-1.11.0.min.js"></script>
    <script src="${ctx }/js/star-sign/rem2.js"></script>
    <script src="${ctx }/js/common/sms/sms.js"></script>

    <style>
        input[type="button"], input[type="submit"], input[type="reset"] {

            -webkit-appearance: none;

        }
        * {
            margin:0px;
            padding:0px;
        }

        body{
            /*background: #541B86;*/
        }
        .data{
            position: absolute;
            top:7.3rem;
            font-size: 0;
            /*background: #fff;*/
        }
        .data input{
            width: 95%;
            height: .7rem;
            padding-left: 5%;
            border-left-style: none;
            border-right-style: none;
            border-bottom-style: none;
            border-top-color: rgba(165, 165, 165, 0.24);
            border-top-width: thin;
            font-size:16px;
            -webkit-appearance:none; /* 方法2 */
            border-radius: 0;
        }
        .rule{
            position: absolute;
            top: 9.5rem;
        }
        .data .sms{
            width:35%;
            padding: 0;
            background: #541B86;
            color: #fff;
            font-size:18px;
            border-bottom-width: thin;
        }
        .data .but{
            width:50%;
            height:.8rem;
            margin:.1rem 25% 0;
            background: url(${ctx }/images/football/button.png);
            background-size: 100%;
            border:none;
        }

        .mask, .mask1{
            width:100%;
            z-index: 10;
            height:13.4rem;
            position: absolute;
            top:0;
            background: rgba(0, 0, 0, 0.44);
            display: none;
        }
        .mask .tips, .mask1 .tips1{
            position: absolute;
            top: 4.4rem;
        }
        .mask p{
            font-size: .2rem;
            position: absolute;
            top: 1.5rem;
            width: 100%;
            text-align: center;
            color: #fff;
        }
        .mask .img, .mask1 .img1{

            width:70%;
            margin-left: 15%;
        }

        .mask .confirm, .mask1 .confirm1{
            width: 40%;
            height: .5rem;
            position: absolute;
            top: 2.1rem;
            margin-left: 2.2rem;
        }
    </style>
    <title></title>
</head>
<body>
<div class="container">
    <img style="width:100%" src="${ctx }/images/football/bg.jpg"/>
    <p style="width:100%"></p>
    <form id="form" name="form" method="post">
        <div class="data">
            <input type="text" class="sms-tel" name="mobile" style="width:60%" placeholder="请输入手机号码"/>\<input type="button"
                                                                                                                class="sms"
                                                                                                                value="验证码"
                                                                                                                onclick="sms()"/>
            <input type="text" id="code" name="" placeholder="请输入验证码"/>
            <button type="submit" class="but"></button>
            <input type="hidden" id="validate_code">
        </div>
    </form>

    <div class="rule">
        <img style="width:100%" src="${ctx }/images/football/rule.png"/>
    </div>
</div>
<div class="mask" >
    <div class="tips">
        <img class="img"  />
        <p id="amount"></p>
        <div  class="confirm" >

        </div>
    </div>
</div>
<div class="mask1"  >
    <div class="tips1">
        <img class="img1"  src="${ctx }/images/gift/un-target.png" />
        <div  class="confirm1" style="top:6.8rem;height:.8rem">

        </div>
    </div>
</div>
</body>
</html>
<script src="${ctx }/js/common/jquery-form.js"></script>
<script>
    $('#form').click(function () {
        var options={
            url:'login',
            dataType:'json',
            beforeSubmit:validated,
            success:function (data) {
                $img = $('.img');
                if(data.resultCode == '200'){
                    $('#amount').text("您目前是第"+ data.message +"位成功为国足助威的好友");
                    $img.attr("src","${ctx }/images/football/success.png");
                }else if(data.resultCode == '305'){
                    $img.attr("src","${ctx }/images/football/repeat.png");
                }else if(data.resultCode == '304'){
                    $img.attr("src","${ctx }/images/football/timeout.png");
                }else{
                    $img.attr("src","${ctx }/images/gift/failed.png");
                }

                $('.mask').show();

            },
            error:function () {

            }
        };
        $('#form').ajaxForm(options);
    });

    function validated(formData,jqForm,options){
        // var queryString = $.param(formData);
        var form=jqForm[0];
        if($('#code').val() == '' || $('#code').val() != $('#validate_code').val()){
            alert("验证码有误...");
            return false;
        }
        if(form.mobile.value == ''){
            alert("手机号码不能为空...");
            return false;
        }
    }

    $('.confirm').click(function () {
        $('#amount').html("");
        $('.mask').hide();
        window.location.reload();
    });

    $('.confirm1').click(function () {
        $('.mask1').hide();
    })
</script>
