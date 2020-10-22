package com.salallegra.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.Entity.Branch;
import com.salallegra.library.Entity.Copies;
import com.salallegra.library.Entity.Loan;
import com.salallegra.library.dao.BookDAO;
import com.salallegra.library.dao.BorrowerDAO;
import com.salallegra.library.dao.BranchDAO;
import com.salallegra.library.dao.CopiesDAO;
import com.salallegra.library.dao.LoansDAO;

public class BorrowerService {

	public ConnectionUtil conUtil = new ConnectionUtil();

	// Get cards so we can validate if the card number entered is valid
	public List<Borrower> getAllCards() throws SQLException{
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			return borrowerDAO.readAllCardNos();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public List<Book> getAllBooksForBranch(int branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			return bookDAO.getAllBooksForBranch(branchId);
			

		} catch (ClassNotFoundException | SQLException e) {

			return null;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	public List<Copies> getNumberOfCopies(int branchId, int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			CopiesDAO copiesDAO = new CopiesDAO(conn);
			//conn.close();
			return copiesDAO.getBookCopy(bookId, branchId);
			

		} catch (ClassNotFoundException | SQLException e) {

			return null;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/*
	 * checkOutBook will insert a new row into tbl_book_loans this is part of the
	 * borrower module called from the Borrower Menu
	 */
	public boolean checkOutBook(Loan loan) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			LoansDAO ldao = new LoansDAO(conn);
			ldao.addLoan(loan);
			conn.commit();
			// close connection here
			conn.close();
			return false;

		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("This book is already checked out");

			return true;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public List<Book> getCheckedOutBooks(int cardNo, int branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			// return ldao.addLoan(loan);
			return bdao.getCheckedOutBooks(cardNo, branchId);
			// conn.commit();

		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("Error, re-start the system");
			return null;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void checkInBook(Loan loan) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			borrowerDao.updateLoan(loan);
			conn.commit();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

}
