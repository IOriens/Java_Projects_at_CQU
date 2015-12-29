<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="com.zhaoyang.album.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>相册</title>
</head>
<body>
<input type="button"  onclick="javascript:history.back(-1);" 
		value="返回" style="height:30px;width:80px;">
<p align="center">相册信息</p>
<table>
	<tr>
		<td>相册名称</td><td>照片数量</td><td>管理</td>
	</tr>
	<c:forEach items="${albums22}" var="album2">
		<tr>			
			<td>${album2.albumName}</td>
			<td>${album2.number }</td>
			<td>
			    <br/>
			    <a href="ImageServlet?op=listImage&albumID=${album2.getAlbumId()}"> 进入相册</a><br>
				<a href="addImage.jsp?albumID=${album2.getAlbumId()} ">添加照片</a><br/>
				<a href="updateAlbum.jsp?uID=${album2.uID }&albumID=${album2.getAlbumId() }&albumName=${album2.albumName }">修改相册信息</a><br/>
				<a href="AlbumServlet?op=deleteAlbum&albumID=${album2.getAlbumId() }">删除相册</a><br/><br/>
			</td>
		</tr>
	</c:forEach>
</table>
<br/><br/>
</body>
</html>