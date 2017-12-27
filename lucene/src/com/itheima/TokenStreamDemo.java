package com.itheima;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TokenStreamDemo {
	public static void main(String[] args) throws Exception{
		Analyzer analyzer=new StandardAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream(null, "Learn how to create a web page with Spring MVC.");
		
		tokenStream.reset();
		
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		
		while(tokenStream.incrementToken()){
			System.out.println(charTermAttribute);
		}


		tokenStream.close();
	}
	
	
	@Test
	public void test1() throws Exception{
		Analyzer analyzer=new SmartChineseAnalyzer();
		
		TokenStream tokenStream = analyzer.tokenStream(null, "全文检索是将整本书java、整篇文章中的任意内容信息查找出来的检索，java");
		
		tokenStream.reset();
		
		
		CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		
		while(tokenStream.incrementToken()){
			System.out.println(addAttribute);
		}
		
		tokenStream.close();
		
	}
	
	@Test
	public void test2() throws Exception{
		Analyzer analyzer=new IKAnalyzer();
		
		TokenStream tokenStream = analyzer.tokenStream(null, "全文检索是将整本书java、整篇文章中的任意内容信息查找出来的检索，java");
		
		tokenStream.reset();
		
		CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		
		
		while(tokenStream.incrementToken()){
			System.out.println(addAttribute);
		}
		
		tokenStream.close();
	}
}
