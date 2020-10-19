package com.salallegra.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.dao.BookDAO;
import com.salallegra.library.dao.BorrowerDAO;

public class BorrowerService {

	public ConnectionUtil conUtil = new ConnectionUtil();

	// Get cards so we can validate if the card number entered is valid
	public List<Borrower> getAllCards() {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			return borrowerDAO.readAllCardNos();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Book> getAllBooksForBranch() {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			return bookDAO.getAllBooksForBranch();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
