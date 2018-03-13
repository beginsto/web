window.onload = function(){
	  function stopBuble(ev) {
    if(ev&& ev.stopPropagation) {
      ev.stopPropagation();
    }
    else{
      window.event.cancelBubble=true;
    }
  }
  var choose = '';
//登录
function toDouble(num) {
  if (num < 10) {
    return '0' + num;
  }
  else {
    return '' + num;
  }
}
var phone,validate_code
var oBtn = document.getElementById("getNum");
var set = false;
oBtn.onclick = function (ev) {
  if (set) {
    return;
  }
  set = true;
  phone = $('.tel').val()
  $.ajax({
      url:'http://jxyd.1860.cn/main/sms/sendMsg',
      data:{mobile:phone},
      dataType: 'jsonp',
      jsonpCallback: "jsonpCallback",
      jsonp: "callback",
      processData: false,
      success:function (data) {

          var num = 60;
          oBtn.innerHTML = num + 'S后重新获取';
          timer = setInterval(function () {
              num--;
              oBtn.innerHTML = toDouble(num) + 'S后重新获取';
              if (num == 0) {
                  clearInterval(timer);
                  oBtn.innerHTML = '获取验证码';
                  set=false
              }
          }, 1000);

          if (data.resultCode == 200){
              validate_code = data.message;
          }else{
              alert(data.message)
          }
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
          //alert(XMLHttpRequest.status);
          //alert(XMLHttpRequest.readyState);
          //alert(textStatus);
      }
  })


}
$("#choose1").bind("click",function(){
  $(".choose1 img").show();
   $(".choose2 img").hide();
    $(".choose3 img").hide();
    choose = "ch1";
});
$("#choose2").bind("click",function(){
  $(".choose2 img").show();
   $(".choose1 img").hide();
    $(".choose3 img").hide();
    choose = "ch2";
});
$("#choose3").bind("click",function(){
  $(".choose3 img").show();
   $(".choose2 img").hide();
    $(".choose1 img").hide();
    choose = "ch3";
});

var flag = false;
$('#login').click(function(ev){
    if (flag)
        return
    flag=true;
    if($('.num').val() != validate_code){
        alert("验证码有误...")
        flag = false
        return
    }
    if (choose == ''){
        alert("请选择体验平台")
        flag = false
        return;
    }
    $.ajax({
        url:'../../rwk/confirm',
        data:{tel:phone,platform:choose},
       // data:{tel:$('.tel').val(),platform:choose},
        dataType:'json',
        success:function (data) {
            console.log(data.data);
            if (data.resultCode == 200){
                if (data.message == '3个月'){
                    $(".rs p").text("恭喜您获得3个月内免费体验活动奖励！");
                    $(".hide").show();
                    $(".rs").show();
                }else if(data.message == '6个月'){
                    $(".rs p").text("恭喜您获得6个月内免费体验活动奖励！");
                    $(".hide").show();
                    $(".rs").show();
                }
            }else if(data.resultCode == 201){
                $(".rs p").text("啊哦，与奖励擦肩而过~");
                $(".hide").show();
                $(".rs").show();
                $(document.body).css("overflow","hidden");
            }else if (data.resultCode == 202){
                $(".rs p").text("尊敬的客户，您已成功参加任我看免费体验抽奖活动，请勿重复参加，感谢您的关注！");
                $(".rs p").css("font-size","0.36rem");
                $(".hide").show();
                $(".rs").show();
                $(document.body).css("overflow","hidden");
            }else{
                $(".rs p").text("啊哦，与奖励擦肩而过~");
                $(".hide").show();
                $(".rs").show();
                $(document.body).css("overflow","hidden");
            }

        }
    })
    flag=false
 
	});
$(".close").bind("click",function(){
      $(".hide").hide();
      $(".rs").hide();
      $(document.body).css("overflow-y","scroll");
});
$(".sp1").bind("click",function(){
      $(".hide").hide();
      $(".rs").hide();
      $(document.body).css("overflow-y","scroll");
});
}

  