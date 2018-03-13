window.onload = function(){
	var r = window.location.search.substr(1)//得到第几组的数据
	if(r==""){
		r=1;
	}
	var arry = ["lllll","hhhhh"];//根据r得到数组 这个数组
	$("#listHead").attr("src","image/pm"+r+".png");
	var $ull = $("#ulList");
	$.ajax({
		url:'../../battle/getTeamData',
		data:{teamid:r},
		dataType:'json',
		success:function (data) {
			//console.log(JSON.stringify(data.data))
			var _html='';
			$.each(JSON.parse(data.data),function (index,content) {
                _html += '<li><div class="liDiv">' +
						'<span class="span1"><p class="p1">' + content.rkTeam+ '</p></span>' +
					'	<span class="span2"><p class="p2">' + content.teamName + '</p></span>' +
					'	<img class="hx" src="image/hx.png" /></div></li>"'
            })
            $ull.append(_html)
        }
	})
	/*for(var i = 0;i<arry.length;i++){
		var s = i + 1;
		var $li = ("<li><div class='liDiv'>"+
			"<span class='span1'><p class='p1'>"+s+
			"</p></span><span class='span2'><p class='p2'>"+arry[i]+
			"</p></span><img class='hx' src='image/hx.png'/></div></li>");
		$ull.append($li);
	}*/
}