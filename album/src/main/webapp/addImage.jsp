<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>上传图片</title>
</head>
<body>
<form action="ImageServlet?op=addImage&albumId=<%=request.getParameter("albumID")%>" enctype="multipart/form-data" method="post">
	<span>所属相册:</span>
	<span><%=request.getParameter("albumID")%></span>
	<br><br>
	<label for="imagename">名称</label><input type="text" id="imagename" name="imageName" height="90" size="20" maxlength="12"/><br><br>
	<label for="description">描述</label><input type="text" id="description" name="imageDes" height="90" size="60" maxlength="30"/><br><br>
	<label for="imageFile">选择文件</label><input type="file" name="imageFile" id="imageFile"><br><br>
	<input type="submit" name="upload" value="上传" style="height: 30px; width: 80px"/>
</form>
</body>
</html>
