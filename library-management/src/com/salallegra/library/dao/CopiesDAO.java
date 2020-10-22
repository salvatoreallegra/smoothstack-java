package com.salallegra.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Branch;
import com.salallegra.library.Entity.Copies;

public class CopiesDAO extends BaseDAO<Copies>{
	public CopiesDAO(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}
	
	public Integer addBookWithPk(Book book) throws ClassNotFoundException, SQLException {
		return saveWithPk("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET bookName = ? WHERE bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Copies> readAllBooks() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book", null);
	}
	public List<Copies> getBookCopy(int bookId, int branchId) throws SQLException, ClassNotFoundException{
		return read("SELECT bookId,branchId,noOfCopies "
				+ "FROM tbl_book_copies "
				+ "WHERE bookId = " + bookId + " and branchId = " +  branchId,null);
	}
	public List<Copies> getBookCopy(int bookId) throws SQLException, ClassNotFoundException{
		return read("SELECT bookId,branchId,noOfCopies "
				+ "FROM tbl_book_copies "
				+ "WHERE bookId = " + bookId ,null);
	}
	
	public void updateCopies(Copies copies, Branch branch) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? and branchId = ?",
				new Object[] {  copies.getNoCopies(),copies.getBookId(), branch.getBranchID() });
	}
	
	public List<Copies> readAllBooksByName(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {searchString});
	}
	
//	public List<Copies> readAllBooksByName(String searchString) throws SQLException, ClassNotFoundException {
//		searchString = "%"+searchString+"%";
//		return read("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {searchString});
//	}

	@Override
	public List<Copies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Copies> copies = new ArrayList<>();
		//CopiesDAO cdao = new CopiesDAO(conn);
		while (rs.next()) {
			
			Copies c = new Copies(rs.getInt("bookId"), rs.getInt("branchId"), rs.getInt("noOfCopies"));
			//c.setAuthors(cdao.read("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));
			//b.setGenres()
			copies.add(c);
		}
		return copies;
	}
	

}
