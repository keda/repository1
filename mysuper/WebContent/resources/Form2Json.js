/**
 * 
 * 将web Form 的数据转化成json字符串及json对象进行处理的类
 * 
 */

function Form2Json() {

	this.isEscape = false;// 表单元素中输入的文本是否进行escape转义，默认不转义

	this.isArray = false;// 表单所有元素对应的值生成的字符串是否都作为数组，默认不是数组

	this.formToJSON = tool_formToJSON;// 将web Form 采集的数据转化成json字符串

	this.getSelectResult = tool_getSelectResult;// 取得SELECT的值组成的json字符串

	this.getCheckboxResult = tool_getCheckboxResult;// 取得form中所有同名的checkbox的值组成的json字符串

	this.parseTxt2JsonStr = tool_parseTxt2JsonStr;// 将输入的文本转换成json序列化字符串

	this.string2json1 = tool_string2json1;// 将字符串转化为json对象的方法一

	this.string2json2 = tool_string2json2;// 将字符串转化为json对象的方法二

	this.string2json3 = tool_string2json3;// 将字符串转化为json对象的方法三

	this.simplejson2string = tool_simplejson2string;// 将简单的json对象转换成字符串（不能处理包含数组和嵌套json对象的情况）

	this.json2string = tool_json2string;// 将json对象转换成字符串

	/***************************************************************************
	 * 
	 * 处理将web form中的元素转换成json字符串的函数
	 * 
	 **************************************************************************/

	/**
	 * 
	 * 将web Form 采集的数据转化成json字符串
	 * 
	 * 传入web form对象
	 * 
	 * 输出由form元素名称及其值组成的json字符串
	 * 
	 * 元素的值全部使用escape()进行了转意处理
	 * 
	 * 每个元素对应的值全部是数组
	 * 
	 * 格式形如：{"xm":["%u5F20%u4E09"],"xb":["%u7537"],"VIP":["h1","h3","h5"]}
	 * 
	 */

	function tool_formToJSON(form, isEscape, isArray) {

		var json = '{';
		
		var i = 0;

		var max = form.elements.length;

		var e, name, lastarr;

		var tmpstr;

		if (isEscape == null || isEscape == "undeifined") {

			isEscape = this.isEscape;

		}

		if (isArray == null || isArray == "undeifined") {

			isArray = this.isArray;

		}

		var arrayPrefix = "";

		var arrayPostfix = "";

		if (isArray === true) {

			arrayPrefix = "[";

			arrayPostfix = "]";

		}

		// 循环处理form的元素

		for (i = 0; i < max; i++) {

			e = form.elements[i];

			// alert("name="+e.name+"\ntype="+e.type+"\nvalue="+e.value)

			name = e.name;

			switch (e.type) {

				case 'checkbox' :

					if (json.indexOf('"' + name + '":') < 0) // 表示该checkbox没有处理过

					{

						tmpstr = this.getCheckboxResult(form, e, isEscape,
								isArray);

						if (tmpstr != "") // 空值不处理

							json += tmpstr + ",";

					}

					break;

				case 'radio' :

					if (!e.checked) {
						break;
					} // 没有被选中，就不处理

				case 'hidden' :

				case 'password' :

				case 'text' :

				case 'textarea' :

					tmpstr = e.value;

					if (tmpstr != null) // 空值不处理

						if (isEscape === true) {

							json += '"' + name + '":' + arrayPrefix + '"'
									+ escape(tmpstr) + '"' + arrayPostfix + ',';

						} else {

							json += '"' + name + '":' + arrayPrefix + '"'
									+ this.parseTxt2JsonStr(tmpstr) + '"'
									+ arrayPostfix + ',';

						}

					break;

				case 'select-one' :

				case 'select-multiple' :

					tmpstr = this.getSelectResult(e, isEscape, isArray);

					if (tmpstr != "") // 空值不处理

						json += tmpstr + ",";

					break;

				case 'button' :

				case 'file' :

				case 'image' :

				case 'reset' :

				case 'submit' :

					break;

				default :

			}

		};

		return json.substring(0, json.length - 1) + '}'; // 返回json字符串

	}

	/**
	 * 
	 * 取得SELECT的值组成的json字符串
	 * 
	 * 传入SELECT对象
	 * 
	 * 输出由SELECT元素名称及其被选取的选项的值组成的json字符串
	 * 
	 * 形如：多选下拉列表为"name":["v1","v2","v3]，单选下拉列表为"name":"v1"
	 * 
	 */

	function tool_getSelectResult(oS, isEscape, isArray) {

		var l = oS.options.length;

		var i = 0;

		var eName = oS.name;

		var eValue = "";

		if (isEscape == null || isEscape == "undeifined") {

			isEscape = this.isEscape;

		}

		if (isArray == null || isArray == "undeifined") {

			isArray = this.isArray;

		}

		var arrayPrefix = "";

		var arrayPostfix = "";

		if (isArray === true) {

			arrayPrefix = "[";

			arrayPostfix = "]";

		}

		for (i = 0; i < l; i++) {

			if (oS.options[i].selected) {

				if (isEscape === true) {

					eValue += ',"' + escape(oS.options[i].value) + '"';

				} else {

					eValue += ',"' + this.parseTxt2JsonStr(oS.options[i].value)
							+ '"';

				}

			}

		}

		if (eValue != "") {

			if (oS.type == "select-multiple") {

				eValue = '"' + eName + '":[' + eValue.substr(1) + "]";

			} else if (oS.type == "select-one") {

				eValue = '"' + eName + '":' + arrayPrefix + eValue.substr(1)
						+ arrayPostfix;

			}

		}

		return eValue;

	}

	/**
	 * 
	 * 取得form中所有同名的checkbox的值组成的json字符串
	 * 
	 * 传入form和checkbox对象
	 * 
	 * 输出由checkbox元素名称及其值组成的json字符串
	 * 
	 * 形如：checkboxname:["c1","c2","c3]
	 * 
	 */

	function tool_getCheckboxResult(form, e, isEscape, isArray) {

		var max = form.elements.length;

		var i = 0;

		var num = 0;// 同名复选框个数

		var oE;

		var strTemp = "";

		if (isEscape == null || isEscape == "undeifined") {

			isEscape = this.isEscape;

		}

		if (isArray == null || isArray == "undeifined") {

			isArray = this.isArray;

		}

		var arrayPrefix = "";

		var arrayPostfix = "";

		if (isArray === true) {

			arrayPrefix = "[";

			arrayPostfix = "]";

		}

		for (i = 0; i < max; i++) {

			oE = form.elements[i];

			if (oE.name != e.name) {

				continue; // 元素名称不同，就跳过

			} else {

				num++;

				if (oE.checked) { // 只有选中的才处理

					if (isEscape === true) {

						strTemp += ',"' + escape(oE.value) + '"';

					} else {

						strTemp += ',"' + this.parseTxt2JsonStr(oE.value) + '"';

					}

				}

			}

		}

		if (strTemp != "") {

			if (num == 1) {

				strTemp = '"' + e.name + '":' + arrayPrefix + strTemp.substr(1)
						+ arrayPostfix;

			} else {

				strTemp = '"' + e.name + '":[' + strTemp.substr(1) + "]";

			}

		}

		return strTemp;

	}

	/**
	 * 
	 * 将输入的文本转换成json序列化字符串
	 * 
	 */

	function tool_parseTxt2JsonStr(text) {

		var temp = text.replace(/\\/g, "\\\\");// 在反斜杠前加一个反斜杠

		var jsonStr = temp.replace(/\"/g, "\\\"")// 在双引号前加一个反斜杠

		return jsonStr;

	}

	/***************************************************************************
	 * 
	 * 处理json字符串与json对象转换的函数
	 * 
	 **************************************************************************/

	/**
	 * 
	 * 将字符串转化为json对象的方法一
	 * 
	 */

	function tool_string2json1(strJson) {

		try {

			var j = "(" + strJson + ")"; // 用括号将json字符串括起来

			return eval(j); // 返回json对象

		} catch (e) {

			return null;

		}

	}

	/**
	 * 
	 * 将字符串转化为json对象的方法二
	 * 
	 */

	function tool_string2json2(strJson) {

		try {

			eval("var j = " + strJson); // 得到一个变量

			return j; // 返回变量的值

		} catch (e) {

			return null;

		}

	}

	/**
	 * 
	 * 将字符串转化为json对象的方法三
	 * 
	 */

	function tool_string2json3(strJson) {

		try {

			var f = new Function("return " + strJson + ";"); // 得到一个函数

			return f(); // 执行函数，并返回函数的值

		} catch (e) {

			return null;

		}

	}

	/**
	 * 
	 * 将简单的json对象转换成字符串（不能处理包含数组和嵌套json对象的情况）
	 * 
	 */

	function tool_simplejson2string(oJson) {

		var s = ""

		for (var property in oJson) {

			s += (',"' + property + '":"' + oJson[property].valueOf() + '"');

		}

		return s.substring(1);

	}

	/**

	 * 将json对象转换成字符串

	 */

	function tool_json2string(o) {

		// 闭包函数，处理不同的json属性

		var fmt = function(s) {

			if (s == null) {

				return "null";

			}
			switch (s.constructor) {

				case Array :

					return "[" + howwaJson.json2string(s) + "]";

				case Object :

					return howwaJson.json2string(s);

				case Number :

					return s;

				case String :

					return '\"' + s.replace(/\//g, '\\/') + '\"';

				case Boolean :

					return s ? "true" : "false";

			}

		}

		// 开始解析json对象

		if (o == null)
			return "";

		var arr = [];

		for (var i in o) {

			if (/\d+/.test(i)) {

				arr.push(fmt(o[i]));

			} else {

				arr.push('\"' + i + '\":' + fmt(o[i]));

			}

		}

		if (o.constructor == Object) {

			return '{' + arr.join(',') + '}';

		} else {

			return arr.join(',');

		}

	}

}