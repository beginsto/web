window.onload = function () {
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

   $("#ljyz").bind("click",function(){
   		var tel=$("input[name='tel']").val();//电话
   		var code=$("input[name='num']").val();//验证码
   		var r = /^\+?[1-9][0-9]*$/;
   		var tr=r.test(tel);
   		var tn=r.test(code);
   		if(tel!=null && code != null && tr && tn){
   			var rs=1;//返回值
   			if(rs==1){
   				$(".friendY").hide();
   				$(".myyz").show();
   			}
   		}else{
   			alert("请输入手机号码或者验证码");
   		}
   	});

   $(".cj").bind("click",function(){
   		$(".hideF").show();
   		$(".ewmDiv").show();
   });
   $(".closeF").bind("click",function(){
   		$(".hideF").hide();
   		$(".ewmDiv").hide();
   });
}