/**
 * 
 */
package com.salallegra.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Genre;

/**
 * @author ppradhan
 *
 */
public class GenreDAO extends BaseDAO<Genre>{
	
		
	public GenreDAO(Connection conn) {
		super(conn);
	}
	public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre", null);
	}
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		AuthorDAO adao = new AuthorDAO(conn);
		while (rs.next()) {
			Genre b = new Genre(rs.getInt("genre_id"), rs.getString("genre_name"));
			//b.setAuthors(adao.read("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));
			//b.setGenres()
			genres.add(b);
		}
		return genres;
	}
	
	

}
