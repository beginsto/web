var code;
var tel;
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
    var oBtn = document.getElementById('code');
    var set = false;
   $('#code').bind('click',function(ev){

		stopBuble(ev);
        if (set) {
            return;
        }
       var mobile = $("#tel").val();
        tel = mobile;
       var reg = /^1[3|4|5|8|7][0-9]\d{8}$/;
       //发送验证码,第一步验证手机号码
       if (!reg.test(mobile)) {
           alert("手机号码格式不正确");
           return false;
       }else {
           set = true;
           var num = 60;
           oBtn.innerHTML = num + 'S后重新获取';
           timer = setInterval(function () {
               num--;
               oBtn.innerHTML = toDouble(num) + 'S后重新获取';
               if (num == 0) {
                   clearInterval(timer);
                   set = false;
                   oBtn.innerHTML = '重新获取';
               }
           }, 1000);
           LodaData(mobile);
       }


	})
//好友验证
/*var to=0;
 if(to==0){
	 $('.myyz').css('display','none');
	 $('.f1').css('display','block');
	  
 }else{
	 $('.f1').css('display','none');
	 $('.myyz').css('display','block');
	 
 }*/
 
//好友验证结果
$('.login').bind('click',function(){


	if (code == null || code != $('#num').val()){
		alert("验证码有误...");
	}else{
		$.ajax({
			url:'validate',
			type:'post',
			data:{openid:$("#openid").val(),mobile:tel},
			dataType:'json',
			success:function (data) {
                var rs=data.resultCode;//得到后台返回结果 1,2,3
                if(rs==1){
                    $(".fDiv").hide();
                    $(".fDiv-1").show();
                }
                if(rs==2){
                    alert("你也不是魔羯座哦");

                }
                if(rs==3){
                    alert("每人只可帮好友验证5次哦~");

                }
                if (rs == 404){
                    alert("参数有误,您打开链接的姿势好想不太对哦...~");

				}
                if (rs == 4){
                    alert(" 小嘉身体不适，暂时不能为您服务了！");
                }
                if (rs == 5){
                    alert(" 您已经为该好友验证过了");
				}

                setInterval(function(){
                    clearInterval();
                    $('.fyzjg').hide();
                },3000)
            }

		})
	}
})
    $(".ido").bind("click",function(){
        $(".idoDiv").show();
    });

    $(".close").bind("click",function(){
        $(".idoDiv").hide();
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

function LodaData(tel)
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
    $.ajax({//
        type: "GET",
        url: "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?type=0&token="+encode64(encode64(encode64(v_resul)))+"&teleNum="+tel+"&sendType=1",
        dataType:'jsonp',
        jsonpCallback : "jsonpCallback",
        jsonp : "callback",
        processData: false,
        success: function(data) {
            code = data.msg;
        }, error: function(XMLHttpRequest, textStatus, errorThrown)
        {
            //alert(XMLHttpRequest.status);
            //alert(XMLHttpRequest.readyState);
            //alert(textStatus);
        }
    });
}
