(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery)

var openid = $.getUrlParam('openid')
var isLogin = 0;//是否登录了
var rs = 1;//1目标用户 0非目标用户
var className = "context";
if(openid == undefined){

    openid= new Date().getTime()
    //window.location.href="http://www.baidu.com"
}


$(document).ready(function () {


    $.ajax({
        url:'../../qd/getInfo',
        data:{openid:openid},
        dataType:'json',
        success:function (data) {
            if (data.resultCode == 20000 ){
                if (data.message != undefined){
                    className = "isLogin";
                    $('.p2').text(data.message.substr(0,3)+'****'+data.message.substr(7))
                    $(".isLogin").show();
                    $(".login").bind("click",function(){
                        window.location.href = "active.html?num="+data.message;
                    });
                }else{
                    $('.context').show();
                    $(".login").bind("click",function(){
                        var tel=$("input[name='tel']").val();//电话
                        var num=$("input[name='num']").val();//验证码
                        var r = /^\+?[1-9][0-9]*$/;
                        var tr=r.test(tel);
                        var tn=r.test(num);

                         if (num == validate_code){
                            $.ajax({
                                url:'../../qd/validate',
                                data:{openid:openid,num:phone},
                                dataType:'json',
                                success:function (data) {
                                    if(data.resultCode == 20000){
                                        if(data.message == 'success'){
                                            window.location.href = "active.html?num="+phone;
                                        }else{
                                            $(".rs").css("opacity","1");
                                            $(".rs").show();
                                        }
                                    }else{
                                        alert("小伙伴们太热情了，活动被挤爆拉...")
                                    }
                                },
                                error:function () {

                                }

                            })
                        }else{
                             alert("请重新输入手机号码或者验证码");
                         }
                    });
                }
            }else{

            }

            var swiper = new Swiper('.swiper-container', {
                direction: 'vertical',
                slidesPerView :'auto',
                on :{
                    init:function(){
                        slideZero();
                    },
                    slideChangeTransitionEnd: function(){
                        var activeIndex = this.activeIndex;
                        if(activeIndex==0){
                            slideZero();
                        }
                        if(activeIndex==1){
                            slideOne(className);
                            $('.context').show();
                         //   console.log(1)
                        }
                    },
                    slideChangeTransitionStart: function(){
                        $(".slideClear *").removeAttr("style");
                        $(".slideClear .godown").hide();
                      ///  console.log(2)
                        //clearInterval(timerA);
                    },
                },
            });
            $(".loading").hide();
        },
        error:function () {
            
        }
    })




    var phone,validate_code
    function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }
    var oBtn = document.getElementById('code');
    var set = false;
    oBtn.onclick = function (ev) {
        if (set) {
            return;
        }
        phone=$("input[name='tel']").val();
        if(phone==""){
            alert("请输入手机号码");
            return;
        }
        set = true;
        $.ajax({
            url:'../../sms/sendMsg',
            data:{mobile:phone},
            dataType:'json',
            success:function (data) {

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

                if (data.resultCode == 200){
                    validate_code = data.message;
                }else{
                    alert(data.message)
                }
            }
        })


    }




})

/*window.addEventListener('load',function(){

if(isLogin==1){
  if(rs==1){
    className = "isLogin";
    $(".isLogin").show();
    $(".login").bind("click",function(){
      window.location.href = "active.html";
    });
  }else{
    className = "rs";
    $(".rs").show();
  }
}else{
  className = "context";
  $(".context").show();
  $(".login").bind("click",function(){
        var tel=$("input[name='tel']").val();//电话
        var num=$("input[name='num']").val();//验证码
        var r = /^\+?[1-9][0-9]*$/;
        var tr=r.test(tel);
        var tn=r.test(num);
        if(tel!="" && num != "" && tr && tn){
          rs = 1;//登录返回结果
        if(rs==1){
          window.location.href = "active.html";
        }else{
          $(".rs").css("opacity","1");
          $(".rs").show();
        }
      }else{
        alert("请重新输入手机号码或者验证码或者中奖码");
      }
    });
}


function toDouble(num) {
      if (num < 10) {
        return '0' + num;
      }
      else {
        return '' + num;
      }
    }
    var oBtn = document.getElementById('code');
    var set = false;
    oBtn.onclick = function (ev) {
      if (set) {
        return;
      }
      tel=$("input[name='tel']").val();
      if(tel==""){
        alert("请输入手机号码");
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


});*/

function slideOne(arg){
    timer2 = setInterval(function(){
      $(".slideOne .slideOneBg").show();
      $(".slideOne .slideOneBg").animate({backgroundSize:"75%"},200,function(){
        $(".slideOne .slideOneBg").animate({backgroundSize:"65%"},100,function(){
          $(".slideOne .slideOneBg").animate({backgroundSize:"75%"},100,function(){

          })
        })
      })
      clearInterval(timer2);
    },100);
    timer3 = setInterval(function(){
      $(".slideOne .next").show();
      $(".slideOne .next").animate({backgroundSize:"90%"},200,function(){
        $(".slideOne .next").animate({backgroundSize:"75%"},100,function(){
          $(".slideOne .next").animate({backgroundSize:"90%"},100,function(){

          })
        })
      })
      clearInterval(timer3);
    },550);
    timer1 = setInterval(function(){
      $("."+arg).animate({opacity:"1"},200);
      clearInterval(timer1);
    },1000);

    timer4 = setInterval(function(){
      $(".slideOne .shou").show();
      $(".slideOne .shou").animate({marginLeft:"77%"},200);
      clearInterval(timer4);
    },1250);
}

function slideZero(){
    var i = 90;
    var s = 0;
    var m = 20;
    timer0 = setInterval(function(){
        i--;
        $(".zHead").css("transform","rotateX(-"+i+"deg)");
        $(".zHead").css("-webkit-transform","rotateX(-"+i+"deg)");
        if(i==0){
            clearInterval(timer0);
            i=90;
            timer01 = setInterval(function(){
                s++;
                $(".zHead").css("transform","rotateX(-"+s+"deg)");
                $(".zHead").css("-webkit-transform","rotateX(-"+s+"deg)");
                if(s==20){
                    clearInterval(timer01);
                    s=0;
                    timer02 = setInterval(function(){
                        m--;
                        $(".zHead").css("transform","rotateX(-"+m+"deg)");
                        $(".zHead").css("-webkit-transform","rotateX(-"+m+"deg)");
                        if(m==0){
                            clearInterval(timer02);
                            m = 20;
                        }
                    },2);
                }
            },5);
        }
    },2);
    var fangZi = setTimeout(function(){
        $(".fangZi").animate({opacity:"1"},1500);
    },500);
    // var foot = setTimeout(function(){
    //   $(".footDiv").animate({backgroundSize:"100%"},1000);
    // },1700);
    var w = 0;
    var wid = setTimeout(function(){
        timer03 = setInterval(function(){
            w++;
            $(".footDiv").css("background-size",w+"% "+w+"%");
            if(w==100){
                clearInterval(timer03);
            }
        },10);
    },1700)
    var goD = setTimeout(function(){
        $(".slideZero .godown").show();
    },2900);
}
