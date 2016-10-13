package localDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;

import requests.CVrequest;

public class LocalDB {

	private static String url = "jdbc:sqlite:./DigLongBox.db";
	private static Connection conn;
	private static Statement stat;



	public static boolean addIssue(JSONObject jo){
		try {
			conn = DriverManager.getConnection(url);
			stat = conn.createStatement();

			//stat.executeUpdate("DELETE FROM issue;");
			//stat.executeUpdate("VACUUM");
			int id = jo.getInt("id");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
			String formattedDate = sdf.format(date);

			//stat.executeUpdate("INSERT INTO issue (id) VALUES ('" + id + "');");
			String[] names = JSONObject.getNames(jo);
			String qNames = "INSERT INTO issue (timeStamp,";
			String qVals = "VALUES ('" + formattedDate + "',";


			int nameNum = names.length;
			ArrayList<String> goodNames = new ArrayList<>();
			ArrayList<String> goodValues = new ArrayList<>();
			String value = "";
			String currName = "";
			for(int i = 0; i < nameNum; i++){
				currName = names[i];
				if(!jo.isNull(currName) && !currName.equals("image")){
					value = jo.get(names[i]).toString();
					if(!value.equals("[]")){
						goodNames.add(names[i]);
						goodValues.add(jo.get(names[i]).toString());
					}
				}
			}

			nameNum = goodNames.size();
			for(int i = 0; i < nameNum; i++){
				if(i != nameNum-1){
					qNames += (goodNames.get(i) + ", ");
					qVals += (" ? ,");
				} else {
					qNames += (goodNames.get(i) + ") ");
					qVals += (" ? );");
				}
			}
			String sql = qNames+ qVals;
			PreparedStatement pre = conn.prepareStatement(sql); 
			for(int i = 0; i < nameNum; i++){
				pre.setString((i+1), goodValues.get(i));
			}

			pre.executeUpdate();


			//printTable("issue");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*finally {
			try {
				if(conn.isClosed() == false){
					conn.close();
				}
				if(stat.isClosed() == false){
					stat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				
		}*/
		return true;
	}

	public static boolean executeUpdate(String str){
		try {
			conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			System.out.println(stat.executeUpdate(str));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally {
			try {
				if(conn.isClosed() == false){
					conn.close();
				}
				if(stat.isClosed() == false){
					stat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				
		}*/
		return true;
	}

	public static ResultSet executeQuery(String str){
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			rs = stat.executeQuery(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally {
			try {
				if(conn.isClosed() == false){
					conn.close();
				}
				if(stat.isClosed() == false){
					stat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				
		}*/
		return rs;
	}

	public static boolean test(){
		try {
			conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			/*stat.executeUpdate("DELETE FROM issue;");
			stat.executeUpdate("VACUUM");
			stat.executeUpdate("INSERT INTO issue (id) VALUES ('" + 552139 + "');");*/
			String value = CVrequest.getIssue("552139").get("location_credits").toString();
			//value = org.json.simple.JSONObject.escape(value);
			//String sql = "UPDATE issue SET location_credits ='" + value + "' WHERE id='552139';";
			PreparedStatement pre = conn.prepareStatement("UPDATE issue SET location_credits = ? WHERE id='552139'");
			pre.setString(1, value);
			pre.executeUpdate();

			ResultSet rs = stat.executeQuery("SELECT id, location_credits FROM issue;");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t "+ rs.getString("location_credits"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	public static boolean printTable(String tbl){

		ResultSet rs;
		try {
			conn = DriverManager.getConnection(url);
			stat = conn.createStatement();
			rs = stat.executeQuery("SELECT * FROM " +  tbl + ";");
			ResultSetMetaData rsMeta = rs.getMetaData();
			int cols = rsMeta.getColumnCount();

			while (rs.next()) {
				for(int i = 1; i < cols; i++){
					String colName = rsMeta.getColumnName(i);
					String colVal = rs.getString(i);
					System.out.println(colName + ": " + colVal);
				}
				System.out.println("***************************************end row***********************************");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean loadSQL(String path){
		String url = "jdbc:sqlite:./DigLongBox.db";
		try {

			conn = DriverManager.getConnection(url);
			stat = conn.createStatement();
			stat.executeUpdate("drop table if exists issue;");
			BufferedReader in = new BufferedReader(new FileReader(path));
			String longAssCommand = "";
			String temp;

			while ((temp = in.readLine()) != null){
				longAssCommand += temp;
			}

			System.out.println(longAssCommand);
			in.close();

			stat.executeUpdate(longAssCommand);
			System.out.println("Printing columns");
			ResultSet rs = stat.executeQuery("PRAGMA table_info('issue');");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "\t "+ rs.getString("type"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	

}


