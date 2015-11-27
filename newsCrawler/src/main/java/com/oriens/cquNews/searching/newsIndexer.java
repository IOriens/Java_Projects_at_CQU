package com.oriens.cquNews.searching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.oriens.cquNews.controller.newsCrawler;
import com.oriens.cquNews.domains.News;
import com.oriens.cquNews.domains.NewsCategory;


public class newsIndexer {


	/*private File f=null;			
	private Directory directory = null;
	private Analyzer analyzer = null;
	private IndexWriterConfig iwc=null;
	private IndexWriter iwriter=null;
	private Document doc =null;
	private newsCrawler nc = null;

	public newsIndexer() {
				
		try {
			f=new File("E:/lucene/logs");	
			directory = FSDirectory.open(Paths.get((f.getPath())));
			analyzer = new StandardAnalyzer();
			iwc = new IndexWriterConfig(analyzer); 
			iwc.setOpenMode(OpenMode.CREATE);
			iwriter = new IndexWriter(directory, iwc);
			nc = new newsCrawler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	public void indexNews(){
		File f=new File("temp/lucene/logs");			
		Directory directory;
		try {
			directory = FSDirectory.open(Paths.get((f.getPath())));
			Analyzer analyzer = new StandardAnalyzer();  
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer); 
			iwc.setOpenMode(OpenMode.CREATE);
			IndexWriter iwriter = new IndexWriter(directory, iwc);

			Document doc =null;


			newsCrawler nc = new newsCrawler();
			List<NewsCategory> newsCategories=nc.findNewsCategories();
			String categoryId=null;
			String newsId;
			News news;
			
			for(NewsCategory newsCategory:newsCategories){
				categoryId=newsCategory.getId();
				List<News> importantNewsList = nc.findNewsByCategory(categoryId);			
				for (News ns : importantNewsList){
					newsId=ns.getId();
					news = nc.findNews(newsId);
					news.setId(newsId);
					System.out.println(news.getTitle());

					doc=new Document();
					doc.add(new StringField("title", news.getTitle(), Field.Store.YES));
					doc.add(new StringField("id", news.getId(), Field.Store.YES));
					doc.add(new TextField("content", news.getContent()+news.getTitle(),Field.Store.NO));
					iwriter.addDocument(doc);
				}
			}			
			iwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		newsIndexer newsIndexerIns=new newsIndexer();
		newsIndexerIns.indexNews();
	}
}
