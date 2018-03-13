window.onload = function(){
    function stopBuble(ev) {
        if(ev&& ev.stopPropagation) {
            ev.stopPropagation();
        }
        else{
            window.event.cancelBubble=true;
        }
    }
    var l=1;//改
    $(".ruleBtn").bind("click",function(){
        var bwidth=$(".rule").position().left;
        if(l>1){
            $(".big").css("background","rgba(0,0,0,0)");
            $(".bigOne").animate({left:"5.29rem"},"slow");//改
            $('#body').css("overflow","auto");
            l=0;//改
        }else{
            $(".bigOne").animate({left:".29rem",borderWidth:"0px"},"slow");//改
            $(".big").css("background","rgba(0,0,0,0.8)");
            $('#body').css("overflow","hidden");
            l=2;//改
        }
    });
    $(function () {
        $.ajax({
            type:'post',
            url:'getInfo',
            data:{openid:$('#openid').val(), mid:$('#mid').val()},
            dataType:'json',
            success:function (data) {
                $('.mask1').hide();
                console.log(JSON.stringify(data));
                if (data.data == null)
                    return;

                var result = JSON.parse(data.data);
                $("#nickName").val(result.nick);
                var isLogin = result.isLogin;
                var isBind = result.isBind;
                var hadAward = result.hadAward;
                var isRaffle = result.isRaffle;
                var isIssue = result.isIssue;
                var isInvited = result.isInvited;
                var isLocal = result.isLocal;

                if(isBind == 'N'){
                    $('.yanzheng').show();
                    return ;
                }

                if (isIssue == 'N' && isInvited=='N'){
                    $(".gift span").on("click",function(){
                        $(".noTpz").show();
                        $('#body').css("overflow","hidden");
                        timer = setInterval(function(){
                            $('.noTpz').hide();
                            $('#body').css("overflow","auto");
                            clearInterval(timer);
                        },3000);
                    })
                    return;
                }

                if (isRaffle == 'Y'){
                    $(".gift span").on("click",function(){
                        $('.nog').show();
                        $('.nogift').show();
                        $('#body').css("overflow","hidden");
                        timer = setInterval(function(){
                            $('.nog').hide();
                            $('.nogift').hide();
                            $('#body').css("overflow","auto");
                            clearInterval(timer);
                        },3000);
                    })
                    return;

                }

                if(hadAward == 0){
                    $(".gift span").on("click",function(){
                        $(".noTpz").show();
                        $('#body').css("overflow","hidden");
                        timer = setInterval(function(){
                            $('.noTpz').hide();
                            $('#body').css("overflow","auto");
                            clearInterval(timer);
                        },3000);
                    })
                    return;
                }

                if (isLocal == 'N'){
                    $(".gift span").on("click",function(){
                        alert("您绑定的号码为非嘉兴号段，小嘉也很无奈...");

                    })
                    return;
                }
                if (hadAward == 1) {
                    $('#mFive').bind('click',function () {
                        raffle(1,$('#openid').val(),result.mobile,result.zodiac,result.issue,result.nick);
                    })
                    $('#mSix').bind('click',function () {
                        $('.nog').show();
                        $('.nogift').show();
                        $('#body').css("overflow","hidden");
                        timer = setInterval(function(){
                            $('.nog').hide();
                            $('.nogift').hide();
                            $('#body').css("overflow","auto");
                            clearInterval(timer);
                        },3000);
                    })
                    return;
                }

                if (hadAward == 2){
                    $('#mFive').bind('click',function () {
                        $('.nog').show();
                        $('.nogift').show();
                        $('#body').css("overflow","hidden");
                        timer = setInterval(function(){
                            $('.nog').hide();
                            $('.nogift').hide();
                            $('#body').css("overflow","auto");
                            clearInterval(timer);
                        },3000);
                    })
                    $('#mSix').bind('click',function () {
                        raffle(2,$('#openid').val(),result.mobile,result.zodiac,result.issue,result.nick);
                    })
                    return;
                }

                if (hadAward == 3){
                    $('#mFive').bind('click',function () {
                        raffle(1,$('#openid').val(),result.mobile,result.zodiac,result.issue,result.nick);
                    })
                    $('#mSix').bind('click',function () {
                        raffle(2,$('#openid').val(),result.mobile,result.zodiac,result.issue,result.nick);
                    })
                    return;
                }



            }
        })
    })

    var flag = true;
    function raffle(type,openid,mobile,zodiac,issue,nick) {
      if (flag){
        flag = false;
          $.ajax({
              url:'raffle',
              type:'post',
              data:{raffleType:type,openid:openid,unionid:mobile,zodiac:zodiac,issue:issue,nickname:nick},
              dataType:'json',
              success:function (data) {flag = true;
                  var award = data.message;

                  if(award == 1){
                      $('.gift1').css('display','block');
                      $("#giftall").attr('src','../images/star-sign/libra/M5.png');
                  }else if (award == 2){
                      $('.gift1').css('display','block');
                      $("#giftall").attr('src','../images/star-sign/libra/M6.png');
                  }

                  if (award == 300){
                      $('.nog').show();
                      $('.nogift').show();
                      $('#body').css("overflow","hidden");
                      timer = setInterval(function(){
                          $('.nog').hide();
                          $('.nogift').hide();
                          $('#body').css("overflow","auto");
                          clearInterval(timer);
                      },3000);
                  }

                  if( award == 404 || award == 500){
                      alert("小嘉身体不适,暂时不能为您服务了...")
                  }
                  if (award == 405){
                      alert("小嘉未能获取您的星座信息，请尝试刷新页面...");
                  }
              },
              error:function (res) {
                  flag = true;
              }
          })
      }

    }
  /*var sessionId = 1;//验证是否登录
  var getTime = 1;//是否抽奖了
  var isNo = 1 //是否天枰座
  $(".gift span").on("click",function(){
    var rs = $(this).attr("class");
    if(sessionId==1){
      if(isNo==1){
      if(getTime==1){
        $('.gift1').css('display','block');
        $("#giftall").attr('src','../images/star-sign/libra/'+rs+'.png');
        getTime=0;
      }else{
        $('.nog').show();
        $('.nogift').show();
        timer = setInterval(function(){
          $('.nog').hide();
          $('.nogift').hide();
          clearInterval(timer);
        },3000);
      }
    }else{
      $(".noTpz").show();
      timer = setInterval(function(){
          $('.noTpz').hide();
          clearInterval(timer);
        },3000);
    }
    }else{
     $('.yanzheng').show();
   }
 })*/


//奖品关闭
$(".closeGift").bind("click",function(){
  $(".gift1").hide();
});
$("#footer").bind("click",function(){
  window.location.href = 'forward?openid='+$("#openid").val()+'&nickName='+$("#nickName").val();
});
}
