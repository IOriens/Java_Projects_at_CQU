<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>更新相册信息</title>
</head>
<body>
<form action="AlbumServlet?op=editAlbum&albumID=<%=request.getParameter("albumID")%>" method="post">
	<h1>User ID : <%=request.getParameter("uID")%></h1>	
	<p>相册编号 : <%=request.getParameter("albumID")%></p>	
	<p>相册名称 : <%=request.getParameter("albumName")%></p>
	<label for="newAlbumName">新名称</label>
	<input type="text" id="newAlbumName" name="newAlbumName"/>	
	<input type="submit" value="确定" />
</form>
</body>
</html>