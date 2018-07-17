package com.young.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.young.dao.BookDao;
import com.young.domain.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> findAllBook() {
//		���ݿ�����
		Connection conn = null;
//		Ԥ����statement
		PreparedStatement ps = null;
//		���ؽ����
		ResultSet rs = null;
//		ͼ���б�ʵ����
		List<Book> books = new ArrayList<Book>();
//		�������ݿ�����
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		�������ݿ�
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/solr","root","root");
//		SQL���
		String sql = "select * from book";
//		����prepareStatement
		ps = conn.prepareStatement(sql);
//		��ȡ�����
		rs=ps.executeQuery();
//		���������
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setName(rs.getString("name"));
			book.setPrice(rs.getFloat("price"));
			book.setPic(rs.getString("pic"));
			book.setDescription(rs.getString("description"));
			books.add(book);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
}
