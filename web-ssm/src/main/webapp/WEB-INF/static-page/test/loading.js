(function (factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node / CommonJS
        factory(require('jquery'));
    } else {
        factory(jQuery);
    }
})

+function(){
    'use strict';

    var JUI = {
        ajaxTimeout: 10000,
        statusCode: {ok:200, error:300, timeout:301},
        keys: {statusCode:'statusCode', message:'message',data:'data'},
        eventType: {

        },
        debug: function(msg){
            console.log(msg)
        }
    }

    window.JUI = JUI
}(jQuery);

+function (){

    window.FRAG = {
        loading:
        '<div class="loading">' +
        '    <div class="loading-panel">' +
        '        <div class="container">' +
        '            <div class="loading-container container-one">' +
        '                <div class="circle-one"></div>' +
        '                <div class="circle-two"></div>' +
        '                <div class="circle-three"></div>' +
        '                <div class="circle-four"></div>' +
        '            </div>' +
        '            <div class="loading-container container-two">' +
        '                <div class="circle-one"></div>' +
        '                <div class="circle-two"></div>' +
        '                <div class="circle-three"></div>' +
        '                <div class="circle-four"></div>' +
        '            </div>' +
        '            <div class="loading-container container-three">' +
        '                <div class="circle-one"></div>' +
        '                <div class="circle-two"></div>' +
        '                <div class="circle-three"></div>' +
        '                <div class="circle-four"></div>' +
        '            </div>' +
        '        </div>' +
        '    </div>' +
        '</div>'

    }
}(jQuery);


+function($){
    'use strict';
    function Loading($element){
        this._loading = $element
        this.init()
    }

    Loading.prototype = {

        init: function(){
            this.initLoader();
        },
        initLoader: function (){
            this._loading.append(FRAG.loading)
        },

    }

    $(function () {
        return new Loading($('body'))
    });
}(jQuery);