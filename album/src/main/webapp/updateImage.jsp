<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>更新照片信息</title>
</head>
<body>
<form  action="ImageServlet?op=editImage&imageId=<%=request.getParameter("imageId") %>" method="post"  >
	<h1>Image ID : <%=request.getParameter("imageId") %></h1>	
	<label for="imageName">相片名称:</label>
	<input type="text" name="imageName" value="<%=request.getParameter("imageName")%>" >
	<br><br>		
	<%-- <label for="imageDes">描述:	<%=new String(request.getParameter("imageDes").getBytes("iso8859-2"),"utf-8")%></label><br> --%>
	<label for="imageDes">描述:</label><br>
	
	<textarea  id="imageDes" name="imageDes" rows="4"><%=request.getParameter("imageDes")%></textarea>
<!--     <input type="button"  onclick="javascript:history.back(-1);" 
		value="返回" style="height:30px;width:80px;"> -->
    <input type="submit" >
</form>
</body>
</html>