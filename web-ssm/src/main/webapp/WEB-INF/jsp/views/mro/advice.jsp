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

	<link href="${ctx }/css/mro/index.css" rel="stylesheet"/>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${ctx }/js/common/flexible.js"></script>
	<title></title>
</head>
<body>
	<div class="allN">
		<img class="head" src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/head.png"/>
		<p class="pa">No.3&nbsp;嘉兴市嘉善县</p>
		<div class="nhq da tcAll">
			<div class="turnT">
				<ul id="jsxT">
					<!--<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/jsx-1.jpg"></div></li>
					<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/jsx-2.jpg"></div></li>
					<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/jsx-3.jpg"></div></li>
					<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/jsx-4.jpg"></div></li>
					<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/jsx-5.jpg"></div></li>-->
				</ul>
			</div>
			<div class="tc">
				<textarea id="cont" rows="3" cols="20" maxlength="200" autofocus="autofocus" placeholder="您可将需要表达的意见或者建议在此输入框提交…（字数限制200字）"></textarea>
			</div>
			<span class="sub">立即提交</span>
			<div class="spM spJ spTc">
				<span class="sp1 red"></span>
				<span class="sp2"></span>
				<span class="sp3"></span>
				<span class="sp4"></span>
				<span class="sp5"></span>
			</div>
	    </div>
	</div>
	<div class="tcRs">
		<div class="div1">
			<div>
				<p>请勿重复提交哦！</p>
			</div>
		</div>
	</div>
	<div class="ruleDiv">
		<img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/rule.png"/>
		<div class="rule">
			<p>活动时间：<br>即日起-2018年1月20号</p>
			<p>活动对象：<br>全体嘉兴市民</p>
			<p>活动规则：<br>请在页面上给喜欢的单位投票哦，如果你有意见或者建议也可向我们吐槽。本次投票活动每期投放三个单位，您可选择其中一个为其投票助力哦~</p>
		</div>
		<span class="closeRule"></span>
	</div>
	<img class="ruleBtn" src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/ruleBtn.png">
	<script>
		window.onload = function(){
			var winwidth = $(".allN").width();
	var liwidth = $(".da .turnT li").width();
	if(winwidth<375){
		//alert(winwidth);
		$(".spM").css("margin-top","-6.1rem");
	}
			$(".ruleBtn").bind("click",function(){
		$(".ruleDiv").show();
	});
	$(".closeRule").bind("click",function(){
		$(".ruleDiv").hide();
	});
	var j = 0;
	timerJ = setInterval(function(){
		j++;
		var lll = j * liwidth;
		var s2 = j + 1;
		if(j<5){
			$("#jsxT").animate({marginLeft:"-"+lll},500);
			timer2 = setInterval(function(){
				$(".spJ .red").removeClass("red");
				$(".spJ .sp"+s2).addClass("red");
				clearInterval(timer2);
			},300);
		}else{
			$("#jsxT").css("margin-left","0rem");
			$(".spJ .red").removeClass("red");
			$(".spJ .sp1").addClass("red");
			j = 0;
		}
	},2000);
	var rs = 0;
			var longW = $("#cont").val();
			/*$(".sub").bind("click",function(){
				if(rs==1){
					$(".div1 p").text("请勿重复提交哦！");
					$(".tcRs").show();
					tiemr = setInterval(function(){
						$(".tcRs").hide();
					},3000);
					return;
				}
				$(".div1 p").text("您的意见提交成功，非常感谢您的反馈！");
				$(".tcRs").show();
					tiemr = setInterval(function(){
						$(".tcRs").hide();
					},3000);
					rs=1;
			});*/
		}
	</script>
</body>
</html>
<script>
	var flag=false

	$(document).ready(function () {
		var pid = '${pid }'
		var openid = '${openid }'
		var issue = '${issue }'
		if (pid != null){
		    $.ajax({
				url:'getAdviceData',
				data:{pid:pid},
				dataType:'json',
				success:function (data) {
					if (data.resultCode == 200){
					    var resCon = JSON.parse(data.data)

						$('.pa').text(resCon.title)
                        var img_banner = resCon.imgBanner.split(",")
                        var _html = ''
                        for (var i=0;i<img_banner.length;i++){
                            _html +=  '<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+img_banner[i]+'"></div></li>'
                        }
                        $('#jsxT').html(_html)

                        $(".sub").bind("click",function(){
                            if (flag){
                                $(".div1 p").text("请勿重复提交哦！");
                                $(".tcRs").show();
                                tiemr = setInterval(function(){
                                    $(".tcRs").hide();
                                },3000);
                                return;
							}
							flag = true
                         	$.ajax({
								url:'commit',
								data:{openid:openid,issue:issue,advice:$('#cont').val(),pid:pid},
								dataType:'json',
								success:function (data) {
									if(data.resultCode == 200){
                                        $(".div1 p").text("您的意见提交成功，非常感谢您的反馈！");
                                        $(".tcRs").show();
                                        tiemr = setInterval(function(){
                                            $(".tcRs").hide();
                                        },3000);
									}else{
									    if (data.message == 20000){
                                            $(".div1 p").text("请勿重复提交哦！");
                                            $(".tcRs").show();
                                            tiemr = setInterval(function(){
                                                $(".tcRs").hide();
                                            },3000);
										}else{
                                            $(".div1 p").text(data.message);
                                            $(".tcRs").show();
                                            tiemr = setInterval(function(){
                                                $(".tcRs").hide();
                                            },3000);
										}

									}
                                },
								error:function (res) {
                                    $(".div1 p").text("小伙伴们太热情了，服务器被挤爆拉！");
                                    $(".tcRs").show();
                                    tiemr = setInterval(function(){
                                        $(".tcRs").hide();
                                    },3000);
                                    flag = false
                                }
							})

                        });


					}else{

					}
                },
				error:function (res) {
                    $(".div1 p").text("小伙伴们太热情了，服务器被挤爆拉！");
                    $(".tcRs").show();
                    tiemr = setInterval(function(){
                        $(".tcRs").hide();
                    },3000);
                    flag = false
                }
			})
		}
    })
</script>