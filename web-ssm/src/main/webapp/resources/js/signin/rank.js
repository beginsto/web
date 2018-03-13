$(document).ready(function () {

    $.ajax({
        url:'getRankUserInfo',
        type:'post',
        data:{openid:$('#openid').val()},
        dataType:'',
        success:function (result) {
            //console.log(data);
            if (result.resultCode == 200){
                var data = JSON.parse(result.data) ;
                //console.log(data.award)
                $("#flow").text("总流量:"+data.award+"M");
                $("#myflowTime").text(data.signintime);
                $("#myflowDay").text(data.count+"天");
                $("#myflowNum").text(data.awardtoday+"M");
                $(".head .nd img").attr("src",data.headimg);
            }

        }

    });



    var peoNum = 21342;//总参与人数
    var img = "image/cs.png";
    $(".mid p").text("共有"+peoNum+"人参加早起计划");

    // 页数
    var page = 0,page_w=0,page_p=0;
    // 每页展示5个
    var size = 5;
    var flag_f=true,flag_w=true,flag_p=true;
    $(".contentList ul").scroll(function() {
        if(flag_f){
            loadFriend()
        }
    });

    $(".rankList ul").scroll(function() {
        if(flag_w){
            loadWorld()
        }
    });

    $(".humanList ul").scroll(function() {
        if (flag_p){
            loadPolularity()
        }

    });
    $(".contentList ul").scroll();
    $(".rankList ul").scroll();
    $(".humanList ul").scroll();
    
    function loadFriend() {
        flag_f=false;
        page++;
        // 拼接HTML
        var str = '';
        $.ajax({
            type: 'GET',
            url: 'getRankFriendData?page='+page+'&size='+size+'&openid='+$('#openid').val(),
            dataType: 'json',
            success: function(result){
                if (result.resultCode == 200){
                    var data =JSON.parse(result.data);
                    if (page == 1){
                        var user = JSON.parse(data.user);
                        $(".contentHead span img").attr("src",user.headimg);
                        $(".wName").text(user.nickname);
                        $(".todayDk").text(data.day+"打卡时间为"+data.time+" "+";在好友中排行第"+data.rankself+"位");

                    }

                    var rank = JSON.parse(data.rank);
                    if (rank.length > 0){
                        $.each(rank,function (index,content) {
                            var headimg=content.headimg==null?'':content.headimg,
                                nickname=content.nickname==null?'':content.nickname,
                                time=content.signintime==null?'':content.signintime,
                                islike=content.islike==null?0:content.islike,
                                amount=content.amount==null?0:content.amount;

                            if (islike == 1){
                                str += "<li>"+
                                    "<input type='hidden' class='friendid' value='"+content.friendid+"' />" +
                                    "<span class='pm'>"+((page-1)*size+index+1)+"</span>"+
                                    "<span class='touXiang'><img src='"+headimg+"'/></span>"+
                                    "<p class='nameWx'>"+nickname+"</p>"+
                                    "<p class='timeT'>"+time+"</p>"+
                                    "<span class='dZan'><img class='zd like' src='../images/signin/zc.png'/></span>"+
                                    "<span class='zanNum'>"+amount+"</span>"+
                                    "<div class='xian'></div>"+
                                    "</li>";
                            }else{
                                str += "<li>"+
                                    "<input type='hidden' class='friendid' value='"+content.friendid+"' />" +
                                    "<span class='pm'>"+((page-1)*size+index+1)+"</span>"+
                                    "<span class='touXiang'><img src='"+headimg+"'/></span>"+
                                    "<p class='nameWx'>"+nickname+"</p>"+
                                    "<p class='timeT'>"+time+"</p>"+
                                    "<span class='dZan'><img class='zd' src='../images/signin/zh.png'/></span>"+
                                    "<span class='zanNum'>"+amount+"</span>"+
                                    "<div class='xian'></div>"+
                                    "</li>";
                            }

                        })



                    }

                    $('.contentList ul').append(str);
                    $('.zd').bind("click",function () {
                        var _this = $(this);
                        if (_this.hasClass('like')){
                            _this.removeClass('like');
                            _this.attr("src","../images/signin/zh.png")
                            var index =$('.zd').index(this)
                            var num = parseInt($('.zanNum').eq(index).text()==null?0:$('.zanNum').eq(index).text()) - 1;
                            $('.zanNum').eq(index).text(num)
                            like(0,$('.friendid').eq(index).val())
                        }else{
                            _this.addClass('like');
                            _this.attr("src","../images/signin/zc.png")
                            console.log($('.zd').index(this));
                            var index =$('.zd').index(this)
                            var num = parseInt($('.zanNum').eq(index).text()==null?0:$('.zanNum').eq(index).text()) + 1;
                            $('.zanNum').eq(index).text(num)
                            like(1,$('.friendid').eq(index).val())
                        }

                    })
                    flag_f=true;
                }
            },
            error: function(xhr, type){
                alert('数据载入异常...请刷新!');
            }
        });
    }

    var flag=true;
    function like(islike,friendid) {
        if (flag){
            flag = false;
            $.ajax({
                url:'like',
                type:'post',
                data:{openid:$('#openid').val(),friendid:friendid,islike:islike},
                dataType:'json',
                success:function (result) {
                    flag=true;
                }
            })
        }else{
            alert("您操作太快了...")
        }
    }

    function loadWorld(me) {
        flag_w=false;
        page_w++;
        // 拼接HTML
        var str = '';
        $.ajax({
            type: 'post',
            url: 'getRankWorldData?page='+page_w+'&size='+size+'&openid='+$('#openid').val(),
            dataType: 'json',
            success: function(result){
                if (result.resultCode == 200){
                    var data =JSON.parse(result.data);
                    if (page_w == 1){
                        var user = JSON.parse(data.user);
                        $(".rankNum").text(data.rankself);
                        $(".rankTxiang img").attr("src",user.headimg);
                        $(".rankName").text(user.nickname);
                        $(".rankDk").text(user.signintime);

                    }

                    var rank = JSON.parse(data.rank);
                    if (rank.length > 0){
                        $.each(rank,function (index,content) {
                            if(index==0 && page_w == 1)
                                str+="<li><span class='rankPh'><img src='../images/signin/1.png'/></span>";
                            else if(index==1 && page_w == 1)
                                str+="<li><span class='rankPh'><img src='../images/signin/2.png'/></span>";
                            else if(index==2 && page_w == 1)
                                str+="<li><span class='rankPh'><img src='../images/signin/3.png'/></span>";
                            else
                            	str+="<li><span class='rankPh'>"+((page_w-1)*size+index + 1)+"</span>";

                            str+="<span class='rankTouX'><img src='"+content.headimg+"'/></span>"+
                                "<p class='rankNameWx'>"+content.nickname+"</p>"+
                                "<p class='rankTimeT'>"+content.signintime+"</p>"+
                                "<div class='rankXian'></div>"+
                                "</li>";
                        })
                    }

                    $('.rankList ul').append(str);
                    flag_w=true;

                }
            },
            error: function(xhr, type){
                alert('Ajax error!');
            }
        });
    }


    function loadPolularity() {
        flag_p=false;
        page_p++;
        // 拼接HTML
        var str = '';
        $.ajax({
            type: 'GET',
            url: 'getRankPopularityData?page='+page_p+'&size='+size+'&openid='+$('#openid').val(),
            dataType: 'json',
            success: function(result){
                if (result.resultCode == 200){
                    var data =JSON.parse(result.data);
                    if (page_p == 1){
                        var user = JSON.parse(data.user);
                        $(".humanQiHead img").attr("src",user.headimg);
                        $(".humanQiHead .qiName").text(user.nickname);
                        $(".humanQiHead .humanQiNum").text(data.amount+'人膜拜');

                    }

                    var rank = JSON.parse(data.rank);
                    if (rank.length > 0){
                        $.each(rank,function (index,content) {
                            if(index==0 && page_p == 1)
                                str+="<li><span class='humanPh'><img src='../images/signin/1.png'/></span>";
                            else if(index==1 && page_p == 1)
                                str+="<li><span class='humanPh'><img src='../images/signin/2.png'/></span>";
                            else if(index==2 && page_p == 1)
                                str+="<li><span class='humanPh'><img src='../images/signin/3.png'/></span>";
                            else
                                str+="<li><span class='humanPh'>"+((page_p-1)*size+index + 1)+"</span>";

                            str+="<span class='humanTouX'><img src='"+content.headimg+"'/></span>"+
                                "<p class='humanNameWx'>"+content.nickname+"</p>"+
                                "<p class='humanNum'>"+content.amount+"</p>"+
                                "<div class='humanXian'></div>";
                        })
                    }
                    $('.humanList ul').append(str);
                    flag_p=true;
                }
            },
            error: function(xhr, type){
                alert('Ajax error!');
            }
        });
    }
});



