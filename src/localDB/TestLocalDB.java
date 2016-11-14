package localDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Issue;
import model.Volume;
import org.json.JSONException;

/**
 *
 * @author sqlitetutorial.net
 */
public class TestLocalDB {

	/**
	 * Connect to a sample database
	 */
	public static void testConnection() {
		System.out.println("(start connect())");
		String url = "jdbc:sqlite:./DigLongBox.db";
		Connection conn;

		try {
			conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("PRAGMA table_info('issue');");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "\t " + rs.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("(end connect())\n");
	}

	/**
	 * test various search queries for volume
	 *
	 * @throws org.json.JSONException
	 * @throws java.sql.SQLException
	 */
	public static void testVolumeSearchQueries() throws JSONException, SQLException {
		ArrayList<Volume> list = new ArrayList<>();

		System.out.println("(start LocalDB.searchVolumeByName)");
		list = LocalDB.searchVolumeByName("batman");
		for (Volume element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchVolumeByName) \n");

		System.out.println("(start LocalDB.searchVolumeByPublisher)");
		list = LocalDB.searchVolumeByPublisher("Marvel");
		for (Volume element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchVolumeByPublisher) \n");

		System.out.println("(start LocalDB.searchVolumeByCountOfIssues)");
		list = LocalDB.searchVolumeByCountOfIssues("25", Operator.LESS_THAN);
		for (Volume element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchVolumeByCountOfIssues) \n");

		System.out.println("(start LocalDB.searchVolumeByYear)");
		list = LocalDB.searchVolumeByYear("2012", Operator.GREATER_THAN);
		for (Volume element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchVolumeByYear) \n");

		System.out.println("(start LocalDB.searchVolumeByYear)");
		list = LocalDB.searchVolumeByYear("2016");
		for (Volume element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchVolumeByYear) \n");
	}

	public static void testIssueSearchQueries() throws JSONException, SQLException {
		ArrayList<Issue> list = new ArrayList<>();

		System.out.println("(start LocalDB.searchIssueByName)");
		list = LocalDB.searchIssueByName("I Am Gotham Part Four");
		for (Issue element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchIssueByName) \n");

		System.out.println("(start LocalDB.searchIssueByName)");
		list = LocalDB.searchIssueByName("Gotham");
		for (Issue element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchIssueByName) \n");

		System.out.println("(start LocalDB.searchIssueByIssueNumber)");
		list = LocalDB.searchIssueByIssueNumber("10");
		for (Issue element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchIssueByIssueNumber) \n");

		System.out.println("(start LocalDB.searchIssueByIssueNumber with opertor)");
		list = LocalDB.searchIssueByIssueNumber("35", Operator.GREATER_THAN);
		for (Issue element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchIssueByIssueNumber with opertor) \n");

		// searchIssueByVolumeName
		System.out.println("(start LocalDB.searchIssueByVolumeName)");
		list = LocalDB.searchIssueByVolumeName("Old Man Logan");
		for (Issue element : list) {
			System.out.println(list.toString());
		}
		System.out.println("(end LocalDB.searchIssueByVolumeName) \n");

	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws org.json.JSONException
	 * @throws java.sql.SQLException
	 */
	public static void main(String[] args) throws JSONException, SQLException {

//		testConnection();
//		testVolumeSearchQueries();
//		testIssueSearchQueries();
		
		testIssuesSorting();
		//testVolumeSort();
	}
	

	private static void testIssuesSorting() {

		ArrayList<Issue> list = LocalDB.getAllIssues();

		// sortIssuesByAuthors-------------------------------------------
		System.out.println("------sortIssuesByName true--------");

		LocalDB.sortIssuesByName(list, true);
		for (Issue element : list) {
			System.out.println("issue id : " + element.getID()  + ", " + "issue name : " + element.getName());
		}

		System.out.println("-------sortIssuesByName false-------");

		LocalDB.sortIssuesByName(list, false);
		for (Issue element : list) {
			System.out.println("issue id : " + element.getID()  + ", " + "issue name : " + element.getName());
		}

		// sortIssuesByVolumeName-------------------------------------------
		System.out.println("-------sortIssuesByVolumeName true--------");

		LocalDB.sortIssuesByVolumeName(list, true);
		for (Issue element : list) {
			System.out.println(element.getVolumeName() + " - " + element.getName());
		}

		System.out.println("-------sortIssuesByVolume false---------");

		LocalDB.sortIssuesByVolumeName(list, false);
		for (Issue element : list) {
			System.out.println(element.getVolumeName() + " - " + element.getName());
		}

		// sortIssuesByIssueID-------------------------------------------
		System.out.println("-------sortIssuesByIssueID true--------");

		LocalDB.sortIssuesByIssueID(list, true);
		for (Issue element : list) {
			System.out.println(element.getID() + " - " + element.getName());
		}

		System.out.println("-------sortIssuesByIssueID false---------");

		LocalDB.sortIssuesByIssueID(list, false);
		for (Issue element : list) {
			System.out.println(element.getID() + " - " + element.getName());
		}

		// sortIssuesByCoverDate-------------------------------------------
		System.out.println("-------sortIssuesByCoverDate true--------");

		LocalDB.sortIssuesByCoverDate(list, true);
		for (Issue element : list) {
			System.out.println(element.getCoverDate() + " - " + element.getName());
		}

		System.out.println("-------sortIssuesByCoverDate false--------");

		LocalDB.sortIssuesByCoverDate(list, false);
		for (Issue element : list) {
			System.out.println(element.getCoverDate() + " - " + element.getName());
		}
	}

	private static void testVolumeSort() {
		ArrayList<Volume> list = LocalDB.getAllVolumes();
		// sortIssuesByAuthors-------------------------------------------
		System.out.println("------sortIssuesByAuthors true--------");

		// sortVolumesByName
		LocalDB.sortVolumesByName(list, true);
		for (Volume element : list) {
			System.out.println("volume name : " + element.getName());
		}

		System.out.println("------sortIssuesByAuthors false--------");
		LocalDB.sortVolumesByName(list, false);
		for (Volume element : list) {
			System.out.println("volume name : " + element.getName());
		}

		// sortVolumesByID
		System.out.println("------sortVolumesByID true--------");
		LocalDB.sortVolumesByID(list, true);
		for (Volume element : list) {
			System.out.println("id : " + element.getID() + ", " + element.getName());
		}

		System.out.println("------sortVolumesByID false--------");
		LocalDB.sortVolumesByID(list, false);
		for (Volume element : list) {
			System.out.println("id : " + element.getID() + ", " + element.getName());
		}

		// sortVolumesByStartYear
		System.out.println("------sortVolumesByStartYear true--------");
		LocalDB.sortVolumesByStartYear(list, true);
		for (Volume element : list) {
			System.out.println("start year : " + element.getStartYear() + ", issue title : " + element.getName());
		}

		System.out.println("------sortVolumesByStartYear false--------");
		LocalDB.sortVolumesByStartYear(list, false);
		for (Volume element : list) {
			System.out.println("start year : " + element.getStartYear() + ", issue title : " + element.getName());
		}

		// sortVolumesByStartCountOfIssues
		System.out.println("------sortVolumesByStartCountOfIssues true--------");
		LocalDB.sortVolumesByStartCountOfIssues(list, true);
		for (Volume element : list) {
			System.out.println("count_of_issues : " + element.getCountofIssue() + ", issue title : " + element.getName());
		}

		System.out.println("------sortVolumesByStartCountOfIssues false--------");
		LocalDB.sortVolumesByStartCountOfIssues(list, false);
		for (Volume element : list) {
			System.out.println("count_of_issues : " + element.getCountofIssue() + ", issue title : " + element.getName());
		}

		// sortVolumesByPublisher
		System.out.println("------sortVolumesByPublisher true--------");
		LocalDB.sortVolumesByPublisher(list, true);
		for (Volume element : list) {
			System.out.println("publisher : " + element.getPublisher() + ", issue title : " + element.getName());
		}

		System.out.println("------sortVolumesByPublisher false--------");
		LocalDB.sortVolumesByPublisher(list, false);
		for (Volume element : list) {
			System.out.println("publisher : " + element.getPublisher() + ", issue title : " + element.getName());
		}

	}

}
