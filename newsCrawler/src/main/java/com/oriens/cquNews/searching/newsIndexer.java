package com.oriens.cquNews.searching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class newsIndexer {

	public static void main(String[] args) {
		try {
			File f=new File("E:/lucene/logs");
			Directory directory = FSDirectory.open(Paths.get((f.getPath())));
			Analyzer analyzer = new StandardAnalyzer();  
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer); 	
			IndexWriter iwriter = new IndexWriter(directory, iwc);
			Document doc = new Document();
			String text = "This is the text to be indexed.";
			doc.add(new StringField("fieldname", text,Field.Store.YES));
			iwriter.addDocument(doc);
			iwriter.close();

			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			// Parse a simple query that searches for "text":
			QueryParser parser = new QueryParser( "fieldname", analyzer);
			Query query = parser.parse("text");
			ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
			System.out.println(hits.length);
			// Iterate through the results:
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println(hitDoc.get("fieldname"));
			}
			ireader.close();
			directory.close();


		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
