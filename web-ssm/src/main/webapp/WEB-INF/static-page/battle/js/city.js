
$(document).ready(function () {
    var areaCode = $('#areaCode').val();
    $.ajax({
        url:'../../battle/getAreaData',
        type:'get',
        data:{areaCode:areaCode},
        dataType:'json',
        success:function (data) {
            if (data.resultCode == 200)
            {
                var obj = JSON.parse(data.data);
                if (areaCode == 'jh')
                    createJH(obj)
                else if (areaCode == 'js')
                    createJS(obj)
                else if (areaCode == 'hy')
                    createHY(obj)
                else if (areaCode == 'hn')
                    createHN(obj)
                else if (areaCode == 'tx')
                    createTX(obj)
                else if (areaCode == 'ph')
                    createPH(obj)
            }
        }
    })

    var str
    function createJH(obj) {
       // //console.log(obj.nh);

        var nh = obj.nh
      //  //console.log(obj.nh);
        var _nh=$('#nh');
        str = getHtml(nh,'jh/nh/')
        _nh.append(str)

        var xz = obj.xz
        var _xz=$('#xz');
        str = getHtml(xz,'jh/xz/')
        _xz.append(str)

        var hh = obj.hh
        var _hh=$('#hh');
        // //console.log(JSON.stringify(hh))
        str = getHtml(hh,'jh/hh/')
        _hh.append(str)

        var yx = obj.yx
        var _yx=$('#yx');
        // //console.log(JSON.stringify(yx))
        str = getHtml(yx,'jh/yx/')
        _yx.append(str)

        var xf = obj.xf
        var _xf=$('#xf');
        ////console.log(JSON.stringify(xf))
        str = getHtml(xf,'jh/xf/')
        _xf.append(str)

        var wjj = obj.wjj
        var _wjj=$('#wjj');
        ////console.log(JSON.stringify(wjj))
        str = getHtml(wjj,'jh/wjj/')
        _wjj.append(str)

    }



    function createJS(obj) {
        var wt = obj.wt
        var _wt=$('#wt');
        str = getHtml(wt,'js/wt/')
        // //console.log(JSON.stringify(wt))
        _wt.append(str)

        var jk = obj.jk
        var _jk=$('#jk');
        str = getHtml(jk,'js/jk/')
        // //console.log(JSON.stringify(jk))
        _jk.append(str)

        var gy = obj.gy
        var _gy=$('#gy');
        str = getHtml(gy,'js/gy/')
        // //console.log(JSON.stringify(gy))
        _gy.append(str)

        var xt = obj.xt
        var _xt=$('#xt');
        str = getHtml(xt,'js/xt/')
        ////console.log(JSON.stringify(xt))
        _xt.append(str)

    }

    function createHY(obj) {
        var wy = obj.wy
        var _wy=$('#wy');
        str = getHtml(wy,'hy/wy/','hy')
        //  //console.log(JSON.stringify(wy))
        _wy.append(str)

        var qs  = obj.qs
        var _qs=$('#qs');
        str = getHtml(qs,'hy/qs/','hy')
        //  //console.log(JSON.stringify(qs))
        _qs.append(str)

        var bb = obj.bb
        var _bb=$('#bb');
        str = getHtml(bb,'hy/bb/','hy')
        // //console.log(JSON.stringify(bb))
        _bb.append(str)
    }

    function createHN(obj) {
        var xs = obj.xs
        var _xs=$('#xs');
        str = getHtml(xs,'hn/xs/','hn')
        // //console.log(JSON.stringify(xs))
        _xs.append(str)

        var xc  = obj.xc
        var _xc=$('#xc');
        str = getHtml(xc,'hn/xc/','hn')
        // //console.log(JSON.stringify(xc))
        _xc.append(str)

        var ca = obj.ca
        var _ca=$('#ca');
        str = getHtml(ca,'hn/ca/','hn')
        // //console.log(JSON.stringify(ca))
        _ca.append(str)

        var yg = obj.yg
        var _yg=$('#yg');
        str = getHtml(yg,'hn/yg/','hn')
        // //console.log(JSON.stringify(yg))
        _yg.append(str)

        var yh = obj.yh
        var _yh=$('#yh');
        str = getHtml(yh,'hn/yh/','hn')
        ////console.log(JSON.stringify(yh))
        _yh.append(str)
    }

    function createTX(obj) {
       // //console.log(obj.nh);
        var cf = obj.chf
        var _cf=$('#cf');
        str = getHtml(cf,'tx/chf/')
        // //console.log(JSON.stringify(cf))
        _cf.append(str)

        var wt  = obj.wt
        var _wt=$('#wt');
        str = getHtml(wt,'tx/wt/')
        // //console.log(JSON.stringify(wt))
        _wt.append(str)

        var zq = obj.zq
        var _zq=$('#zq');
        str = getHtml(zq,'tx/zhq/')
        ////console.log(JSON.stringify(zq))
        _zq.append(str)

        var wz = obj.wz
        var _wz=$('#wz');
        str = getHtml(wz,'tx/wz/')
        // //console.log(JSON.stringify(wz))
        _wz.append(str)

        var py = obj.py
        var _py=$('#py');
        str = getHtml(py,'tx/py/')
        // //console.log(JSON.stringify(py))
        _py.append(str)
    }

    function createPH(obj) {
        var dh = obj.dh
        var _dh=$('#dh');
        str = getHtml(dh,'ph/dh/')
        ////console.log(JSON.stringify(dh))
        _dh.append(str)

        var dsg  = obj.dsg
        var _dsg=$('#dsg');
        str = getHtml(dsg,'ph/dsg/')
        // //console.log(JSON.stringify(dsg))
        _dsg.append(str)

        var zp = obj.zhp
        var _zp=$('#zp');
        str = getHtml(zp,'ph/zhp/')
        // //console.log(JSON.stringify(zp))
        _zp.append(str)

        var xd = obj.xd
        var _xd=$('#xd');
        str = getHtml(xd,'ph/xd/')
        ////console.log(JSON.stringify(xd))
        _xd.append(str)
    }

    function getHtml (obj,src,area) {
        //console.log(obj)
        var _html='';
        $.each(obj,function (index,content) {
            if (index < 1){
                if(index%2 == 0)
                    _html+='<ul> '
                        + '  <li><img class="img1" src="image/' + content.rk + 'pm.png"/><img class="img2" src="image/team/' + src  + getTeamImg(area,content.teamName) + '.png"/></li>'

                else
                    _html+='<li><img class="img1" src="image/' + content.rk+ 'pm.png"/><img class="img2" src="image/team/' + src + getTeamImg(area,content.teamName) + '.png"/></li>'
                        + '</ul>'
            }else{

                if(index%2 == 0)
                    _html+='<ul class="lil">'
                        + '<li><img class="img1" src="image/' + content.rk +  'pm.png"/> <img class="img2" src="image/team/' + src + getTeamImg(area,content.teamName) + '.png"/></li>'
                else
                    _html+='<li><img class="img1" src="image/' + content.rk + 'pm.png"/> <img class="img2" src="image/team/' + src + getTeamImg(area,content.teamName) + '.png"/></li>'
                        + '</ul>'
            }

        })
        return _html
    }
})




function getTeamImg(area,teamName) {

    if (area == 'hy')
        return getTeamImgHY(teamName);
    else if(area == 'hn'){
        return getTeamImgHN(teamName);
    }else {
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

        }
    }
}

function getTeamImgHY(teamName) {
    switch (teamName){
        case '无敌队' : return 'wdd';
        case '胜羽队' : return 'jyd';
        case '极限队' : return 'jxd';
        case '豪杰队' : return 'hjd';
        case '超越队' : return 'cyd';
        case '战鼓队' : return 'zgd';
        case '飞越队' : return 'fyd';
        case '卓越队' : return 'zyd';
        case '精英队' : return 'jyd';

    }
}

function getTeamImgHN(teamName) {
    switch (teamName){
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
        case '超越队' :
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