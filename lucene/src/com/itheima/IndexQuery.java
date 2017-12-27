package com.itheima;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class IndexQuery {
	public static void main(String[] args) throws Exception {
		//指定索引库位置
		Directory dir=FSDirectory.open(new File("C:/Users/64566/Desktop/index"));
		
		//创建indexreader对象
		IndexReader reader=DirectoryReader.open(dir);
		
		//创建indexsearcher对象
		IndexSearcher searcher=new IndexSearcher(reader);
		
		//创建query对象
		Query query=new TermQuery(new Term("context", "java"));
		
		//执行查询，并设置最大返回条数
		TopDocs topDocs = searcher.search(query, 3);
		
		//获取查询到的总条数
		int totalHits = topDocs.totalHits;
		System.out.println("总条数"+totalHits);
		
		//获取查询的索引文档
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		for (ScoreDoc scoreDoc : scoreDocs) {
			//获取文档id
			int docId = scoreDoc.doc;
			
			Document document = searcher.doc(docId);
			
			System.out.println("文件名"+document.get("name"));
			System.out.println("路径"+document.get("path"));
			System.out.println("大小"+document.get("size"));
			System.out.println("内容"+document.get("context"));
			System.out.println("我是分割线========================================");
		}
		
		reader.close();
	}
	
	
	

		
		@Test
		public void query() throws Exception{
			Directory dir=FSDirectory.open(new File("C:/Users/64566/Desktop/index"));
			IndexReader reader=DirectoryReader.open(dir);
			
			IndexSearcher searcher=new IndexSearcher(reader);
			
			Query query=new TermQuery(new Term("context", "apache"));
			
			
			TopDocs topDocs = searcher.search(query, 3);
			
			int totalHits = topDocs.totalHits;
			System.out.println("总条数"+totalHits);
			
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				
				int docId = scoreDoc.doc;
				
				Document document = searcher.doc(docId);
				
				System.out.println("文件名"+document.get("name"));
				System.out.println("路径"+document.get("path"));
				System.out.println("大小"+document.get("size"));
				System.out.println("内容"+document.get("context"));
				System.out.println("我是分割线========================================");
			}
			
			reader.close();
		}
}
