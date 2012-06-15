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
<link rel="stylesheet" href="resources/css/app.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="resources/css/nice_button.css" type="text/css" media="screen, projection">
<link rel="stylesheet" type="text/css" href="resources/extjs/resources/css/ext-all.css"/>
<script type="text/javascript" src="resources/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="resources/app.js"></script>
<script type="text/javascript" src="resources/extjs/ext-all.js"></script>
<title>首页</title>

<script type="text/javascript">
	var data = ['导航11','导航12','导航13','导航14','导航15','导航16'];
	Ext.onReady(function(){
		Navigator(data, '', $('#nav_panel'));
		
		$('div[class="datazone"]').load('${pageContext.request.contextPath}/velocity/view1.html');
		
		//选项卡HOVER事件
		$('ol[class="sub_nav_ol"] li').live('hover', function(){
			$(this).addClass('sub_nav_li_hover');
			$('span', $(this)).attr('style', 'display: inline-block;width: 100%;height: 100%;');
		});
	});
	
	$(function(){
		var i = $('ol[class="sub_nav_ol"] li').length;
		
		//工具条中'工具'点击事件
		$('li[user-data="msp_tool"]').bind('click', function(){
			$('.tool_box').toggle();
			var h = $('.tool_box').css('display') == 'block' ? '24px' : '22px';
			$(this).css('height', h).toggleClass('atv_tool atv_hover');
		});
		
		//点击选项卡事件
		$('ol[class="sub_nav_ol"] li').live('click', function(){
			var had = $('span[class="selected"]', $('ol[class="sub_nav_ol"] li'));
			if(had && had != this){
				$(had).removeClass('selected');
				$(had+'~a').css('display', 'none');
			}
			$('span', $(this)).toggleClass('selected');
			
			//load content
			$('div[class="datazone"]').load($('span', $(this)).attr('data-url'));
			
		});
		
		/**
		//点击关闭选项卡事件
		$('a[class="close_icon"]').live('click', function(event){
			if($('ol[class="sub_nav_ol"] li').length == 1){
				alert('只剩下最后一个啦！就别关闭拉吧....');
				return false;
			}
			
			$(this.parentNode.parentNode).remove();
			event.stopPropagation(); //阻止冒泡事件的发生
			$('ol[class="sub_nav_ol"] li:last').trigger('click');
			
		});
		
		//添加面板按钮点击事件
		$('button[name="add_view_item"]').bind('click', function(){
			var subNavOl = $('ol[class="sub_nav_ol"]');
			if($('li', subNavOl).length >=9 ){
				alert('抱歉无法在添加标签了！');
				return false;
			}
			
			$('<li><span>'+'View'+(++i)
					+'<a href="javascript:void(0);" class="close_icon"></a></span></li>').appendTo(subNavOl);
		});

		//弹出框事件
		$('button[class="blue-pill"]').bind('click', function(){
		
			$('<div/>').attr({
				id: 'disabled'
			}).css('height', function(){
				return $('body').height();
			}).appendTo('body');
			$('<div/>').attr({
				id: 'dialog'
			}).appendTo('body');
			
		*/
		//~~~~~~~
		$('button[name="toVelocity"]').bind('click', function(){
			$('<form/>').attr({
				'name':'toVelocity',
				'action':'${pageContext.request.contextPath}/velocity',
				'style':'display: none;'
			}).appendTo('body').submit();
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
		<div id="nav_panel"></div>
	</div>
	<div id="wrapper">
		<div class="sub_nav">
			<button name="add_view_item" class="minimal">Add+</button>
			<ol class="sub_nav_ol">
			  <li><span class="selected" data-url="${pageContext.request.contextPath}/velocity/view1.html">View1</span></li>
			  <li><span data-url="${pageContext.request.contextPath}/taobao/tracer/query.do">View2</span></li>
			  <li><span data-url="${pageContext.request.contextPath}/velocity/view1.html">View3</span></li>
			  <li><span data-url="${pageContext.request.contextPath}/taobao/tracer/query.do">View4</span></li>
			</ol>
		</div>
		<div class="datazone">
		</div>
	</div>
	<div id="footer">
	</div>
</body>
</html>