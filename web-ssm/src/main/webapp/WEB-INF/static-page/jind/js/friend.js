window.addEventListener('load',function(){
 var swiper = new Swiper('.swiper-container', {
  direction: 'vertical',
  slidesPerView :'auto',
  on :{
  init:function(){
    slideOne();
  },
},
});
});

function slideOne(){
   
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

}
