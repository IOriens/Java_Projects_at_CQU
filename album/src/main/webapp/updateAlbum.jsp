<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>更新相册信息</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
 <div class="container">
   <h1>更新相册信息</h1>
   <h2>User ID : <%=request.getParameter("uID")%></h2>
   <p>相册编号 : <%=request.getParameter("albumID")%></p>
   <form action="AlbumServlet?op=editAlbum&albumID=<%=request.getParameter("albumID")%>" method="post" class="form-inline">
    <p>相册名称 : <%=request.getParameter("albumName")%></p>
    <label for="newAlbumName">新名称</label>
    <input type="text" id="newAlbumName" name="newAlbumName" class="form-control "/>
 	<br><br>
    <input type="submit" value="确定" class="btn btn-default"/>
  </form>
</div>
</body>
</html>
