<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
</head>
<body>
	<form action="UserServlet?op=register" method="post" id="register">
		<fieldset>
			<legend>用户名与密码:</legend>
			<label for="userName">用户名:</label> <input type="text" id="userName"
				name="userName" /> <label for="userPswd">密码:</label> <input
				type="password" id="userPswd" name="userPswd" />
		</fieldset>
		<fieldset>
			<legend>性别:</legend>
			<label for="boy">男</label> <input type="radio" value="男" id="boy"
				name="sex" /> <label for="girl">女</label> <input type="radio"
				value="女" id="girl" name="sex" /> <label for="secrecy">保密</label> <input
				type="radio" value="保密" id="secrecy" name="sex" />
		</fieldset>
		<fieldset>
			<legend>我最喜爱的:</legend>
			<label for="computer">计算机</label> <input type="checkbox" value="计算机"
				id="computer" name="hobby" /> <label for="trval">旅游</label> <input
				type="checkbox" value="旅游" id="trval" name="hobby" /> <label
				for="buy">购物</label> <input type="checkbox" value="购物" id="buy"
				name="hobby" />
		</fieldset>


		<fieldset>
			<legend>提交:</legend>
			<input type="submit" value="注册" id="submit" name="submit" /> <input
				type="reset" value="重置" id="reset" name="reset"
				onclick="javascript:history.back(-1)'" />
		</fieldset>
	</form>

</body>


</html>