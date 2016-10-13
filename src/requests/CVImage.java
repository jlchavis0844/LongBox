package requests;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;

import localDB.*;
import model.Issue;

public class CVImage {
	private static BufferedImage img = null;

	public static void addAllImages(JSONObject jo){
		JSONObject joImg = jo.getJSONObject("image");
		String names[] = JSONObject.getNames(joImg);
		String id = jo.get("id").toString();

		String urlStr = "";
		int nameLen = names.length;

		for(int i = 0; i < nameLen; i++){
			urlStr = joImg.get(names[i]).toString();
			CVImage.addIssueImg(urlStr, id, names[i]);
		}
	}

	public static BufferedImage getRemoteImage(String urlStr){
		URL url = null;

		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31"
							+ "(KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
			//img = ImageIO.read(conn.getInputStream()); Faster, but easily corrupted
			img = ImageIO.read(ImageIO.createImageInputStream(conn.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	public static boolean addIssueImg(Issue i, String size){
		String urlType = null;
		
		if(size.contains("medium")){
			urlType = i.getMediumUrl();
		} //add more later
		
		return addIssueImg(urlType, i.getID(), size);
	}
	
	public static boolean addIssueImg(String urlStr, String id, String size){
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31"
							+ "(KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
			//img = ImageIO.read(conn.getInputStream());
			img = ImageIO.read(ImageIO.createImageInputStream(conn.getInputStream()));
			String ext = "png";
			File dir = new File("./images/issue/");
			String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), ext);
			File file = new File(dir, name);
			ImageIO.write(img, "png", file);
			String col = size.replace("_url", "");
			
			String str = "UPDATE issue SET "+ col + " ='./Images/issue/" + name +"' WHERE id='" + id + "';";

			System.out.println(str);
			LocalDB.executeUpdate(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static BufferedImage getLocalImage(String id, String size){
		String col = size.replace("_url", "");

		String str = "SELECT " + col + " FROM issue WHERE id = '" + id + "';";
		ResultSet rs = LocalDB.executeQuery(str);

		try {
			rs.next();
			String path = rs.getString(col);
			img =  ImageIO.read(new File(path));
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
