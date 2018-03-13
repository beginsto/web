window.onload = function(){
	var arry = ["image/team/hn/xs/sfd.png","image/team/hn/xs/sfd.png","image/team/hn/xs/sfd.png"]; //每组id[oneG,twoG,threeG,fourG,fiveG,sixG,sevenG,eightG,nineG,tenG,elevenG,tweG]
	var team_id = ['oneG','twoG','threeG','fourG','fiveG','sixG','sevenG','eightG','nineG','tenG','elevenG','tweG'];
	//groupRank("oneG",arry);
//最简单的跳转
	$("#oneG").bind("click",function(){
			window.location.href = "pm.html?1";
		});
	$("#twoG").bind("click",function(){
			window.location.href = "pm.html?2";
		});
	$("#threeG").bind("click",function(){
			window.location.href = "pm.html?3";
		});
	$("#fourG").bind("click",function(){
			window.location.href = "pm.html?4";
		});
	$("#fiveG").bind("click",function(){
			window.location.href = "pm.html?5";
		});
	$("#sixG").bind("click",function(){
			window.location.href = "pm.html?6";
		});
	$("#sevenG").bind("click",function(){
			window.location.href = "pm.html?7";
		});
	$("#eightG").bind("click",function(){
			window.location.href = "pm.html?8";
		});
	$("#nineG").bind("click",function(){
			window.location.href = "pm.html?9";
		});
	$("#tenG").bind("click",function(){
			window.location.href = "pm.html?10";
		});
	$("#elevenG").bind("click",function(){
			window.location.href = "pm.html?11";
		});
	$("#tweG").bind("click",function(){
			window.location.href = "pm.html?12";
		});
	
//最简单的跳转结束
	$("#jh").bind("click",function(){
			window.location.href = "jh.html?jh";
		});
	$("#hn").bind("click",function(){
			window.location.href = "hn.html?hn";
		});
	$("#hy").bind("click",function(){
			window.location.href = "hy.html?hy";
		});
	$("#js").bind("click",function(){
			window.location.href = "js.html?js";
		});
	$("#ph").bind("click",function(){
			window.location.href = "ph.html?ph";
		});
	$("#tx").bind("click",function(){
			window.location.href = "tx.html?tx";
		});
	$("#oneOne").bind("click",function(){
		$("#oneOne img").attr("src","image/14yes.png");
		$("#twoTwo img").attr("src","image/58no.png");
		$("#threeTh img").attr("src","image/912no.png");
		$(".page1").show();
		$(".page2").hide();
		$(".page3").hide();
	});
	$("#twoTwo").bind("click",function(){
		$("#oneOne img").attr("src","image/14no.png");
		$("#twoTwo img").attr("src","image/58yes.png");
		$("#threeTh img").attr("src","image/912no.png");
		$(".page1").hide();
		$(".page2").show();
		$(".page3").hide();
	});
	$("#threeTh").bind("click",function(){
		$("#oneOne img").attr("src","image/14no.png");
		$("#twoTwo img").attr("src","image/58no.png");
		$("#threeTh img").attr("src","image/912yes.png");
		$(".page1").hide();
		$(".page2").hide();
		$(".page3").show();
	});


    var _html=''
	$.ajax({
		url:'../../battle/getData',
		dataType:'json',
		success:function (data) {
			////console.log(JSON.stringify(data.data));
            var json = JSON.parse(data.data);

            $.each(json, function(i) {
                var teamid = i;
                var val = json[i]
                ////console.log(getTeamId(teamid))
                var _div = $('#' + team_id[getTeamId(teamid)])

                $.each(val,function (index, content) {
                    if(index >0)
                        return true
                    var teamimg  = "";
                    /*if (content.teamName == '超越队' && teamid == '七')
                        teamimg = 'index/'+getTeamImg('七超越队')
                    else if(content.teamName == '超越队' && teamid== '十一')
                        teamimg = 'index/'+getTeamImg('十一超越队')
                    else if(content.teamName == '潘小红红姐队' && teamid == '九'){
                        teamimg = "team/tx/wz/hjd"
                    }else if(content.teamName == '方煜杰方向队' && teamid == '十'){
                        teamimg = "team/tx/wz/fxd"
                    }else if(content.teamName == '精英队' && teamid == '二')
                        teamimg = 'team/hy/wy/jyd'
                    else*/

                    if(content.areaName == 'hy'){
                        teamimg = 'team/'+content.areaName+'/'+content.centerName+'/' + getHYTeamId(content.teamName)
                    }else if(content.areaName == 'hn'){
                        teamimg = 'team/'+content.areaName+'/'+content.centerName+'/' + getHNTeamId(content.teamName)
                    }else
                        teamimg = 'team/'+content.areaName+'/'+content.centerName+'/' + getTeamImg(content.teamName)
                    //console.log(content.teamName+":"+teamimg)
                    _html += '<span class="spOne' + (index + 1) + ' sp"><img src="image/' + teamimg + '.png"/></span>'
                })
                ////console.log(_html)
                _div.append(_html)
                _html='';

            });
        }
	})
}



