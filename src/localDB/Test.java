package localDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class Test {
     /**
     * Connect to a sample database
     */
    public static void connect() {
        String url = "jdbc:sqlite:./DigLongBox.db";
        Connection conn;
        
		try {
			conn = DriverManager.getConnection(url);
	        Statement stat = conn.createStatement();
	        
	        ResultSet rs = stat.executeQuery("PRAGMA table_info('issue');");
	        while (rs.next()) {
	            System.out.println(rs.getString("name") + "\t "+ rs.getString("type"));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}
