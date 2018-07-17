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
//		������������������
		QueryParser queryParser = new QueryParser("name", analyzer);
//		���ò�ѯ����
		Query query = queryParser.parse("java");
//		ָ���������ַ��������
		Directory directory = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\english"));
//		��ȡ���������ݶ���
		IndexReader indexReader = DirectoryReader.open(directory);
//		������������
		IndexSearcher indexSearch = new IndexSearcher(indexReader);
//		ִ�в�ѯ�����ؽ����
		TopDocs topDocs = indexSearch.search(query, 2);
//		�������װ����
		ScoreDoc[] scoreDoc= topDocs.scoreDocs;
//		������������
		for (ScoreDoc scoreDoc2 : scoreDoc) {
//			��ȡÿһ�������е��ĵ�ID
			int docId = scoreDoc2.doc;
//			�����ĵ�ID�����ĵ��������ĵ�����
			Document document = indexSearch.doc(docId);
//			��ӡ�ĵ�����
			System.out.println(document.get("id"));
			System.out.println(document.get("name"));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
