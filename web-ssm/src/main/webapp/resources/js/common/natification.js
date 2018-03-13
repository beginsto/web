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

	var JUI = {
		ajaxTimeout: 30000,
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
/**/

+function ($){
	'use strict';
	$(function() {
		JUI.setRegional('notification',{
			status 	:{'success':'成功'},
			title 	:{'':''},
			message :{'':''}
		})
	})
}

+function ($){

	window.FRAG = {
		loader: '<div class="loader-bounce">' +
        		'	<div class="bounce bounce1"></div>' +
        		'	<div class="bounce bounce2"></div>' +
        		'	<div class="bounce bounce3"></div>' +
      			'</div>'
      	,
      	notification:   '<div class="notification-portal">' +
      				 	'	<div class="notification">' +
      				 	'		<div class="notification-context"></div>' +
						'		<div class="notification-close">关闭</div>' +
						'	</div>' + 
						'</div>'
	}
}(jQuery);

+function($){

	'use strict';

	function Natification(){
		this._body = $('body')
		this.init()
	}

	Natification.prototype = {

		init: function(){
			this.initLoader()
			this.addListener()
		},
		initLoader: function (){
			this._body.append(FRAG.notification);
			this._notification = this._body.find('.notification')
			this._close = this._body.find('.notification-close')
		},
		addListener: function(){
			this._close.on('click', $.proxy(this.click, this))
		},
		click: function(){
            this._notification.animate({top:'-60'},300);
            setTimeout($.proxy(this.removeStatusClass, this),300)
		},
		removeStatusClass:function () {
			var obj = this._notification
            if (obj.hasClass('notification-success'))
                obj.removeClass('notification-success')
            else if(obj.hasClass('notification-failed'))
                obj.removeClass('notification-failed')
            else if(obj.hasClass('notification-timeout'))
                obj.removeClass('notification-timeout')
        }
	}

	$(function () {
        return new Natification();
    });

}(jQuery);

+function($){
	 'use strict';
	 function Loader($element){
		this.$Loader = $element
		this.init()
	}

	Loader.prototype = {

		init: function(){
			this.initLoader();
		},
		initLoader: function (){
			this.$Loader.html(FRAG.loader)
		},
		
	}

	$(function () {
        return new Loader($('.loader'))
    });
}(jQuery);



+function ($){
	'use strict';

	$.fn.extend({
		modal: function(options){

			this._notification_portal = $(this)

			this._notification = this._notification_portal.find('.notification')
			this._context = this._notification.find('.notification-context')

			this._notification.addClass('notification-' + options.status)
			this._context.html('<h3>'+options.title+'</h3>' + options.msg)

			$.notification_animate(this._notification,{top:'0'},300)
			
		},
		ajax: function(op){
			var _this = this;
			var _load = $('.loader');
			console.log("type:"+_op.type+"||url:"+_op.url+"||data:"+_op.data)
		}
	})

	JUI.isJson = function(obj) {
        var flag = true
        try {
            flag = $.parseJSON(obj)
        } catch (e) {
            return false
        }
        return flag ? true : false
    }

    $.notification_animate = function(obj,options,time){
    	obj.animate(options,time);
    }

    $.notification_removeClass = function (obj) {
        if (obj.hasClass('notification-success'))
            obj.removeClass('notification-success')
        else if(obj.hasClass('notification-failed'))
            obj.removeClass('notification-failed')
        else if(obj.hasClass('notification-timeout'))
            obj.removeClass('notification-timeout')
    }
}(jQuery);

+function ($){
	'use strict';
	var JUIAjax = function(element, options){
		var _this = $(this)
	}

	JUIAjax.DEFAULT = {
		loading 	: 	true,
		validated 	: 	true,
	}
	JUIAjax.prototype.ajax = function (options){
		var _this = this
		console.log("ajax测试："+JSON.stringify(options))
	}
	JUIAjax.prototype.ajaxform = function(_form,options){
		console.log("ajaxform测试:"+JSON.stringify(options))
	}
	function plugin(options){
		var _body = $('.body')
		return 
	}

	JUI.ajax = function(element, options){
		if (!options) 
			console.log("options为空...")
		if (!options.url) {
			console.log("url 为空...")
		}
		if (!options.data) {
			console.log("data 为空...")
		}
		if (!options.type) {
			console.log("type 为空...")
		}
		var juiajax = new JUIAjax(element,options)
		juiajax.ajaxform(null,options);
		juiajax.ajax(options);
	}
}(jQuery);