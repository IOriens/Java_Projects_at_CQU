<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>新建相册</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
 <div class="container">
   <h1>新建相册</h1>
   <form action="AlbumServlet?op=creatAlbum&uid=<%=session.getAttribute("uID")%>" method="post" class="form-inline">
    <label for="albumName" >相册名</label>
    <input type=text id="albumName" name="albumName" class="form-control"/>
    <br/><br/>
    <input type="submit"value="添加" class="btn btn-default"/>
  </form>
</div>
</body>
</html>
