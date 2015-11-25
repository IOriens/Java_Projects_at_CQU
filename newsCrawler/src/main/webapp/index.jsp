<%@page import="com.oriens.cquNews.controller.newsCrawler"%>
<%@page import="com.oriens.cquNews.domains.News"%>
<%@page import="java.util.List"%>

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>重大新闻网</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/indexStyle.css">
</head>
<body>

	<header class="w3-container w3-theme w3-padding" id="myHeader">
		<div class="w3-center">
			<h1 class="w3-animate-bottom">
				重大新闻网 <sup>new~</sup>
			</h1>
		</div>
	</header>

	<%		
		newsCrawler nc = new newsCrawler();		
	%>

	<div class="w3-row">
		<div class="w3-col" style="width: 25%">
			<p></p>
		</div>
		<div class="w3-col w3-card-2 w3-padding-top w3-center"
			style="width: 50%">
			<h3>*重大新闻*</h3>
			<%
				String categoryId = "00";
				List<News> importantNewsList = nc.findNewsByCategory(categoryId);
			%>
			<ul class="w3-ul w3-card-4">
				<%for (News ns : importantNewsList) {%>
				<li><a id="<%out.print(ns.getId());%>"
					href="showNews.jsp?newsId=<%out.print(ns.getId());%>"
					rel="external"> <%out.print(ns.getTitle());%>
				</a></li>
				<%}%>
			</ul>
		</div>

		<div class="w3-col" style="width: 25%">
			<p></p>
		</div>
	</div>
			
	<div class="w3-row-padding w3-center w3-margin-top">
	
		<div class="w3-third">
			<div class="w3-card-2 w3-padding-top" style="min-height: 460px">			
				<h4>*重大教学*</h4>
				<%
					categoryId="48";
					importantNewsList = nc.findNewsByCategory(categoryId);
				%>
				<ul class="w3-ul w3-card-4">
					<%for (News ns : importantNewsList) {%>
					<li class="w3-card-2"><a id="<%out.print(ns.getId());%>"
						href="showNews.jsp?newsId=<%out.print(ns.getId());%>"
						rel="external">					
						<img src="<%out.print(ns.getThumb());%>" alt="<%out.print(ns.getTitle());%>" style="width: 100%">						
						<%out.print(ns.getTitle());%>										
					</a></li>					
					<%}%>
				</ul>
			</div>
		</div>
	
		<div class="w3-third">
			<div class="w3-card-2 w3-padding-top" style="min-height: 460px">
				<h4>*重大科研*</h4>
				<%
					categoryId="46";
					importantNewsList = nc.findNewsByCategory(categoryId);
				%>
				<ul class="w3-ul w3-card-4">
					<%for (News ns : importantNewsList) {%>
					<li class="w3-card-2"><a id="<%out.print(ns.getId());%>"
						href="showNews.jsp?newsId=<%out.print(ns.getId());%>"
						rel="external">					
						<img src="<%out.print(ns.getThumb());%>" alt="<%out.print(ns.getTitle());%>" style="width: 100%">						
						<%out.print(ns.getTitle());%>										
					</a></li>					
					<%}%>
				</ul>
			</div>
		</div>

		<div class="w3-third">
			<div class="w3-card-2 w3-padding-top" style="min-height: 460px">
				<h4>*招生就业*</h4>
				<%
					categoryId="53";
					importantNewsList = nc.findNewsByCategory(categoryId);
				%>
				<ul class="w3-ul w3-card-4">
					<%for (News ns : importantNewsList) {%>
					<li class="w3-card-2"><a id="<%out.print(ns.getId());%>"
						href="showNews.jsp?newsId=<%out.print(ns.getId());%>"
						rel="external">					
						<img src="<%out.print(ns.getThumb());%>" alt="<%out.print(ns.getTitle());%>" style="width: 100%">						
						<%out.print(ns.getTitle());%>										
					</a></li>					
					<%}%>
				</ul>
			</div>
		</div>		
	</div>
	
	<br>	
	
	<footer class="w3-container w3-theme-dark">
		<div class="w3-center">
			<h4>
				Created by <a href="http://www.ioriens.com">Oriens</a> and his
				teammates!
			</h4>
			<a class="w3-text-white w3-large" href="#myHeader">Go to top!</a>
		</div>
	</footer>
</body>
</html>
