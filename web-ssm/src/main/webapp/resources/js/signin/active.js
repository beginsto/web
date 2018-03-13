window.onload = function(){
    var flag = true;
    var arr = "../images/signin/cs.png";//图片数组
    for (var i = 0;i<10;i++) {
        var $li;
        $li = $("<li>"+"<span>"+"<img src='"+arr+"'/>"+"</span>"+"</li>");
        if(i<9){
            $(".oneUl").append($li);
        }else{
            var $lil = $("<li>"+"<span>"+"..."+"</span>"+"</li>");
            $(".oneUl").append($lil);
        }
    }

    $.ajax({
        url:'getData',
        type:'post',
        data:{openid:$("#openid").val()},
        dataType:'json',
        success:function (data) {
            //console.log( JSON.stringify(data))
            arr = data.participator;
            $("#rens").text(data.count);

            var _oneUl = $('.oneUl');
            var _li_one = _oneUl.find('li');
            $.each(data.participator,function(index,content){
                if (index < 9)
                    _li_one.eq(index).find('img').attr("src",content.headimg);
            });
        }

    })
    var myDay
    $.ajax({
        url:'getActiveData',
        type:'post',
        data:{openid:$("#openid").val()},
        dataType:'json',
        success:function (data) {
            //console.log( JSON.stringify(data))
            $(".head .nd").attr("src",data.headimg);
            $(".head p").text(data.nickname);
            myDay = data.count;//得到天数
            $(".context .p1 span").text(myDay);
            if (data.isSign){
                window.location.href="detail?openid="+$("#openid").val();
            }

        }
    })

    var myDate = new Date();
    var year=myDate.getFullYear();
    var month=myDate.getMonth()+1;
    var date=myDate.getDate();
    var h=myDate.getHours();
    var dateRs = month+'/'+date;
    $(".context .p2").text("时间:"+dateRs+" 5:00-10:00");
    if(h>20||h<5){
        $("#nyYn").text("活动未开始");
        $("#nyYn").css("background","rgb(179,179,179)");
    }else{
        $("#nyYn").bind("click",function(){
            if (flag){
                flag=false;
                $.ajax({
                    url:'signin',
                    type:'post',
                    data:{openid:$("#openid").val()},
                    dataType:'json',
                    success:function (data) {
                        if (data.resultCode == 200){
                            $(".hide").show();
                            $(".dk").show();
                            if (myDay<10) {
                                myDay = '0' + myDay;
                            }
                            $("#num").text(myDay);
                            $("#num1").text("今天是您打卡的第"+myDay+"天");
                        }else if(data.resultCode == 300){

                        }else{

                        }
                    }
                });
            }
        });
    }


	$("#closeDk").bind("click",function(){
		$("#nyYn").text("查看今日排名");
		$("#nyYn").unbind();
		$("#nyYn").bind("click",function(){
			window.location.href = "rank?openid="+$("#openid").val();
		});
		$(".hide").hide();
		$(".dk").hide();
	});

	$("#rule").bind("click",function(){
  		window.location.href = "rule";
	})
	var resr = window.location.search.substring(1);
	var rr = resr.substring(0,2);
	 if(rr == "ph"){
	 	$("#nyYn").text("查看今日排名");
	 	$("#nyYn").css("background","rgb(247,147,30)")
	 	$("#nyYn").bind("click",function(){
	 		window.location.href = "rank.html";
	 	});
	 }else if(rr=="wk"){
	 	$("#nyYn").text("活动未开始");
		$("#nyYn").css("background","rgb(179,179,179)");
	 }
}