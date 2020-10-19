/**
 * 
 */
package com.salallegra.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Loan;

/**
 * @author ppradhan
 *
 */
public class LoansDAO extends BaseDAO<Loan> {
	
	public LoansDAO(Connection conn) {
		super(conn);
	}

	public void addLoan(Loan loan) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate,dateIn) VALUES (?,?,?,?,?,?)", new Object[] { loan.getBookId(),loan.getBranchId(),loan.getCardNo(),loan.getDateOut(),loan.getDueDate(),loan.getDateIn() });
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

//	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
//		return read("SELECT * FROM tbl_book", null);
//	}
//	public List<Book> getAllBooksForBranch(int branchId) throws SQLException,ClassNotFoundException{
//		return read("SELECT bk.bookId, bk.title  "
//				+ "From tbl_book bk "
//				+ "INNER JOIN tbl_book_copies on bk.bookId = tbl_book_copies.bookId "
//				+ "Where tbl_book_copies.noOfCopies > 0 and tbl_book_copies.branchId = 4",null);
//				
//	}
//	public List<Book> getBookCopy(int bookId) throws SQLException, ClassNotFoundException{
//		return read("SELECT tbl_book.title, tbl_book_copies.noOfCopies"
//				+ "FROM tbl_book"
//				+ "INNER JOIN tbl_book_copies ON tbl_book.bookId=tbl_book_copies.bookId"
//				+ "WHERE tbl_book.bookId = 1",null);
//	}
//	
	public List<Loan> readAllBooksByName(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {searchString});
	}

	@Override
	public List<Loan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Loan> loans = new ArrayList<>();
		AuthorDAO adao = new AuthorDAO(conn);
		while (rs.next()) {
//			Book b = new Book(rs.getInt("bookId"), rs.getString("title"));
//			b.setAuthors(adao.read("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));
//			//b.setGenres()
//			loans.add(b);
		}
		return loans;
	}

}
