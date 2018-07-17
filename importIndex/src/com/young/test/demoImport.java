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
//		采集数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> list = bookDao.findAllBook();
//		创建文档对象
		Document document = new Document();
//		创建文档集合
		List<Document> docList = new ArrayList();
		for (Book book : list) {
//		创建Field对象
				Field idField = new StringField("id", book.getId().toString(), Store.YES);
				Field nameField = new StringField("name",book.getName(),Store.YES);
				Field priceField = new FloatField("price", book.getPrice(), Store.YES);
				Field picField = new StoredField("pic", book.getPic());
				Field descField = new TextField("desc",book.getDescription(),Store.YES);
//				吧field对象放入文档对象中
				document.add(idField);
				document.add(nameField);
				document.add(priceField);
				document.add(picField);
				document.add(descField);
//				把文档放入文档集合中
				docList.add(document);
		}
//		创建分词对象（中文）
		IKAnalyzer ikanalyzer = new IKAnalyzer();
//		创建索引库地址流对象（Director）
		Directory director = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\chinese")); 
//		创建写入索引库的配置对象（indexWriterConfig）
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, ikanalyzer);
//		创建写入索引库的对象（IndexWriter）
		IndexWriter indexWriter = new IndexWriter(director, indexWriterConfig);
//		将文档遍历写入索引库
		for (Document doc : docList) {
			indexWriter.addDocument(doc);
		}
//		关闭流
		indexWriter.close();
	}
	
}
