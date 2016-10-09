package Requests;
import java.awt.Image;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class CVImage {

	public static Image getImage(String urlStr){
		URL url = null;
		Image img = null;
		
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31"
							+ "(KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
			img = ImageIO.read(conn.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
