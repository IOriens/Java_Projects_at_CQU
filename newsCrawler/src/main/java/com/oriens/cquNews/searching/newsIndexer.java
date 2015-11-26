package com.oriens.cquNews.searching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.oriens.cquNews.controller.newsCrawler;
import com.oriens.cquNews.domains.News;


public class newsIndexer {

	public static void main(String[] args) {
		try {
			File f=new File("E:/lucene/logs/342342.txt");			
			Directory directory = FSDirectory.open(Paths.get((f.getPath())));
			Analyzer analyzer = new StandardAnalyzer();  
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer); 
			iwc.setOpenMode(OpenMode.CREATE);
			IndexWriter iwriter = new IndexWriter(directory, iwc);
			
			Document doc = new Document();
			
			
			
			String newsId="/news/article/article_71157.html";
			newsCrawler c = new newsCrawler();			
			News news = c.findNews(newsId);
			news.setId(newsId);
			System.out.println(news.getTitle());
			
			
			doc.add(new StringField("title", news.getTitle(), Field.Store.YES));
			doc.add(new StringField("id", news.getId(), Field.Store.YES));
			doc.add(new TextField("content", news.getContent()+news.getTitle(),Field.Store.NO));
	
			iwriter.addDocument(doc);
			iwriter.close();
			
			
			
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			// Parse a simple query that searches for "text":
			QueryParser parser = new QueryParser( "content", analyzer);
			parser.setDefaultOperator(QueryParser.AND_OPERATOR);					
//			Query query = parser.parse("\"重大\"");
			Query query = parser.parse("重大");
			
			TopDocs results = isearcher.search(query, 5 * 10);
		    ScoreDoc[] hits = results.scoreDocs;
		    
		    int numTotalHits = results.totalHits;
		    System.out.println(numTotalHits + " total matching documents");
						
			
			// Iterate through the results:
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println(hitDoc.get("title"));
				System.out.println(hitDoc.get("id"));				
			}
			
			ireader.close();
			directory.close();

		} catch (IOException | ParseException e) {

			e.printStackTrace();
		}
	}
}
