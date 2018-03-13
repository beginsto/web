window.onload = function(){
	  function stopBuble(ev) {
    if(ev&& ev.stopPropagation) {
      ev.stopPropagation();
    }
    else{
      window.event.cancelBubble=true;
    }
  }

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
                    $('.award').bind('click',function () {
                        console.log("ssssssss")
                        alert("您暂时还没有参与活动，快去参加活动吧！")
                    })

                }else{
                    $('.award').bind('click',function () {
                        console.log("ssss")
                        alert("你已获得"+isRaffle+"奖励");
                    })
                }

                $('.mask').hide();
                if(isBind == 'N'){
                    $("#start").bind("click",function(){
                        $(".hideH").show();
                        $(".login").show();
                    })
                    return;
                }
                //console.log("已绑定")



                if (hadAward == 'N'){
                    $("#start").bind("click",function(){
                        alert("本期奖品已领完...");
                    })
                    return;
                }
               // console.log("奖品有剩余")

                if(isRaffle != ''){
                    $("#start").bind("click",function(){
                        $(".rs img").attr("src","../images/star-sign/scorpio/lastTime.png");
                        $(".hideH").show();
                        $(".rs").show();
                        timer2 = setInterval(function(){
                            $(".hideH").hide();
                            $(".rs").hide();
                            clearInterval(timer2);
                        },3000);
                    })
                    return;
                }

               // console.log("本期未抽奖")



                if (isIssue == 'N' && isInvited == 'N'){
                    $("#start").bind("click",function(){
                        $(".rs img").attr("src","../images/star-sign/scorpio/lose.png");
                        $(".hideH").show();
                        $(".rs").show();
                        timer1 = setInterval(function(){
                            $(".hideH").hide();
                            $(".rs").hide();
                            clearInterval(timer1);
                        },3000);
                    })
                    return;
                }
               // console.log("满足抽奖条件")

                $("#start").bind("click",function(){
                    $.ajax({
                        url:'raffle',
                        type:'post',
                        data:{openid:$("#openid").val(),unionid:result.mobile,zodiac:result.zodiac,issue:result.issue,nickname:result.nick},
                        dataType:'json',
                        success:function (data) {
                            console.log(JSON.stringify(data));
                            if(data.resultCode == 200){
                                var dataR = ["1R","2R","3R","5R","100M","200M","300M","500M"];
                                var dataV = ["1元话费券","2元话费券","3元话费券","5元话费券","100M流量券","200M流量券","300M流量券","500M流量券"];
                                $(".gift img").attr("src","../images/star-sign/scorpio/"+dataR[data.message]+".png");
                                $(".hide").show();
                                $(".startGift").show();
                                gift();
                                $('.award').unbind().bind('click',function () {
                                    console.log("ssss")
                                    alert("你已获得"+dataV[data.message]+"奖励");
                                })
                            }else if(data.resultCode == 300){
                                $(".rs img").attr("src","../images/star-sign/scorpio/lastTime.png");
                                $(".hideH").show();
                                $(".rs").show();
                                timer2 = setInterval(function(){
                                    $(".hideH").hide();
                                    $(".rs").hide();
                                    clearInterval(timer2);
                                },3000);
                            }else{
                                alert(data.message);
                            }


                        }
                    })

                    /*if(isLogin ==1){//号码已经绑定
                        if(isTx == 1){//满足星座
                            if(isgift == 1){//已领奖

                            }else{//抽奖
                                var dataR = ["1R","2R","3R","5R","100M","200M","300M","500M"];
                                var st = myRandom();
                                var giftRs = dataR[st-1];
                                $(".gift img").attr("src","image/"+giftRs+".png");
                                $(".hide").show();
                                $(".startGift").show();
                                gift();
                                isgift = 1;
                            }
                        }else{//星座未满足

                        }
                    }else{//未绑定手机号码
                    }*/
                });


            }
        })
    })

	var isLogin = 0; 
	var isTx = 1;//是否天蝎座
	var isgift = 0;//领过奖励了么

	$("#noT").bind("click",function(){
		window.location.href = "forward?openid="+$("#openid").val();
	});
//活动规则
	var rs = 1;
	$(".ruleButton").bind("click",function(){
		if(rs==1){
			//$("#start").css("z-index","-1");
			//$("#noT").css("z-index","-1");
			//$(".hide").css("z-index","0");
			//$(".hide").show();
			$(".ruleX").animate({marginLeft:"0%"},"slow");
            timer=setInterval(function(){
                $("#start").css("z-index","-1");
                $("#noT").css("z-index","-1");
                $(".award").css("z-index","-1");
                clearInterval(timer);
            },300);

           //$(".hide").css("z-index","0");
			rs = 0;
		}else{
			$(".ruleX").animate({marginLeft:"87%"},"slow");
			timer=setInterval(function(){
				//$(".hide").hide();
				$("#start").css("z-index","10");
				$("#noT").css("z-index","10");
                $(".award").css("z-index","10");
				//$(".hide").css("z-index","13");
				clearInterval(timer);
			},500);
				
			rs = 1;
		}
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

/*立即绑定*/


}

function gift(){
    $('.knowI').hide();
    var s = 0;
    var l = 90;
    var r = 6;
    timer0 = setInterval(function(){
        r = r - 0.1;
        $(".turn").css("margin-top",r+"rem");
        if(r < 0.8){
            clearInterval(timer0);
            timer = setInterval(function(){
                s = s + 1;
                $(".wen").css("-webkit-transform","rotateY("+s+"deg)")
                $(".wen").css("transform","rotateY("+s+"deg)")
                if(s == 90){
                    clearInterval(timer);
                    $(".wen").css("display","none");
                    $(".gift").css("display","block");
                    timer2 = setInterval(function(){
                        l = l - 1;
                        $(".gift").css("-webkit-transform","rotateY("+l+"deg)")
                        $(".gift").css("transform","rotateY("+l+"deg)")
                        if( l == 0){
                            $('.knowI').show().bind("click",function(){
                                $(".hide").hide();
                                $(".startGift").hide();
                                isgift = 1;
                            });
                            clearInterval(timer2);
                        }
                    },10);
                }
            },10);
        }

    },20);
}

function myRandom(){
	var rm=Math.random();
	if(0< rm < 0.2 ) return 1;
	if(0.2 <= rm < 0.3) return 2;
	if(0.3 <= rm < 0.34) return 3;
	if(0.34 <= rm < 0.35) return 4;
	if(0.35 <= rm < 0.6) return 5;
	if(0.6 <= rm < 0.8) return 6;
	if(0.8 <= rm < 0.95) return 7;
	if(0.95 <= rm < 1) return 8;
}