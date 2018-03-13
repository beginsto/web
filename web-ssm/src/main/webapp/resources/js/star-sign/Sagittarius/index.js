window.onload = function(){
	function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
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
                      //  console.log("ssssssss")
                        alert("您暂时还没有参与活动，快去参加活动吧！")
                    })

                }else{
                    $('.award').bind('click',function () {
                      //  console.log("ssss")
                        alert("你已获得"+isRaffle+"奖励");
                    })
                }

                $('.mask').hide();
                if(isBind == 'N'){
                    $("#start").bind("click",function(){
                        $(".loginDiv").show();
                    })
                    return;
                }
                console.log("已绑定")



                if (hadAward == 'N'){
                    $("#start").bind("click",function(){
                        alert("本期奖品已领完...");
                    })
                    return;
                }
                 console.log("奖品有剩余")

                if(isRaffle != ''){
                    $("#start").bind("click",function(){
                        $(".rsGift img").attr("src","../images/star-sign/Sagittarius/hasGift.png");
                        $(".rsGift").show();
                    })
                    return;
                }

                 console.log("本期未抽奖")



                if (isIssue == 'N' && isInvited == 'N'){
                    $("#start").bind("click",function(){


                        $(".rs img").attr("src","../images/star-sign/Sagittarius/false.png");
                       // $(".rs span").addClass("close");
                       // $(".close").bind("click",function(){
                         //   $(".rs").hide();
                        //});
                        $(".rs").show();
                    })
                    return;
                }
                 console.log("满足抽奖条件")

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
                                $(".rsGift img").attr("src","../images/star-sign/Sagittarius/"+dataR[data.message]+".png");
                                $(".rsGift").show();
                                isGift = 1;

                              /*  var dataR = ["1R","2R","3R","5R","100M","200M","300M","500M"];
                                var dataV = ["1元话费券","2元话费券","3元话费券","5元话费券","100M流量券","200M流量券","300M流量券","500M流量券"];
                                $(".gift img").attr("src","../../images/star-sign/Sagittariuss/star-sign/scorpio/"+dataR[data.message]+".png");
                                $(".hide").show();
                                $(".startGift").show();
                                gift();
                                $('.award').unbind().bind('click',function () {
                                    console.log("ssss")
                                    alert("你已获得"+dataV[data.message]+"奖励");
                                })*/
                            }else if(data.resultCode == 300){
                                $(".rsGift img").attr("src","../images/star-sign/Sagittarius/hasGift.png");
                                $(".rsGift").show();
                            }else{
                                alert(data.message);
                            }


                        }
                    })


                });
            }
        })
    })

    $(".rule").bind("click",function(){
        $(".ruleDiv").show();
    });
    $(".closeRule").bind("click",function(){
        $(".ruleDiv").hide();
    });
    $(".close2").bind("click",function(){
        $(".rsGift").hide();
    });
    $(".noStar").bind("click",function(){
        window.location.href="forward?openid="+$("#openid").val();
    });
    $(".cha").bind("click",function(){
        $(".loginDiv").hide();
    });

    $('.rsGift img').bind('click',function () {
        $(".rsGift").hide();
    })


    $('.rs img').bind('click',function () {
        window.location.href="forward?openid="+$("#openid").val();
    })

    $('.login').bind('click',function () {
        window.location.href='http://jxyd.1860.cn/app/bindMobile/index?openid='+$('#openid').val();
    })

  	/*$(".rule").bind("click",function(){
  		$(".ruleDiv").show();
  	});
  	$(".closeRule").bind("click",function(){
  		$(".ruleDiv").hide();
  	});

  	$(".noStar").bind("click",function(){
        window.location.href = "forward?openid="+$("#openid").val();
  	});
    $(".cha").bind("click",function(){
      $(".loginDiv").hide();
    });

    $(".close").bind("click",function(){
        alert("close");
        $(".rs").hide();
    });
    $(".close2").bind("click",function(){
        alert("close2");
        $(".rsGift").hide();
    });*/
}

