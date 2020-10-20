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
import com.salallegra.library.Entity.Branch;
import com.salallegra.library.Entity.Publisher;

/**
 * @author ppradhan
 *
 */
public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection conn) {
		super(conn);
	}
	public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_publisher", null);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {

			publishers.add(new Publisher(rs.getInt("publisherId"), rs.getString("publisherName"),
					rs.getString("publisherAddress"), rs.getString("publisherPhone")));

		}
		return publishers;
	}

}
