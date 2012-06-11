function tracerWithjQuery(callback, safe){
	if(typeof(jQuery) == "undefined") {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js";

		if(safe) {
			var cb = document.createElement("script");
			cb.type = "text/javascript";
			cb.textContent = "jQuery.noConflict();(" + callback.toString() + ")(jQuery);";
			script.addEventListener('load', function() {
				document.head.appendChild(cb);
			});
		}
		else {
			var dollar = undefined;
			if(typeof($) != "undefined") dollar = $;
			script.addEventListener('load', function() {
				jQuery.noConflict();
				$ = dollar;
				callback(jQuery);
			});
		}
		document.head.appendChild(script);
	} else {
		callback(jQuery);
	}
}

tracerWithjQuery(function($){
	
	var app_url = 'http://192.168.53.65:9090/mysuper/taobao/tracer/';
	var specilChar = new RegExp('&', 'g');
	
	$('a', $('div[class="col-main"]')).bind('click', function(){
		
		alert($(this).attr('href')+'>>>>'+$(this).attr('href').replace(specilChar, '#'));
		
		data = 'infoName='+$(this).text()+'&infoUrl='+$(this).attr('href').replace(specilChar, '#')+'&timestamp='+$.now(); 
		alert( encodeURI(data));
		$.getJSON(app_url+'logTraceInfo.do', encodeURI(data));
	});
}, true);