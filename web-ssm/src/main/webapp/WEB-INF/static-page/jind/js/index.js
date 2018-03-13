var timerA;
window.addEventListener('load',function(){
  var name = "小明";
  var firstPhone = "12313211265";
  var sanNum = 1234;
  var h = 321;
  var mi = 34;
  var beat = 80; 
  var rank = 90;
  var power = "12343M";
  var eveP = "23M";
  var powerRank = 342;
  $("#name").text(name);
  $("#firstPhone").text(firstPhone);
  $("#sanNum").text(sanNum);
  $("#h").text(h);
  $("#m").text(mi);
  $("#beat").text(beat+"%");
  $("#rank").text(rank);
  $("#power").text(power);
  $("#eveP").text(eveP);
  $("#powerRank").text(powerRank);
  $(".name").text(name);

  var swiper1 = document.getElementById("swiper");  
 /* var scale = document.body.clientHeight / document.body.clientWidth;
  swiper1.style.height = document.body.clientWidth * scale + "px"; */

  $(".jindan").bind("click",function(){
    window.location.href = "active.html";
  });
  var rs = 0;//2.4.5.7 俩种情况
  if(rs==1){
    $(".slideTwo").show();
    $(".slideFour").show();
    $(".slideFive").show();
    $(".slideSeven").show();
    $(".slideTwoFu").hide();
    $(".slideFourFu").hide();
    $(".slideFiveFu").hide();
    $(".slideSevenFu").hide();
  }else{
   $(".slideTwo").hide();
   $(".slideFour").hide();
   $(".slideFive").hide();
   $(".slideSeven").hide();
   $(".slideTwoFu").show();
   $(".slideFourFu").show();
   $(".slideFiveFu").show();
   $(".slideSevenFu").show();
 }
 var w = $(".canvasDiv").width();
 var h = $(".canvasDiv").height();
 var c = document.getElementById("process");
 c.width = w;
 c.height = h;

 var swiper = new Swiper('.swiper-container', {
  direction: 'vertical',
  slidesPerView :'auto',
  pagination: {
    el: '.swiper-pagination',
  },
  on: {
    slideChangeTransitionEnd: function(){
      var activeIndex = this.activeIndex;
      if(activeIndex==0){
        slideOne();  
      }
      if(activeIndex==1){
        if(rs==1){
          slideTwo();
        }else{
          slideTwoFu();
        }  
      }
      if(activeIndex==2){
        slideThree();
      }
      if(activeIndex==3){
        if(rs==1){
          slideFour();
        }else{
         slideFourFu();
       }
     }
     if(activeIndex==4){
      if(rs==1){
        slideFive();
      }else{
        slideFiveFu();
      }

    }
    if (activeIndex==5) {
      slideSix(beat);
    }
    if (activeIndex==6){
      if(rs==1){
        slideSeven();
      }else{
        slideSevenFu();
      }
    }
    if (activeIndex==7) {

      slideNine();
    }
    if (activeIndex==8) {

      slideEight();
    }
  },
  slideChangeTransitionStart: function(){
    $(".slideClear *").removeAttr("style");
    $(".slideClear .godown").hide();
    clearInterval(timerA);
  },
  init:function(){
    slideOne();
    bj=0;
  }
},
});
//  var startScroll, touchStart, touchCurrent;
//  swiper.slides.on('touchstart', function (e) {
//   startScroll = this.scrollTop;
//   touchStart = e.targetTouches[0].pageY;
// }, true);
//  swiper.slides.on('touchmove', function (e) {
//   touchCurrent = e.targetTouches[0].pageY;
//   var touchesDiff = touchCurrent - touchStart;
//   var slide = this;
//   var onlyScrolling =
//             ( slide.scrollHeight > slide.offsetHeight ) && //allow only when slide is scrollable
//             (
//                 ( touchesDiff < 0 && startScroll === 0 ) || //start from top edge to scroll bottom
//                 ( touchesDiff > 0 && startScroll === ( slide.scrollHeight - slide.offsetHeight ) ) || //start from bottom edge to scroll top
//                 ( startScroll > 0 && startScroll < ( slide.scrollHeight - slide.offsetHeight ) ) //start from the middle
//                 );
//             if (onlyScrolling) {
//               e.stopPropagation();
//             }
//           }, true);
});


