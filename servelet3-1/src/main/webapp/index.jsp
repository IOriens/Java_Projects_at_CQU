<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet3</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%request.setCharacterEncoding("utf8"); %>
<form action="test1" method="post">
	<input type="text" name="name"/>
	<input type="submit" value="Submit"/>
</form>
</body>
</html>
