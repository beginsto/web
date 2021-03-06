window.onload = function(){
	  function stopBuble(ev) {
    if(ev&& ev.stopPropagation) {
      ev.stopPropagation();
    }
    else{
      window.event.cancelBubble=true;
    }
  }
  var isLogin = 0;//是否登录了
  var isGetGift = 0;//是否抽过奖了
  var isMJZ = 0;//是否是摩羯座
  var friendMJZ = 0;//朋友帮忙验证的摩羯座
  var gift = 5//如果已经抽过了的奖品

    var _btn = $(".btn")


    $(function () {
        $.ajax({
            type:'post',
            url:'getInfo',
            data:{openid:$('#openid').val(), mid:$('#mid').val()},
            dataType:'json',
            success:function (data) {
                //console.log(JSON.stringify(data));
                var result = JSON.parse(data.data);
                $("#nickName").val(result.nick);
                var isLogin = result.isLogin;
                var isBind = result.isBind;
                var hadAward = result.hadAward;
                var isRaffle = result.isRaffle;
                var isIssue = result.isIssue;
                var isInvited = result.isInvited;
                var isLocal = result.isLocal;
                if(isRaffle == ''){
                    $(".context-2 img").css("opacity","0");
                    var str = "<p class='noGiftP'>您还没有抽奖哦！</p>";
                    $(".context-2").append(str);

                }else{
                    var str;
                    if(isRaffle.indexOf("话") > -1){
                        str = "<p class='rGiftP'><span class='rGiftSpan'>"+isRaffle.substr(0,isRaffle.indexOf('元'))+"</span>元</p>";
                        $(".context-2 img").attr("src","../images/star-sign/Capricornus/hf.png");
                    }else{
                        str = "<p class='mGiftP'><span class='mGiftSpan'>"+isRaffle.substr(0,isRaffle.indexOf('M'))+"</span>M</p>";
                        $(".context-2 img").attr("src","../images/star-sign/Capricornus/ll.png");
                    }
                    $(".context-2 img").css("opacity","1");
                    $(".context-2").append(str);
                }

                $('.mask').hide();


                if(isBind == 'N'){
                    _btn.bind("click",function(){
                        $(".loginDiv").show();
                    })
                    return;
                }
               // console.log("已绑定")

                if (hadAward == 'N'){
                    _btn.bind("click",function(){
                        alert("本期奖品已领完...");
                    })
                    return;
                }
               // console.log("奖品有剩余")

                if(isRaffle != ''){
                    _btn.bind("click",function(){
                        $(".isGiftDiv").show();
                    })
                    return;
                }
            //    console.log("本期未抽奖")

                if (isIssue == 'N' && isInvited == 'N'){
                    _btn.bind("click",function(){
                        $(".falseDiv").show();
                    })
                    return;
                }
                //console.log("满足抽奖条件")

                _btn.bind("click",function(){
                    if(isGetGift == 1){
                        $(".isGiftDiv").show();
                    }else{
                        $.ajax({
                            url:'raffle',
                            type:'post',
                            data:{openid:$("#openid").val(),unionid:result.mobile,zodiac:result.zodiac,issue:result.issue,nickname:result.nick},
                            dataType:'json',
                            success:function (data) {
                                console.log(JSON.stringify(data));
                                if(data.resultCode == 200){

                                    raffle(data.message)

                                }else if(data.resultCode == 300){
                                    $(".isGiftDiv").show();
                                }else{
                                    alert(data.message);
                                }
                            }
                        })
                    }
                });
            }
        })
    })

    
    function raffle(index) {
        var giftArray = ["1","2","3","5","100","200","300","500"];//奖品
        var degArray = ["322","291","255","220","174","144","101","55"];//奖品角度
        var str

        if(index > 3){
            str = "<img src='../images/star-sign/Capricornus/ll.png' style='opacity:1'> <p class='mGiftP'><span class='mGiftSpan'>"+giftArray[index]+"</span>M</p>";
            $(".context-2 img").attr("src","../images/star-sign/Capricornus/ll.png");
        }else{
            str = "<img src='../images/star-sign/Capricornus/hf.png' style='opacity:1'> <p class='rGiftP'><span class='rGiftSpan'>"+giftArray[index]+"</span>元</p>";

        }
        $(".context-2").html(str);
        $(".context-2 img").css("opacity","1");


        gift = giftArray[index];
        var intDeg = degArray[index];
        var i = 0;
        timer0 = setInterval(function(){
            i = i+5;
            $(".zhiZhenDiv").css("transform","rotate("+i+"deg)");
            $(".zhiZhenDiv").css("-ms-transform","rotate("+i+"deg)");
            $(".zhiZhenDiv").css("-webkit-transform","rotate("+i+"deg)");
            if (i==1080) {
                clearInterval(timer0);
                i = 0;
                timer1 = setInterval(function(){
                    i = i+3;
                    $(".zhiZhenDiv").css("transform","rotate("+i+"deg)");
                    $(".zhiZhenDiv").css("-ms-transform","rotate("+i+"deg)");
                    $(".zhiZhenDiv").css("-webkit-transform","rotate("+i+"deg)");
                    if(i==720){
                        clearInterval(timer1);
                        i = 0;
                        timer2 = setInterval(function(){
                            i = i+2;
                            $(".zhiZhenDiv").css("transform","rotate("+i+"deg)");
                            $(".zhiZhenDiv").css("-ms-transform","rotate("+i+"deg)");
                            $(".zhiZhenDiv").css("-webkit-transform","rotate("+i+"deg)");
                            if(i==360){
                                clearInterval(timer2);
                                i = 0;
                                timer3 = setInterval(function(){
                                    i = i+1;
                                    $(".zhiZhenDiv").css("transform","rotate("+i+"deg)");
                                    $(".zhiZhenDiv").css("-ms-transform","rotate("+i+"deg)");
                                    $(".zhiZhenDiv").css("-webkit-transform","rotate("+i+"deg)");
                                    if(i==intDeg){
                                        clearInterval(timer3);
                                        if(gift>50){
                                            $(".giftContext span").text(gift+"M流量券");
                                        }else{
                                            $(".giftContext span").text(gift+"元话费券");
                                        }
                                        timer4=setInterval(function(){
                                            $(".getGiftDiv").show();
                                            clearInterval(timer4);
                                        },500);
                                        isLogin=1;
                                        isGetGift=1;
                                    }
                                },1);
                            }
                        },1);
                    }
                },1);
            }
        },1);
    }
    
	$(".closeThis").bind("click",function(){
		$(".isGiftDiv").hide();  
	});
	$(".closeGift").bind("click",function(){
		$(".getGiftDiv").hide();  
	});

    $(".myGiftBtn").bind("click",function(){

        $(".myGiftDiv").show();
    })
//礼物弹窗
	/*$(".myGiftBtn").bind("click",function(){
		if (isLogin==1) {
			if (isMJZ==1||friendMJZ==1) {
				if (isGetGift==1) {
					var str;
					if(gift<50){
						str = "<p class='rGiftP'><span class='rGiftSpan'>"+gift+"</span>元</p>";
						$(".context-2 img").attr("src","image/hf.png");
					}else{
						str = "<p class='mGiftP'><span class='mGiftSpan'>"+gift+"</span>M</p>";
						$(".context-2 img").attr("src","image/ll.png");
					}
					$(".context-2 img").css("opacity","1");
					$(".context-2").append(str);
					$(".myGiftDiv").show();
				}else{
					$(".context-2 img").css("opacity","0");
					var str = "<p class='noGiftP'>您还没有抽奖哦！</p>";
					$(".context-2").append(str);
					$(".myGiftDiv").show();
				}
			}else{
				$(".myGiftDiv").hide();
				$(".falseDiv").show();
			}
		}else{
			$(".context-2 img").css("opacity","0");
			var str = "<p class='noGiftP'>你还没有登录哦！<a class='dl'>登录</a></p>";
			$(".context-2").append(str);
			$(".myGiftDiv").show();
			$(".dl").on("click",function(){
				$(".context-2 p").remove();
				$(".myGiftDiv").hide();
				$(".loginDiv").show();
			});
		}
	});*/
	$(".closeMyGift").bind("click",function(){
		$(".context-2 p").remove();
		$(".myGiftDiv").hide();  
	});
	$(".closeFalse").bind("click",function(){
		$(".falseDiv").hide();
	});
//礼物结束
//不是摩羯座

    $('.rs img').bind('click',function () {
        window.location.href="forward?openid="+$("#openid").val();
    })

    $('.login').bind('click',function () {
        window.location.href='http://jxyd.1860.cn/app/bindMobile/index?openid='+$('#openid').val();
    })

	$(".iNoBtn").bind("click",function(){
        window.location.href="forward?openid="+$("#openid").val();
	});
//活动规则
	$(".ruleBtn").bind("click",function(){
		$(".ruleDiv").show();
	});
	$(".closeRule").bind("click",function(){
		$(".ruleDiv").hide();
	});
//活动规则结束
//登录
function toDouble(num) {
  if (num < 10) {
    return '0' + num;
  }
  else {
    return '' + num;
  }
}
/*var oBtn = document.getElementById('code');
var set = false;
oBtn.onclick = function (ev) {
  if (set) {
    return;
  }
  set = true;
  var num = 60;
  oBtn.innerHTML = num + 'S后重新获取';
  timer = setInterval(function () {
    num--;
    oBtn.innerHTML = toDouble(num) + 'S后重新获取';
    if (num == 0) {
      clearInterval(timer);
      oBtn.innerHTML = '获取验证码';
    }
  }, 1000);
}
$('.login').click(function(ev){
	stopBuble(ev);
  var tel=$("input[name='tel']").val();//电话
  var num=$("input[name='num']").val();//验证码
  var r = /^\+?[1-9][0-9]*$/;
  var tr=r.test(tel);
  var tn=r.test(num);
  if(tel!=null && num != null && tr && tn){
  			//submit
			$(".loginDiv").hide();
			$(".successDiv").show();
			var m = 3;
			timerL = setInterval(function(){
				m--;
				$("#mm").text(m+"秒后");
				if(m==0){
					clearInterval(timerL);
					$(".successDiv").hide();
				}	
			},1000);
			isLogin = 1;//登陆成功
			isMJZ = 1;//是否是摩羯座
			friendMJZ = 1;//不是摩羯座朋友帮忙验证了
			isGetGift = 0;//是否抽奖了
			gift = 5;//如果抽奖了奖品是什么
		}else{
			alert("请输入手机号码或者验证码");
		}
	});*/
}
function myRandom(){
	var rs = Math.random();
	if(0<=rs<0.2) return 0; 
	if(0.2<=rs<0.3) return 1;
	if(0.3<=rs<0.34) return 2;
	if(0.34<=rs<0.35) return 3;
	if(0.35<=rs<0.6) return 4;
	if(0.6<=rs<0.8) return 5;
	if(0.8<=rs<0.95) return 6;
	if(0.95<=rs<1) return 7;
}


	