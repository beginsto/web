<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name = "format-detection" content = "telephone=no">
    <title>答题</title>
    <link href="${ctx }/css/answer/index.css" rel="stylesheet">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        var s = document.documentElement.clientWidth;
        document.documentElement.style.fontSize = s/25+ "px";
    </script>
    <script>
        var phone = '${phone }';
        var openid = '${openid }';
        //开始答题
        function beginAnswer(){
			if(phone == 'null' || phone == '')
				//$(".mask01").show();
                alert("号码未关联，请在公众号页面回复‘绑定’关联手机号码");
			else{
                if ('${data }' == 'Non-target')
                    alert("只针对特定用户开放，您当前关联号码为:"+phone+"，如需更换请在官微回复'绑定'");
                else
                    $('.youHui').show();
				/*$.ajax({
					type:'post',
					url:'validate.jsp',
					data:{openid:openid,phone:phone},
					dataType:'json',
					success:function(rs){
						//alert("题库更新中...预计11点结束，敬请谅解。")
						if(rs.result == 'success'){
// 							alert("抱歉...维护中...预计下午2点开放！");
							$('.youHui').show();
// 							alert("题库更新中...预计4点结束")
						}else if(rs.result == 'failed'){
							alert("阿哦，服务器开小差了~");
						}else if(rs.result == 'param-error'){
							alert("号码未关联，请在公众号页面回复‘绑定’关联手机号码");
						}else if(rs.result == 'not-fined'){
							alert("只针对特定用户开放，如有疑问，请在公众号页面回复‘绑定’查看当前关联的手机号码！");
						}
					}
				});*/
			}
        }
        //绑定提醒
        function bindTip(){
            $(".mask").hide();
            $(".mask01").show();
        }
        //立刻绑定
        function bindNow(){
        	$.ajax({
				type:'post',
				url:'validate.jsp',
				data:{openid:openid,phone:$("#mob").val()},
				dataType:'json',
				success:function(rs){
					//alert("题库更新中...预计11点结束，敬请谅解。")
					if(rs.result == 'success'){
//							alert("抱歉...维护中...预计下午2点开放！");
						$('#youHui1').attr('href','answer?userId=${userId }&platForm=zh');
						$('#youHui2').attr('href','answer?userId=${userId }&platForm=bus');
						$('#youHui3').attr('href','answer?userId=${userId }&platForm=gov');
						

						$('.youHui').show();
//							alert("题库更新中...预计4点结束")
					}else if(rs.result == 'failed'){
						alert("阿哦，服务器开小差了~");
					}else if(rs.result == 'param-error'){
						alert("号码未关联，请在公众号页面回复‘绑定’关联手机号码");
					}else if(rs.result == 'not-fined'){
						alert("只针对特定用户开放，请确认您的手机号码是否有误....");
					}
				}
			});
        	 $(".mask01").hide();
        }
        //获取验证码
        function getCode(){

        }
    </script>
<style>
.youHui {position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.5);display: none;}
.youHui .sec {position:absolute;top: 10rem;width: 90%;left: 5%;padding-bottom: 1.5rem;font-size: 1.4rem;background-color: white;}
.youHui .sec .p1 {color:white;text-align: center;background-color: #0087d0;padding: 0.5rem 0;}
.youHui .sec .ss {width: 90%;font-size: 0.9rem;color: black;padding: 0.4rem 5%; border-radius: 0.8rem;}
.youHui .sec .ss p:nth-child(1) {text-align: center;color:red;margin-bottom: 0.4rem;}
.youHui .sec #youHui1 {display: block;margin-top: 1.5rem;width: 80%;margin-left: 10%;border-radius: 0.8rem;border: 2px solid grey;}
.youHui .sec #youHui2 {display: block;margin-top: 1rem;width: 80%;margin-left: 10%;border-radius: 0.8rem;border: 2px solid grey;}
.youHui .sec #youHui3 {display: block;margin-top: 1rem;width: 80%;margin-left: 10%;border-radius: 0.8rem;border: 2px solid grey;}

</style>
</head>
<body>
    <div class="wrap">
        <img src="${ctx }/images/answer/index_bg.png">
        <div class="content">
            <div class="content_div">
                <img src="${ctx }/images/answer/tip.png">
                <p>各位同事，答题过程中请注意如下三点：</p>
                <p>1、每页题目全部答完后再点击提交，否则未答题目提交答案记录为空；</p>
                <p>2、若因故本页答题内容未提交，提前退出，则下次登录后将重新回答本页题目；</p>
                <p>3、每天可最多答100题。</p>
            </div>
            <p style="width:100%;text-align:center;color:#fff;margin-top:10px;">本期，您合计答题${count }道，正确数${countRig } </p>
            <div class="bottom">
                <a href="javascript:;" class="begin_answer" onclick="beginAnswer()">开始答题</a>
            </div>
        </div>
    </div>
    <div class="youHui">
		<div class="sec">
			<p class="p1">请选择类型</p>
			<a href="answer?userId=${userId }&platForm=bus" id="youHui1">
				<div class="ss" id="zc1">
					<p>市场卷</p>
					
				</div>
			</a>
			<a href="answer?userId=${userId }&platForm=zh" id="youHui2">
				<div class="ss" id="zc2">
					<p>综合卷</p>
				</div>
			</a>
			<a href="answer?userId=${userId }&platForm=gov" id="youHui3">
				<div class="ss" id="zc3">
					<p>政企知识</p>
				</div>
			</a>
		</div>
	</div>
    <!--绑定提醒-->
    <div class="mask">
        <div class="mask_wrap">
            <div class="tip">
                <p>亲，系统无法自动获取到您的手机号码。可能您暂未绑定手机号码，请点击下方按钮可直接前往绑定页面，成功绑定后您还可获赠<a href="javascript:;" class="money">5元话费</a>奖励~</p>
                <a href="javascript:;" class="now_bind" onclick="bindTip()">好的，立即绑定</a>
            </div>
        </div>
    </div>
    <!--绑定页面弹窗-->
    <div class="mask01">
        <div class="mask_wrap01">
            <div class="tip01">
                <input class="input01" id="mob" placeholder="请输入手机号码" maxlength="11" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;">
                <div>
<!--                     <input class="input02" placeholder="请输入验证码"> -->
<!--                     <a class="input03" href="javascript:;" onclick="getCode()">获取验证码</a> -->
                </div>
                <div class="bind_div">
                    <a href="javascript:;" class="now_bind01" onclick="bindNow()">立即答题</a>
                </div>
            </div>
        </div>
    </div>
    <!--绑定成功弹窗-->
    <div class="mask02">
        <div class="mask_wrap02">
            <div class="tip02">
                <p>绑定成功，3秒后为您跳转至活动页面</p>
            </div>
        </div>
    </div>
</body>
</html>