window.onload = function(){
	function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }

    var phone,validate_code
    var oBtn = document.getElementById('code');
    var set = false;
    oBtn.onclick = function () {
        if (set) {
            return;
        }
        set = true;
        phone = $('.tel').val()
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
                        set = false;
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
    var flag = false;
    $(".login").bind("click",function(){


        if (flag)
            return
        flag=true;
        if($('.num').val() != validate_code){
            alert("验证码有误...")
            flag = false
            return
        }
        if ($('.sf').val() == '' || $('.sf').val().length != 18){
            alert("请输入正确的身份证号码")
            flag = false
            return;
        }
        $.ajax({
            url:'../../register/commit',
            data:{mobile:phone,source:'spd',reserveone:$('.sf').val()},
            dataType:'json',
            success:function (data) {
                if (data.resultCode == 200){
                    $(".rs img").attr("src","image/success.png");
                    $(".go").css("margin-top","-3.5rem");
                    // $(".go").addClass("igo");
                    $(".rs").show();
                }else{
                    $(".rs img").attr("src","image/false.png");
                    $(".go").css("margin-top","-3.25rem");
                    $(".rs").show();
                }
                flag = false;
            },
            error:function (res) {
                alert('小伙伴们太热情了...请稍后再尝试...');
                flag = false;
            }
        })

  	});

  	$(".close").bind("click",function(){
  		$(".rs").hide();
  	});
    $(".go").bind("click",function(){
      window.location.href = "https://ecentre.spdbccc.com.cn/creditcard/indexActivity.htm?data=Z313962";
    });
}
