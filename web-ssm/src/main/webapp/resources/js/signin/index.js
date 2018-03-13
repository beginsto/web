window.onload = function(){
  //头像列表
  var arr = "../images/signin/cs.png";//图片数组
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

  $.ajax({
      url:'getData',
      type:'post',
      data:{openid:$("#openid").val()},
      dataType:'json',
      success:function (data) {
          //console.log( JSON.stringify(data))
          arr = data.participator;
          $("#rens").text(data.count);
          $(".head p").text(data.starttime+'开始');
          if(data.isLogin == 1){
              $(".hide").show();
              $(".oneTime").show();
              isLogin = 0;
          }
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

$("#knowF").bind("click",function(){
	$(".hide").hide();
  $(".oneTime").hide();
})
//第一次登录结束
var myDate = new Date();
var year=myDate.getFullYear();
var month=myDate.getMonth()+1;
var date=myDate.getDate();

//头像列表结束

//规则
$("#ruleD").bind("click",function(){
  window.location.href = "rule.html";
})

//验证码
function toDouble(num) {
  if (num < 10) {
    return '0' + num;
  }
  else {
    return '' + num;
  }
}
var getNum=$("#code");
$("#code").bind("click",function cn(){
  var num = 60;
  getNum.text(num + 'S后重新获取');
  timer = setInterval(function () {
    num--;
    getNum.text(toDouble(num) + 'S后重新获取');
    $("#code").unbind();
    if (num == 0) {
      clearInterval(timer);
      $("#code").bind("click",cn);
      getNum.text ('获取验证码');
    }
  }, 1000);
})
//验证码结束
//登录
$("#join").bind("click",function(){
  $.ajax({
      url:'validate',
      type:'post',
      data:{openid:$('#openid').val()},
      dataType:'json',
      success:function (data) {
        console.log(JSON.stringify(data));
        if (data.resultCode == 200){
            window.location.href="active?openid="+$('#openid').val();//后台跳吧！
        }else{
            $(".hide").show();
            $(".login").show();

        }
      }
  })

})
$(".close").bind("click",function(){
 $(".hide").hide();
 $(".login").hide();
})
var r = /^\+?[1-9][0-9]*$/; 
$("#goLogin").bind("click",function(){

  var tel=$("input[name='tel']").val();
  var num=$("input[name='num']").val();
  var tr=r.test(tel);
  var nu = r.test(num);
  if(tr && nu && tel!="" && num != ""){
      $(".login").hide();
      $(".success").show();
      timer = setInterval(function () {

    clearInterval(timer);
    $(".hide").hide();
    $(".success").hide();
    window.location.href="active.html";//后台跳吧！

             }, 3000);
  }else{
    alert("输入不正确");
  }
//登录结束
});
$(".closeS").bind("click",function(){
 $(".hide").hide();
 $(".success").hide();
})
}