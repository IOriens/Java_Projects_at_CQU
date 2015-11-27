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
		<div class="w3-col w3-center" style="width: 25%">
			<br>
			<br>
			<div id="newsSearch2" class="w3-card-4" style="width: 80%">
				<div class="w3-container w3-blue" >
					<h3>新闻查询</h3>
				</div>
				<form id="form1" method="post" class="w3-form" action="showOutCome.jsp">
					<div class="w3-input-group">
						<input class="w3-input" type="text" name="queryWords">
					</div>
					<div>
						<input type="submit" class="w3-btn w3-blue" value="提交">
					</div>
				</form>
			</div>
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

		<div class="w3-col w3-cneter" style="width: 25%">
			<br>
			<br>  
			<div id="newsSearch3" class="w3-card-4 w3-center" style="width: 80%">
				<br>
				<p>
					<a href="prepareNews.jsp" class="w3-btn w3-center w3-blue">数据准备</a>
				<p>
				<br>
			</div>
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
		<div class="w3-center" >
			<h4>
				Created by <a href="http://www.ioriens.com">Oriens</a> and his
				teammates!
			</h4>
			<a class="w3-text-white w3-large" href="#myHeader">Go to top!</a>
		</div>
	</footer>
</body>
</html>
