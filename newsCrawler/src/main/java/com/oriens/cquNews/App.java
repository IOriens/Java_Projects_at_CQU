package com.oriens.cquNews;



import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class App 
{
	public static void main( String[] args )
	{
		
		Document doc;	
		try {
			doc = Jsoup.connect("http://news.cqu.edu.cn/news/article/article_71157.html").get();
			Elements newsHeadlines=doc.getElementsByClass("title");			
			System.out.println(newsHeadlines.text());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
