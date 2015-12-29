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
		<br> 用户名 <input type="text" id="userName"
			name="userName" size="40" maxlength="12" /> <br>
		<br> 密码 <input
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