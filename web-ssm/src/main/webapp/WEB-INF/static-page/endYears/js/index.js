var validate_code,phone,id,rk,userId
window.onload = function() {
    var oBtn = document.getElementById('code');
    var set = false;

    function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }

    var timer;
    oBtn.onclick = function () {
        if (set) {
            return;
        }
        set = true;
        phone = $('.tel').val()
        $.ajax({
            url: '../../sms/sendMsg',
            type: 'post',
            data: {mobile: phone},
            dataType: 'json',
            success: function (data) {
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
                if (data.resultCode == 200) {
                    validate_code = data.message;
                } else {
                    alert(data.message)
                }
            },
            error: function (res) {
                set = false;
            }
        })
    }

    var flag = true;
    $("#login").click( function() {
        var code = $('.num').val();
        if (validate_code != code) {
            alert('验证码有误')
            return
        }


        if(flag){
            flag=false
            $.ajax({
                url: '../../cm/validate',
                data: {mobile: phone},
                dataType: 'json',
                success: function (data) {
                    flag = true;
                    $(".onceRs").hide();
                    if (data.resultCode == 200) {
                        rk = data.message
                        userId = data.data
                        $(".onceRs").show();
                        //$(".rsSucc").show();
                    } else {
                        if (data.message == 20000)
                            alert("请勿重复登记")

                        else if (data.message == 10001)
                            $(".rsFalse").show();
                        else
                            alert('小伙伴们太热情了...请稍后再尝试...');
                    }
                },
                error: function (res) {
                    flag = true;
                    alert('小伙伴们太热情了...请稍后再尝试...');
                }
            })
        }else{
            alert("您的操作太快了...")
        }
    })


    $(".reset").bind("click", function () {
        $(".onceRs").hide();
        $(".tel").val("");
        validate_code = ''
        phone = ''
        rk = '';
        userId = '';
    });

    $(".oklogin").click(function () {
        if (flag) {
            flag = false
            $.ajax({
                url: '../../cm/commit',
                data: {mobile: phone,rk:rk,userId:userId},
                dataType: 'json',
                success: function (data) {
                    flag = true;
                    $(".onceRs").hide();
                    if (data.resultCode == 200) {
                        $(".rsSucc").show();
                    } else {
                        alert('小伙伴们太热情了...请稍后再尝试...');
                    }
                },
                error: function (res) {
                    flag = true;
                    alert('小伙伴们太热情了...请稍后再尝试...');
                }
            })
        } else {
            alert("您的操作太快了...")
        }

    });


    $(".ok").bind("click", function () {
        $(".rsSucc").hide();
    })

    $(".rsFalse").click(function () {
        $(this).hide()
    })

}