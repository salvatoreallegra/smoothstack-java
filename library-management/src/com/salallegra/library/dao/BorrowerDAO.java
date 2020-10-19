package com.salallegra.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salallegra.library.Entity.Author;
import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.Entity.Loan;

public class BorrowerDAO extends BaseDAO<Borrower> {
	
	public BorrowerDAO(Connection conn) {
		super(conn);
	}
	public List<Borrower> readAllCardNos() throws SQLException, ClassNotFoundException {
		return read("SELECT cardNo FROM tbl_borrower", null);
	}
	public List<Borrower> readAllBooksBranch() throws SQLException, ClassNotFoundException {
		return read("SELECT cardNo FROM tbl_borrower", null);
	}
	public void updateLoan(Loan loan) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dateIn = ? WHERE bookId = ? and branchId = ?",
				new Object[] { loan.getDateIn(), loan.getBookId(), loan.getBranchId() });
	}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
//			authors.add(new Author(rs.getInt("authorId"), rs.getString("authorName")));
			//also populate the books written by this Author
			borrowers.add(new Borrower(rs.getInt("cardNo")));
		}
		return borrowers;
	}
}
