/**
 * 
 */
package com.salallegra.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Sal Allegra
 *
 */
public abstract class BaseDAO<T> {

	public static Connection conn = null;

	public BaseDAO(Connection conn) {
		this.conn = conn;
	}

	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);

		if (vals != null) {

			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		} 
		pstmt.executeUpdate();
	}

	
	public Integer saveWithPk(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(0); // see if it's 0 or 1
		}
		return null;
	}

	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		return extractData(pstmt.executeQuery());
	}

	public abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;
}
