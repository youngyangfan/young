package com.young.test;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class demoQuery {
	
	@Test
	public void DemoQuery() throws Exception {
		Analyzer analyzer = new StandardAnalyzer();
//		创建搜索解析器对象
		QueryParser queryParser = new QueryParser("name", analyzer);
//		设置查询条件
		Query query = queryParser.parse("java");
//		指定索引库地址的流对象
		Directory directory = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\english"));
//		读取索引库数据对象
		IndexReader indexReader = DirectoryReader.open(directory);
//		创建搜索对象
		IndexSearcher indexSearch = new IndexSearcher(indexReader);
//		执行查询，返回结果集
		TopDocs topDocs = indexSearch.search(query, 2);
//		结果集封装数组
		ScoreDoc[] scoreDoc= topDocs.scoreDocs;
//		遍历坐标数据
		for (ScoreDoc scoreDoc2 : scoreDoc) {
//			获取每一个坐标中的文档ID
			int docId = scoreDoc2.doc;
//			根据文档ID搜索文档，返回文档对象
			Document document = indexSearch.doc(docId);
//			打印文档对象
			System.out.println(document.get("id"));
			System.out.println(document.get("name"));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
