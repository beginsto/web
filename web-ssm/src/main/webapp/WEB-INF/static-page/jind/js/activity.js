(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

var openid = $.getUrlParam('openid');
var isGift = 0;//是否抽过奖了
window.onload = function () {

    $.ajax({
        url:'../../jind/queryGift',
        dataType:'json',
        success:function (data) {
            if (data.resultCode == 20000){
                if (data.data == null || data.data == 'null' || data.data == ''){

				}else{
                    var con = JSON.parse(data.data)
                    $('.page-gift p').text(con.award)
                    isGift=1
				}


            }else{
                alert("认证过期，请返回首页重新登陆");
            }

        },
        error:function () {

        }
    })

  /*  timer = setInterval(function () {
        $.ajax({
            url:'../../jind/raffle',
            data:{openid:openid},
            dataType:'json',
            success:function (data) {
            	if (data.data == null){
					console.log("抽奖异常")
				}else{
                    console.log(data)
				}

            },
			error:function () {
				console.log("ajax异常");
            }
        })
    },80)*/




	$(".right").bind("click",function(){
		var midP = $(".mid-t p").text();
		var arr = new Array(); 
		if(midP==1){
			arr[0] = "jd1";
			arr[1] = "jd2";
			arr[2] = "jd3";
			arr[3] = "jd4";
			arr[4] = "jd5";
		}
		if(midP==2){
			arr[0] = "jd2";
			arr[1] = "jd3";
			arr[2] = "jd4";
			arr[3] = "jd5";
			arr[4] = "jd1";
		}
		if(midP==3){
			arr[0] = "jd3";
			arr[1] = "jd4";
			arr[2] = "jd5";
			arr[3] = "jd1";
			arr[4] = "jd2";
		}
		if(midP==4){
			arr[0] = "jd4";
			arr[1] = "jd5";
			arr[2] = "jd1";
			arr[3] = "jd2";
			arr[4] = "jd3";
		}
		if(midP==5){
			arr[0] = "jd5";
			arr[1] = "jd1";
			arr[2] = "jd2";
			arr[3] = "jd3";
			arr[4] = "jd4";
		}
		setTimeout(function(){
			$("."+arr[0]).animate({width:"30%",marginLeft:"70%",marginTop:"10.5rem"},50);
			$("."+arr[0]).removeClass("mid-t").addClass("zhong-t");
			$("."+arr[0]+" img").attr("src","image/yl.png");
		},10);
		setTimeout(function(){
			$("."+arr[1]).animate({width:"25%",marginLeft:"56%",marginTop:"11rem"},50);
			$("."+arr[1]).removeClass("zhong-t").addClass("hou-t");
			$("."+arr[1]+" img").attr("src","image/yl.png");
		},10);
		setTimeout(function(){
			$("."+arr[2]).animate({width:"25%",marginLeft:"18.5%",marginTop:"11rem"},50);
			$("."+arr[2]).removeClass("hou-t").addClass("hou-t");
			$("."+arr[2]+" img").attr("src","image/zl.png");
		},10);
		setTimeout(function(){
			$("."+arr[3]).animate({width:"30%",marginLeft:"0%",marginTop:"10.5rem"},50);
			$("."+arr[3]).removeClass("hou-t").addClass("zhong-t");
			$("."+arr[3]+" img").attr("src","image/zl.png");
		},10);
		setTimeout(function(){
			$("."+arr[4]).animate({width:"35%",marginLeft:"32.5%",marginTop:"10.2rem"},50);
			$("."+arr[4]).removeClass("zhong-t").addClass("mid-t");
			$("."+arr[4]+" img").attr("src","image/yl.png");
		},10);	
	});
	$(".left").bind("click",function(){
		var midP = $(".mid-t p").text();
		var arr = new Array(); 
		if(midP==1){
			arr[0] = "jd1";
			arr[1] = "jd2";
			arr[2] = "jd3";
			arr[3] = "jd4";
			arr[4] = "jd5";
		}
		if(midP==2){
			arr[0] = "jd2";
			arr[1] = "jd3";
			arr[2] = "jd4";
			arr[3] = "jd5";
			arr[4] = "jd1";
		}
		if(midP==3){
			arr[0] = "jd3";
			arr[1] = "jd4";
			arr[2] = "jd5";
			arr[3] = "jd1";
			arr[4] = "jd2";
		}
		if(midP==4){
			arr[0] = "jd4";
			arr[1] = "jd5";
			arr[2] = "jd1";
			arr[3] = "jd2";
			arr[4] = "jd3";
		}
		if(midP==5){
			arr[0] = "jd5";
			arr[1] = "jd1";
			arr[2] = "jd2";
			arr[3] = "jd3";
			arr[4] = "jd4";
		}
		setTimeout(function(){
			$("."+arr[0]).animate({width:"30%",marginLeft:"0%",marginTop:"10.5rem"},50);
			$("."+arr[0]).removeClass("mid-t").addClass("zhong-t");
			$("."+arr[0]+" img").attr("src","image/zl.png");
		},10);
		setTimeout(function(){
			$("."+arr[1]).animate({width:"35%",marginLeft:"32.5%",marginTop:"10.2rem"},50);
			$("."+arr[1]).removeClass("zhong-t").addClass("mid-t");
			$("."+arr[1]+" img").attr("src","image/yl.png");
		},10);
		setTimeout(function(){
			$("."+arr[2]).animate({width:"30%",marginLeft:"70%",marginTop:"10.5rem"},50);
			$("."+arr[2]).removeClass("hou-t").addClass("zhong-t");
			$("."+arr[2]+" img").attr("src","image/yl.png");
		},10);
		setTimeout(function(){
			$("."+arr[3]).animate({width:"25%",marginLeft:"56%",marginTop:"11rem"},50);
			$("."+arr[3]).removeClass("hou-t").addClass("hou-t");
			$("."+arr[3]+" img").attr("src","image/yl.png");
		},10);
		setTimeout(function(){
			$("."+arr[4]).animate({width:"25%",marginLeft:"18.5%",marginTop:"11rem"},50);
			$("."+arr[4]).removeClass("zhong-t").addClass("hou-t");
			$("."+arr[4]+" img").attr("src","image/zl.png");
		},10);	
	});
	$(".za").bind("click",function(){
		if(isGift==1){
			$(".lose").show();
		}else{
			var deg = 0;
			timer = setInterval(function(){
				deg++;
				$(".chui").css("transform","rotate(-"+deg+"deg)");
				$(".chui").css("-ms-transform","rotate(-"+deg+"deg)");
				$(".chui").css("-webkit-transform","rotate(-"+deg+"deg)");
				if(deg==40){
					clearInterval(timer);
					timer2 = setInterval(function(){
						deg--;
						$(".chui").css("transform","rotate(-"+deg+"deg)");
						$(".chui").css("-ms-transform","rotate(-"+deg+"deg)");
						$(".chui").css("-webkit-transform","rotate(-"+deg+"deg)");
						if(deg==0){
							clearInterval(timer2);
                            $.ajax({
                                url:'../../jind/raffle',
                                data:{openid:openid},
                                dataType:'json',
                                success:function (data) {
                                	if(data.resultCode == 200){
                                        var dataR = ["500M","1G","2G","2R","5R","10R","50R","188R"];
                                        window.location.href = "gift.html?gift="+dataR[data.message]+"&openid="+openid;
									}else{
                                		alert(data.message)
									}

                                },
                                error:function () {
                                    console.log("小伙伴们太热情了，活动被挤爆拉...");
                                }
                            })

						}
					},5);
				}
			},10);
		}
	});
	$(".rule").bind("click",function(){
		$(".ruleDiv").show();
	});
	$(".closeI").bind("click",function(){
		$(".lose").hide();
	});
	$(".ruleTurn").bind("click",function(){
		$(".ruleTurn").attr("src","image/ruleTurnR.png");
		$(".giftTurn").attr("src","image/giftTurnH.png");
		$(".page-rule").show();
		$(".page-gift").hide();
	});
	$(".giftTurn").bind("click",function(){
		$(".ruleTurn").attr("src","image/ruleTurnH.png");
		$(".giftTurn").attr("src","image/giftTurnR.png");
		$(".page-rule").hide();
		$(".page-gift").show();
	});
	$(".closeR").bind("click",function(){
		$(".ruleDiv").hide();
		$(".ruleTurn").attr("src","image/ruleTurnR.png");
		$(".giftTurn").attr("src","image/giftTurnH.png");
		$(".page-rule").show();
		$(".page-gift").hide();
	});
	$(".back").bind("click",function(){
        window.location.href = "http://jxyd.1860.cn/main/jind/index?openid="+openid+"&platForm=mp";
      });
	 $(".closeFor").bind("click",function(){
        $(".forwardDiv").hide();
      });
      $(".forw").bind("click",function(){
        $(".forwardDiv").show();
      });
	
}

function myRandom(){
  	var rm=Math.random();
	if(0.00000 <= rm < 0.00010 ) return 1;
	if(0.00010 <= rm < 0.02125) return 2;
	if(0.02125 <= rm < 0.08875) return 3;
	if(0.08875 <= rm < 0.25750) return 4;
	if(0.25750 <= rm < 0.53875) return 5;
	if(0.53875 <= rm < 0.55000) return 6;
	if(0.55000 <= rm < 0.71875) return 7;
	if(0.71875 <= rm <= 1.00000) return 8;
}