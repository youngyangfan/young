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
		// 采集数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.findAllBook();
		// 创建文档集合
		List<Document> documents = new ArrayList<>();
		//遍历数据集合
		for(Book book:bookList)
		{
			System.out.println(book);
			// 创建一个文档对象
			Document document = new Document();
			/*
			 *创建field对象，并存储到文档中
			document.add(new TextField("id",book.getId().toString(),Store.YES));
			document.add(new TextField("name",book.getName().toString(),Store.YES));
			document.add(new TextField("price",book.getPrice().toString(),Store.YES));
			document.add(new TextField("pic",book.getPic().toString(),Store.YES));
			document.add(new TextField("desc",book.getDesc().toString(),Store.YES));
			*/
			// 创建field对象，存储数据
			//创建field对象，存储数据，参数说明：1、指定域名 2、指定域名 3、是否存储
			Field idField = new StringField("id",book.getId()+"",Store.YES);
			Field nameField = new TextField("name",book.getName(),Store.YES);
			Field priceField = new FloatField("price", book.getPrice(), Store.YES);
			Field picField = new StoredField("pic",book.getPic());
			Field descField = new TextField("desc",book.getDescription(),Store.YES);
			// field加入文档
			document.add(idField);
			document.add(nameField);
			document.add(priceField);
			document.add(picField);
			document.add(descField);
			// 把文档加入文档集合中
			documents.add(document);
		}
		// 创建分词对象
		Analyzer analyzer = new StandardAnalyzer();
		// 创建索引库地址的流对象Director
		Directory directory = FSDirectory.open(new File("E:\\all\\Lucene&Solr\\Lucene\\lucene\\english"));
		// 创建写入索引库的配置对象indexWriterConfig
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		// 创建写入索引库的对象
		// indexWriter需要两个参数：指定索引库地址流，写入索引库配置文件
		IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
		// 把文档集合遍历一个个的文档写入索引库
		for (Document document : documents) {
			indexWriter.addDocument(document);
		}
		// 关闭流
		indexWriter.close();
	}
}
