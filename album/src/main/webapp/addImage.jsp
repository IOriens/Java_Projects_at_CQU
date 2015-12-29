<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>上传图片</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
  <div class="container">
    <h1>添加照片</h1>
    <p>Album ID : <%=request.getParameter("albumID")%></p>
    <form action="ImageServlet?op=addImage&albumId=<%=request.getParameter("albumID")%>" enctype="multipart/form-data" method="post" class="form-inline">
      <label for="imagename">名称</label>
      <input type="text" id="imagename" name="imageName" height="90" size="20" maxlength="12" class="form-control "/><br><br>
      <label for="description">描述</label><br>
      <textarea id="description" name="imageDes" rows="5" class="form-control "></textarea><br><br>
      <input type="file" name="imageFile" id="imageFile" class="form-control "><br><br>
      <input type="submit" name="upload" value="上传" class="btn btn-default"/>
    </form>
  </div>
</body>
</html>