<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<title>Servlet3</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<h1>你好,<%=request.getAttribute("username")%>!</h1>
<p>现在是：<%=new Date() %></p>
<p>User Number=<%=request.getAttribute("usernum") %></p>
</body>
</html>
