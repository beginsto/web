(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery)

var num = $.getUrlParam('num')

window.addEventListener('load',function(){

  $.ajax({
      url:'../../qd/getData',
      data:{num:num},
      dataType:'json',
      success:function (data) {
          console.log(JSON.stringify(data.data))
          var shopNum = data.fd;//多少家店
          var fHao = data.fh;//总计放号
          var zDuan = data.zd;//终端销售量
          var kd = data.kd;//宽带业务
          var zf = data.zf;//资费业务
          var tel = data.phone1;//手机号
          var jf = data.jf;//全年积分
          var money = Math.ceil(data.cj/10000);//酬金
          var chengHao = "dbtx";
          if(money <= 1)
              chengHao='ccml'
          else if(money > 1 && money <= 5)
              chengHao = 'jhxx'
          else if(money > 5 && money <= 15)
              chengHao = 'zltj'
          else if(money > 15 && money <= 30)
              chengHao = 'xysc'
          else if(money > 30 && money <= 50)
              chengHao = 'smqq'
          else if(money > 50 && money <= 80)
              chengHao = 'rrzt'
          else if(money > 80 && money <= 120)
              chengHao = 'mysh'
          else if(money > 120 && money <= 200)
              chengHao = 'wzbf'
          else if(money > 200 && money <= 300)
              chengHao = 'dbtx'
          else if(money > 300)
              chengHao = 'jswm'


          var fisrtShop = data.cretime;//第一个商店
          var arr = fisrtShop.split("/");
          $(".year").text(arr[0]);
          $(".month").text(arr[1]);
          $(".day").text(arr[2]);
          $("#shopNum").text(shopNum);
          $("#fHao").text(fHao);
          $("#zDuan").text(zDuan);
          $("#kd").text(kd);
          $("#zf").text(zf);
          $("#jf").text(jf);
          $("#money").text(money+"w+");
          $(".chengHao").attr("src","image/"+chengHao+".png");
          var firstPhoneH = tel.toString().substring(0,3);
          var firstPhoneF = tel.toString().substring(7,12);
          $("#tel").text(firstPhoneH+"****"+firstPhoneF);
//swiper插件
          var swiper = new Swiper('.swiper-container', {
              direction: 'vertical',
              slidesPerView :'auto',
              pagination: {
                  // el: '.swiper-pagination',
              },
              on: {
                  slideChangeTransitionEnd: function(){
                      var activeIndex = this.activeIndex;
                      if(activeIndex==0){
                          slideThree();
                      }
                      if(activeIndex==1){
                          slideTwo();
                      }
                      if(activeIndex==2){
                          slideFive();
                      }
                      if(activeIndex==3){
                          slideNine();
                      }
                      if(activeIndex==4){
                          eight();
                      }
                  },
                  slideChangeTransitionStart: function(){
                      $(".slideClear *").removeAttr("style");
                      $(".slideClear .godown").hide();
                      //clearInterval(timerA);
                  },
                  init:function(){
                      slideThree();
                  },
              }
          });
          $(".loading").hide();
      },
      error:function () {
          
      }
  })


});