function slideOne(){
  timerA = setInterval(function(){
   
    timer1 = setInterval(function(){
      $(".slideOne .nameP").show();
      $(".slideOne .nameP").animate({marginLeft:"1.6rem"},200,function(){
        $(".slideOne .nameP").animate({marginLeft:"2rem"},100,function(){
          $(".slideOne .nameP").animate({marginLeft:"1.6rem"},50,function(){
            $(".slideOne .nameP").animate({marginLeft:"1.8rem"},50,function(){
              $(".slideOne .nameP").animate({marginLeft:"1.6rem"},25,function(){

              });
            });
          });
        });
      });
      clearInterval(timer1);
    },360);
    timer2 = setInterval(function(){
      $(".slideOne .slideOneBg").show();
      $(".slideOne .slideOneBg").animate({backgroundSize:"75%"},200,function(){
        $(".slideOne .slideOneBg").animate({backgroundSize:"65%"},100,function(){
          $(".slideOne .slideOneBg").animate({backgroundSize:"75%"},100,function(){

          })
        })
      })
      clearInterval(timer2);
    },895);
    timer3 = setInterval(function(){
      $(".slideOne .next").show();
      $(".slideOne .next").animate({backgroundSize:"90%"},200,function(){
        $(".slideOne .next").animate({backgroundSize:"75%"},100,function(){
          $(".slideOne .next").animate({backgroundSize:"90%"},100,function(){

          })
        })
      })
      clearInterval(timer3);
    },1410);
    timer4 = setInterval(function(){
      $(".slideOne .shou").show();
      $(".slideOne .shou").animate({marginLeft:"77%"},200);
      clearInterval(timer4);
    },1960);
    timer5 = setInterval(function(){
      $(".slideOne .godown").show();
      clearInterval(timer5);
    },2250);
    clearInterval(timerA);
  },1);

}
function slideTwo(){
  timerA = setInterval(function(){
   $(".slideTwo .tou").show();
   $(".slideTwo .tou").animate({marginTop:"1rem"},150,function(){
    $(".slideTwo .tou").animate({marginTop:".9rem"},60,function(){
      $(".slideTwo .tou").animate({marginTop:"1rem"},30);
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
    $(".slideTwo .phone").animate({opacity:"1"},500,function(){
      $(".slideTwo .left1").animate({opacity:"1"},200,function(){
        $(".slideTwo .left2").animate({opacity:"1"},200,function(){
          $(".slideTwo .left3").animate({opacity:"1"},200,function(){
            $(".slideTwo .right3").animate({opacity:"1"},200,function(){
              $(".slideTwo .right2").animate({opacity:"1"},200,function(){
                $(".slideTwo .right1").animate({opacity:"1"},200);
              });
            });
          });
        });
      });
    });
    clearInterval(timer8);
  },1100);
   timer9 = setInterval(function(){
    $(".slideTwo .godown").show();
    clearInterval(timer9);
  },3000);
   clearInterval(timerA);
 },1);

}
function slideTwoFu(){
  timerA = setInterval(function(){
    $(".slideTwoFu .tou2").show();
    $(".slideTwoFu .tou2").animate({marginTop:"1rem"},150,function(){
      $(".slideTwoFu .tou2").animate({marginTop:".9rem"},60,function(){
        $(".slideTwoFu .tou2").animate({marginTop:"1rem"},30);
      });
    });
    timer10 = setInterval(function(){
      $(".slideTwoFu .pDiv").animate({marginLeft:"0rem"},300);
      clearInterval(timer10);
    },350);
    timer11 = setInterval(function(){
      $(".slideTwoFu .pDiv2").animate({marginLeft:"0rem"},300);
      clearInterval(timer11);
    },720);
    timer12 = setInterval(function(){
      $(".slideTwoFu .ewm").animate({opacity:"1"},700);
      clearInterval(timer12);
    },1100);
    timer13 = setInterval(function(){
      $(".slideTwoFu .godown").show();
      clearInterval(timer13);
    },1900);
    clearInterval(timerA);
  },1);
  
}

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
                  $(".slideThree .sp8").animate({opacity:"1"},100);
                });
              });
            });
          });
        });
      });
    });
    clearInterval(timer17);
  },400);
  timer18 = setInterval(function(){
    $(".slideThree #firstPhone").animate({opacity:"1"},300,function(){
      $(".slideThree .onePhone").animate({opacity:"1"},300);
      clearInterval(timer18);
    });
  },1350);
  timer19 = setInterval(function(){
    $(".slideThree .onePhoneImg").animate({opacity:"1"},500);
    clearInterval(timer19);
  },2100);
  timer20 = setInterval(function(){
    $(".slideThree .godown").show();
    clearInterval(timer20);
  },2700);
}

