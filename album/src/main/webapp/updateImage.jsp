<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>更新照片信息</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
 <div class="container">
  <h1>更改照片信息</h1>
  <p>Image ID : <%=request.getParameter("imageId") %></p>
  <form  action="ImageServlet?op=editImage&imageId=<%=request.getParameter("imageId") %>" method="post" class="form-inline">
    <label for="imageName">相片名称:</label>
    <input type="text" name="imageName" value="<%=request.getParameter("imageName")%>" class="form-control ">
    <br><br>
    <label for="imageDes">描述:</label><br>
    <textarea  id="imageDes" name="imageDes" rows="5" class="form-control "><%=request.getParameter("imageDes")%></textarea><br><br>
    <input type="submit" value=提交 class="btn btn-default">
  </form>
</div>
</body>
</html>
