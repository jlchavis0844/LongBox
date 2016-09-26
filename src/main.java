import java.util.Scanner;

import javax.xml.ws.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;

public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		/*JsonNode response = new CVrequest().search("Snotgirl");			
		System.out.println(response.toString());
		JsonNode response = new CVrequest().search("Snotgirl", 10, "volume");
		System.out.println(response.toString());
		String[] fields = {"name","start_year","publisher","id","count_of_issues"};
		JsonNode response = new CVrequest().search("Snotgirl", "volume", 5, fields);
		System.out.println(response.toString());
		JSONObject jo = new JSONObject(response);
		System.out.println(response);*/
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter volume to search for");
		String input = in.nextLine();
		
		JsonNode volResp = new CVrequest().searchVolume(input);
		JSONArray volObj = volResp.getObject().getJSONArray("results");
		
		for(int i = 0; i < volObj.length(); i++){
			JSONObject jo = (JSONObject) volObj.get(i);
			//"name,start_year,publisher,id,count_of_issues"
			String name = jo.getString("name");
			String sYear = jo.getString("start_year");
			String pub = jo.getJSONObject("publisher").getString("name");
			int id = jo.getInt("id");
			int numIssues = jo.getInt("count_of_issues");
			System.out.println("name: " + name + "\tstart_year: " + sYear + "\tpublisher: " + pub 
								+ "\tid: " + id + "\tcount_of_issues: " + numIssues);
			
		}
		
		System.out.println("Enter volume id to search issues");
		 input = in.nextLine();
		 
		JsonNode response = new CVrequest().getVolumeIDs(input,1);
		//System.out.println(response);

		JSONArray ja = response.getObject().getJSONArray("results");
		//System.out.println(ja.toString());

		for(int i = 0; i < ja.length(); i++){

			JSONObject jo = (JSONObject) ja.get(i);
			JSONObject jImg = (JSONObject) jo.get("image");
			String name, issueNum;
			int id;
			if(jo.has("name")){
				name = jo.getString("name");
			} else name = "no name";
			if(jo.has("issue_number")){

				issueNum = jo.getString("issue_number");
			} else issueNum = "-1";
			
			if(jo.has("id")){
				id = jo.getInt("id");
			} else id = -1;
			System.out.println("issue#: " + issueNum + "\tid: " + id + "\t name: " + name); 
			/*System.out.println("icon_url: " + jImg.get("icon_url"));
			System.out.println("thumb_url: " + jImg.get("thumb_url"));
			System.out.println("tiny_url: " + jImg.get("tiny_url"));
			System.out.println("small_url: " + jImg.get("small_url"));
			System.out.println("super_url: " + jImg.get("super_url"));
			System.out.println("screen_url: " + jImg.get("screen_url"));
			System.out.println("medium_url: " + jImg.get("medium_url"));*/
			System.out.println("***************************END ISSUE**********************************\n\n");
		}
	}

}
