window.onload = function() {
	function stopBuble(ev) {
						if(ev&& ev.stopPropagation) {
							ev.stopPropagation();
						}else{
							window.event.cancelBubble=true;
						}
					}
	var data = [{
		"Day":"10月09日 5:00",
		"state":"打卡成功",
	},{
		"Day":"10月10日 5:00",
		"state":"已错过",
	},{
		"Day":"10月11日 5:00",
		"state":"打卡成功",
	},{
		"Day":"10月12日 5:00",
		"state":"已错过",
	},{
		"Day":"10月13日 5:00",
		"state":"打卡成功",
	},{
		"Day":"10月14日 5:00",
		"state":"null",
	},{
		"Day":"10月15日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},{
		"Day":"10月16日 5:00",
		"state":"未开始",
	},]
	var myStartTime = "2017/10/12";//得到开始参加的日期
	var myStartMonth = 10 ;//月
	var myStartDay = 9;//日
	//var myStartDk = 7:15;
    var arr = "../images/signin/cs.png";//图片数组
	var howP = 32143;//总人数
	$(".head p").text(myStartTime+" "+"开始");

//头像列表
    for (var i = 0;i<20;i++) {
        var $li;
        $li = $("<li>"+"<span>"+"<img src='"+arr+"'/>"+"</span>"+"</li>");
        if(i<10){
            $(".oneUl").append($li);
        }
        if(i>9 && i<19){
            $(".twoUl").append($li);
        }
        if(i == 19){
            $lil = $("<li>"+"<span>"+"..."+"</span>"+"</li>");
            $(".twoUl").append($lil);
            break;
        }
    }
//头像列表结束

    $.ajax({
        url:'getData',
        type:'post',
        data:{openid:$("#openid").val()},
        dataType:'json',
        success:function (data) {
            //console.log( JSON.stringify(data))
            arr = data.participator;
            $("#howP").text(data.count);
            $(".head p").text(data.starttime+" "+"开始");
            var _oneUl = $('.oneUl');
            var _twoUl = $('.twoUl');
            var _li_one = _oneUl.find('li');
            var _li_two = _twoUl.find('li');
            $.each(data.participator,function(index,content){
                if (index < 10)
                    _li_one.eq(index).find('img').attr("src",content.headimg);
                else
                    _li_two.eq(index-10).find('img').attr("src",content.headimg);
                //console.log("this man's no.is" + index + ",and the imgurl is " + content.headimg)
            });
        }

    })

	$("#ruleD").bind("click",function(){
		window.location.href = "rule.html";
	});
	$("#invite").bind("click",function(){
		$(".hide").show();
		$(".zf").show();
	});
	$("#close").bind("click",function(){
		$(".hide").hide();
		$(".zf").hide();
	});
//日程列表
	var dayListWord = $("#dayListWord");
	$.ajax({
        url:'getSignList',
        type:'post',
        data:{openid:$("#openid").val()},
        dataType:'json',
        success:function (data) {
            //console.log( JSON.stringify(data.json))
            if (data.isMall){
                $("#mall").bind("click",function(){
                    window.location.href = "mall.html";
                });
			}else{
                $("#mall").css("background","rgb(179,179,179)");
			}
            var _li;
            $.each(data.json,function(index,content){
            	if (content.state == '打卡成功'){
                    _li= $("<li><div class='aready'><span>"+(index+1)+"</span><p class='p1'>"+content.day+"</p><p class='p2'>21天早起达人计划&nbsp;第"+(index+1)+"天</p><a id='res' href='#''>"+content.state+"</a></div></li>");
				}else if(content.state == '已错过'){
                    _li= $("<li><div class='miss'><span>"+(index+1)+"</span><p class='p1'>"+content.day+"</p><p class='p2'>21天早起达人计划&nbsp;第"+(index+1)+"天</p><a id='res' href='#''>"+content.state+"</a></div></li>");
				}else if(content.state.indexOf('小时后开始') >= 0){
                    _li = $("<li><div class='getStart'><span>"+(index+1)+"</span><p class='p1'>"+content.day+"</p><p class='p2'>21天早起达人计划&nbsp;第"+(index+1)+"天</p><a id='res' href='#''>"+content.state+"</a></div></li>");
				}else if(content.state == '未开始'){
                    _li= $("<li><div class='noStart'><span>"+(index+1)+"</span><p class='p1'>"+content.day+"</p><p class='p2'>21天早起达人计划&nbsp;第"+(index+1)+"天</p><a id='res' href='#''>"+content.state+"</a></div></li>");
                }else if (content.state == '进行中'){
                    _li= $("<li><div class='aready'><span>"+(index+1)+"</span><p class='p1'>"+content.day+"</p><p class='p2'>21天早起达人计划&nbsp;第"+(index+1)+"天</p><a id='res' href='#''>"+content.state+"</a></div></li>");
				}
                dayListWord.append(_li);
            });

        }

	})
	var res = document. getElementsByClassName("aready");
	var resl = document. getElementsByClassName("getStart");
	for(var i=0;i<res.length;i++){
		$(res[i]).on("click",function(){
			var m = $(this).text();
			var ms = m.substring(2,9);
			var vp = ms.substring(0,2);
			var vp1 = ms.substring(3,5);
			var pp = vp + vp1;
			window.location.href = "active.html?ph&&rq="+pp;
		});
	}
	for(var i=0;i<resl.length;i++){
		$(resl[i]).on("click",function(){
			var m = $(this).text();
			var ms = m.substring(2,9);
			window.location.href = "active.html?wk";
		});
	}

//日程列表结束
	 var resr = window.location.search.substring(1);
	 if(resr == "invite"){
	 	$(".hide").show();
	 	$(".zf").show();
	 }
}

