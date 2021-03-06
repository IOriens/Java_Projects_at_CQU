<%@page import="com.oriens.cquNews.controller.newsCrawler"%>
<%@page import="com.oriens.cquNews.domains.News"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<%
	newsCrawler c = new newsCrawler();
	String para = request.getParameter("newsId");
	News news = c.findNews(para);
%>

<head>
<title>
	<%
		out.print(news.getTitle());
	%>
</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/showNewsStyle.css">
</head>
<body>
	<div class="w3-container w3-theme w3-card-4 w3-center">
		<h1 class="w3-xxlarge">
			<%
				out.print(news.getTitle());
			%>
		</h1>
	</div>
	<div class="w3-padding w3-white">
		<%
			out.println(news.getContent());
		%>
	</div>
	<footer class="w3-container w3-theme-dark ">
		<div class="w3-center" >	
		<br>		
			<a class="w3-text-white w3-large" href="index.jsp">Back Home!</a>
		</div>
		<br>
	</footer>
</body>
</html>
