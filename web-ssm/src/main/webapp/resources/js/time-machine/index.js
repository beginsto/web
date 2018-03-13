var timerA,openid,t=0;
var ch = ['ccml','jhxx','zltj','xysc','smqq','rrzt','mysh','wzbf','dbtx','jswm']

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

var num = $.getUrlParam('num');
if (num!=null){
    $('.footer').addClass(".footerhide")
}

window.addEventListener('load',function(){
    openid = $('#openid').val();
    var shouTing = 1, zhouSan = 1,qinQingWang = 1,kuanDai = 1;
  $.ajax({
      url:'indexData',
      data:{openid:openid},
      dataType:'json',
      success:function (data) {
          if (data.resultCode == 10002){
              window.location.href="http://jxyd.1860.cn/main/page/jind/bindTel.html?openid="+openid;
          }else if(data.resultCode == 200){
//  console.log(data.data)
              var d = JSON.parse(data.data);
              var name = data.message==null?"未知":data.message;//姓名
              var firstPhone = d.firstCallNumber;//首个电话
              var sanNum = 105248;//周三活动共多少人
              var h = d.mtdCallDurationM==null?0:parseInt(d.mtdCallDurationM/60);//总计通话 小时
              var mi = d.mtdCallDurationM==null?0:parseInt(d.mtdCallDurationM/3600);//分钟
              var rank = d.rkCall==null?0:d.rkCall;//排行
              var beat = parseInt((5245137-rank)*100/5245137); //打败百分比通话
              var power = d.gprsVolume==null?0:d.gprsVolume;//流量用量
              var eveP = d.gprsVolumeAvg==null?0:d.gprsVolumeAvg;//平均每天
             // if (eveP.indexOf('.')==0){
                //  eveP = 1024 * eveP.replace(".","0.");
              //}else
               //   eveP = 1024 * eveP
              eveP = Math.ceil(power*1024/365)
              var powerRank = d.rkGprs==null?0:d.rkGprs;//流量排名
              var gg = d.cntJyz==null?0:d.cntJyz;//光顾次数周三活动次数
              var ggM = d.moneyJyz==null?0:d.moneyJyz//得到话费奖励周三
              var num  = d.cntSjyytLogin==null?0:d.cntSjyytLogin;//访问手厅次数
              var yw = d.cntSjyytYw==null?0:d.cntSjyytYw;//办理业务次数
              var th = d.mtdQqwShortCallDurationM==null?0:d.mtdQqwShortCallDurationM;//亲情网通话时长
              var interNum = d.kdUseDays==null?0:Math.ceil(d.kdUseDays);//用网时长
              var perNum = d.cntQqw==null?0: d.cntQqw;//通话家人数
              //  var chengHao = ch[ Math.ceil(Math.random()*5)] //(首字母) 移动老铁、移动老司机、移动小鲜肉、移动战斗机、移动流量王、移动小金库
              //console.log(Math.random()*6)
              var hasPhone = 1;//第一个电话，1是目标用户 有第三屏，0是没有第三屏

              if (firstPhone == null || firstPhone == '' || firstPhone == 'null' || firstPhone ==0 )
                  hasPhone = 0
              else{
                  if(firstPhone.length==11){
                      var firstPhoneH = firstPhone.toString().substring(0,3);
                      var firstPhoneF = firstPhone.toString().substring(7,12);
                      $("#firstPhone").text(firstPhoneH+"****"+firstPhoneF);
                  }else{
                      $("#firstPhone").text(firstPhone);
                  }

              }
            //  hasPhone = 0
              var drX = parseInt(power) ;//流量达人 总共流量数据
              //var drX = parseInt(dr.toString().substring(0,2));
              console.log("drX:"+drX)
              if(drX < 14){
                  $("#dr").text("流量小生");
              }else{
                  $("#dr").text("流量达人");
              }


              if(drX<=2){
                  t=0
              }else if( drX > 2  && drX <= 3){

                  t=1
              }else if(drX >3 && drX <= 6){

                  t=2
              }else if(drX > 6 && drX <= 12){

                  t=3
              }else if(drX > 12 && drX <= 24){

                  t=4
              }else if(drX > 24 && drX <= 60){

                  t=5
              }else if(drX > 60 && drX <= 120){

                  t=6
              }else if(drX > 120 && drX <= 240){

                  t=7
              }else if(drX > 240 && drX <= 600){

                  t=8
              }else{

                  t=9
              }
              //// console.log("t="+t)
              $("#name").text(name);

            //  $("#firstPhone").text(firstPhone);
              $("#sanNum").text(sanNum);
              $("#h").text(h);
              $("#m").text(mi);
              $("#beat").text(beat+"%");
              $("#rank").text(rank);
              $("#power").text(power+'GB');
              $("#eveP").text(eveP+'MB');
              $("#powerRank").text(powerRank);
              $(".name").text(name);
              $("#gg").text(gg);
              $(".ggM").text(ggM+"元");
              $("#num").text(num);
              $("#yw").text(yw);
              $("#tonghua").text("通话"+th+"分钟");
              $(".interNum").text('第'+interNum+'天');
              $("#name1").text(name);
              $("#perNum").text(perNum);
              $(".chengHao").attr("src","http://wzyd11.gotone.net.cn:9000/wzyd5_static/time-machine/"+ch[t]+".png")


              if (num==0)
                  shouTing=0
              if (gg == 0)
                  zhouSan=0
              if(th == 0)
                  qinQingWang=0
              if(interNum == 0)
                  kuanDai=0

              //console.log(interNum)
              // console.log(kuanDai)

              //第二页手厅
              if(shouTing==1){
                  $(".slideTwo").show();
                  $(".slideTwoFu").hide();
              }else{
                  $(".slideTwo").hide();
                  $(".slideTwoFu").show();
              }
              //第四页周三活动
              if(zhouSan==1){
                  $(".slideFour").show();
                  $(".slideFourFu").hide();
              }else{
                  $(".slideFour").hide();
                  $(".slideFourFu").show();
              }
              //第五页亲情网
              if (qinQingWang==1){
                  $(".slideFive").show();
                  $(".slideFiveFu").hide();
              }else{
                  $(".slideFive").hide();
                  $(".slideFiveFu").show();
              }
              //第七页宽带
              if (kuanDai==1){
                  $(".slideSeven").show();
                  $(".slideSevenFu").hide();
              }else{
                  $(".slideSeven").hide();
                  $(".slideSevenFu").show();
              }

//图片跳转
              $(".jindan").bind("click",function(){
                  window.location.href = "http://jxyd.1860.cn/main/page/jind/active.html?openid="+openid;
              });
              $(".zhousan").bind("click",function(){
                  window.location.href = "http://wap.zj.10086.cn/wact/deploy/PL20170117143638/lottery/AC20170630092111/index_1.html";
              });
              $(".other").bind("click",function(){
                  window.location.href = "http://www.zj.10086.cn/szhy/cztcdp/200003924278.html";
              });
              $(".sevA").bind("click",function(){
                  window.location.href = "http://dx.10086.cn/mUziuq";
              });
              $("#zsw").bind("click",function(){
                  window.location.href = "http://wap.zj.10086.cn/wact/deploy/PL20170117143638/lottery/AC20170630092111/index_1.html";
              });
              $("#qqw").bind("click",function(){
                  window.location.href = "http://wap.zj.10086.cn/new/mobileStore/familyNet/index.do";
              });
//canvas设置宽高
              var w = $(".canvasDiv").width();
              var h = $(".canvasDiv").height();
              var c = document.getElementById("process");
              c.width = w;
              c.height = h;

//swiper插件
              //swiper插件
              var swiper = new Swiper('.swiper-container', {
                  direction: 'vertical',
                  slidesPerView :'auto',
                  pagination: {
                      el: '.swiper-pagination',
                  },
                  on: {
                      // slideChange:function(){
                      //   alert(this.activeIndex);
                      // },
                      slideChangeTransitionEnd: function(){
                          var activeIndex = this.activeIndex;
                          if(activeIndex==0){
                              slideOne();
                          }
                          if(activeIndex==1){
                              if(shouTing==1){
                                  slideTwo();
                              }else{
                                  slideTwoFu();
                              }
                          }
                          if(hasPhone==1){
                              if(activeIndex==2){
                                  slideThree();
                              }
                              if(activeIndex==3){
                                  if(zhouSan==1){
                                      slideFour();
                                  }else{
                                      slideFourFu();
                                  }
                              }
                              if(activeIndex==4){
                                  if(qinQingWang==1){
                                      slideFive();
                                  }else{
                                      slideFiveFu();
                                  }

                              }
                              if (activeIndex==5) {
                                  slideSix(beat);
                              }
                              if (activeIndex==6){
                                  if(kuanDai==1){
                                      slideSeven();
                                  }else{
                                      slideSevenFu();
                                  }
                              }
                              if (activeIndex==7) {

                                  slideNine();
                              }
                              if (activeIndex==8) {

                                  eight();
                              }
                          }else{
                              if(activeIndex==2){
                                  if(zhouSan==1){
                                      slideFour();
                                  }else{
                                      slideFourFu();
                                  }
                              }
                              if(activeIndex==3){
                                  if(qinQingWang==1){
                                      slideFive();
                                  }else{
                                      slideFiveFu();
                                  }

                              }
                              if (activeIndex==4) {
                                  slideSix(beat);
                              }
                              if (activeIndex==5){
                                  if(kuanDai==1){
                                      slideSeven();
                                  }else{
                                      slideSevenFu();
                                  }
                              }
                              if (activeIndex==6) {

                                  slideNine();
                              }
                              if (activeIndex==7) {

                                  eight();
                              }
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

              if(hasPhone==0){
                  swiper.removeSlide(2);
              };
          }else{
            alert(data.message);
          }

      },
      error:function (res) {
          alert("小伙伴们太热情拉，活动被挤爆了");
      }
  })




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
        $(".slideTwo .tou").animate({marginTop:".6rem"},150,function(){
            $(".slideTwo .tou").animate({marginTop:".9rem"},60,function(){
                $(".slideTwo .tou").animate({marginTop:".6rem"},30);
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
        $(".slideTwoFu .tou2").animate({marginTop:".6rem"},150,function(){
            $(".slideTwoFu .tou2").animate({marginTop:".9rem"},60,function(){
                $(".slideTwoFu .tou2").animate({marginTop:".6rem"},30);
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
function slideFour(){
    $(".slideFour .head4Div").animate({backgroundSize:"100%"},500,function(){
        $(".slideFour .htou").show();
        $(".slideFour .htou").animate({marginTop:"1.8rem",opacity:"1"},300,function(){
            $(".slideFour .hn").show();
            $(".slideFour .hn").animate({marginTop:"2.6rem",opacity:"1"},300,function(){
                $(".slideFour .zhousanDiv").animate({marginTop:"4.1rem",opacity:"1"},300,function(){
                    $(".slideFour .godown").show();
                });
            });
        });
    });
}
function slideFourFu(){
    $(".slideFourFu .touFour").animate({backgroundSize:"100%"},500,function(){
        $(".slideFourFu .fourN").animate({marginTop:"2.6rem",opacity:"1"},300,function(){
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
    $(".touSeven").animate({marginLeft:"35%"},300,function(){
        $(".sevenCont").animate({marginLeft:"0%"},300,function(){
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
    var i = 0;
    timerEi = setInterval(function(){
        clearInterval(timerEi);
        tiemrEi1 = setInterval(function(){
            i = i+4;
            $(".jindan").css("transform","rotate("+i+"deg)");
            $(".jindan").css("-ms-transform","rotate("+i+"deg)");
            $(".jindan").css("-webkit-transform","rotate("+i+"deg)");
            if(i==20){
                clearInterval(tiemrEi1);
                timerEi2 = setInterval(function(){
                    i= i-4;
                    $(".jindan").css("transform","rotate("+i+"deg)");
                    $(".jindan").css("-ms-transform","rotate("+i+"deg)");
                    $(".jindan").css("-webkit-transform","rotate("+i+"deg)");
                    if(i==0){
                        clearInterval(timerEi2);
                        timerei3 = setInterval(function(){
                            i = i + 4;
                            $(".jindan").css("transform","rotate(-"+i+"deg)");
                            $(".jindan").css("-ms-transform","rotate(-"+i+"deg)");
                            $(".jindan").css("-webkit-transform","rotate(-"+i+"deg)");
                            if(i==20){
                                clearInterval(timerei3);
                                timerei4 = setInterval(function(){
                                    i = i-4;
                                    $(".jindan").css("transform","rotate(-"+i+"deg)");
                                    $(".jindan").css("-ms-transform","rotate(-"+i+"deg)");
                                    $(".jindan").css("-webkit-transform","rotate(-"+i+"deg)");
                                    if(i==0){
                                        clearInterval(timerei4);
                                        timerei5 = setInterval(function(){
                                            i= i+5;
                                            $(".jindan").css("transform","rotate("+i+"deg)");
                                            $(".jindan").css("-ms-transform","rotate("+i+"deg)");
                                            $(".jindan").css("-webkit-transform","rotate("+i+"deg)");
                                            if(i==15){
                                                clearInterval(timerei5);
                                                timerei6 = setInterval(function(){
                                                    i=i-5;
                                                    $(".jindan").css("transform","rotate("+i+"deg)");
                                                    $(".jindan").css("-ms-transform","rotate("+i+"deg)");
                                                    $(".jindan").css("-webkit-transform","rotate("+i+"deg)");
                                                    if(i==0){
                                                        clearInterval(timerei6);
                                                    }
                                                },10);
                                            }
                                        },10);
                                    }
                                },20);
                            }
                        },20);
                    }
                },20);
            }
        },20);
    },300);
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

function eight(){
    var i = 0;
    var s = 0;
    var l = 100;
    timergh1 = setInterval(function(){
        i = i+36;
        s = s+2;
        l = (100-s)/2
        $(".jindan").css("transform","rotate("+i+"deg)");
        $(".jindan").css("-ms-transform","rotate("+i+"deg)");
        $(".jindan").css("-webkit-transform","rotate("+i+"deg)");
        $(".jindan").css("width",s+"%");
        $(".jindan").css("margin-left",l+"%");
        if(i==1080){
            clearInterval(timergh1);
        }
    },10);
}