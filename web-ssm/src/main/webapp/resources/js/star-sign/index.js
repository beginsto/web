window.onload = function(){

    function stopBuble(ev) {
        if(ev&& ev.stopPropagation) {
            ev.stopPropagation();
        }
        else{
            window.event.cancelBubble=true;
        }
    }

    function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }
    /*var oBtn = document.getElementById('getNumb');
    var set = false;
    oBtn.onclick = function (ev) {
        stopBuble(ev);
        if (set) {
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
                set = false;
                oBtn.innerHTML = '获取验证码';
            }
        }, 1000);

    }*/
    /*立即绑定*/
    /*$('.now').click(function(ev){
        stopBuble(ev);
        $('.yanzheng').hide();
        $('.bangding').css('display','block');
        setInterval(function(){
            clearInterval();
            $('.bangding').hide();
        },3000)
        return false;
    });*/



    $(function () {
        $.ajax({
            type:'post',
            url:'getInfo',
            data:{openid:$('#openid').val(), mid:$('#mid').val()},
            dataType:'json',
            success:function (data) {
                $('.mask1').hide();
                console.log(JSON.stringify(data));
                if (data.data == null)
                    return;
                var result = JSON.parse(data.data);
                var isLogin = result.isLogin;
                var isBind = result.isBind;
                var hadAward = result.hadAward;
                var isRaffle = result.isRaffle;
                var isIssue = result.isIssue;
                var isInvited = result.isInvited;
                var isLocal = result.isLocal;
                $("#nickName").val(result.nick);

                if ((isIssue == 'Y' || isInvited == 'Y') && isRaffle == 'N'){
                    $("#zhizhen").attr('src','/main/images/star-sign/zhizhen1.png');
                }
                if (hadAward == 'N'){
                    $(".nog").css('display','block');
                    timer=setInterval(function(){

                        $(".nog").css('display','none');
                        clearInterval(timer);
                    },2000)
                }


                $("#zhizhen").rotate({
                    bind:
                        {
                            click: function(){
                                if(isBind=='N'){
                                    timeOut();
                                    return;
                                }

                                if(hadAward == 'N'){
                                    $(".nog").css('display','block');
                                    timer=setInterval(function(){
                                        $(".nog").css('display','none');
                                        clearInterval(timer);
                                    },2000)
                                    return;
                                }

                                if(isRaffle == 'Y'){
                                    $('.gift').css('display','block');
                                    $("#giftall").attr('src','/main/images/star-sign/last.png');
                                    return;
                                }

                                if (isLocal == 'N'){
                                    alert("您绑定的号码为非嘉兴号段，小嘉也很无奈...");
                                    return;
                                }

                                if (isIssue == 'N' && isInvited == 'N'){
                                    alert("不是"+result.issue +"？赶紧邀请身边的"+result.issue+"～");
                                    return;
                                }

                                if (isIssue == 'Y' || isInvited == 'Y'){
                                    $.ajax({
                                        url:'raffle',
                                        type:'post',
                                        data:{openid:$("#openid").val(),unionid:result.mobile,zodiac:result.zodiac,issue:result.issue,nickname:result.nick},
                                        dataType:'json',
                                        success:function (data) {
                                            var award = data.message;
                                            if(award==0){
                                                rotateFunc(0,23,'100M');
                                            }
                                            if(award==1){
                                                rotateFunc(1,95,'200M')
                                            }
                                            if(award==2){
                                                rotateFunc(2,167,'300M')
                                            }
                                            if(award==3){
                                                rotateFunc(3,239,'500M')
                                            }
                                            if(award==4){
                                                rotateFunc(4,311,'1GB')
                                            }
                                            if(award == -1){
                                                $(".nog").css('display','block');
                                                timer=setInterval(function(){
                                                    $(".nog").css('display','none');
                                                    clearInterval(timer);
                                                },2000)
                                            }
                                            if (award == 300){
                                                $('.gift').css('display','block');
                                                $("#giftall").attr('src','/main/images/star-sign/last.png');
                                            }
                                            if( award == 404 || award == 500){
                                                alert("小嘉身体不适,暂时不能为您服务了...")
                                            }
                                            if (award == 405){
                                                alert("小嘉未能获取您的星座信息，请尝试刷新页面...");
                                            }
                                        }
                                    })
                                }else{
                                    alert("您进入了一片未知领域...小嘉兴暂时不能为您服务了...");
                                    return;
                                }
                            }
                        }
                });
            }
        })
    })

    var timeOut = function(){
        $("#zhizhen").rotate({
            angle:0,
            duration: 8000,
            animateTo: 360,
            callback:function(){
                $('.yanzheng').css('display','block');
                $('.yanzheng1').css('display','block');
            }
        });

    };
    var rotateFunc = function(awards,angle,text){
        $('#zhizhen').stopRotate();
        $("#zhizhen").rotate({
            angle:0,
            duration: 4000,
            animateTo: angle+3600,
            callback:function(){
                $('.gift').css('display','block');
                $("#giftall").attr('src','/main/images/star-sign/'+text+'.png');
                gifttime=true;
            }
        });
    };



    $(function(){
        setInterval('autoScroll(".apple")',3000);
    })
//跳转页面
    $("#noo").bind("click",function(){

        $(location).attr('href', 'forward?openid='+$("#openid").val()+'&nickName='+$("#nickName").val());
    })
    $('.gift').click(function(ev){
        ev.preventDefault()
        $('.gift').hide();

    })


    var clientWidth = document.body.clientWidth;
    var ruleWidth = $("#ruleBtn").innerWidth();
    var isHide = false;
    var leftLength = clientWidth - ruleWidth;
    $("#rule").css("left",leftLength);
    $("#body").css("overflow-x","hidden");
    function lockBody() {
        $("#body").css("overflow","hidden");
    }
    function unlockBody() {
        $("#body").css("overflow-y","scroll");
        $("#body").css("overflow-x","hidden");
    }
    $("#ruleBtn").click(function () {
        if(isHide){
            hideRule();
        }else{

            showRule();
        }

    });
    function hideRule(){
        $("#rule").animate({"left":leftLength},"slow",function(){
            unlockBody();
            $("#ruleDiv").hide();
            isHide = false;
        });
    }

    function showRule() {
        $("#ruleDiv").show();
        $("#rule").animate({"left":"-10%"},"slow",function(){
            isHide = true;
            lockBody();
        });
    }
}

function autoScroll(obj){

    $(obj).find("ul").animate({

        marginTop : "-20px"

    },500,function(){

        $(this).css({marginTop : "0px"}).find("li:first").appendTo(this);

    })

}

