<%@page import="com.oriens.cquNews.controller.newsCrawler"%>
<%@page import="com.oriens.cquNews.domains.News"%>
<%@page import="com.oriens.cquNews.searching.newsSearcher"%>
<%@page import="com.oriens.cquNews.searching.newsIndexer"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>数据准备</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/showNewsStyle.css">
</head>
<body>
	<div class="w3-container w3-theme w3-card-4 w3-center">
		<h1 class="w3-xxlarge">
			数据准备
		</h1>		
	</div>
	<br>
	<div class="w3-row">
		<div class="w3-col" style="width: 20%">
			<p></p>
		</div>
		<div class="w3-col w3-card-2 w3-padding-top w3-center"
			style="width: 60%; min-height: 400px">
			<h1 style="line-height: 200px">数据准备中!</h1>
			<%
				
				
				Thread t=new Thread(new Runnable(){
					public void run(){
						newsIndexer newsIndexerIns = new newsIndexer();
						newsIndexerIns.indexNews();
					}
				});
				t.start();
			%>
		</div>
		<div class="w3-col" style="width: 20%">
			<p></p>
		</div>
	</div>
	<br>
	<footer class="w3-container w3-theme-dark ">
		<div class="w3-center" >	
		<br>		
			<a class="w3-text-white w3-large" href="index.jsp">Back Home!</a>
		</div>
		<br>
	</footer>
</body>
</html>
