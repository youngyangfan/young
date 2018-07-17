package com.young.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.StoredField;

import com.young.dao.BookDao;
import com.young.dao.impl.BookDaoImpl;
import com.young.domain.Book;

public class demoImport {
	@Test
	public void testDemo() throws Exception {
//		�ɼ�����
		BookDao bookDao = new BookDaoImpl();
		List<Book> list = bookDao.findAllBook();
//		�����ĵ�����
		Document document = new Document();
//		�����ĵ�����
		List<Document> docList = new ArrayList();
		for (Book book : list) {
//		����Field����
				Field idField = new StringField("id", book.getId().toString(), Store.YES);
				Field nameField = new StringField("name",book.getName(),Store.YES);
				Field priceField = new FloatField("price", book.getPrice(), Store.YES);
				Field picField = new StoredField("pic", book.getPic());
				Field descField = new TextField("desc",book.getDescription(),Store.YES);
//				��field��������ĵ�������
				document.add(idField);
				document.add(nameField);
				document.add(priceField);
				document.add(picField);
				document.add(descField);
//				���ĵ������ĵ�������
				docList.add(document);
		}
//		�����ִʶ������ģ�
		IKAnalyzer ikanalyzer = new IKAnalyzer();
//		�����������ַ������Director��
		Directory director = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\chinese")); 
//		����д������������ö���indexWriterConfig��
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, ikanalyzer);
//		����д��������Ķ���IndexWriter��
		IndexWriter indexWriter = new IndexWriter(director, indexWriterConfig);
//		���ĵ�����д��������
		for (Document doc : docList) {
			indexWriter.addDocument(doc);
		}
//		�ر���
		indexWriter.close();
	}
	
}
