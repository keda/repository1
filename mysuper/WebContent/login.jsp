<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/msp.css" />
<link rel="stylesheet" type="text/css" href="styles/style3.css" />
<script type="text/javascript" src="APP-JS/util/MspApp.js"></script>
<title>Welcome</title>
</head>
<body>
	<div class="container">
		<header></header>
		<section>
			<div id="container_demo">
				<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
				<a class="hiddenanchor" id="toregister"></a>
				<a class="hiddenanchor" id="tologin"></a>
				<div id="wrapper">
					<div id="login" class="animate form">
						<form id="loginForm" action="login.do?comeFrom=localLogin" autocomplete="on" method='post' enctype="application/x-www-form-urlencoded">
							<h1>Log in</h1>
							<p>
								<label for="username" class="uname" data-icon="u">
									用户名 或 邮箱 </label> <input id="username" name="username"
									required="required" type="text"
									placeholder="myusername or mymail@mail.com" />
							</p>
							<p>
								<label for="password" class="youpasswd" data-icon="p">
									密码 </label> <input id="password" name="password"
									required="required" type="password" placeholder="eg. X8df!90EO" />
							</p>
							<p class="keeplogin">
								<input type="checkbox" name="loginkeeping" id="loginkeeping"
									value="loginkeeping" /> <label for="loginkeeping">
									记住我</label>
							</p>
							<p class="login button">
								<input id="btn_submit" type="button" value="Login" />
							</p>
							<p class="change_link">
								<a href="#toregister" class="to_tao">淘宝登陆</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
<script type="text/javascript">
	
	$(function(){
		$('.to_tao').bind('click', function(event){
			$('<FORM />').attr({
				action: 'login.do?comeFrom=toTao',
				name: 'toTao',
				method: 'POST',
				style: 'display: none'
			}).appendTo('body').submit();
		});
		
		$('#btn_submit[type=button]').click(function(){
			doSubmit($('#loginForm'));
		});
	});
	
	//提交表单
	function toSubmit(){
		
		//提交表单
		doSubmit($('#testForm'));
		
	}
</script>
</html>