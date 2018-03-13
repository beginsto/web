
$(document).ready(function(){
	var _city = $('#cityList')
	var _area = $('#areaList')
    var _table = $(".tableList")
	var _searchKey = $('#searchKey');
	var _but = $('#search');
	_city.change(function(){
			CityChange()
	})

	function CityChange(){
		var city = _city.val()
		_area.empty()
		_area.append("<option value=''>请选择</option>")
		$.getJSON('data/area.json',function (data) {
		//console.log(JSON.stringify(data));
			var obj = data[city]
			console.log(obj)
			if (obj != null){
                $.each(obj,function (index,content) {
                    _area.append("<option value='"+content.name+"'>"+content.name+"</option>")
                })
			}

    	});
	}




    $.getJSON('data/bus.json',function (data) {
        if (data != null) {
            $.each(data, function (index, content) {
                _table.append('<tr><td class="th1">' + content.city + '</td><td class="th2">' + content.area + '</td><td class="th3">' + content.name + '</td><td class="th4">' + content.addr + '</td></tr>')
            })
        }
    });

	_but.bind('click',function () {
		var c = _city.val(),a = _area.val(),k = _searchKey.val()
        _table.html("<tr>" +
			"	<td class=\"th1 headth h1\" >县市</td>" +
			"	<td class=\"th2 headth h2\" >区域</td><" +
			"	<td class=\"th3 headth h3\" >渠道名称</td>" +
			"	<td class=\"th4 headth h4\" >渠道地址</td></tr>")

        $.getJSON('data/bus.json',function (data) {
            if (data != null) {
                $.each(data, function (index, content) {
                    console.log("c:" + c + "/a:" + a + "/k:" + k)
                    if (c != '' && c != content.city)
                        return true
                    if (a != '' && content.area.indexOf(a) < 0)
                        return true;
                    if (k != null && k != '' && JSON.stringify(content).indexOf(k) < 0)
                        return true
                    _table.append('<tr><td class="th1">' + content.city + '</td><td class="th2">' + content.area + '</td><td class="th3">' + content.name + '</td><td class="th4">' + content.addr + '</td></tr>')
                })
            }
        });
    })

});


