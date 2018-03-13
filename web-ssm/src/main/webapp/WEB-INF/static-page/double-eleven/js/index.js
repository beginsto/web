window.onload = function(){
	function stopBuble(ev) {
		if(ev&& ev.stopPropagation) {
			ev.stopPropagation();
		}
		else{
			window.event.cancelBubble=true;
		}
	}
//规则
$(".rule").bind("click",function(){
	$(".hide").show();
	$(".ruleDiv").show();
	$(document.body).css("overflow","hidden");
});
$("#ruleClose").bind("click",function(){
	$(".hide").hide();
	$(".ruleDiv").hide();
	$(document.body).css("overflow-y","scroll");
});
//规则结束
//商家
$(".mall").bind("click",function(){
	window.location.href = "mall.html";
});
//商家结束
//登录
var islogin = 0;
var isrepeat = 1;
var isQx = 1;//目标用户
$(".qJuan").bind("click",function(){
	if(islogin==1){
		if(isQx==1){
			$(".rs img").attr("src","image/qx.png");
			$(".hide").show();
			$(".rs").show();
			$(document.body).css("overflow","hidden");
		}else{
		if(isrepeat==1){
			$(".rs img").attr("src","image/lose.png");
			$(".hide").show();
			$(".rs").show();
			$(document.body).css("overflow","hidden");
		}else{
			$(".rs img").attr("src","image/success.png");
			$(".hide").show();
			$(".rs").show();
			$(document.body).css("overflow","hidden");
			isrepeat = 1;
		}
		}
	}else{
		$(".hide").show();
		$(".login").show();
		$(document.body).css("overflow","hidden");
	}
});
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
oBtn.onclick = function (ev) {
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
      oBtn.innerHTML = '获取验证码';
    }
  }, 1000);

}
$(".loginS").bind("click",function(){
	var tel=$("input[name='tel']").val();//电话
	var num=$("input[name='num']").val();//验证码
	var r = /^\+?[1-9][0-9]*$/;
    var tr=r.test(tel);
    var tn=r.test(num);
    if(tel!=null && num != null && tr && tn){
			alert("登录成功");
			$(".hide").hide();
			$(".login").hide();
			islogin = 1;
			isrepeat = 0;//查询是否重复领奖了
			isQx=0;
		}else{
			alert("请输入手机号码或者验证码");
		}
});
$("#close").bind("click",function(){
	$(".hide").hide();
	$(".rs").hide();
	$(document.body).css("overflow-y","scroll");
});
}