window.onload = function(){
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

	var data = [{
		"time":"2017/9/10 06:12:23",
		"state":"打卡成功",
		"liuL":"+10M",
	},{
		"time":"2017/9/10 06:12:23",
		"state":"兑换奖品",
		"liuL":"-10M",
	},{
		"time":"2017/9/10 06:12:23",
		"state":"打卡成功",
		"liuL":"+10M",
	},{
		"time":"2017/9/10 06:12:23",
		"state":"兑换奖品",
		"liuL":"-10M",
	}]
	var myName = "微信昵称";
	var img = "image/cs.png";
	var nowLl = 200;//当前流量
	$(".wxName").text(myName);
	$(".nowLl span").text(nowLl+"M");
	$(".nd img").attr("src",img);

	var xQlist = $(".xQList ul");
	for(var i = 0;i<data.length;i++){
		var li;
		li = $("<li>"+
					"<div class='divS'></div>"+
					"<p class='p1'>"+data[i].time+"</p>"+
					"<p class='p3'>"+data[i].state+"</p>"+
					"<p class='p2'><span id='xQLower'>"+data[i].liuL+"</span>流量</p>"+
				"</li>")
		xQlist.append(li);
	}
}