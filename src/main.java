
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import localDB.*;
import model.Issue;
import model.Volume;
import requests.*;

public class main {

	public static void main(String[] args) {

		/*System.out.println(SQLQuery.register("user6", "warrior"));
		System.out.println(SQLQuery.sendIDs("user6","warrior",new String[]{"1234","5678","91011"}).toString());
		System.out.println(SQLQuery.getIDs("user6", "warrior", "2017-10-02 00:00:00").toString());*/
		//testCVcalls();
		//testImage();
		//System.out.println(CVrequest.getIssue("552139").toString());
		//LocalDB.addIssue(CVrequest.getIssue("552139"));
		//LocalDB.executeUpdate("ALTER TABLE issue CHANGE COLUMN id id INT;");
		//LocalDB.test();
		//LocalDB.loadSQL("./issueTable.sql");
		//LocalDB.printTable("issue");
		//String urlStr = "http://comicvine.gamespot.com/api/image/scale_avatar/5444374-01.jpg";
		/*Long start;
		Long stop;
		start = System.currentTimeMillis();
		String id = "551282";
		JSONObject jo = CVrequest.getIssue(id);
		LocalDB.addIssue(jo);
		//CVImage.addAllImages(jo);
		CVImage.addIssueImg(jo.getJSONObject("image").getString("medium_url"), id, "medium_url");
		stop = System.currentTimeMillis();
		System.out.println("operation took (ms)" + (stop - start));
		viewImage(CVImage.getLocalImage(id, "medium_url"));*/

		//testCVcalls();
		//viewImage(CVImage.getRemoteImage("http://comicvine.gamespot.com/api/image/scale_medium/4031792-01.jpg"));
		
		Issue test = new Issue(CVrequest.getIssue("516107"));
		System.out.println(test.getPerson("writer"));
		


	}

	public static void testCVcalls(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter volume to search for");
		String input = in.nextLine();

		for(Volume v: CVrequest.searchVolume(input)){
			System.out.println(v.toString());
		}

		System.out.println("Enter volume id to search issues");
		input = in.nextLine();
		ArrayList<Issue> issues = CVrequest.getVolumeIDs(input);

		for(int i = 0; i < issues.size(); i++){
			System.out.println("Index " + i + "\t " + issues.get(i));
		}
		System.out.println("enter INDEX Number, not id (simulate) clicking on something");
		input = in.nextLine();
		in.close();

		Long start, stop;
		start = System.currentTimeMillis();
		
		Issue bigBoyIssue = issues.get(Integer.valueOf(input));//get index of the issue we want to add
		bigBoyIssue.populate();//make search issue into a full issue
		LocalDB.addIssue(bigBoyIssue.getFullObject());//add to the local DB
		CVImage.addIssueImg(bigBoyIssue.getMediumUrl(), bigBoyIssue.getID(), "medium_url");//add the image to the local
		stop = System.currentTimeMillis();
		System.out.println("operation took (ms)" + (stop - start));
		viewImage(CVImage.getLocalImage(bigBoyIssue.getID(), "medium_url"));
		
		//String id = "551282";
//		JSONObject jo = CVrequest.getIssue(input);
//		LocalDB.addIssue(jo);
//		//CVImage.addAllImages(jo);
//		CVImage.addIssueImg(jo.getJSONObject("image").getString("medium_url"), input, "medium_url");
//		CVImage.getLocalImage(input, "medium_url");
//		stop = System.currentTimeMillis();
//		System.out.println("operation took (ms)" + (stop - start));
//		//viewImage(CVImage.getLocalImage(input, "medium_url"));

	}


	public static void testImage(){
		Image img = CVImage.getRemoteImage("http://comicvine.gamespot.com/api/image/scale_large/5444374-01.jpg");

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
	}

	public static void viewImage(BufferedImage img){
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
	}
}
