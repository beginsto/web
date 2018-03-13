window.onload = function(){
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
    oBtn.onclick = function () {
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

    }
    $(".login").bind("click",function(){
    	var tel=$("input[name='tel']").val();//电话
  		var num=$("input[name='num']").val();//验证码
  		var r = /^\+?[1-9][0-9]*$/;
  		var tr=r.test(tel);
  		var tn=r.test(num);
  		if(tel!=null && num != null && tr && tn){
  			//submit;
  			var rs = 1;//结果
  			var isStar = 1;
  			if(rs==1){
  				if(isStar==1){
  					$(".loginDiv").hide();
  					$(".yzcg").show();
  				}else{
  					alert("你也不是射手座哦");
  				}
  			}else{
  				alert("登录失败");
  			}
  		}else{
  			alert("手机号验证码不能为空！");
  		}
  	});

    $(".ido").bind("click",function(){
    	$(".idoDiv").show();
    });

    $(".close").bind("click",function(){
    	$(".idoDiv").hide();
    });
}