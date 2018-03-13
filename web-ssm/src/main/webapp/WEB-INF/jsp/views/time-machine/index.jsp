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
    <script src="${ctx }/js/common/flexible.js"></script>
    <link href="${ctx }/css/time-machine/index.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx }/css/time-machine/swiper.min.css">
    <script type="text/javascript" src="${ctx }/js/common/Data/amount.js"></script>
    <style>
        .footerhide{display: none}
    </style>
    <title>我的2017</title>
</head>
<body>
<div class="swiper-container" id="swiper">
    <div class="swiper-wrapper">
        <div class="swiper-slide">
            <div class="slideOne slideClear">
                <div class="slideOneTu">
                </div>
                <p class="nameP">To:<span id="name">艾瑞莉娅</span></p>
                <div class="slideOneBg"></div>
                <div class="next"></div>
                <img class="shou" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/shou.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideTwo slideTwoAll slideClear">
                <div class="slideTwoTu"></div>
                <img class="tou" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/jz.png"/>
                <div class="pDiv">
                    <p class="p1">浙江移动手机营业厅2017年<br>总计被您翻牌了<span id="num" class="cheng">321</span>次</p>
                </div>
                <div class="pDiv2">
                    <p class="p2">通过手厅APP您总计办理了<span id="yw" class="cheng">21</span>笔业务<br>手厅精彩活动等你来发现!</p>
                </div>
                <div class="twoImages">
                    <img class="phone" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/phone.png"/>
                    <img class="left1" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/left1.png"/>
                    <img class="left2" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/left2.png"/>
                    <img class="left3" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/left3.png"/>
                    <img class="right1" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/right1.png"/>
                    <img class="right2" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/right2.png"/>
                    <img class="right3" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/right3.png"/>
                </div>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
            <div class="slideTwoFu slideTwoAll slideClear">
                <div class="slideTwoTu"></div>
                <img class="tou2" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/jzfu.png"/>
                <div class="pDiv">
                    <p class="p1">您暂时还未加入移动大本营哦！<br>可知错过了几个亿？！</p>
                </div>
                <div class="pDiv2">
                    <p class="p2">还不快快<span class="cheng">长按下方</span>二维码，<br><span class="cheng">下载</span>手机营业厅APP</p>
                </div>
                <img class="ewm" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/ewm.png"/>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideThree slideClear">
                <div class="slideTwoTu"></div>
                <img class="touth" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/2017.png"/>
                <p class="onePerson"><span class="sp1">您</span><span class="sp2">第</span><span class="sp3">一</span><span class="sp4">个</span><span class="sp5">想</span><span class="sp6">到</span><span class="sp7">的</span><span class="sp8">人</span></p>
                <p class="firstPhone"><span id="firstPhone" class="cheng">13578912345</span><span class="onePhone">，于是您给TA打了第一个电话，想必TA对您而言一定很重要。</span></p>
                <img class="onePhoneImg" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/onePhone.png"/>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideFour slideClear">
                <div class="slideTwoTu"></div>
                <div class="head4Div"></div>
                <div class="htou">&nbsp;据可靠消息透露，</div>
                <div class="hn">您已光顾周三加油站活动<span class="cheng" id="gg">34</span>次;<br>通过本次活动，您已获得<span class="cheng ggM" >200元</span>话费奖励</div>
                <div class="zhousanDiv">
                    <div class="zhousan"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/zheng4_01.png"/></div>
                    <img class="moreh" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/moreh.png"/>
                    <div class="other"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/zheng4-2_01.png"/></div>
                </div>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
            <div class="slideFourFu slideClear">
                <div class="slideTwoTu"></div>
                <div class="touFour"></div>
                <div class="fourN">
                    <p class="yhnum">已有<span id="sanNum" class="cheng">1873位</span>移动用户参加了<br>周三加油站活动哦！</p>
                    <p class="othernum">这么好的优惠活动，<br>当然不能缺了您！</p>
                </div>
                <div class="fourO">
                    <img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/tj.png"/>
                    <div class="fourO-N" id="zsw"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/fu4_01.png"></div>
                </div>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideFive slideClear">
                <div class="slideTwoTu"></div>
                <div class="touFi"></div>
                <div class="fiveFamly">
                    <p>您通过亲情网，总计<span class="cheng" id="tonghua">通话2132分钟</span><br>您的亲情网家人有<span class="cheng" id="perNum">34</span>个<br>相信您一定很爱您的家人<br>非常感谢您加入亲情网，<br>让沟通变得更亲密！</p>
                </div>
                <img class="jia" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/jia.png"/>
                <img class="footer-five" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/fu5_02.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
                <div id="footer-five"></div>
            </div>
            <div class="slideFiveFu slideClear">
                <div class="slideTwoTu"></div>
                <div class="touFi"></div>
                <img class="fiveFu" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/fivef.png"/>
                <div class="fiveO-N" id="qqw"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/fu5_02.png"/></div>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideSix slideClear">
                <div class="slideTwoTu"></div>
                <img class="touSix" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/five.png"/>
                <div class="sixCont">
                    <p>总计通话<span class="cheng" id="h">231</span>小时<span class="cheng" id="m">30</span>分<br>打败<span class="cheng" id="beat">79%</span>的移动用户<br>在全嘉兴排列<span class="cheng" id="rank">32</span>位</p>
                </div>
                <div class="canvasDiv">
                    <canvas id="process"></canvas>
                </div>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideSeven slideClear">
                <div class="slideTwoTu"></div>
                <img class="touSeven" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/today.png"/>
                <div class="sevenCont">
                    <p>是您使用移动宽带的<span class="cheng interNum" >第34天</span><br>自从使用了移动宽带，<br>每下载一部电影，都为您节省了1小时<br>每传输一次文件，都让对方好友为您的传输效率点个赞<br>每玩一次游戏，您都远远地甩开了敌人，连胜再也不是梦！</p>
                </div>
                <img class="sevenCom" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/com.png"/>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
            <div class="slideSevenFu slideClear">
                <div class="slideTwoTu"></div>
                <img class="touSevenFu" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/sevFu.png"/>
                <div class="sevA"><img src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/fu7_02.png"/></div>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slideNine slideClear">
                <div class="slideTwoTu"></div>
                <div class="touNine"></div>
                <div class="nineCont">
                    <p>2017年总计使用流量<span class="cheng" id="power">193213M</span><br>原来你就是组织上一直在寻找的<br><span id="dr" class="cheng">流量达人</span>你平均每天流量使用量为：<span class="cheng" id="eveP">12M</span><br>在全嘉兴排名<span class="cheng" id="powerRank">第392名</span></p>
                </div>
                <img class="ll" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/ll.png"/>
                <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
                <img class="godown" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/godown.png"/>
            </div>

        </div>
        <div class="swiper-slide">
            <div class="slideEight slideClear">
                <div class="slideTwoTu"></div>
                <img class="eightCont" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/eight.png"/>
                <div class="ownAll">
                    <p>To:<span class="cheng" id="name1">艾瑞莉娅</span><br>根据你在流量江湖的各种表现
                        <br>嘉兴移动官方鉴定你为</p>
                    <img class="chengHao" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/ydxxr.png"/>
                </div>
                <img class="jindan" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/eightBtn.png"/>
            </div>
            <img class="footer" src="http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/footer.png"/>
        </div>
    </div>
    <input type="hidden" id="openid" value="${openid }" />
    <!-- Add Pagination -->
    <div class="swiper-pagination" style="display: block;"></div>
</div>
</body>

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    window.location.href="http://jxyd.1860.cn/main/page/end/end.html"
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.0.2/js/swiper.min.js"></script>
<script src="${ctx }/js/time-machine/index_v201712281104.js"></script>

</html>