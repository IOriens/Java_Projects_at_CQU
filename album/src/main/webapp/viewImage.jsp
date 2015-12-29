<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="com.zhaoyang.album.domain.*"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>照片</title>
</head>

<input type="button"  onclick="javascript:history.back(-1);" value="返回" style="height:30px;width:80px;">
<p></p>
<!-- border="0" width="600" align="center" -->
<table >
	<tr>
		<td>照片名称</td><td>照片</td><td>描述</td><td>相关操作</td>
	</tr>
<%-- 	<%List<UserImage> images=(List<UserImage>) request.getAttribute("userImages");%>
	<!-- 这个warning不用管 -->
	<%for(UserImage photo:images){%>
		<tr>
			<td><%=photo.getImageName()	%></td>			
			<td><img src="PhotoServlet?op=image&imageId=<%=photo.getImageId()	%>" width="200"></td>
			<td><img src="ImageServlet?op=image&imageId=<%=photo.getImageId()	%>" width="200"></td>
			<td><%=photo.getImageDes()	%></td>
			<td>
				<a href="updateImage.jsp?imageId=<%=photo.getImageId()	%>&imageName=<%=photo.getImageName()	%>&imageDes=<%=photo.getImageDes()	%>">修改照片</a><br><br>
				<a href="ImageServlet?op=deleteImage&imageID=<%=photo.getImageId()	%>">删除照片</a><br>
			</td>
		</tr>
	<%}%>	 --%>
		<c:forEach items="${userImages}" var="photo">
	<tr>
		<td>${photo.getImageName()}</td>		
		<td><img src="ImageServlet?op=image&imageId=${photo.getImageId()}" width="400"></td>
		<td>${photo.getImageDes()}</td>
		<td>
			<a href="updateImage.jsp?imageId=${photo.getImageId()	}&imageName=${photo.getImageName()	}&imageDes=${photo.getImageDes()	}">修改照片</a><br><br>
			<a href="ImageServlet?op=deleteImage&imageID=${photo.getImageId()}">删除照片</a><br>
		</td>
	</tr>
	</c:forEach>
	
</table>
<br/><br/>
</body>
</html>