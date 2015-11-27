package com.oriens.cquNews.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.oriens.cquNews.domains.News;
import com.oriens.cquNews.domains.NewsCategory;


public class newsCrawler 
{


	public List<NewsCategory> findNewsCategories()
	{
		List<NewsCategory> categories=new ArrayList<NewsCategory>();
		categories.add(new NewsCategory("46","重大科研"));
		categories.add(new NewsCategory("47","重大学术" ));
		categories.add(new NewsCategory("48","重大教学"));
		categories.add(new NewsCategory("53","招生就业"));
		categories.add(new NewsCategory("00","重要新闻"));		
		return categories;
	}
	
	public List<NewsCategory> indexNewsCategories()
	{
		List<NewsCategory> categories=new ArrayList<NewsCategory>();
		categories.add(new NewsCategory("46","重大科研"));
		categories.add(new NewsCategory("48","重大教学"));
		categories.add(new NewsCategory("53","招生就业"));
	
		return categories;
	}
	

	public List<News> findNewsByCategory(String categoryId)
	{
		List<News> newsList=new ArrayList<News>();
		try {
			//当为重要新闻时，没有固定的归类，所以要分情况判断
			//categoryId=00表示重要新闻
			if(categoryId.equals("00")){
				Document doc = Jsoup.connect("http://news.cqu.edu.cn/news/").timeout(5000).get();
				Elements topnews = doc.select("div.topnews li.tag_title");
				for(Element row:topnews)
				{
					Element link=row.select("a").first();
					News news=new News(link.attr("href"),link.attr("title"),"","","");
					newsList.add(news);
				}
			}else{				
				Document doc = Jsoup.connect("http://news.cqu.edu.cn/news/article/list.php?catid="+categoryId).get();
				Elements liphoto = doc.select("div.liphoto div.row1");
				for(Element row:liphoto)
				{
					Element link=row.select("a").first();
					Element img=row.select("img").first();
					News news=new News(link.attr("href"),img.attr("alt"),"","","http://news.cqu.edu.cn/"+img.attr("src"));
					newsList.add(news);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsList;
	}

	public News findNews(String newsId)
	{
		News news=new News();
		try {
			System.out.println("http://news.cqu.edu.cn"+newsId);
			Document doc = Jsoup.connect("http://news.cqu.edu.cn"+newsId).timeout(5000).get();
			
			news.setTitle(doc.select("div.title h1").first().text());
			Element content=doc.select("#zoom").first();		
			Elements imgs=doc.select("img");
			for(Element img:imgs)
			{
				String src=img.attr("src");
				img.attributes().remove("height");
				img.attributes().remove("alt");
				img.attr("class","w3-card-4");
				img.attr("width","100%");
				img.attr("src","http://news.cqu.edu.cn"+src );
			}
			content.attributes().remove("id");
			content.attr("class","newsContent");
			news.setContent(content.toString());
		} catch (IOException e) {
			System.out.println(e);
		}
		return news;
	}

	public static void main( String[] args )
	{		
		
		newsCrawler c=new newsCrawler();
		News news=c.findNews("/news/article/show.php?itemid=71307");
		System.out.println(news.getTitle());
		System.out.println(news.getContent());

	}
}