function getTeamId(teamid) {
    switch (teamid){
        case '一' :
            return 0;
        case '二' :
            return 1;
        case '三' :
            return 2;
        case '四' :
            return 3;
        case '五' :
            return 4;
        case '六' :
            return 5;
        case '七' :
            return 6;
        case '八' :
            return 7;
        case '九' :
            return 8;
        case '十' :
            return 9;
        case '十一' :
            return 10;
        case '十二' :
            return 11;
    }
}

function getTeamImg(teamName) {

    switch (teamName) {
        case '红' :
            return "red";
        case '蓝' :
            return "blue";
        case '黄' :
            return "yellow";
        case '橙' :
            return "cd";
        case '黑' :
            return "hd";
        case '粉' :
            return "fd";
        case 'captain队' :
            return 'captain';
        case '花木兰队' :
            return 'hmld';
        case '地表最强队' :
            return 'dbzqd';
        case '第二战队' :
            return 'dezd';
        case '团结队' :
            return 'tj';
        case '希望队' :
            return 'xw';
        case '农夫山泉队' :
            return 'nfsq';
        case '争先队' :
            return 'zx';
        case '麒麟队' :
            return 'ql';
        case '荣耀队' :
            return 'ry';
        case '百事队' :
            return 'bs';
        case '勇士队' :
            return 'ys';
        case '王者MVP' :
            return 'mvp';
        case '勤奋队' :
            return 'qf';
        case '隼龙队' :
            return 'sl';
        case '魏塘超越队' :
            return 'wtcyd';
        case '魏塘冲锋队' :
            return 'wtcfd';
        case '魏塘老姜队' :
            return 'wtljd';
        case '魏塘狮子队' :
            return 'wtszd';
        case '魏塘同心队' :
            return 'wttxd';
        case '经开平凡队' :
            return 'jkpfd';
        case '经开桃花岛队' :
            return 'jkthd';
        case '干窑奔跑队' :
            return 'bp';
        case '干窑飞越队' :
            return 'fy';
        case '西塘下只角队' :
            return 'xtxzj';
        case '冲锋队' :
            return 'phcfd';
        case '超越队' :
            return 'phcyd';
        case '进取队' :
            return 'phjqd';
        case '勇猛队' :
            return 'phymd';
        case '飞虎队' :
            return 'phfhd';
        case '飞狼队' :
            return 'phfld';
        case '飞鹰队' :
            return 'phfyd';
        case '拼搏队' :
            return 'pbd';
        case '蚂蚁队' :
            return 'myd';
        case '凝心队' :
            return 'nxd';
        case '聚力队' :
            return 'phjld';
        case '张桂娟冲锋队' :
            return 'zgjcfd';
        case '邱晓丽Shero队' :
            return 'qxld';
        case '范磊游击队' :
            return 'flyjd';
        case '陈亚萍飞跃队' :
            return 'cypfyd';
        case '陈振杰屠龙队' :
            return 'czjtld';
        case '高利玉凤鸣队' :
            return 'glyfmd';
        case '林阿丽英雄联盟队' :
            return 'lallol';
        case '吕新蓝剑传说队' :
            return 'lxljcs';
        case '姚丰西域雄狮队' :
            return 'yfxyxs';
        case '钟亚梅东方雄鹰队' :
            return 'zymdfxsd';
        case '屠赟超王者荣耀队' :
            return 'wzry';
        case '韩斐突击队' :
            return 'hftjd';
        case '方煜杰方向队' :
            return 'fxd';
        case '潘小红红姐队' :
            return 'hjd';
        case '沈翊彬移兵队' :
            return 'ybd';
        case '费袁杰队' :
            return 'fyjd';
        case '吕佩清队' :
            return 'lpqd';
        case '王可惠羊毛队' :
            return 'wkhymd';
        case '无敌队' : return 'wdd';
        case '胜羽队' : return 'jyd';
        case '极限队' : return 'jxd';
        case '豪杰队' : return 'hjd';
        case '十一超越队' : return 'cyd';
        case '战鼓队' : return 'zgd';
        case '飞越队' : return 'fyd';
        case '卓越队' : return 'zyd';


    }
}


function getHYTeamId(teamName) {
    switch (teamName){
        case '飞越队' :
            return 'fyd';
        case '卓越队' :
            return 'zyd';
        case '超越队' :
            return 'cyd';
        case '战鼓队' :
            return 'zgd';
        case '豪杰队' :
            return 'hjd';
        case '极限队' :
            return 'jxd';
        case '精英队' :
            return 'jyd';
        case '无敌队' :
            return 'wdd';
    }
}


function getHNTeamId(teamName) {
    switch (teamName) {
        case '野狼队' :
            return 'hnyld';
        case '神峰队' :
            return 'sfd';
        case '挑战队' :
            return 'hntzd';
        case '精英队' :
            return 'hnjyd';
        case '飞悦队' :
            return 'fyd';
        case '七超越队' :
            return 'hncyd';
        case '飞翔队' :
            return 'fxd';
        case '青春队' :
            return 'hnqcd';
        case '逐梦队' :
            return 'zmd';
        case '神话特工队' :
            return 'shtgd';
        case '弄潮儿' :
            return 'ncd';
        case '黄湾队' :
            return 'hnhwd';
        case '袁花队' :
            return 'yhd';
    }
}



