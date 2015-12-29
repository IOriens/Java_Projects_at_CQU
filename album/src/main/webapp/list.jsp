<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户首页</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
	<div class="container">
		<h1><%=session.getAttribute("userName")%> , 你好！</h1>
	    <p>User ID: <%=session.getAttribute("uID")%></p>
	    <p>性别 : <%=session.getAttribute("sex")%></p>
	    <p>爱好 : <%=session.getAttribute("hobby")%></p>
	    <a href="AlbumServlet?op=list&uid=<%=session.getAttribute("uID")%>" class="btn btn-default">查看相册</a>
	    <a href="addAlbum.jsp?uid=<%=session.getAttribute("uID")%>" class="btn btn-default">新建相册</a>
	</div>    
</body>
</html>