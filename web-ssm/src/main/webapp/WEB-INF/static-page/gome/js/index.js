var validate_code,phone,id
window.onload = function(){
    var ci = 1;
	$("#city").change(function(){
        var s=$("#city").find("option:selected").text();
        $("#tm").text(s);
        $(".tm").css("color","black");
        ci=0;
    });
	function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }
   /* var oBtn = document.getElementById('code');
    var set = false;
    oBtn.onclick = function () {
        if (set) {
            return;
        }
        set = true;
        phone =$('.tel').val()
        $.ajax({
            url:'../../sms/sendMsg',
            type:'post',
            data:{mobile:phone},
            dataType:'json',
            success:function (data) {
                set = false;
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
            },
            error:function (res) {
                set = false;
            }
        })

    }*/
    $(".login").bind("click",function(){
        var num=$("input[name='num']").val();//验证码
        var area = $('#city').val()

        if (area == ''){
            alert("请选择县市！");
            return;
        }

       /* if (num != validate_code){
            alert('验证码有误...');
            return;

        }*/

        $.ajax({
            url:'../../register/commit',
            data:{mobile:$('.tel').val(),source:'gome',reserveone:area},
            dataType:'json',
            success:function (data) {
                if (data.resultCode == 200){
                    $(".rs .p1").text("登记成功");
                    $(".rs .p2").text("感谢您的参与!");
                   // $(".rs .p3").text("请于2017年12月1日前往嘉兴国美永乐线下门店消费哦!");
                    $(".rs").show();
                }else{
                    $(".rs .p1").text("登记失败");
                    $(".rs .p2").text("您的信息已登记,");
                    $(".rs .p3").text("请勿重复登记哦!");
                    $(".rs").show();
                }
            },
            error:function (res) {
                alert('小伙伴们太热情了...请稍后再尝试...');
            }
        })

    });
    $(".close").bind("click",function(){
        $(".rs").hide();
    });
    $(".mdlist").bind("click",function(){
        window.location.href = "md.html";
    });
}
