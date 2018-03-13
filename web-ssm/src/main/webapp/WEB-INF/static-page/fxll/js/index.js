window.onload = function(){
	  function stopBuble(ev) {
    if(ev&& ev.stopPropagation) {
      ev.stopPropagation();
    }
    else{
      window.event.cancelBubble=true;
    }
  }
  var textl = $("input[id='text1']").val(); 
  var texts = $("input[id='text2']").val();
  if(textl=="请填写您的详细地址"){
  	$(".text1").css("color","rgba(77,77,77,0.7)");
  }
  $(".text1").focus(function(){
  	$(".text1").css("color","black");
  });
  $(".text1").blur(function(){
  	var t = $("input[id='text1']").val();
  	if(t=="请填写您的详细地址"){
  	    $(".text1").css("color","rgba(77,77,77,0.7)");
  	}else{
  		$(".text1").css("color","black");
  	}
  });
  if(texts=="请填写您的真实姓名"){
  	$(".text2").css("color","rgba(77,77,77,0.7)");

  }
  $(".text2").focus(function(){
  	$(".text2").css("color","black");
  });
  $(".text2").blur(function(){
  	var t = $("input[id='text2']").val();
  	if(t=="请填写您的真实姓名"){
  	$(".text2").css("color","rgba(77,77,77,0.7)");
  	}else{
  		$(".text2").css("color","black");
  	}
  });
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
var oBtn = document.getElementById("code");
var set = false;
/*oBtn.onclick = function (ev) {
  if (set) {
    return;
  }
  set = true;
    phone =$('.tel').val()
  $.ajax({
	  url:'../../fxll/sms',
	  type:'post',
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


}*/

    $('#form').click(function () {
        var options={
            url:'../../fxll/reg',
            dataType:'json',
            beforeSubmit:validated,
            success:function (data) {
            	/*if (data.resultCode == 303){
                    $(".rs .p1").text("请勿重复登记哟~");
                    $(".hide").show();
                    $(".rs").show();
                    timer = setInterval(function(){
                        $(".hide").hide();
                        $(".rs").hide();
                        clearInterval(timer);
                    },3000);
				}else*/ if (data.resultCode == 200){
                    $(".rs .p1").text("登记成功");
                    $(".hide").show();
                    $(".rs").show();
                    timer1 = setInterval(function(){
                        $(".hide").hide();
                        $(".rs").hide();
                        clearInterval(timer1);
                    },3000);
				}else {
                    $(".rs .p1").text(data.message);
                    $(".hide").show();
                    $(".rs").show();
                    timer = setInterval(function(){
                        $(".hide").hide();
                        $(".rs").hide();
                        clearInterval(timer);
                    },3000);
                }

            },
            error:function () {

            }
        };
        $('#form').ajaxForm(options);
    });

    function validated(formData,jqForm,options){
        // var queryString = $.param(formData);
        var form=jqForm[0];
        if(!isMobileNum(form.mobile.value) ){
            alert("手机号码有误...");
            return false;
        }
        if($('.num').val() == '' ){
            alert("身份证号码有误...");
            return false;
        }
       
        if(form.addr.value == '' ){
            alert("地址不能为空...");
            return false;
        }
        if(form.realname.value == '' ){
            alert("真实姓名不能为空...");
            return false;
        }
    }

    function isMobileNum(tel){
        var reg = /^1[3|4|5|8|7][0-9]\d{8}$/;
        if(reg.test(tel))
            return true;
        else
            return false;
    }

}

  