window.onload = function(){
	$("#inviteF").bind("click",function(){
		$(".hide").show();
		$(".zf").show();
		$("body").css("overflow-y","hidden");
	});
	$("#close").bind("click",function(){
		$(".hide").hide();
		$(".zf").hide();
		$("body").css("overflow-y","scroll");
	});

	//切换标签
	$("#rankFriend").bind("click",function(){
		$(".active").removeClass("active");
		$(this).addClass("active");
		$(".content").css("display","block");
		$(".contentRank").css("display","none");
		$(".humanQi").css("display","none");
	});
	$("#rankAll").bind("click",function(){
		$(".active").removeClass("active");
		$(this).addClass("active");
		$(".contentRank").css("display","block");
		$(".humanQi").css("display","none");
		$(".content").css("display","none");
	});
	$("#rankPop").bind("click",function(){
		$(".active").removeClass("active");
		$(this).addClass("active");
		$(".humanQi").css("display","block");
		$(".contentRank").css("display","none");
		$(".content").css("display","none");
	});

	//点赞结束
   function excludeSpecial(s) {
		// 去掉转义字符
		s = s.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
		// 去掉特殊字符
		s = s.replace(/[\@\#\$\%\^\&\*\{\}\:\"\L\<\>\?]/);
		return s;
	}
}

