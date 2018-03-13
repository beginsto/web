window.onload = function(){
	var myName = "微信昵称";
	var img = "image/cs.png";
	var nowLl = 200;//当前流量
	$(".wxName").text(myName);
	$(".nowLl span").text(nowLl+"M");
	$(".nd img").attr("src",img);
	var qZero = "07天13小时22分";
	var qday = qZero.substring(0,2);
	var qHour = qZero.substring(3,5);
	var qMiu = qZero.substring(7,9);
	$(".llNo span").text(qday+"天"+qHour+"小时"+qMiu+"分");
	//alert(qday+qHour+qMiu);
	timer = setInterval(function(){
		qMiu--
		if(qMiu==0){
			qHour = parseInt(qHour) - 1;
			qMiu=59;
			if(qHour==0){
				qday = parseInt(qday) - 1;
				qHour = 23;
				if(qday==0){
					clearInterval(timer);
				}
			}
		}
		$(".llNo span").text(qday+"天"+qHour+"小时"+qMiu+"分");
	},60000);

	var one = 1334;
	var two = 1234;
	var three = 5234;
	var five = 2234;
	var oneG = 6234;
	$(".giftOne .p2 span").text(one);
	$(".giftTwo .p2 span").text(two);
	$(".giftThree .p2 span").text(three);
	$(".giftFour .p2 span").text(five);
	$(".giftFive .p2 span").text(oneG);
	$("#gift100M").bind("click",function(){
		$(".hide").show();
		$(".dhuan").show();
		timer = setInterval(function(){
			$(".hide").hide();
			$(".dhuan").hide();
			clearInterval(timer);
		},3000);
	});
	$("#gift200M").bind("click",function(){
		$(".hide").show();
		$(".dhuan").show();
		timer = setInterval(function(){
			$(".hide").hide();
			$(".dhuan").hide();
			clearInterval(timer);
		},3000);
	});
	$("#gift300M").bind("click",function(){
		$(".hide").show();
		$(".dhuan").show();
		timer = setInterval(function(){
			$(".hide").hide();
			$(".dhuan").hide();
			clearInterval(timer);
		},3000);
	});
	$("#gift500M").bind("click",function(){
		$(".hide").show();
		$(".dhuan").show();
		timer = setInterval(function(){
			$(".hide").hide();
			$(".dhuan").hide();
			clearInterval(timer);
		},3000);
	});
	$("#gift1G").bind("click",function(){
		$(".hide").show();
		$(".dhuan").show();
		timer = setInterval(function(){
			$(".hide").hide();
			$(".dhuan").hide();
			clearInterval(timer);
		},3000);
	});
	$("#llxq").bind("click",function(){
		window.location.href = "llxq.html";
	});
	$("#dhjl").bind("click",function(){
		window.location.href = "change.html";
	});
}