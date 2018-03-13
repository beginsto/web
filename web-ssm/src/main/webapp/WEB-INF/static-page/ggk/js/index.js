var validate_code,phone,id
var islogin=0,isgift=0
window.onload = function(){
    var ht = $(".ht").css("font-size").substring(0,2);
    var isdown = false;
    var cover = document.getElementById("canvas");
    var w = cover.width;
    var h = cover.height;
    var covercanvas = cover.getContext("2d");
    covercanvas.fillStyle="transparent";
    covercanvas.fillRect(0,0,w,h);
    function fillter( canvas ){
        canvas.fillStyle="gray";
        canvas.fillRect(0,0,w,h);

    }
    function ful(canvas){
        canvas.fillStyle="black";
        canvas.font = "0.7rem Courier New";
        canvas.fillText("刮开此图层", 90, 80);
    }
    function isDown(e){
        e.preventDefault();
        isdown=true;
    }
    function isUp(e){
        isdown=false;
    }
    function draw( e ){
        e.preventDefault();
        if(islogin==0){
            $(".hide").show();
            $(".loginDiv").show();
            return;
        }
        if(isdown){
            if(e.changedTouches){
                e=e.changedTouches[e.changedTouches.length-1];
            }
            var offsetX = cover.offsetLeft, offsetY = cover.offsetTop;
            var hei = 0;
            var wid = 0;
            if(ht==37){
                wid = 40;
                hei = 260;
            }
            if(ht==32){
                wid = 15;
                hei = 210;
            }
            if(ht==41){
                wid = 40;
                hei = 285;
            }
            if(ht==36){
                wid = 30;
                hei = 245;
            }
            touchTop=(e.clientX + document.body.scrollLeft - wid || e.pageX) - offsetX || 0,
                touchLeft=(e.clientY + document.body.scrollTop -hei || e.pageY) - offsetY || 0;
            with(covercanvas){
                beginPath();
                arc(touchTop, touchLeft, 20, 0, Math.PI * 2);
                fill();
            }
            isgift=1;
            var data = covercanvas.getImageData(0,0,w,h).data;
            var total = data.length/4;
            var completed = 0;
            for(var i=3;i<data.length;i+=4){
                if(data[i] === 0){
                    completed +=1;
                }
            }
            if(completed > total/2){
                $("#canvas").hide();
            }
        }
        //alert(touchTop);
    }
    fillter(covercanvas);
    ful(covercanvas);
    covercanvas.globalCompositeOperation = 'destination-out';
    cover.addEventListener('touchstart',isDown);
    cover.addEventListener('touchmove',draw);
    cover.addEventListener('touchend',isUp);
    cover.addEventListener('mousemove',draw);
    cover.addEventListener('mousedown',isDown);
    cover.addEventListener('mouseup',isUp);

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
    var oBtn = document.getElementById('getNumb');
    var set = false;
    oBtn.onclick = function (ev) {
        stopBuble(ev);
        if (set) {
            return;
        }
        set = true;

        phone = $('.tel').val();
        LodaData(phone);
       $.ajax({
            url:'../../sms/sendMsg',
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

    $(".login").bind("click",function(){
        //var tel=$("input[name='tel']").val();//电话
        var num=$("input[name='num']").val();//验证码
        //var r = /^\+?[1-9][0-9]*$/;
       // var tr=r.test(tel);
       // var tn=r.test(num);
        var ismub=1;
        if (num == validate_code){
            islogin=1
            $(".hide").hide();
            $(".loginDiv").hide();


           console.log(id);
           // console.log(resu)
            if(id == undefined)
                ismub=0;

            $.ajax({
                url:'../../ggk/validator',
                data:{mobile:phone,id:id},
                dataType:'json',
                success:function (data) {
                    if (data.resultCode != 200)
                        isgift = 1;

                    console.log("isgift:"+isgift+"|ismub:"+ismub)
                    if(isgift==1){
                        if(ismub==1){
                            $(".p1").css("font-size",".38rem").text("您已参加本次活动，请勿重复参与！").show();
                            $(".p2").hide();
                            $(".p3").hide();
                            $(".p4").hide();
                         //   $("#canvas").hide();
                        }else{
                            $(".p1").css("font-size",".38rem").text("您已参加本次活动，请勿重复参与！").show();
                            $(".p2").hide();
                            $(".p3").hide();
                            $(".p4").hide();
                          //  $("#canvas").hide();
                        }
                    }else{
                        if(ismub==1){

                            $(".p1").hide();
                            $(".p2").show();
                            $(".p3").show();
                            $(".p4").show();
                        }else{
                            $(".p1").show();
                            $(".p2").hide();
                            $(".p3").hide();
                            $(".p4").hide();
                        }
                    }
                }

            })


        }else{
            alert("请输入手机号码或者验证码");
        }
    });

}

var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"
    + "wxyz0123456789+/" + "=";

function encode64(input) {

    var output = "";
    var chr1, chr2, chr3 = "";
    var enc1, enc2, enc3, enc4 = "";
    var i = 0;
    do {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);
        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;
        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }
        output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
            + keyStr.charAt(enc3) + keyStr.charAt(enc4);
        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";
    } while (i < input.length);

    return output;
}
// base64¼ÓÃÜ½áÊø

function LodaData(tele) {
    var date = new Date();
    var v_year = date.getFullYear();
    var v_month = date.getMonth() + 1;
    if (parseInt(v_month) < 10)
        v_month = "0" + v_month;
    var v_day = date.getDate();
    if (v_day < 10)
        v_day = "0" + v_day;
    var v_hours = date.getHours();
    var v_minutes = date.getMinutes();
    var v_seconds = date.getSeconds();
    if (v_hours < 10)
        v_hours = "0" + v_hours;
    if (v_minutes < 10)
        v_minutes = "0" + v_minutes;
    if (v_seconds < 10)
        v_seconds = "0" + v_seconds;
    var v_random = Math.floor(Math.random() * (200 - 1));
    //ÔÂÄêÈÕ+random(200)+ÃëÊ±·Ö
    var v_resul = v_month + "" + v_year + "" + v_day + "" + v_random + "" + v_seconds + "" + v_hours + "" + v_minutes;
    ///
    $.ajax({//
        type: "GET",
        url: "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?type=7&token=" + encode64(encode64(encode64(v_resul))) + "&tele=" + tele,
        dataType: 'jsonp',
        jsonpCallback: "jsonpCallback",//×Ô¶¨ÒåµÄjsonp»Øµ÷º¯ÊýÃû,Ä¬ÈÏÎªjquery×Ô¶¯Éú³ÉµÄËæ»úº¯Êý
        jsonp: "callback",//´«µÝ¸øÇëÇó´¦Àí³ÌÐò»òÒ³ÃæµÄ,ÓÃÒÔ»ñµÃjsonp»Øµ÷º¯ÊýÃûµÄ²ÎÊýÃû(Ä¬ÈÏÎªcallback)
        processData: false,
        success: function (data) {
        	//console.log(data.userId + "|" + data.billId)
        	id = data.orgId
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(XMLHttpRequest.readyState);
            //alert(textStatus);
        }
    });
}
