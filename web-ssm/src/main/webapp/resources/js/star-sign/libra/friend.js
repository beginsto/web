window.onload = function(){
	$("#al").css("overflow","hidden");
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
    var oBtn = document.getElementById('tnum');
    var set = false;
   $('#tnum').bind('click',function(ev){

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
                oBtn.innerHTML = '重新获取';
            }
        }, 1000);

	})
 var per = 1;//好友或者本人
 if(per==1){
 //好友表单
   $("#ljyz").bind("click",function(){
   		var tel=$("input[name='tel']").val();//电话
   		var code=$("input[name='num']").val();//验证码
   		var rs=1;//返回值
   		if(tel==null && code==null){
   			//没填写内容
   		}else{
   			if(rs==1){

   			}
   		}
   });
}else{
	$(".f1").hide();
	$(".myyz").show();
}
//好友验证	
	$('#guan').bind('click',function(){
		
		$('.fewm').hide();
	})
	$('.cj').bind('click',function(){
		
		$('.fewm').show();
	})
	$('#ewm').bind('click',function(){
		
		//长按二维码
	})
	
}