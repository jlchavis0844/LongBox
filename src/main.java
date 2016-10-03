import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.JsonNode;

public class main {

	public static void main(String[] args) {
	
		System.out.println(SQLQuery.register("user6", "warrior"));
		System.out.println(SQLQuery.sendIDs("user6","warrior",new String[]{"1234","5678","91011"}).toString());
		System.out.println(SQLQuery.getIDs("user6", "warrior", "2017-10-02 00:00:00").toString());
	}

	public static void testCVcalls(){
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter volume to search for");
		String input = in.nextLine();

		JSONArray volObj = CVrequest.searchVolume(input);

		for(int i = 0; i < volObj.length(); i++){
			JSONObject jo = (JSONObject) volObj.get(i);
			//"name,start_year,publisher,id,count_of_issues"
			String name, sYear, pub;
			int id, numIssues;

			if(!jo.isNull("name"))
				name = jo.getString("name");
			else name = "missing";

			if(!jo.isNull("start_year"))
				sYear = jo.getString("start_year");
			else sYear = "missing";

			if(!jo.isNull("publisher")){
				JSONObject temp = jo.getJSONObject("publisher");
				if(!jo.isNull("name"))
					pub = temp.getString("name");
				else pub = "missing";
			} else pub = "missing";

			if(!jo.isNull("id"))
				id = jo.getInt("id");
			else id = 9999999;

			if(!jo.isNull("count_of_issues"))
				numIssues = jo.getInt("count_of_issues");
			else numIssues = -1;

			System.out.println("name: " + name + "\t\t\tstart_year: " + sYear + "\tpublisher: " + pub 
					+ "\t\tid: " + id + "\tcount_of_issues: " + numIssues);
		}

		System.out.println("Enter volume id to search issues");
		input = in.nextLine();

		JSONArray ja = CVrequest.getVolumeIDs(input);
		//System.out.println(ja.toString());

		for(int i = 0; i < ja.length(); i++){

			JSONObject jo = (JSONObject) ja.get(i);
			//JSONObject jImg = (JSONObject) jo.get("image");
			String name, issueNum, coverDate = null;
			int id;
			if(!jo.isNull("name")){
				name = jo.getString("name");
			} else name = "no name";
			if(!jo.isNull("issue_number")){
				issueNum = jo.getString("issue_number");
			} else issueNum = "-1";

			if(!jo.isNull("id")){
				id = jo.getInt("id");
			} else id = -1;

			if(!jo.isNull("cover_date")){
				coverDate = jo.getString("cover_date");
			} else id = -1;
			System.out.println("issue#: "+issueNum+"\tid: "+id+"\t name: "+name+"\tcover date: "+coverDate+"\n"); 
			/*System.out.println("icon_url: " + jImg.get("icon_url"));
			System.out.println("thumb_url: " + jImg.get("thumb_url"));
			System.out.println("tiny_url: " + jImg.get("tiny_url"));
			System.out.println("small_url: " + jImg.get("small_url"));
			System.out.println("super_url: " + jImg.get("super_url"));
			System.out.println("screen_url: " + jImg.get("screen_url"));
			System.out.println("medium_url: " + jImg.get("medium_url"));
			System.out.println("***************************END ISSUE**********************************\n\n");*/
		}

		System.out.println("enter issue id");
		input = in.nextLine();
		in.close();

		JSONObject joIssue = CVrequest.getIssue(input);
		String []names;
		names = JSONObject.getNames(joIssue);
		for(String s: names){
			System.out.print(s + ", ");
		}
		System.out.println();

		for(String s: names)
			System.out.println(s + "= " + joIssue.get(s).toString());

		JSONObject images = joIssue.getJSONObject("image");
		try {
			String urlStr = images.getString("super_url");
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31"
							+ "(KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
			Image img = ImageIO.read(conn.getInputStream());

			JDialog dialog = new JDialog();
			//dialog.setModal(true);
			//dialog.setUndecorated(true);
			JLabel label = new JLabel((Icon) new ImageIcon(img));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.add(label);
			dialog.addWindowListener(new WindowAdapter() { 
				@Override public void windowClosed(WindowEvent e) { 
					System.exit(0);
				}
			});
			dialog.pack();
			dialog.setVisible(true);

		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

