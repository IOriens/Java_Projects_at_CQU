<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>照片</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
  <div class="container text-center">
    <h1>照片</h1>
    <table class="table table-bordered">
      <tr>
        <td>照片名称</td><td>照片</td><td>描述</td><td>相关操作</td>
      </tr>
      <c:forEach items="${userImages}" var="photo">
      <tr>
        <td>${photo.getImageName()}</td>
        <td><img src="ImageServlet?op=image&imageId=${photo.getImageId()}" width="400"></td>
        <td>${photo.getImageDes()}</td>
        <td>
          <a href="updateImage.jsp?imageId=${photo.getImageId() }&imageName=${photo.getImageName()  }&imageDes=${photo.getImageDes()  }">修改照片</a><br><br>
          <a href="ImageServlet?op=deleteImage&imageID=${photo.getImageId()}">删除照片</a><br>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
