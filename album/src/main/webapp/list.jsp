<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户首页</title>
</head>
<body>
	<a href="addAlbum.jsp?uid=<%=session.getAttribute("uID")%>" target="_parent">
		新建相册
	</a>
	<br>
	<form action="AlbumServlet?op=list&uid=<%=session.getAttribute("uID")%>" method="post">
		<h1><%=session.getAttribute("userName")%> , 你好！</h1> 
		<p>uId: <%=session.getAttribute("uID")%></p>
		<p>性别 : <%=session.getAttribute("sex")%></p>
		<p>爱好 : <%=session.getAttribute("hobby")%></p>
		<input	type="submit" value="查看相册" />
	</form>
</body>
</html>