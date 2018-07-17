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
//		�����ִ�������
		Analyzer analyzer = new StandardAnalyzer();
//		������������������ 
		QueryParser queryParser = new QueryParser("name", analyzer);
//		���ò�ѯ����
		Query query = queryParser.parse("java");
//		����ָ���������ַ��������Directory
		Directory director = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\english"));
//		������ȡָ��������Ķ���DirectoryReader
		IndexReader indexReader = DirectoryReader.open(director);
//		������������indexSearch��Ҫ��ȡ���������
		IndexSearcher searcher = new IndexSearcher(indexReader);
//		ִ�в�ѯ�����ؽ������ָ����ѯ������ָ����ѯ���ؼ�¼����
		TopDocs topDocs = searcher.search(query, 2);
//		�������Ҫ�������꣨������ʽ����������������
		ScoreDoc[] docs = topDocs.scoreDocs;
//		������������
		for (ScoreDoc scoreDoc : docs) {
//			��ȡÿһ�������е��ĵ�ID
			int docId = scoreDoc.doc;
//			�����ĵ�ID�����ĵ��������ĵ�����
			Document doc = searcher.doc(docId);
//			��ӡ�ĵ�����
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("price"));
			System.out.println(doc.get("desc"));
		}
	}
	
}
