package com.young.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.junit.Test;

import com.young.dao.BookDao;
import com.young.dao.impl.BookDaoImpl;
import com.young.domain.Book;

public class ImportIndex {

	@Test
	public void test() throws Exception {
		// �ɼ�����
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.findAllBook();
		// �����ĵ�����
		List<Document> documents = new ArrayList<>();
		//�������ݼ���
		for(Book book:bookList)
		{
			System.out.println(book);
			// ����һ���ĵ�����
			Document document = new Document();
			/*
			 *����field���󣬲��洢���ĵ���
			document.add(new TextField("id",book.getId().toString(),Store.YES));
			document.add(new TextField("name",book.getName().toString(),Store.YES));
			document.add(new TextField("price",book.getPrice().toString(),Store.YES));
			document.add(new TextField("pic",book.getPic().toString(),Store.YES));
			document.add(new TextField("desc",book.getDesc().toString(),Store.YES));
			*/
			// ����field���󣬴洢����
			//����field���󣬴洢���ݣ�����˵����1��ָ������ 2��ָ������ 3���Ƿ�洢
			Field idField = new StringField("id",book.getId()+"",Store.YES);
			Field nameField = new TextField("name",book.getName(),Store.YES);
			Field priceField = new FloatField("price", book.getPrice(), Store.YES);
			Field picField = new StoredField("pic",book.getPic());
			Field descField = new TextField("desc",book.getDescription(),Store.YES);
			// field�����ĵ�
			document.add(idField);
			document.add(nameField);
			document.add(priceField);
			document.add(picField);
			document.add(descField);
			// ���ĵ������ĵ�������
			documents.add(document);
		}
		// �����ִʶ���
		Analyzer analyzer = new StandardAnalyzer();
		// �����������ַ��������Director
		Directory directory = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\english"));
		// ����д������������ö���indexWriterConfig
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		// ����д��������Ķ���
		// indexWriter��Ҫ����������ָ���������ַ����д�������������ļ�
		IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
		// ���ĵ����ϱ���һ�������ĵ�д��������
		for (Document document : documents) {
			indexWriter.addDocument(document);
		}
		// �ر���
		indexWriter.close();
	}
}
