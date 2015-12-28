<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<head>
<title>登陆界面</title>
</head>
<body>
	<h1>欢迎登陆网络相册！</h1>
	<form action="UserServlet?op=login" method="post">
		<br> 用&nbsp;户&nbsp;名&nbsp;&nbsp; <input type="text" id="userName"
			name="userName" size="40" maxlength="12" /> <br>
		<br> 密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp; <input
			type="password" id="userPswd" name="userPswd" height="90" size="60"
			maxlength="12" /> <br>
		<br>		
		<input type="submit" value="登录" /> <input type="button" value="注册"
			onclick="location.href='register.jsp' " />
	</form>
	<br>
	<br>
</body>
</html>