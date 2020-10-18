package com.salallegra.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.salallegra.library.Entity.Author;
import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Branch;
import com.salallegra.library.Entity.Copies;
import com.salallegra.library.dao.AuthorDAO;
import com.salallegra.library.dao.BookDAO;
import com.salallegra.library.dao.BranchDAO;
import com.salallegra.library.dao.CopiesDAO;

public class LibrarianService {

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
			// for(Author a: book.getAuthors()) {
			// adao.addBookAuthors(book.getBookId(), a.getAuthorId());
			// }
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

	public List<Branch> getAllBranches() {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BranchDAO branchDAO = new BranchDAO(conn);
			return branchDAO.readAllBranches();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	public List<Book> getAllBooks() {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			return bookDAO.readAllBooks();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<Copies> getBookCopies(int id) {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			CopiesDAO copiesDAO = new CopiesDAO(conn);
			return copiesDAO.getBookCopy(id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void updateBranch(Branch branch) {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BranchDAO branchDAO = new BranchDAO(conn);
			branchDAO.updateBranch(branch);
			conn.commit();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public void updateCopy(Copies copy) {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			CopiesDAO copiesDAO = new CopiesDAO(conn);
			copiesDAO.updateCopies(copy);
			conn.commit();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
	}

}
