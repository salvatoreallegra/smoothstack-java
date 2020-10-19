package com.salallegra.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.Entity.Loan;
import com.salallegra.library.dao.BookDAO;
import com.salallegra.library.dao.BorrowerDAO;
import com.salallegra.library.dao.LoansDAO;

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

	public List<Book> getAllBooksForBranch(int branchId) {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			return bookDAO.getAllBooksForBranch(branchId);

		} catch (ClassNotFoundException | SQLException e) {
			
			return null;
		}

	}
	/*
	 * checkOutBook will insert a new row into tbl_book_loans
	 * this is part of the borrower module called from the Borrower Menu
	 */
	public boolean checkOutBook(Loan loan) {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			LoansDAO ldao = new LoansDAO(conn);
			//return ldao.addLoan(loan);
			ldao.addLoan(loan);
			conn.commit();
			return false;

		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("This book is already checked out");
			//return null;
			return true;
		}

	}
	public List<Book> getCheckedOutBooks(int cardNo) {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			//return ldao.addLoan(loan);
			return bdao.getCheckedOutBooks(cardNo);
			//conn.commit();
			

		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("Error, re-start the system");
			return null;
		}

	}

}
