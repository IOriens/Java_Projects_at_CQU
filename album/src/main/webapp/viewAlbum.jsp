<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="com.zhaoyang.album.domain.*"%>
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
	
	<%List<UserAlbum> albums=(List<UserAlbum>) request.getAttribute("albums22");%>
	<!-- 这个warning不用管 -->
	
		<%for(UserAlbum album:albums){%>				
				<tr>		
					<td><%=album.getAlbumName() %></td>
					<td><%=album.getNumber() %></td>
					<td>
					    <br/>
					    <a href="ImageServlet?op=listImage&albumID=<%=album.getAlbumId()	%>"> 进入相册</a><br>
						<a href="addImage.jsp?albumID=<%=album.getAlbumId()	%>">添加照片</a><br/>
						<a href="updateAlbum.jsp?uID=<%=album.getuID()%>&albumID=<%=album.getAlbumId()	%>&albumName=<%=album.getAlbumName()	%>">修改相册信息</a><br/>
						<a href="AlbumServlet?op=deleteAlbum&albumID=<%=album.getAlbumId()	%>">删除相册</a><br/><br/>
					</td>
				</tr>
				
		<%}%>
</table>
<br/><br/>
</body>
</html>