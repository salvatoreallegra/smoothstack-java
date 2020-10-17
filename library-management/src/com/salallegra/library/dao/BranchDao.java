/**
 * 
 */
package com.salallegra.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salallegra.library.Entity.Branch;

/**
 * @author salallegra
 *
 */
public class BranchDAO extends BaseDAO<Branch> {
	public BranchDAO(Connection conn) {
		super(conn);
	}

//	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
//		save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { author.getAuthorName() });
//	}
//
//	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
//		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
//				new Object[] { author.getAuthorName(), author.getAuthorId() });
//	}
//
//	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] { author.getAuthorId() });
//	}
//
	public List<Branch> readAllBranches() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_library_branch", null);
	}
//	
//	public List<Author> readAllAuthorsByName(String searchString) throws SQLException, ClassNotFoundException {
//		searchString = "%"+searchString+"%";
//		return read("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] {searchString});
//	}
//	
//	public void addBookAuthors(Integer bookId, Integer authorId) throws ClassNotFoundException, SQLException {
//		save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { bookId, authorId });
//	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		List<Branch> branches = new ArrayList<>();
		while (rs.next()) {
			
			branches.add(new Branch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress")));
			//also populate the books written by this Author
		}
		return branches;
	}

}
