package localDB;

import java.io.BufferedReader;
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
import model.Issue;
import model.Volume;
import org.json.JSONException;
import requests.CVImage;
import requests.CVrequest;


/**
Operator	Description	Example
=	Checks if the values of two operands are equal or not, if yes then condition becomes true.	(a = b) is not true.
!=	Checks if the values of two operands are equal or not, if values are not equal then condition becomes true.	(a != b) is true.
<>	Checks if the values of two operands are equal or not, if values are not equal then condition becomes true.	(a <> b) is true.
>	Checks if the value of left operand is greater than the value of right operand, if yes then condition becomes true.	(a > b) is not true.
<	Checks if the value of left operand is less than the value of right operand, if yes then condition becomes true.	(a < b) is true.
>=	Checks if the value of left operand is greater than or equal to the value of right operand, if yes then condition becomes true.	(a >= b) is not true.
<=	Checks if the value of left operand is less than or equal to the value of right operand, if yes then condition becomes true.	(a <= b) is true.
!<	Checks if the value of left operand is not less than the value of right operand, if yes then condition becomes true.	(a !< b) is false.
!>	Checks if the value of left operand is not greater than the value of right operand, if yes then condition becomes true.	(a !> b) is true.

Operator	Description
ALL	The ALL operator is used to compare a value to all values in another value set.
AND	The AND operator allows the existence of multiple conditions in an SQL statement's WHERE clause.
ANY	The ANY operator is used to compare a value to any applicable value in the list according to the condition.
BETWEEN	The BETWEEN operator is used to search for values that are within a set of values, given the minimum value and the maximum value.
EXISTS	The EXISTS operator is used to search for the presence of a row in a specified table that meets certain criteria.
IN	The IN operator is used to compare a value to a list of literal values that have been specified.
LIKE	The LIKE operator is used to compare a value to similar values using wildcard operators.
NOT	The NOT operator reverses the meaning of the logical operator with which it is used. Eg: NOT EXISTS, NOT BETWEEN, NOT IN, etc. This is a negate operator.
OR	The OR operator is used to combine multiple conditions in an SQL statement's WHERE clause.
IS NULL	The NULL operator is used to compare a value with a NULL value.
UNIQUE	The UNIQUE operator searches every row of a specified table for uniqueness (no duplicates).
*/

interface Operator {

    String EQUAL = "=";
    String NOT_EQUAL = "!=";
    String GREATER_THAN = ">";
    String LESS_THAN = "<";
    String LESS_THAN_OR_EQUAL = "<=";
    String GREATER_THAN_OR_EQUAL = ">=";
    String NOT_LESS_THAN = "!<";
    String NOT_GREATER_THAN = "!>";
    String BETWEEN = "<>";  
    
    String WORD_ALL = "ALL";
    String WORD_AND = "AND";
    String WORD_BETWEEN = "BETWEEN";
    String WORD_EXIT = "EXIST";
    String WORD_IN = "IN";
    String WORD_LIKE = "LIKE";
    String WORD_NOT = "NOT";
    String WORD_OR = "OR";
    String WORD_IS_NULL = "IS NULL";
    String WORD_UNIQUE = "UNIQUE";
    
}

public class LocalDB {
    
    private static String mURL = "jdbc:sqlite:./DigLongBox.db";
    private static String DATE_FORMAT = "MM/dd/yyyy h:mm:ss a";
    private static Connection mConnection;
    private static Statement mStatement;
    public static int ISSUE = 0;
    public static int VOLUME = 1;

    public static boolean addIssue(Issue i) throws JSONException {
        return addIssue(i.getFullObject());
    }