function slideThree(){
  $(".slideThree .touth").show();
  var s = 45;
  timer14 = setInterval(function(){
    s--;
    $(".slideThree .touth").css("-webkit-transform","rotate(-"+s+"deg)");
    $(".slideThree .touth").css("-ms-transform:","rotate(-"+s+"deg)");
    $(".slideThree .touth").css("transform","rotate(-"+s+"deg)");
    if(s==0){
      clearInterval(timer14);
      timer15 = setInterval(function(){
        s++
        $(".slideThree .touth").css("-webkit-transform","rotate(-"+s+"deg)");
        $(".slideThree .touth").css("-ms-transform:","rotate(-"+s+"deg)");
        $(".slideThree .touth").css("transform","rotate(-"+s+"deg)");
        if(s==10){
          clearInterval(timer15);
          timer16 = setInterval(function(){
            s--
            $(".slideThree .touth").css("-webkit-transform","rotate(-"+s+"deg)");
            $(".slideThree .touth").css("-ms-transform:","rotate(-"+s+"deg)");
            $(".slideThree .touth").css("transform","rotate(-"+s+"deg)");
            if(s==0){
              clearInterval(timer16);
            }
          },1);
        }
      },2);
    }
  },4);
    timer17 = setInterval(function(){
        $(".slideThree .sp1").animate({opacity:"1"},100,function(){
            $(".slideThree .sp2").animate({opacity:"1"},100,function(){
                $(".slideThree .sp3").animate({opacity:"1"},100,function(){
                    $(".slideThree .sp4").animate({opacity:"1"},100,function(){
                        $(".slideThree .sp5").animate({opacity:"1"},100,function(){
                            $(".slideThree .sp6").animate({opacity:"1"},100,function(){
                                $(".slideThree .sp7").animate({opacity:"1"},100,function(){
                                    $(".slideThree .sp8").animate({opacity:"1"},100,function(){
                                        $(".slideThree .sp9").animate({opacity:"1"},100,function(){
                                            $(".slideThree .sp10").animate({opacity:"1"},100,function(){
                                                $(".slideThree .sp11").animate({opacity:"1"},100,function(){
                                                    $(".slideThree .sp12").animate({opacity:"1"},100);
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
        clearInterval(timer17);
    },500);
  timer18 = setInterval(function(){
    $(".slideThree .firstPhone").animate({opacity:"1"},300);
    clearInterval(timer18);
  },1350);
  timer19 = setInterval(function(){
    $(".slideThree .onePhoneImg").animate({opacity:"1"},500);
    clearInterval(timer19);
  },1700);
  timer20 = setInterval(function(){
    $(".slideThree .godown").show();
    clearInterval(timer20);
  },2300);
}
function slideTwo(){
   $(".slideTwo .tou").show();
   $(".slideTwo .tou").animate({marginTop:".8rem"},150,function(){
    $(".slideTwo .tou").animate({marginTop:".4rem"},60,function(){
      $(".slideTwo .tou").animate({marginTop:".8rem"},30);
    });
  });
   timer6 = setInterval(function(){
    $(".slideTwo .pDiv").animate({marginLeft:"0rem"},300);
    clearInterval(timer6);
  },350);
   timer7 = setInterval(function(){
    $(".slideTwo .pDiv2").animate({marginLeft:"0rem"},300);
    clearInterval(timer7);
  },720);
   timer8 = setInterval(function(){
    $(".slideTwo .n2").show();
    $(".slideTwo .n2").animate({top:"5rem"},200);
    clearInterval(timer8);
  },1100);
   timer9 = setInterval(function(){
    $(".slideTwo .godown").show();
    clearInterval(timer9);
  },1300);

}
function slideFive(){
  var s = 90;
  var l = 90;
  var m = 90;
  timer21 = setInterval(function(){
    s--;
    $(".slideFive .touFi").css("-webkit-transform","rotateY("+s+"deg)");
    $(".slideFive .touFi").css("-ms-transform:","rotateY("+s+"deg)");
    $(".slideFive .touFi").css("transform","rotateY("+s+"deg)");
    if(s==0){
      clearInterval(timer21);
    }
  },5);
  timer22 = setInterval(function(){
    timer23 = setInterval(function(){
      l--
      $(".slideFive .fiveFamly").css("-webkit-transform","rotateY("+l+"deg)");
      $(".slideFive .fiveFamly").css("-ms-transform:","rotateY("+l+"deg)");
      $(".slideFive .fiveFamly").css("transform","rotateY("+l+"deg)");
      if(l==0){
        clearInterval(timer23);
      }
    },5);
    clearInterval(timer22);
  },500);
  timer24 = setInterval(function(){
    timer25 = setInterval(function(){
      m--
      $(".slideFive .jia").css("-webkit-transform","rotateY("+m+"deg)");
      $(".slideFive .jia").css("-ms-transform:","rotateY("+m+"deg)");
      $(".slideFive .jia").css("transform","rotateY("+m+"deg)");
      if(m==0){
        clearInterval(timer25);
      }
    },5);
    clearInterval(timer24);
  },1000);
  timer26 = setInterval(function(){
    $(".slideFive .godown").show();
    clearInterval(timer26);
  },1500);
  
}
function slideNine(){
  $(".slideNine .touNine").animate({backgroundSize:"100%"},300,function(){
    $(".slideNine .touNine").animate({backgroundSize:"80%"},150,function(){
      $(".slideNine .touNine").animate({backgroundSize:"100%"},80);
    });
  });
  timer31 = setInterval(function(){
    $(".slideNine .nineCont").animate({opacity:"1"},800);
    clearInterval(timer31);
  },750);
  timer32 = setInterval(function(){
    $(".slideNine .ll").animate({opacity:"1"},800);
    clearInterval(timer32);
  },1400);
  timer99 = setInterval(function(){
      $(".slideNine .godown").show();
      clearInterval(timer99);
    },2500);
}

function eight(){
  var h = $(".eightCont").height();
  var marH = parseInt($(".eightCont").css("margin-top").substring(0,2));
  var l = h + marH+15;
  $(".nineFoot").css("top",l+"px");
}
