<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css"	rel="stylesheet" />

<link href="<%=request.getContextPath()%>/css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/libs/nanoscroller.css"  type="text/css" />
<link rel="stylesheet" type="text/css"   href="<%=request.getContextPath()%>/css/compiled/layout.css">
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/compiled/elements.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>爱学派后台管理</title>

<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<script type="text/javascript">
	$(function() {
		$('#loginName').val("");
		$('#password').val("");
		$('#loginName').val(getCookie('name'));
		/* if('${msg}'!=null && '${msg}'!=''){
			 sweetAlert(
		                "",
		                '${msg}',
		                "error");
		} */
	});
	function checkLogin() {
		if ($('#remember-me').is(':checked')) {
			setCookie('name', $('#loginName').val());
		}
		var loginName = $('#loginName').val();
		var password = $('#password').val();
		if (loginName == null || loginName == "") {
			 sweetAlert(
		                "",
		                "请输入用户名！",
		                "error");
			return false;
		}

		if (password == null || password == "") {
			sweetAlert(
	                "",
	                "请输入密码！",
	                "error");
			return false;
		}
	}

	/* 设置cookie */
	function setCookie(name, value) {
		var Days = 30;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString();
	}

	/* 获取cookie */
	function getCookie(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	}
</script>

</head>
<body id="login-page-full">
	<div id="login-full-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div id="login-box">
						<div class="row">
							<div class="col-xs-12">
								<header id="login-header">
									<div id="login-logo">
										<img src="<%=request.getContextPath()%>/img/logo.png" alt=""
											class="normal-logo logo-white" />
									</div>
									<c:choose>
										<c:when test="${msg !=null}">
											<div
												style="text-align: center; width: 100%; height: 10px; color: red">
												<span>${msg}</span>
											</div>
										</c:when>
										<c:otherwise>
											<div
												style="text-align: center; width: 100%; height: 10px; color: red">
												<span></span>
										</c:otherwise>
									</c:choose>
								</header>
								<div id="login-box-inner">
									<form role="form" action="<%=request.getContextPath()%>/login" method="post">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-user"></i></span>
											<input id="loginName" class="form-control" type="text"
												placeholder="登录名" name="name">
										</div>
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-key"></i></span>
											<input id="password" type="password" class="form-control"
												placeholder="密码" name="password">
										</div>
										<div id="remember-me-wrapper">
											<div class="row">
												<div class="col-xs-6">
													<div class="checkbox-nice">
														<input type="checkbox" id="remember-me" checked="checked" />
														<label for="remember-me"> 记住我 </label>
													</div>
												</div>
												<!-- <a href="#" id="login-forget-link" class="col-xs-6">
													忘记密码? </a> -->
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12">
												<button type="submit" onclick="return checkLogin();"
													class="btn btn-success col-xs-12">登录</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
