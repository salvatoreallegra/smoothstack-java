import java.sql.DriverManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		  String JdbcURL = "jdbc:mysql://localhost:3306/library?useSSL=false";
	      String Username = "root";
	      String password = "JediKnight1";
	      Connection con = null;
	      try {
	         System.out.println("Connecting to database..............."+JdbcURL);
	         con=(Connection) DriverManager.getConnection(JdbcURL, Username, password);
	         System.out.println("Connection is successful!!!!!!");
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
		// TODO Auto-generated method stub

	}

}
