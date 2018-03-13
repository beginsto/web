window.onload = function(){
	
	function stopBuble(ev) {
        if(ev&& ev.stopPropagation) {
            ev.stopPropagation();
        }
        else{
            window.event.cancelBubble=true;
        }
    }


//循环导出list
    var $table = $("#list_table");
    var $tr="";
	$.getJSON('data/all.json',function (data) {
		//console.log(JSON.stringify(data));
        createTable(data);
    });

//list结束
	 $("#search").bind("click",function(){
         $('.list').find("p").css("display","none");
	 	var area = $('#area').val();
	 	var typeName = $('#typeName').val();
	 	var searname = $('#searname').val().trim();

        console.log("area:"+area);
         console.log("typeName:"+typeName);
         console.log("searname:"+searname);
		$.getJSON('data/'+area+".json",function (data) {
           // console.log(JSON.stringify(data));
            $table.html("");

            if(typeName == ''){//未选择类别
                $table.append("<tr><th> 区域 </th><th>  商户  </th><th>  类别  </th></tr>" );
                var hadData=false
                $.each(data,function (index,context) {
                    if (JSON.stringify(context).toString().indexOf(searname) != -1){
                        hadData = true;
                        $tr = "<tr>" +
                            "<td>"+ context.area +"</td>" +
                            "<td>"+ context.orgName +"</td>" +
                            "<td>"+ context.type +"</td>" +
                            "</tr>";
                        $table.append($tr);
                    }
                });
                console.log("未选择类别是否有数据"+hadData);
                if (!hadData){
                    $('.list').find("p").css("display","block");
                }
            }else{
                $table.append("<tr><th> 区域 </th><th>  商户  </th><th>  类别  </th></tr>" );
                var hadData=false
                $.each(data,function (index,context) {
                    if (context.type == typeName && JSON.stringify(context).indexOf(searname) != -1){
                        hadData = true;
                        $tr = "<tr>" +
                            "<td>"+ context.area +"</td>" +
                            "<td>"+ context.orgName +"</td>" +
                            "<td>"+ context.type +"</td>" +
                            "</tr>";
                        $table.append($tr);
                    }
                });
                console.log("有类别是否有数据："+hadData);
                if (!hadData){
                    $('.list').find("p").css("display","block");
                }
            }

            /*if (searname == ''){
                createTable(data);
            }else{
                $table.append("<tr><th> 区域 </th><th>  商户  </th><th>  类别  </th></tr>" );
                var hadData=false
                $.each(data,function (index,context) {
                    if (JSON.stringify(context).toString().indexOf(searname) != -1){
                        hadData = true;
                        $tr = "<tr>" +
                            "<td>"+ context.area +"</td>" +
                            "<td>"+ context.orgName +"</td>" +
                            "<td>"+ context.type +"</td>" +
                            "</tr>";
                        $table.append($tr);
                    }
                });
                if (!false){
                    $('.list').find("p").css("display","block");
                }
			}*/


        });
		 
	 	/*if(rs==1){


	 	}else{
	 	$(".show_hide").show();
	 	$(".searchfaile").show();
    	setInterval(function(){
				clearInterval();		
				$(".show_hide").hide();
	 			$(".searchfaile").hide();
					},3000);
    	}*/
    });
	 
	 function createTable(data) {
         $table.append("<tr><th> 区域 </th><th>  商户  </th><th>  类别  </th></tr>" );
         $.each(data,function (index,context) {
             $tr = "<tr>" +
                 "<td>"+ context.area +"</td>" +
                 "<td>"+ context.orgName +"</td>" +
                 "<td>"+ context.type +"</td>" +
                 "</tr>";
             $table.append($tr);
         });
     }
 }