package com.young.test;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class QueryIndex {

	@Test
	public void Query() throws Exception {
//		创建分词器对象
		Analyzer analyzer = new StandardAnalyzer();
//		创建搜索解析器对象 
		QueryParser queryParser = new QueryParser("name", analyzer);
//		设置查询条件
		Query query = queryParser.parse("java");
//		创建指定索引库地址的流对象Directory
		Directory director = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\english"));
//		创建读取指定索引库的对象DirectoryReader
		IndexReader indexReader = DirectoryReader.open(director);
//		创建搜索对象：indexSearch需要读取索引库对象
		IndexSearcher searcher = new IndexSearcher(indexReader);
//		执行查询，返回结果集（指定查询条件，指定查询返回记录数）
		TopDocs topDocs = searcher.search(query, 2);
//		结果集需要的是坐标（数组形式【（），（）】）
		ScoreDoc[] docs = topDocs.scoreDocs;
//		遍历坐标数据
		for (ScoreDoc scoreDoc : docs) {
//			获取每一个坐标中的文档ID
			int docId = scoreDoc.doc;
//			根据文档ID搜索文档，返回文档对象
			Document doc = searcher.doc(docId);
//			打印文档对象
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("price"));
			System.out.println(doc.get("desc"));
		}
	}
	
}
