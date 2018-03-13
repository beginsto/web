window.onload = function(){


    var now = new Date()
    var day = now.getDate()
    var hour = now.getHours();



	var select_area_id='', isSelect,evalId=''
    var imgarr = ['my','jbmy','bmy']
    var unimgarr = ['hmy','hjbmy','hbmy']
	var sp = ['spN','spX','spJ']
	var _all = $('.all')
	var _qiehuan = _all.find('>.qiehuan')
    var _pa = _all.find('.pa')
    var _ul = _all.find('ul')
    var _piaoDiv = _all.find('.piaoDiv')
    var _nhqX = _all.find('.nhqX')
	var _pjDiv = _all.find('.pjDiv')
	var openid = $('#openid')
	var tk = $('#token')
	var issue = $('#issue')
    var areaId
	$.ajax({
		url:'getData',
		data:{openid:openid.val(),areaId:0},
		dataType:'json',
		success:function (data) {
           // console.log(JSON.stringify(data));
			if (data.resultCode == 200 && data.data != null){
                var result  = JSON.parse(data.data)
                if (result.pid != null )
                    select_area_id = result.pid.split(",")
                if (result.evaluate != null )
                    evalId = result.evaluate.split(",")
                if(result.list == undefined || result.list == null){
                    window.location.href="http://jxyd2.1860.cn:9090/jxwx_static/building/index.html";
                    return;
                }
                var resultContent = JSON.parse(result.list)
                $.each(resultContent,function (index,content) {
                    _qiehuan.append('<span>'+content.area+'</span>' )
                    var sp = _qiehuan.find('span')
                    if (index ==  0)
                        sp.eq(0).addClass('active')
                    sp.eq(index).bind('click',function () {
                        removeClass(sp)
                        $(this).addClass('active')

                        areaId = content.id
                        //二次进入，载入已投信息
                       // console.log(select_area_id)
                        if (select_area_id != undefined && select_area_id != null && select_area_id.length>0 ){

                            for(var p=0;p<select_area_id.length;p++){
                                var _img = _pjDiv.find('img')
                                if(p == 0){
                                    _img.eq(0).attr("src",'http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+unimgarr[0]+'.png')
                                    _img.eq(1).attr("src",'http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+unimgarr[1]+'.png')
                                    _img.eq(2).attr("src",'http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+unimgarr[2]+'.png')
                                }


                                if (select_area_id[p] == content.id){
                          //          console.log(select_area_id[p]+"||"+content.id)
                                    _img.eq(evalId[p]).attr("src",'http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+imgarr[evalId[p]]+'.png')
                                }


                            }
                        }
                        issue.val(content.issue)
                        _pa.text(content.title)
                        var img_banner = content.imgBanner.split(",")
                        var _html = ''
                        for (var i=0;i<img_banner.length;i++){
                            _html +=  '<li><div class="tu"><img src="http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+img_banner[i]+'"></div></li>'
                        }

                        _ul.html(_html);
                        _piaoDiv.find('span').text(content.amount==null?0:content.amount)
                      //  _piaoDiv.find('.areaId').val()

                        //console.log(index+'||'+content.id)
                        _nhqX.find('.p1').text('['+content.area+']')
                        _nhqX.find('.p2').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+content.introduce)
                        _nhqX.find('.p3').text(content.bottomTitle)
                        if (content.imgTop != null){
                            _nhqX.find('.nhq-1').css('display','block')
                            _nhqX.find('.nhq-1').attr('src','http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+content.imgTop)
                        }
                        else
                            _nhqX.find('.nhq-1').css('display','none')

                        if (content.imgBottom != null && content.imgBottom != ''){
                            _nhqX.find('.nhq-2').css('display','block')
                            _nhqX.find('.nhq-2').attr('src','http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+content.imgBottom)
                        }
                        else
                            _nhqX.find('.nhq-2').css('display','none')

                    })
                    sp.eq(0).click()
					$('.loading').hide();
                })


                bindClick(_piaoDiv)
                bindEvaluate(_pjDiv)
                bindAnimate()

			}else{//异常状态
				alert("数据载入异常...请稍后再试...")

			}
        },
		error:function (res) {
			//console.log(res)
            alert("数据载入异常...请稍后再试...")
        }
	})

    function removeClass() {
        var obj = $('.qiehuan').find('span')
        $.each(obj,function (index,content) {
            if(obj.eq(index).hasClass('active'))
                obj.eq(index).removeClass('active')

        })
    }

	function bindClick(obj) {

		obj.bind('click',function () {
			$.ajax({
				url:'vote',
				data:{openid:openid.val(),tk:tk.val(),areaId:areaId,issue:issue.val()},
				dataType:'json',
				success:function (data) {
					if (data.resultCode == 200){
                      //  select_area_id = areaId
                        isSelect = true

						var _this =  obj.find('isGet')
                        _this.css("background","url('http://jxyd2.1860.cn:9090/jxwx_static/img/mro/hright.png') center no-repeat");
                        _this.css("background-size","100%");
                        _this.text("已投票");
                        $(".rs img").attr("src","http://jxyd2.1860.cn:9090/jxwx_static/img/mro/success.png");
                        $(".rs").show();

                        var n =  _piaoDiv.find('span').text();
                        _piaoDiv.find('span').text(parseInt(n)+1)
					}else{
                        var _this =  obj.find('isGet')
						if (data.message == 20000){
                            _this.css("background","url('image/hright.png') center no-repeat");
                            _this.css("background-size","100%");
                            _this.text("已投票");
                            $(".rs img").attr("src","http://jxyd2.1860.cn:9090/jxwx_static/img/mro/false.png");
                            $(".rs").show();
						}else{
							alert(data.message)
						}
					}
                },
				error:function (res) {
                    alert("小伙伴们太热情，服务器被挤爆了")
                }
			})
		})


    }

    function bindEvaluate(_pjDiv){
		var d = _pjDiv.find('div')


		//console.log(s)
		$.each(d,function (index,content) {
			d.eq(index).bind('click',function () {
				_this = $(this)
				//if(select_area_id != null && aid != select_area_id){
               //     alert("只可在当前投票的单位进行满意度评价哦~");
                //    return;
				//}
				if (index == 3){
				    //var f = true
				   // for (var q=0;q<select_area_id.length;q++){
                   //     if(aid == select_area_id[q])
                     //       f = false;
                   // }
					//if(f){
                    //    alert("投票之后才能吐槽哦。。。")
                   // }else
                    window.location.href = "advice?pid="+areaId+"&openid="+openid.val()+"&issue="+issue.val();
                    return;
				}
				$.ajax({
					url:'evaluate',
					data:{openid:openid.val(),areaId:areaId,issue:issue.val(),ev:index},
					dataType:'json',
					success:function (data) {
						if (data.resultCode == 200){
                            _this.find('img').attr("src",'http://jxyd2.1860.cn:9090/jxwx_static/img/mro/'+imgarr[index]+'.png')
                            console.log(select_area_id)
                            if (select_area_id == ''){
                                select_area_id = new Array()
                                select_area_id.push(areaId)
                            }else{
                                select_area_id.push(areaId)
                            }

                            if (evalId == ''){
                                evalId = new Array(index)
                                evalId.push(index)
                            }else{
                                evalId.push(index)
                            }


						}else{
							if(data.message == 20000){
                                alert("请勿重复评价哦~");
                                return;
							}else{
                               alert(data.message)
							}
						}
                    },
					error:function () {
                        alert("小伙伴们太热情，服务器被挤爆了")
                    }
				})
            })
        })
	}


    function bindAnimate(){

        var a = $('#nhqT').find('li').length,
            b = $('#xzqT').find('li').length,
            c = $('#jsxT').find('li').length
        var liwidth = $(".da .turnT li").width();
        var n = 0;
        timerN = setInterval(function(){
            n++;
            var l = n * liwidth;
            var s = n + 1;
            if(n<a){
                $("#nhqT").animate({marginLeft:"-"+l},500);
                timer = setInterval(function(){
                    $(".spN .red").removeClass("red");
                    $(".spN .sp"+s).addClass("red");
                    $(".spN .sp"+s).css("display","block")
                    //
                    clearInterval(timer);
                },300);
            }else{
                $("#nhqT").css("margin-left","0rem");
                $(".spN .red").removeClass("red");
                $(".spN .sp1").addClass("red");
                $(".spN .sp1").css("display","block")
                n = 0;
            }
        },2000);
        var x = 0;
        timerX = setInterval(function(){
            x++;
            var ll = x * liwidth;
            var s1 = x + 1;
            if(x<b){
                $("#xzqT").animate({marginLeft:"-"+ll},500);
                timer1 = setInterval(function(){
                    $(".spX .red").removeClass("red");
                    $(".spX .sp"+s1).addClass("red");
                    $(".spX .sp"+s1).css("display","block")
                    clearInterval(timer1);
                },300);
            }else{
                $("#xzqT").css("margin-left","0rem");
                $(".spX .red").removeClass("red");
                $(".spX .sp1").addClass("red");
                $(".spX .sp1").css("display","block")
                x = 0;
            }
        },2000);
        var j = 0;
        timerJ = setInterval(function(){
            j++;
            var lll = j * liwidth;
            var s2 = j + 1;
            if(j<c){
                $("#jsxT").animate({marginLeft:"-"+lll},500);
                timer2 = setInterval(function(){
                    $(".spJ .red").removeClass("red")
                    $(".spJ .sp"+s2).addClass("red")
                    $(".spJ .sp"+s2).css("display","block")
                    clearInterval(timer2);
                },300);
            }else{
                $("#jsxT").css("margin-left","0rem");
                $(".spJ .red").removeClass("red");
                $(".spJ .sp1").addClass("red");
                $(".spJ .sp1").css("display","block")
                j = 0;
            }
        },2000);
	}

    $(".ruleBtn").bind("click",function(){
        $(".ruleDiv").show();
    });
    $(".closeRule").bind("click",function(){
        $(".ruleDiv").hide();
    });
    $(".fow").bind("click",function(){
        $(".rs").hide();
        $(".forw").show();
    });
    $(".guan").bind("click",function(){
        $(".forw").hide();
    });
    $(".closeRs").bind("click",function(){
        $(".rs").hide();
    });

	$(".turn-N").bind("click",function(){
		$(".active").removeClass("active");
		$(".turn-N").addClass("active");
		$(".page-1").show();
		$(".page-2").hide();
		$(".page-3").hide();
	});
	$(".turn-X").bind("click",function(){
		$(".active").removeClass("active");
		$(".turn-X").addClass("active");
		$(".page-1").hide();
		$(".page-2").show();
		$(".page-3").hide();
	});
	$(".turn-J").bind("click",function(){
		$(".active").removeClass("active");
		$(".turn-J").addClass("active");
		$(".page-1").hide();
		$(".page-2").hide();
		$(".page-3").show();
	});




}