<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新建相册</title>
</head>
<body>
<form action="AlbumServlet?op=creatAlbum&uid=<%=session.getAttribute("uID")%>" method="post">
				    <h1><%=session.getAttribute("userName")%> , 你好</h1>
					<label for="albumName">相册名</label>
					<input type=text id="albumName" name="albumName" />
                    <br/>
                    <input type="submit"value="添加" style="height: 30px; width: 80px;" />
</form>
</body>
</html>