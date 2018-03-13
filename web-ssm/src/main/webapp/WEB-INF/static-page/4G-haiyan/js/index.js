	window.onload = function(){
		windowWidth = document.documentElement.clientWidth;
		if(windowWidth>321){
			$(".context a").css("width","1.1rem");
		}
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
//登录
$("#login").bind("click",function(){
	var tel = $("input[name='tel']").val();
	var num = $("input[name='num']").val();
	var r = /^\+?[1-9][0-9]*$/;
	var tr=r.test(tel);
	var tn=r.test(num);
	if(tel!=null && num != null && tr && tn){
			//submit();
			var rs =1;
			if(rs==1){
				$("#rsimg").attr("src","image/scuss.png");
				$(".hide").show();
				$(".rs").show();
			}else{
				$("#rsimg").attr("src","image/fail.png");
				$(".hide").show();
				$(".rs").show();
			}
		}else{
			alert("请输入手机号码或者验证码");
		}
	})
//用户登记人数
$("#rren").text("200");
//关闭弹出
$(".ok").bind("click",function(){
	$(".hide").hide();
	$(".rs").hide();
});
//规则
$("#giftRule").bind("click",function(){
	stopBuble($(this));
	$(".hide").show();
	$(".giftRule").show();
	document.body.ontouchmove=function(e){
    e.preventDefault();
}
var div = document.getElementById("gr");
div.addEventListener('touchmove',function(e){
    e.stopPropagation();
});
});
$(".giftRule").bind("click",function(){
	$(".hide").hide();
	$(".giftRule").hide();
	document.body.ontouchmove=function(e){
    e.stopPropagation();
}
})
}
