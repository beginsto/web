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
+function($){

	'use strict';

	function Natification($element){
		this.$Natification = $element;
		this.init();
	}

	Natification.prototype = {

		init: function(){
			console.log("初始化完成 。。。");
		},
		initLoader: function (){

		}
	}

	$(function () {
        return new Natification($('.notification-portal'));
    });

}(jQuery);
+function($){
	 'use strict';
	 function Loader($element){
		this.$Loader = $element;
		this.init();
	}

	Loader.prototype = {

		init: function(){
			this.$Loader.html(
				'<div class="loader-bounce">'+
        		'<div class="bounce bounce1"></div>'+
        		'<div class="bounce bounce2"></div>'+
        		'<div class="bounce bounce3"></div>'+
      			'</div>'
      			)
			console.log("初始化完成 。。。");
		},
		initLoader: function (){

		}
	}

	$(function () {
        return new Loader($('.loader'));
    });
}(jQuery);