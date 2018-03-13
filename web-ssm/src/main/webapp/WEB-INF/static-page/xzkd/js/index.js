window.onload = function(){
	var phone=''
 	var reg = /^1[3|4|5|8|7][0-9]\d{8}$/
	var data = ["56","26","12","20","42","32","49","5"];//3r 6r 200m 15r 500m 30r 60r 150r
	var dataR = ["3元","6元","3元流量闪充包","15元","6元流量闪充包","30元","60元","150元"];//概率 30,30,13,13,9,4,0.9,0.1
	var set = false; //是否抽过奖了！后台提取
	var ret = false;//转动过程中防止2次点击
	var log =0;//是否登录了
	$("#zz").bind("click",function(){
		if (log==1) {

			$.ajax({
				url:'../../xzkd/raffle',
				type:'post',
				data:{tel:$("#phone").val()},
				dataType:'json',
				success:function (rs) {
					if (rs.resultCode  == 200) {
                        getTurn(data[rs.message], dataR[rs.message]);
                    }else if (rs.resultCode  == 300)
						alert("服务器开小差了...");
                    else if (rs.resultCode  == 303)
                        alert("您已经抽过奖啦...");
                    else if (rs.resultCode  == 404)
                        alert("您打开链接的姿势不对...");
                    else if (rs.resultCode  == 500)
                        alert("服务器开小差了...");
                }
			});
			/*var st=myRandom();
			var gif= dataR[st-1];
			var l = data[st-1];
			if(ret){
				return;
			}
			if(set){
				$(".hide").show();
				$(".nogift").show();
				return;
			}
			ret = true;
			getTurn(l,gif);
			set = true;	//并且传入后台已经抽过奖	*/
		}else{
			$(".hide").show();
			$(".login").show();
		}
	});
	$("#closeGift").bind("click",function(){
		$(".hide").hide();
		$(".gift").hide();
	});
	$("#closenoGift").bind("click",function(){
		$(".hide").hide();
		$(".nogift").hide();
	});
//转盘
function getTurn(arg,arg1){
	var num = 1;
	var n=0;
	var rs = 180 + parseInt(arg);
	timer=setInterval(function(){
		num++;
		n=num*6
		$(".head").css("transform","rotate("+n+"deg)");
		if(num==180){
			clearInterval(timer);
			timer2=setInterval(function(){
				num++
				n=num*6
				$(".head").css("transform","rotate("+n+"deg)");
				if(num==rs){
					clearInterval(timer2);
					timer3=setInterval(function(){

						$("#getGift").text(arg1);
						$(".hide").show();
						$(".gift").show();
						clearInterval(timer3);
						ret = false;
					},600)

				}
			},20);
		}
	},10);
}
//验证码
	function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }
    var oBtn = document.getElementById('yzm');
    var set1 = false;
   /*$('#yzm').bind('click',function(){
   		phone=$("").val();
   		if (isNum($().val())) {

   		}
        if (set1) {
            return;
        }
        set1 = true;
        var num = 60;
        oBtn.innerHTML = num + 'S后重新获取';
        timer5 = setInterval(function () {
            num--;
            oBtn.innerHTML = toDouble(num) + 'S后重新获取';
            if (num == 0) {
                clearInterval(timer5);
                set1 = false;
                oBtn.innerHTML = '重新获取';
            }
        }, 1000);

	})*/
//登录
	$("#tj").bind("click",function(){
		//var tel = $("input[name='tel']").val();
		var num = $("input[name='num']").val();

		if(num!= '' && num == $("#validate_code").val()){
            log=1;
            $(".hide").hide();
            $(".login").hide();
		}else{
			alert("验证有误...")
		}
		/*var r = /^\+?[1-9][0-9]*$/;
		var tr=r.test(tel);
		var tn=r.test(num);
		if(tel!=null && num != null && tr && tn){
			//submit();
			alert("登录成功");
		}else{
			alert("请输入手机号码或者验证码");
		}*/
	})
	
}

function isNum(mobile){
	if (!reg.test(mobile)) 
		return false;
}

//概率
function myRandom(){
	var rm=Math.random();
	if(0< rm < 0.3 ) return 1;
	if(0.3 < rm < 0.6) return 2;
	if(0.6 < rm < 0.73) return 3;
	if(0.73 < rm < 0.86) return 4;
	if(0.86 < rm < 0.95) return 5;
	if(0.95 < rm < 0.99) return 6;
	if(0.99 < rm < 0.998) return 7;
	if(0.998 < rm < 1) return 8;
}

