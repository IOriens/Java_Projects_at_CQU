<%@page import="com.oriens.cquNews.controller.newsCrawler"%>
<%@page import="com.oriens.cquNews.domains.News"%>
<%@page import="com.oriens.cquNews.searching.newsSearcher"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<%

	request.setCharacterEncoding("utf-8");
	String queryWords=request.getParameter("queryWords");
	System.out.println(queryWords);
	newsSearcher newsSearcherIns=new newsSearcher();
	List<String> newsIdList=newsSearcherIns.newsSearch(queryWords);
%>

<head>
<title>查询结果</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/showNewsStyle.css">
</head>
<body>
	<div class="w3-container w3-theme w3-card-4 w3-center">
		<h1 class="w3-xxlarge">
			查询结果
		</h1>
		<h4>
			满足搜索条件的前“<%out.print(newsIdList.size());%>”条相关新闻
		</h4>
	</div>
	<br>
	<div class="w3-row">
		<div class="w3-col" style="width: 20%">
			<p></p>
		</div>
		<div class="w3-col w3-card-2 w3-padding-top w3-center"
			style="width: 60%">
			<ul class="w3-ul w3-card-4">
			<%for(String nl:newsIdList){%>
				<li><%out.print(nl);%></li>
			<%}%>
			</ul>
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
