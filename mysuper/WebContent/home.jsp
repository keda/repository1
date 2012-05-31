<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/blueprint/screen.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="resources/css/blueprint/print.css" type="text/css" media="print">
<!--[if lt IE 8]>
  <link rel="stylesheet" href="resources/css/blueprint/ie.css" type="text/css" media="screen, projection">
<![endif]-->
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/jquery/jquery-1.7.1.js"></script>
<title>首页</title>
<style type="text/css">
body{
	background-color: #dcdcdc;
}
*{
	font-family: "Times New Roman",Georgia,Serif;
}
ol, ul ,a{
	text-decoration: none;
	list-style-type: none;
	color: #FFF;
}
li{
	cursor: pointer;
}
#wrapper{
	width: 90%;
	height: auto;
	margin: 0 auto;
}
#header{
	width: 100%;
	height: 30px;
	background-color: #330033;
	color: #FFFFFF; 
}
.hd_first_ol{
	width: 64px;
	list-style-type: none;
	margin-right: 10px;
	padding: 0 0;
	float: right;
}
.hd_first_ol li{
	width: 30px;
	height: 22px;
	text-align: center;
	float: left;
	padding-top: 5px;
	font-weight: bolder;
	font-size: 7px;
}

.atv_hover:HOVER{
	border-top: 3px outset #cc0066;
	background-color: #c0c0c0;
	cursor: pointer;
}

.tool_box{
	width: 120px;
	background-color: #FFF;
	border-left: 1px solid #c0c0c0;
	border-right: 1px solid #c0c0c0;
	border-bottom: 1px solid #c0c0c0;
	/**shadow*/
	-moz-box-shadow:1px 1px 8px #333333; 
	-webkit-box-shadow:1px 1px 8px #333333; 
	box-shadow:1px 1px 8px #333333;
	
	-webkit-border-bottom-right-radius:5px;
	-webkit-border-bottom-left-radius:5px;
	-moz-border-radius-bottomright:5px;
	-moz-border-radius-bottomleft:5px;
	border-bottom-right-radius:5px;
	border-bottom-left-radius:5px;
	/**position*/
	position: absolute;
	top: 30px;
	right: 12px;
	left: auto;
	
	display: none;
}
.atv_tool{
	border-top: 1px solid #c0c0c0;
	border-left: 1px solid #c0c0c0;
	border-right: 1px solid #c0c0c0;
	background-color: #FFF;
	color: #000;
}

#tbox_ol{
	width: 100%;
}
#tbox_ol li{
	color: #000;
	width: 100%;
	height: 30px;
	border-bottom: 1px solid #c0c0c0;
	position: relative;
	right: 18px;
	line-height: 2.2;
	
}
#tbox_ol .last{
	border-bottom: none;
}
#tbox_ol li:HOVER {
	background-color: #F1F5EE;
	-webkit-border-bottom-right-radius:5px;
	-webkit-border-bottom-left-radius:5px;
	-moz-border-radius-bottomright:5px;
	-moz-border-radius-bottomleft:5px;
	border-bottom-right-radius:5px;
	border-bottom-left-radius:5px;
}


#navigater{
	width: 90%;
	height: 50px;
	margin: 40px auto;
	
	background : -webkit-linear-gradient(top, rgb(252, 252, 252) 05%, rgb(232, 232, 232) 61%);
	background : -moz-linear-gradient(top, rgb(252, 252, 252) 05%, rgb(232, 232, 232) 61%);
	background : -o-linear-gradient(top, rgb(252, 252, 252) 05%, rgb(232, 232, 232) 61%);
	background : -ms-linear-gradient(top, rgb(252, 252, 252) 05%, rgb(232, 232, 232) 61%); 
	background : -linear-gradient(top, rgb(252, 252, 252) 05%, rgb(232, 232, 232) 61%);
	-moz-border-radius:5px; 
	-webkit-border-radius:5px; 
	border-radius:5px;
}

#logo{
	width: 200px;
	height: 48px;
	margin-left: 10px;
	border: 1px dotted red;
	
	float: left;
}

#navigater .nav_ol li{
	float: left;
	height: 48px;
	width: 100px;
	color: #8d8576;
	line-height: 50px;
	padding: 0 10px;
	text-align: center;
}
#navigater .nav_ol li:FIRST-CHILD{
	margin-left: 10px;
}
.atv_nav_ol_li{
	background-color: #ff0000;
}
.atv_nav_ol_li_hover:HOVER{
	background-color: #0b0b0b;
	color: #fff;
}
</style>

<script type="text/javascript">
	$(function(){
		//工具条中‘工具’点击事件
		$('li[user-data="msp_tool"]').bind('click', function(){
			$('.tool_box').toggle();
			var h = $('.tool_box').css('display') == 'block' ? '24px' : '22px';
			$(this).css('height', h).toggleClass('atv_tool atv_hover');
		});
		//导航栏上目录点击事件
		$('ol[class="nav_ol"] li').bind('click', function(){
			var had = $('ol[class="nav_ol"] li[class="atv_nav_ol_li"]');
			if(had && had != this) $(had).removeClass('atv_nav_ol_li').addClass('atv_nav_ol_li_hover'); 
			$(this).toggleClass('atv_nav_ol_li atv_nav_ol_li_hover');
		});
		
	});
</script>

</head>
<body>
	<div id="header">
		<ol class="hd_first_ol">
		  <li class="atv_hover">登陆</li>
		  <li class="atv_hover tool" user-data="msp_tool">工具</li>
		  <div class="tool_box">
		  	<ol id="tbox_ol">
		  	  <li>功能一</li>
		  	  <li>功能二</li>
		  	  <li class="last">功能三</li>
		  	</ol>
		  </div>
		</ol>
	</div>
	<div id="navigater">
		<div id="logo"><span>Logo...</span></div>
		<ol class="nav_ol">
		  <li class="atv_nav_ol_li_hover">点滴</li>
		  <li class="atv_nav_ol_li_hover">点滴</li>
		  <li class="atv_nav_ol_li_hover">点滴</li>
		  <li class="atv_nav_ol_li_hover">点滴</li>
		</ol>
	</div>
	<div id="wrapper">
	</div>
</body>
</html>