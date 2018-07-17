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
//		数据库连接
		Connection conn = null;
//		预编译statement
		PreparedStatement ps = null;
//		返回结果集
		ResultSet rs = null;
//		图书列表实例化
		List<Book> books = new ArrayList<Book>();
//		加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		连接数据库
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/solr","root","root");
//		SQL语句
		String sql = "select * from book";
//		创建prepareStatement
		ps = conn.prepareStatement(sql);
//		获取结果集
		rs=ps.executeQuery();
//		结果集分析
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
