package com.salallegra.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.salallegra.library.dao.AuthorDAO;
import com.salallegra.library.dao.BookDAO;
import com.salallegra.library.dao.PublisherDAO;
import com.salallegra.library.Entity.Author;
import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Publisher;

public class AdministratorService {

	public ConnectionUtil conUtil = new ConnectionUtil();

	public String addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			if (book.getTitle() != null && book.getTitle().length() > 45) {
				return "Book Title cannot be empty and should be 45 char in length";
			}
			book.setBookId(bdao.addBookWithPk(book));
			for (Author a : book.getAuthors()) {
				adao.addBookAuthors(book.getBookId(), a.getAuthorId());
			}
			// Do the same for genres/branche etc.

			conn.commit();
			return "Book added sucessfully";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			return "Unable to add book - contact admin.";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addBookSimple(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			bdao.addBook(book);
		
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			bdao.deleteBook(book);		
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void updateBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			bdao.updateBook(book);		
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Book> getBooks(String searchString) {
		try (Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
			if (searchString != null) {
				return bdao.readAllBooksByName(searchString);
			} else {
				return bdao.readAllBooks();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Publisher> getAllPublishers() {
		try (Connection conn = conUtil.getConnection()) {
			PublisherDAO pdao = new PublisherDAO(conn);

			return pdao.readAllPublishers();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * This is for the top level menu of admin to display all books and their
	 * associated authors
	 */
//	public List<Book> getAllBooksAuthors(String searchString) {
//		try(Connection conn = conUtil.getConnection()) {
//			BookDAO bdao = new BookDAO(conn);
//			if (searchString != null) {
//				return bdao.readAllBooksByName(searchString);
//			} else {
//				return bdao.readAllBooks();
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

}
