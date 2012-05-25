var jquery_url = '<script type="text/javascript" src="APP-JS/jquery-1.7.1.js"></script>';
document.write(jquery_url);

function MspApp(){
	
	this.log = app_log;
	this.fileLoader = app_fileLoader;
	this.doSubmit = app_submit;
	
	function app_debugConsole(id){
		var console_panel = document.getElementById(id);
		
		if(!console_panel){
			console_panel = document.createElement("DIV");//控制台
			console_panel.setAttribute("id", id);
			console_panel.setAttribute("style", "width: 100%; height: 160px; margin-top: 10px; border: 1px solid #000; background-color: #CCFFFF; overflow: auto;");
			document.body.appendChild(console_panel);
		}
		
		return console_panel;
	}
	
	function app_log(msg){
		var cp = app_debugConsole("consolePanel");
		
		var record = document.createElement("P");
		record.setAttribute("style", "height: 20px;margin-top: 3px; margin-bottom: 3px;")
		
		var date = new Date();
		
		var now = '';
		
		now = date.getFullYear()+"-";
		now = now + ((date.getMonth()+1) < 10 ? '0'+(date.getMonth()+1) : (date.getMonth()+1)) + "-";
		now = now + (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + " ";
		now = now + (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ":";
		now = now + (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ":";
		now = now + (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds()) + ".";
		now = now + (date.getMilliseconds() < 100 ? '0'+(date.getMilliseconds() < 10 ? '0'+date.getMilliseconds() : date.getMilliseconds()) : date.getMilliseconds());
		
		var msg_head = document.createElement("LABEL");
		msg_head.setAttribute("width", "180px");
		msg_head.setAttribute("style", "margin-right: 10px");
		msg_head.innerHTML = now;
		
		var msg_body = document.createElement("LABEL");
		msg_body.innerHTML = 'Say> '+msg;
		
		record.appendChild(msg_head);
		record.appendChild(msg_body);
		
		cp.appendChild(record);
	}
	
	function app_fileLoader(fileType, fileName){
		
		if((typeof fileType == 'undefined') || $.trim(fileType) == '') {
			throw new Error("请传入文件类型参数");
		}
		
		if((typeof fileName == 'undefined') || $.trim(fileName) == '') {
			throw new Error("请传入文件名称(完整名称带路径、后缀名)参数");
		}
		
		var dy_file ;
		
		if(fileType == 'javascript'){
			dy_file = document.createElement("SCRIPT");
			dy_file.setAttribute("type", "text/javascript");
			dy_file.setAttribute("src", fileName);
			
		}
		if(fileType == 'css'){
			dy_file = document.createElement("LINK");
			dy_file.setAttribute("rel", "stylesheet");
			dy_file.setAttribute("type", "text/css");
			dy_file.setAttribute("href", fileName);
			
		}
		
		$(dy_file).insertBefore($('SCRIPT:first'));
	}
	
	/**
	 * 由开发员自定义表单生成提交表单，该表单将表单数据序列化成JSON格式字符串。
	 * 暂不支持用于文件的表单
	 * @param {} form 开发员自定义表单
	 * @param string paraName参数名称,用于在servlet中获取值
	 * @param string json格式字符串
	 */
	function app_submit(form, paraName, v) {
		
		if($('#_submitForm')) {
			$('#_submitForm').remove();
		}
		
		var formAttr = {
			id: '_submitForm',
			name: form.attr('name'),
			action: form.attr('action'),
			method: form.attr('method')
		};
		
		//var v = formToJson(form);
		
		var inputAttr = {
			name: paraName,
			value: v
		}
		
		var inputAttr2 = {
			name: 'pojoName',
			value: form.attr('name')
		}
		
		$('<form />').attr(formAttr)
			.css('display', 'none')
			.append($('<input type="text" />').attr(inputAttr), $('<input type="text" />').attr(inputAttr2))
			.appendTo('body')
			.submit();
	}
	
	
}

/**
 * 调试功能
 *
 */
function log(msg){
	app.log(msg);
}
/**
 * 将form表单转换成json格式的字符串，返回json字符串
 * @param {} form 表单对象
 */
function formToJson(form) {
	
	if(typeof form == 'undefined') {
		log("请传入表单对象");
		return ;
	}
	if(form instanceof $) {
		form = form.get(0);//cast to DOM-object
	}
	
	var form2Json , result;
	
	try {
		if(typeof Form2Json == 'undefined') {
			app.fileLoader('javascript', 'APP-JS/Form2Json.js');	
		}
		form2Json = new Form2Json();
		
		result = form2Json.formToJSON(form);
	} catch(e) {
		log(e);
		return;
	}
	
	return result;
}

/**
 * 由开发员自定义表单生成提交表单，该表单将表单数据序列化成JSON格式字符串。
 * 暂不支持用于文件的表单
 * @param {} form
 * @param string paraName参数名称,用于在servlet中获取值
 * @param string jsonStr  json格式字符串
 */
function doSubmit(form, paraName, jsonStr) {
	if(typeof form == 'undefined') {
		log("请传入表单对象");
		return;
	}
	if((typeof paraName == 'undefined') || $.trim(paraName) == '') {
		paraName = 'jsonStr';
	}
	
	if(!(form instanceof $)) {
		form = $(form);
		//log("将Dom对象转换成Jquery对象");
	}
	
	if((typeof jsonStr == 'undefined')|| $.trim(jsonStr) == '') {
		jsonStr = formToJson(form);
	}
	
	if($.trim(jsonStr) == '') {
		log("请求参数为空...");
		if(!confirm('是否继续')) return;
	}
	
	try {
		app.doSubmit(form, paraName, jsonStr);
	} catch(e) {
		log(e);
		return;
	}
}

var app = new MspApp();