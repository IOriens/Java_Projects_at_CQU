package com.oriens.cquNews.searching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class newsSearcher{

	public List<String> newsSearch(String queryPhrase) {
		List<String> result = new ArrayList<String>();		
		try {
			File f=new File("temp/lucene/logs");			
			Directory directory = FSDirectory.open(Paths.get((f.getPath())));
			Analyzer analyzer = new StandardAnalyzer();  

			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);

			// Parse a simple query that searches for "text":
			QueryParser parser = new QueryParser( "content", analyzer);
			parser.setDefaultOperator(QueryParser.AND_OPERATOR);					
			Query query = parser.parse("\""+queryPhrase+"\"");
			//Query query = parser.parse("重庆大学 青年教师");//两种情况
			
			//搜索条数
			int searchNum=100;					
			TopDocs results = isearcher.search(query, searchNum);
			ScoreDoc[] hits = results.scoreDocs;

			int numTotalHits = results.totalHits;
			System.out.println(numTotalHits + " total matching documents");

			// Iterate through the results:
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
//				System.out.println(hitDoc.get("title"));				
				result.add(generateAddress(hitDoc.get("title"),hitDoc.get("id")));							
			}

			ireader.close();
			directory.close();

		} catch (IOException | ParseException e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public String generateAddress(String title,String addr) {
		
		String[] array1 = addr.split("[\\D]+");
		String id=array1[array1.length-1];
		String Address="<a id=\"newsid_"
					  +id+"\""
					  +" href=\"showNews.jsp?newsId="
					  +addr+"\""
					  +" rel=\"external\"> "
					  +title
					  +" </a>";		
		return Address;		
	}
	
	public static void main(String[] args) {
		newsSearcher newsSearcherIns=new newsSearcher();
		List<String> newsIdList=newsSearcherIns.newsSearch("就业");
		for(String nl:newsIdList){
			System.out.println(nl);
		}
	}
}


