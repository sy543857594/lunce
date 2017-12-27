package com.itheima;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class CreateIndex {
	public static void main(String[] args) throws Exception {
		//索引创建的目录
		Directory dir=FSDirectory.open(new File("C:/Users/64566/Desktop/index"));
		
		//创建分词器
		Analyzer analyzer=new StandardAnalyzer();
		
		//创建写索引的配置信息
		IndexWriterConfig writerConfig=new IndexWriterConfig(Version.LATEST, analyzer);
		
		//创建indexwriter对象，用于写索引,传入路径告知往哪写怎么写
		IndexWriter writer=new IndexWriter(dir, writerConfig);
		//读取文件
		File file=new File("C:/Users/64566/Desktop/txt");
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			//读取文件信息及内容
			String fileName = f.getName();
			String filePath = f.getPath();
			String fileContext = FileUtils.readFileToString(f);
			long fileSize = FileUtils.sizeOf(f);
			
			//将内容存放到域对象
			Field fileNameField=new TextField("name", fileName, Store.YES);
			Field filePathField=new TextField("path", filePath, Store.YES);
			Field fileContextField=new TextField("context", fileContext, Store.YES);
			Field fileSizeField=new TextField("size", fileSize+"", Store.NO);
			
			
			//创建索引文件对象
			Document doc=new Document();
			
			//将域对象添加到索引库
			doc.add(fileSizeField);
			doc.add(fileContextField);
			doc.add(filePathField);
			doc.add(fileNameField);
			
			//将索引库写入硬盘
			writer.addDocument(doc);
			
		}
		writer.commit();
		
		writer.close();
		
	}
	
	
}
