/**
 * Created by caodongjie on 2017/8/9.
 */
(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'onorientationchange' in window ? 'onorientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            if(clientWidth>=663){//这是设计稿宽度
                docEl.style.fontSize = '100px';//这是html的fontsize值100px，然后就可以用页面要定义的值除以100，就是rem的值
            }else{
                docEl.style.fontSize = 100 * (clientWidth / 663) + 'px';
            }
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