    public static boolean addIssue(JSONObject inputJSONObject) throws JSONException {
        try {
            mConnection = DriverManager.getConnection(mURL);
            mStatement = mConnection.createStatement();

            //stat.executeUpdate("DELETE FROM issue;");
            //stat.executeUpdate("VACUUM");
            int id = inputJSONObject.getInt("id");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            String formattedDate = sdf.format(date);

            inputJSONObject.put("timeStamp", formattedDate);
            inputJSONObject.put("volName", inputJSONObject.getJSONObject("volume").getString("name"));
            inputJSONObject.put("JSON", inputJSONObject.toString());

            //stat.executeUpdate("INSERT INTO issue (id) VALUES ('" + id + "');");
            String[] names = JSONObject.getNames(inputJSONObject);
            String insertQuery = "INSERT INTO issue (";
            String valuearrayList = "VALUES (";

            int nameNum = names.length;
            ArrayList<String> variableArrayList = new ArrayList<>();
            ArrayList<String> goodValues = new ArrayList<>();
            String value = "";
            String currentName = "";

            for (int i = 0; i < nameNum; i++) {
                currentName = names[i];
                if (!inputJSONObject.isNull(currentName) && !currentName.equals("image")) {
                    value = inputJSONObject.get(names[i]).toString();
                    if (!value.equals("[]")) {
                        variableArrayList.add(names[i]);
                        goodValues.add(inputJSONObject.get(names[i]).toString());
                    }
                }
            }

            nameNum = variableArrayList.size();
            for (int i = 0; i < nameNum; i++) {
                if (i != nameNum - 1) {
                    insertQuery += (variableArrayList.get(i) + ", ");
                    valuearrayList += (" ? ,");
                } else {
                    insertQuery += (variableArrayList.get(i) + ") ");
                    valuearrayList += (" ? );");
                }
            }

            String sql = insertQuery + valuearrayList;
            System.out.println(sql);
            PreparedStatement preparedStatement = mConnection.prepareStatement(sql);

            for (int i = 0; i < nameNum; i++) {
                preparedStatement.setString((i + 1), goodValues.get(i));
            }

            preparedStatement.executeUpdate();

            CVImage.addIssueImg(inputJSONObject.getJSONObject("image").getString("medium_url"), "" + id, "medium");
            CVImage.addIssueImg(inputJSONObject.getJSONObject("image").getString("thumb_url"), "" + id, "thumb");

            addVolume(new Volume(inputJSONObject.getJSONObject("volume")));
            //printTable("issue");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public static boolean addVolume(Volume vol) throws JSONException {
        if (exists(vol.getID(), VOLUME)) {
            return false;
        }

        try {
            mConnection = DriverManager.getConnection(mURL);
            mStatement = mConnection.createStatement();
            JSONObject jsonObject = vol.getVolume();
            //stat.executeUpdate("DELETE FROM issue;");
            //stat.executeUpdate("VACUUM");
            String id = vol.getID();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            String formattedDateString = simpleDateFormat.format(date);

            jsonObject.put("JSON", jsonObject.toString());
            jsonObject.put("timeStamp", formattedDateString);

            //stat.executeUpdate("INSERT INTO issue (id) VALUES ('" + id + "');");
            String[] names = JSONObject.getNames(jsonObject);
            String qNames = "INSERT INTO volume (";
            String qVals = "VALUES (";

            int nameNum = names.length;
            ArrayList<String> goodNames = new ArrayList<>();
            ArrayList<String> goodValues = new ArrayList<>();
            String value = "";
            String currName = "";

            for (int i = 0; i < nameNum; i++) {
                currName = names[i];
                if (!jsonObject.isNull(currName) && !currName.equals("image")) {
                    value = jsonObject.get(names[i]).toString();
                    if (!value.equals("[]")) {
                        goodNames.add(names[i]);
                        goodValues.add(jsonObject.get(names[i]).toString());
                    }
                }
            }

            nameNum = goodNames.size();
            for (int i = 0; i < nameNum; i++) {
                if (i != nameNum - 1) {
                    qNames += (goodNames.get(i) + ", ");
                    qVals += (" ? ,");
                } else {
                    qNames += (goodNames.get(i) + ") ");
                    qVals += (" ? );");
                }
            }

            String sql = qNames + qVals;
            System.out.println(sql);
            PreparedStatement pre = mConnection.prepareStatement(sql);

            for (int i = 0; i < nameNum; i++) {
                pre.setString((i + 1), goodValues.get(i));
            }

            pre.executeUpdate();

            CVImage.addVolumeImg(vol, "medium");
            CVImage.addVolumeImg(vol, "thumb");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (!mConnection.isClosed()) {
                mConnection.close();
            }
            return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public static boolean exists(String id, int type) {
        int count = 0;
        try {
            mConnection = DriverManager.getConnection(mURL);
            Statement statement = mConnection.createStatement();
//                        
            String table = ((type == 0) ? "issue" : "volume");

            //Check to see if the issue/volume has been added
            String sql = "SELECT 1 FROM " + table + " WHERE id = " + "'" + id + "'" + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            //System.out.println(rs.getString(1));

            if (resultSet.isClosed()) {
                System.out.println("not found");
                return false;
            }
            count = Integer.valueOf(resultSet.getString(1));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                mConnection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //if issue/volume hasn't been added, quit and return false
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates the field with the value given.
     *
     * @param id - unique id of the object
     * @param field - the datafield to be updated
     * @param value - the value of the updated field
     * @param type - either localDB.ISSUE or localDB.VOLUME
     * @return boolean if the object does not exists or the update fails,
     * returns null
     */
    public static boolean update(String id, String field, String value, int type) {
        int count = 0;
        try {
            if (!exists(id, type)) {
                return false;
            }
            mConnection = DriverManager.getConnection(mURL);
            Statement statement = mConnection.createStatement();

            String updateQuery = "UPDATE issue SET " + field + " = ? WHERE id = ?";
            PreparedStatement pre = mConnection.prepareStatement(updateQuery);
            pre.setString(1, value);
            pre.setString(2, id);
            count = pre.executeUpdate();

            if (count == 0) {//return true if 1, false if zero 
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean executeUpdate(String inputUpdateQuery) {
        try {
            mConnection = DriverManager.getConnection(mURL);
            Statement stat = mConnection.createStatement();
            System.out.println(stat.executeUpdate(inputUpdateQuery));
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

    public static ResultSet executeQuery(String inputQuery) {
        ResultSet resultSet = null;
        try {
            mConnection = DriverManager.getConnection(mURL);
            Statement statement = mConnection.createStatement();
            resultSet = statement.executeQuery(inputQuery);
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
        return resultSet;
    }

    public static boolean test() throws JSONException {
        try {
            mConnection = DriverManager.getConnection(mURL);
            Statement statement = mConnection.createStatement();
            /*stat.executeUpdate("DELETE FROM issue;");
			stat.executeUpdate("VACUUM");
			stat.executeUpdate("INSERT INTO issue (id) VALUES ('" + 552139 + "');");*/
            String value = CVrequest.getIssue("552139").get("location_credits").toString();
            //value = org.json.simple.JSONObject.escape(value);
            //String sql = "UPDATE issue SET location_credits ='" + value + "' WHERE id='552139';";
            PreparedStatement prearedStatement = mConnection.prepareStatement("UPDATE issue SET location_credits = ? WHERE id='552139'");
            prearedStatement.setString(1, value);
            prearedStatement.executeUpdate();

            ResultSet resultSet = statement.executeQuery("SELECT id, location_credits FROM issue;");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t " + resultSet.getString("location_credits"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                mStatement.close();
                mConnection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return true;
    }

    public static boolean printTable(String inputTable) {

        ResultSet resultSet;
        try {
            mConnection = DriverManager.getConnection(mURL);
            mStatement = mConnection.createStatement();
            resultSet = mStatement.executeQuery("SELECT * FROM " + inputTable + ";");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int cols = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i < cols; i++) {
                    String colName = resultSetMetaData.getColumnName(i);
                    String colVal = resultSet.getString(i);
                    System.out.println(colName + ": " + colVal);
                }
                System.out.println("***************************************end row***********************************");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                mStatement.close();
                mConnection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean loadSQL(String path) {
        String url = mURL;
        try {
            mConnection = DriverManager.getConnection(url);
            mStatement = mConnection.createStatement();
            mStatement.executeUpdate("drop table if exists issue;");
            BufferedReader in = new BufferedReader(new FileReader(path));
            String longAssCommand = "";
            String temp;

            while ((temp = in.readLine()) != null) {
                longAssCommand += temp;
            }

            System.out.println(longAssCommand);
            in.close();

            mStatement.executeUpdate(longAssCommand);
            System.out.println("Printing columns");
            ResultSet rs = mStatement.executeQuery("PRAGMA table_info('issue');");
            while (rs.next()) {
                System.out.println(rs.getString("name") + "\t " + rs.getString("type"));
            }
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            try {
                mStatement.close();
                mConnection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }

//	public static Issue getIssue(String id){
//		Issue issue = null;
//		try {
//			conn = DriverManager.getConnection(url);
//			stat = conn.createStatement();
//			
//			String query  = "SELECT JSON FROM issue WHERE id = ?;";
//			PreparedStatement pre = conn.prepareStatement(query);
//			pre.setString(1, id);
//			ResultSet rs = pre.executeQuery();
//			rs.next();
//			String jsonStr = rs.getString(1);
//			
//			if(!jsonStr.equals("") || jsonStr != null){
//				issue = new Issue(new JSONObject(jsonStr));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return issue;	
//		
//	}
    /**
     * Returns the value of the requested field from the reqested table for the
     * ID passed
     *
     * @param key - the field name
     * @param id - the id of the issue/ volume
     * @param type - LocalDB.ISSUE or LocalDB.VOLUME
     * @return A string of the value corresponding to the key
     */
    public static String getIssueField(String key, String id, int type) {
        try {
            mConnection = DriverManager.getConnection(mURL);
            mStatement = mConnection.createStatement();

            String query = "SELECT " + key + " FROM ? WHERE id = ?;";
            String table = ((type == 0) ? "issue" : "volume");
            PreparedStatement pre = mConnection.prepareStatement(query);
            pre.setString(1, table);
            pre.setString(2, id);
            ResultSet rs = pre.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

//	public static JSONObject getLocalIssue(String id){
//		
//		try {
//			conn = DriverManager.getConnection(url);
//			stat = conn.createStatement();
//			
//			String sql = "SELECT * FROM issue WHERE id = ?";
//			PreparedStatement pre = conn.prepareStatement(sql);
//			pre.setString(1, id);
//			
//			ResultSet rs = pre.executeQuery();
//			ResultSetMetaData meta = rs.getMetaData();
//			List<List<String>> rowList = new LinkedList<List<String>>();
//			Object val = "";
//			
//			while(rs.next()){
//				List<String> colList = new LinkedList<String>();
//				rowList.add(colList);
//				int size = meta.getColumnCount();
//				
//				for(int col = 1; col <=  size; col++){
//					val = rs.getObject(col);
//					colList.add(val.toString());
//				}
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	
    public static ArrayList<Issue> getAllIssues() throws JSONException {
        ArrayList<Issue> iList = new ArrayList<Issue>();
        try {
            mConnection = DriverManager.getConnection(mURL);
            mStatement = mConnection.createStatement();

            String sql = "SELECT JSON FROM issue;";
            PreparedStatement pre = mConnection.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            String val = "";
            JSONObject tObj = null;
            Issue tIssue = null;

            while (rs.next()) {
                val = rs.getString(1);
                tObj = new JSONObject(val);
                tIssue = new Issue(tObj);
                System.out.println("fetching " + tIssue.getVolumeName() + " # " + tIssue.getIssueNum());
                iList.add(tIssue);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (iList.size() == 0) {
            return null;
        }
        return iList;
    }

    public static ArrayList<Volume> getAllVolumes() throws JSONException {
        ArrayList<Volume> iList = new ArrayList<Volume>();
        try {
            mConnection = DriverManager.getConnection(mURL);
            mStatement = mConnection.createStatement();

            String sql = "SELECT JSON FROM volume;";
            PreparedStatement pre = mConnection.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            String value = "";
            JSONObject tObj = null;
            Volume vol = null;

            while (rs.next()) {
                value = rs.getString(1);
                tObj = new JSONObject(value);
                vol = new Volume(tObj);
                System.out.println("fetching " + vol.getName());
                iList.add(vol);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (iList.size() == 0) {
            return null;
        }
        return iList;
    }

    public static ArrayList<Volume> searchVolumeByName(String inputValue) throws JSONException, SQLException {
        return searchVolume("name", inputValue, "");
    }

    public static ArrayList<Volume> searchVolumeByPublisher(String inputValue) throws JSONException, SQLException {
        return searchVolume("publisher", inputValue, "");
    }

    public static ArrayList<Volume> searchVolumeByCountOfIssues(String inputValue, String operator) throws JSONException, SQLException {
        return searchVolume("count_of_issues", inputValue, operator);
    }

    public static ArrayList<Volume> searchVolumeByYear(String inputValue) throws JSONException, SQLException {
        return searchVolume("start_year", inputValue, Operator.EQUAL);
    }

    public static ArrayList<Volume> searchVolumeByYear(String inputValue, String operator) throws JSONException, SQLException {
        return searchVolume("start_year", inputValue, operator);
    }

    public static ArrayList<Volume> searchVolume(String inputColumn, String inputValue, String operator) throws JSONException, SQLException {
        ArrayList<Volume> resultVolume = new ArrayList<>();

        String columnName = inputColumn;

        mConnection = DriverManager.getConnection(mURL);
        mStatement = mConnection.createStatement();

        String searchValue = inputValue;
        String sql = "SELECT JSON FROM volume WHERE ";

        if (inputValue.chars().allMatch(Character::isDigit)) {

            //SELECT * FROM volume WHERE count_of_issues > 5;
            sql += columnName + " " + operator + inputValue;
        } else {
            sql += columnName + " LIKE " + "'" + "%" + inputValue + "%" + "'" + ";";

        }

        PreparedStatement pre = mConnection.prepareStatement(sql);

        ResultSet rs = pre.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();

        String value = "";
        JSONObject tObj = null;
        Volume vol = null;

        while (rs.next()) {
            value = rs.getString(1);
            tObj = new JSONObject(value);
            vol = new Volume(tObj);
            resultVolume.add(vol);
        }

        return resultVolume;
    }

    //----------------------------------------------issues--------------------------------
    public static ArrayList<Issue> searchIssueByIssueNumber(String inputValue, String operator) throws JSONException, SQLException {
        return searchIssue("issue_number", inputValue, operator);
    }
    
       public static ArrayList<Issue> searchIssueByIssueNumber(String inputValue) throws JSONException, SQLException {
        return searchIssue("issue_number", inputValue, Operator.EQUAL);
    }

    public static ArrayList<Issue> searchIssueByName(String inputValue) throws JSONException, SQLException {
        return searchIssue("name", inputValue, "");
    }

      public static ArrayList<Issue> searchIssueByVolumeName(String inputValue) throws JSONException, SQLException {
        return searchIssue("volName", inputValue, "");
    }
    
    public static ArrayList<Issue> searchIssue(String inputColumn, String inputValue, String operator) throws JSONException, SQLException {
        ArrayList<Issue> resultIssue = new ArrayList<>();

        String columnName = inputColumn;

        mConnection = DriverManager.getConnection(mURL);
        mStatement = mConnection.createStatement();

        String searchValue = inputValue;
        String sql = "SELECT JSON FROM issue WHERE ";

        if (inputValue.chars().allMatch(Character::isDigit)) {

            //SELECT * FROM volume WHERE count_of_issues > 5;
            sql += columnName + " " + operator + inputValue;
        } else {
            sql += columnName + " LIKE " + "'" + "%" + inputValue + "%" + "'" + ";";

        }

        PreparedStatement pre = mConnection.prepareStatement(sql);

        ResultSet rs = pre.executeQuery();
        
        String value = "";
        JSONObject tObj = null;
        Issue issue = null;

        while (rs.next()) {
            value = rs.getString(1);
            tObj = new JSONObject(value);
            issue = new Issue(tObj);
            resultIssue.add(issue);
        }

        return resultIssue;
    }


}



