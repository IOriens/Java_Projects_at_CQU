<%@page import="com.oriens.cquNews.controller.newsCrawler"%>
<%@page import="com.oriens.cquNews.domains.News"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>新新重大新闻网</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="css/indexStyle.css">
</head>
<body>



<%-- 	<%		
	newsCrawler c=new newsCrawler();
	News news=c.findNews("/news/article/show.php?itemid=64700");	
	%>
	<h1><%out.print(news.getTitle());%></h1>
	<%out.println(news.getContent());%> --%>
</body>
</html>
