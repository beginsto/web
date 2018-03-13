var sitePath, contentPath = '';
var SITE_LOCATION = document.location;
if (SITE_LOCATION.pathname.indexOf('ybj-open') == 1) {
  contentPath = '/ybj-open';
} else {
  contentPath = '';
}
var policyNo = qUrl('policyNo');
$('[name=policyNo]').val(policyNo);

function qUrl(key) {
  var uri = window.location.search;
  var re = new RegExp("" + key + "=([^&?]*)", "ig");
  return ((uri.match(re)) ? (uri.match(re)[0].substr(key.length + 1)) : null);
}

var mySwiper = new Swiper('.swiper-container', {
  direction: 'vertical'
});

var startScroll, touchStart, touchCurrent;
mySwiper.slides.on('touchstart', function (e) {
  startScroll = this.scrollTop;
  touchStart = e.targetTouches[0].pageY;
}, true);
mySwiper.slides.on('touchmove', function (e) {
  touchCurrent = e.targetTouches[0].pageY;
  var touchesDiff = touchCurrent - touchStart;
  var slide = this;
  var onlyScrolling =
    (slide.scrollHeight > slide.offsetHeight) &&
    (
      (touchesDiff < 0 && startScroll === 0) ||
      (touchesDiff > 0 && startScroll === (slide.scrollHeight - slide.offsetHeight)) ||
      (startScroll > 0 && startScroll < (slide.scrollHeight - slide.offsetHeight))
    );
  if (onlyScrolling) {
    e.stopPropagation();
  }
}, true);
mySwiper.on('slideChangeTransitionEnd', function () {
  (this.activeIndex == (this.slides.length - 1)) ? $('.swiper-arrow').hide(): $('.swiper-arrow').show();
  if (this.activeIndex == 4) {
    if (policyNo == null || policyNo.length == 0) {
      return false;
    }
    //如果没有任何选择则
    if ($('input:checked').length == 0) {
      return false;
    }
    //获取用户填写的数据
    var paraStr = '';
    var $form = $('#form');
    $.each($form.find('.q-item'), function (idx, ele) {
      var $ele = $(ele);
      var qName = $ele.attr('qname');
      var qTitle = $ele.attr('qtitle');
      var qAns = '';
      if ($ele.attr('qtype') == 'checkbox') {
        var ans = [];
        $.each($ele.find('[type="checkbox"]:checked'), function (idx, ele) {
          ans.push(ele.value);
        })
        qAns = ans.join('||');
      } else {
        qAns = $ele.find('[type="radio"]:checked')[0].value;
      }
      paraStr += (idx == 0 ? '' : '&') + qName + '=' + qTitle + '|||' + qAns;
    });
    //拼接policyId(orderId)
    paraStr += '&' + $form.find('.idNumber')[0].name + '=' + $form.find('.idNumber')[0].value;
    // console.log(paraStr);
    $.ajax({
      type: "POST",
      url: contentPath + '/updateUserInfo',
      // data: $('#form').serialize(),
      data: paraStr,
      success: function (data) {
        console.log(data);
      },
      error: function () {
        console.log('error')
      }
    });
  }
})
$('.swiper-arrow').on('click', function () {
  mySwiper.slideNext();
})


$('.pop-close').on('click', function () {
  $('.poplayer, .popcontent').hide();
})
$('#Jpop').on('click', function () {
  $('.poplayer, .popcontent').show();
})