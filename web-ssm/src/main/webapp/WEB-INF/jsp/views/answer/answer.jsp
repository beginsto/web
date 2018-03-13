<%--
  Created by IntelliJ IDEA.
  User: begins
  Date: 17/8/1
  Time: 上午11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <meta name="msapplication-tap-highlight" content="no"/>
    <title>答题页</title>
    <link href="${ctx }/css/answer/index.css" rel="stylesheet">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        var s = document.documentElement.clientWidth;
        document.documentElement.style.fontSize = s/25+ "px";
    </script>
    <script>
        var isTrue;
        //开始答题
        function beginAnswer(){
            isTrue = 1;
            if(isTrue == 1){
                $(".mask03").show();
            }
        }

        //答题错误 弹窗提醒 点击确定进入下一题
        function confirm(){

        }
    </script>
</head>
<body  style="background:#5ce3e5">
<div class="main_answer">
    <img src="${ctx }/images/answer/index_bg.png">
    <form name="form1" id="form1" action="#" method="post">
        <div class="bottom2">
            <input type="hidden" name="userid" value="${userid }">
            <input type="hidden" name="quesid" value="${ques.id }">
            <input type="hidden" name="answerright" value="${ques.answerright }">
            <input type="hidden" name="businesstype" value="${ques.businesstype }" />
            <input type="hidden" name="answer" id="answer" />

            <input class="begin_answer2" style="border:none;margin-top:1rem;" type="submit" name="submit" value="提&nbsp;&nbsp;&nbsp;&nbsp;交">
            <!--                 <a href="javascript:;" class="begin_answer2" onclick="beginAnswer()">开始答题</a> -->
        </div>

    </form>
    <div class="content2">
        <div class="content2_div">
            <c:choose>
                <c:when test="${ques.id > 4000}">
                    <p>${ques.questype }：${ques.question }</p>
                </c:when>
                <c:otherwise>
                    <p>${ques.question }</p>
                </c:otherwise>
            </c:choose>
            <ul>
                <c:if test="${ques.answerA != null && !empty ques.answerA}">
                    <li>
                        <em>A</em>
                        <div class="li_div">
                            <a href="javascript:;" id="A">${ques.answerA }</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${ques.answerB != null && !empty ques.answerB }">
                    <li>
                        <em>B</em>
                        <div class="li_div">
                            <a href="javascript:;" id="B">${ques.answerB }</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${ques.answerC != null && !empty ques.answerC }">
                    <li>
                        <em>C</em>
                        <div class="li_div">
                            <a href="javascript:;" id="C">${ques.answerC }</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${ques.answerD != null && !empty ques.answerD }">
                    <li>
                        <em>D</em>
                        <div class="li_div">
                            <a href="javascript:;" id="D">${ques.answerD }</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${ques.answerE != null && !empty ques.answerE }">
                    <li>
                        <em>E</em>
                        <div class="li_div">
                            <a href="javascript:;" id="E">${ques.answerE }</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${ques.answerF != null && !empty ques.answerF }">
                    <li>
                        <em>E</em>
                        <div class="li_div">
                            <a href="javascript:;" id="F">${ques.answerF }</a>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="answer_tip">

        </div>
        <div class="mask03">
            <div class="mask03_div">
                <p></p>
                <div class="sure">
                    <a href="javascript:;" class="confirm" onclick="confirm()">确定</a>
                </div>
            </div>
        </div>
    </div>
</div>


<!--*******************添加部分**********************-->
<script type="text/javascript" src="${ctx }/js/common/jquery-form.js">
</script>
<script>
    $(document).ready(function(){
        var ra = '${ques.refrenceres }';
        if(ra == null || ra == '' || ra == 'null'){
            $('.answer_tip').hide();
        }else{
            $('.answer_tip').html("<p>"+ra+"</p>");
        }
    });
    $('.confirm').click(function(){
        window.location.reload();
    });
    $("#form1").click(function () {
        var options = {
            url:"confirm",
            dataType:"json",
            beforeSubmit:function(){
                if(form1.answer.value == ''){
                    alert("您还未选择答案...");
                    return false;
                }
            },
            success:function(data){
                if(data.code == '200'){
                    $('.mask03_div p').text('恭喜，您答对了（点击“确定”进入下一题）');
                    $('.mask03').show();
                }else if(data.code == '300'){
                    $('.mask03_div p').text(data.msg);
                    $('.mask03').show();
                }else if(data.code == '500'){
                    alert("服务器开小差了~");
                }else if(data.code == '303'){
                    alert("您今天的答题次数用完了,明天再来吧~");
                }
            }
        };
        $("#form1").ajaxForm(options);
    });

    $(".content2_div ul li").click(function(){
        var question_type = '${ques.questype }';
        var index=$(this).index();
        console.log(index); /*选中的序号*/
        if(question_type == '单选题'){
            $(this).addClass("active").siblings().removeClass("active");
            setValue();
        }else if(question_type == '多选题'){
            var className = $(this).attr('class');
            if(className == 'active')
                $(this).removeClass("active");
            else
                $(this).addClass("active");
            setValue();
        }else{
            alert("异常，请重新登陆...");
        }
    });

    function setValue (){

        var li = $('.content2_div ul li');
        var answerValue = '';
        var answerArr = ['A','B','C','D','E','F'];
        for(var i=0; i< li.length;i++){
            if(li.eq(i).attr('class') == 'active')
                answerValue += answerArr[i];
        }

        //console.log(answerValue);
        $("#answer").val(answerValue);

    }

</script>
<!--*******************添加部分**********************-->

</body>
</html>