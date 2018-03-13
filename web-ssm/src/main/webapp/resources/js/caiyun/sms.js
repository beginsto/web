
function sms() {
    var tel =$('.sms-tel').val();
    if (isMobileNum(tel)){
        var $this = $('.sms');
        setDisable($this);
        $.ajax({
            url:'validated',
            type:'post',
            data:{tel:tel},
            dataType:'json',
            success:function (data) {
                if(data.resultCode == '200'){
                    $.ajax({//
                        type: "GET",
                        url: "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?type=0&token="+encode64(encode64(encode64(v_resul)))+"&teleNum="+tel+"&sendType=1",
                        dataType:'jsonp',
                        jsonpCallback : "jsonpCallback",
                        jsonp : "callback",
                        processData: false,
                        success: function(data) {
                            if(data.msg){
                                // $('#code').val(data.msg);
                                $('#validate_code').val(data.msg);
                            }else{
                                alert("短信服务异常，请稍后再试...");
                            }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown)
                        {
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }
                    });
                }else{
                    $('.mask1').show();
                }
            }

        })

    }else{
        createNotification('failed','请求失败','手机号码有误...')
    }
}

function setDisable (obj) {
    var count = 60;
    var countdown = setInterval(CountDown, 1000);
    function CountDown() {
        //$(obj).attr("disabled", true).css('background','#ccc');
        $(obj).attr("disabled", true);
        $(obj).val( count + "秒");
        if (count == 0) {
            $(obj).val("重新获取").removeAttr("disabled");
            clearInterval(countdown);
        }
        count--;
    }
}

function isMobileNum(tel){
    var reg = /^1[3|4|5|8|7][0-9]\d{8}$/;
    if(reg.test(tel))
        return true;
    else
        return false;
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

function loadData()
{
    var date = new Date();
    var v_year=date.getFullYear();
    var v_month=date.getMonth()+1;
    if(parseInt(v_month)<10)
        v_month="0"+v_month;
    var v_day=date.getDate();
    if(v_day<10)
        v_day="0"+v_day;
    var v_hours=date.getHours();
    var v_minutes=date.getMinutes();
    var v_seconds=date.getSeconds();
    if(v_minutes<10)
        v_minutes="0"+v_minutes;
    if(v_seconds<10)
        v_seconds="0"+v_seconds;
    if(v_hours < 10)
        v_hours="0"+v_hours;
    var v_random=Math.floor(Math.random() * (200 - 1));
    var v_resul=v_month+""+v_year+""+v_day+""+v_random+""+v_seconds+""+v_hours+""+v_minutes;


    console.log(v_resul);
    return v_resul;

}