function slideFour(){
  $(".slideFour .head4Div").animate({backgroundSize:"100%"},500,function(){
    $(".slideFour .htou").show();
    $(".slideFour .htou").animate({marginTop:"4.2rem",opacity:"1"},300,function(){
      $(".slideFour .hn").show();
      $(".slideFour .hn").animate({marginTop:"4.7rem",opacity:"1"},300,function(){
        $(".slideFour .zhousanDiv").animate({marginTop:"7.7rem",opacity:"1"},300,function(){
          $(".slideFour .godown").show();
        });
      });
    });
  });
}
function slideFourFu(){
  $(".slideFourFu .touFour").animate({backgroundSize:"100%"},500,function(){
   $(".slideFourFu .fourN").animate({marginTop:"3rem",opacity:"1"},300,function(){
    $(".slideFourFu .fourO").animate({marginTop:"6.5rem",opacity:"1"},300,function(){
      $(".slideFourFu .godown").show();
    });
  });
 });
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
function slideFiveFu(){
  var s = 90;
  var l = 90;
  var m = 90;
  timer27 = setInterval(function(){
    s--;
    $(".slideFiveFu .touFi").css("-webkit-transform","rotateY("+s+"deg)");
    $(".slideFiveFu .touFi").css("-ms-transform:","rotateY("+s+"deg)");
    $(".slideFiveFu .touFi").css("transform","rotateY("+s+"deg)");
    if(s==0){
      clearInterval(timer27);
    }
  },5);
  timer28 = setInterval(function(){
    timer29 = setInterval(function(){
      l--
      $(".slideFiveFu .fiveFu").css("-webkit-transform","rotateY("+l+"deg)");
      $(".slideFiveFu .fiveFu").css("-ms-transform:","rotateY("+l+"deg)");
      $(".slideFiveFu .fiveFu").css("transform","rotateY("+l+"deg)");
      if(l==0){
        clearInterval(timer29);
      }
    },5);
    clearInterval(timer28);
  },500);
  timer77 = setInterval(function(){
    timer78 = setInterval(function(){
      m--
      $(".slideFiveFu .fiveO-N").css("-webkit-transform","rotateY("+l+"deg)");
      $(".slideFiveFu .fiveO-N").css("-ms-transform:","rotateY("+l+"deg)");
      $(".slideFiveFu .fiveO-N").css("transform","rotateY("+l+"deg)");
      if(l==0){
        clearInterval(timer78);
      }
    },5);
    clearInterval(timer77);
  },1000);

  timer30 = setInterval(function(){
    $(".slideFiveFu .godown").show();
    clearInterval(timer30);
  },1500);
}

function slideSeven(){
  $(".touSeven").animate({marginLeft:"32.5%"},300,function(){
    $(".sevenCont").animate({marginLeft:"5%"},300,function(){
      $(".sevenCom").animate({opacity:"1"},300,function(){
        $(".slideSeven .godown").show();
      });
    });
  });
}
function slideSevenFu(){
  $(".touSevenFu").animate({opacity:"1"},500,function(){
    $(".sevA").animate({opacity:"1"},500,function(){

      $(".slideSevenFu .godown").show();

    });
  });
}
function slideSix(arg){
  $(".touSix").animate({opacity:"1"},400);
  timerq = setInterval(function(){
    $(".sixCont").animate({opacity:"1"},400);
    clearInterval(timerq);
  },550);
  timers = setInterval(function(){
    $(".canvasDiv").animate({opacity:"1"},300);
    var c = document.getElementById("process");
    var ctx = c.getContext('2d');
    var centerX = c.width/2;   //Canvas中心点x轴坐标
    var centerY = c.height/2;  //Canvas中心点y轴坐标
    var rad = Math.PI*2/100; //将360度分成100份，那么每一份就是rad度
    var speed = 0.4;         //加载的快慢就靠它了
    animate();

    function animate(){
      window.requestAnimationFrame(function(){
        if(speed < arg ){
          animate();
        }
      }); 
      ctx.clearRect(0, 0, c.width, c.height);
      speed += 0.4;
      drawCircle(ctx,speed);
    };


    function drawCircle(ctx,percent){
        //画白色的静态圆
        ctx.save();  
        ctx.strokeStyle = "#5BB1EB";  
        ctx.lineWidth = 24; 
        ctx.beginPath();
        ctx.arc(centerX, centerY, 90, 0, Math.PI*2, false);  
        ctx.stroke();     
        ctx.closePath(); 
        ctx.restore();


        //画进度环
        ctx.save();
        ctx.strokeStyle = "#E78F3D";
        ctx.lineWidth = 24;
        ctx.beginPath();
        ctx.arc(centerX, centerY, 90, -Math.PI/2, -Math.PI/2 +percent*rad, false);
        ctx.stroke();     
        ctx.closePath();  
        ctx.restore();

        //百分比文字绘制
        ctx.save();
        ctx.fillStyle = "#474d5d";
        ctx.font = "bold 30px Arial";

        //绘制字体并指定位置
        ctx.fillText(percent.toFixed(0) + '%', centerX-20, centerY+10);
        ctx.restore();
      }
      clearInterval(timers);
    },1100);
  timer66 = setInterval(function(){
    $(".slideSix .godown").show();
    clearInterval(timer66);
  },1200);

}
function slideEight(){
  // timerA = setInterval(function(){
  //   $(".slideEight .godown").show();
  //   clearInterval(timerA);
  // },1);
}
function slideNine(){
  $(".slideNine .touNine").animate({backgroundSize:"100%"},300,function(){
    $(".slideNine .touNine").animate({backgroundSize:"80%"},150,function(){
      $(".slideNine .touNine").animate({backgroundSize:"100%"},80);
    });
  });
  timer31 = setInterval(function(){
    $(".slideNine .nineCont").animate({opacity:"1"},500);
    clearInterval(timer31);
  },720);
  timer32 = setInterval(function(){
    $(".slideNine .ll").animate({opacity:"1"},500);
    clearInterval(timer32);
  },900);
  $(".slideNine .godown").show();
}