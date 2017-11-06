package cn.tarena.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.glassfish.grizzly.StandaloneProcessor;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class TestDemo {
	@Test
	public void createIndext() throws Exception{
		Directory directory=FSDirectory.open(new File("./index"));
		//Analyzer analyzer=new StandardAnalyzer();
		Analyzer analyzer=new IKAnalyzer();
		IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
		IndexWriter writer=new IndexWriter(directory, config);
		Document doc1=new Document();
		doc1.add(new TextField("title","Thinking in Java",Store.YES));
		doc1.add(new TextField("desc","学习Java的入门书籍",Store.YES));
		
		Document doc2=new Document();
		doc2.add(new TextField("title","Thinking in C++",Store.YES));
		doc2.add(new TextField("desc","学习C++的必备教材",Store.YES));
		
		writer.addDocument(doc1);
		writer.addDocument(doc2);
		
		writer.close();
	}
	
	@Test
	public void search() throws Exception{
		Directory directory=FSDirectory.open(new File("./index"));
		
		IndexSearcher searcher=new IndexSearcher(IndexReader.open(directory));
		
		TermQuery query=new TermQuery(new Term("title","学习"));
		
		TopDocs docs=searcher.search(query, 20);
		
		for(ScoreDoc sd:docs.scoreDocs){
			float score=sd.score;
			Document doc=searcher.doc(sd.doc);
			System.out.println("文档得分:"+score+" 标题"+doc.get("title")+" 描述:"+doc.get("desc"));
		}
		
		directory.close();
	}
	
	
	
}
