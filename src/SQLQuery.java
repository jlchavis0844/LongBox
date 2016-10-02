import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SQLQuery {
	
	public static void test(){
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("user", "testUser");
		jo.put("password","password");
		ja.put(new JSONObject("{\"id\":\"5855\"}"));
		ja.put(new JSONObject("{\"id\":\"5856\"}"));
		ja.put(new JSONObject("{\"id\":\"5857\"}"));
		ja.put(new JSONObject("{\"id\":\"5858\"}"));
		jo.put("id_list", ja);
		//OUT:{"password":"password","id_list":[{"id":"5855"},{"id":"5856"},{"id":"5857"},{"id":"5858"}],"user":"testUser"}
		try {
			HttpResponse<JsonNode> response = Unirest.post("http://76.94.123.147:49180/JSONtest.php")
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.body(jo)
					.asJson();
			System.out.println(response.toString());
			System.out.println(response.getBody().toString());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String register(String user, String pass){
		JSONObject jo = new JSONObject();
		jo.put("user", user);
		jo.put("password", pass);
		
		try {
			HttpResponse<String> response = Unirest.post("http://76.94.123.147:49180/LBregister.php")
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.body(jo)
					.asString();
			return response.getBody().toString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "shit";
	}
	
	public static JSONObject getIDs(String user, String pass, String timeStamp){
		JSONObject jo = new JSONObject();
		jo.put("user", user);
		jo.put("password", pass);
		jo.put("timeStamp", timeStamp);
		
		try {
			HttpResponse<JsonNode> response = Unirest.post("http://76.94.123.147:49180/LBgetID.php")
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.body(jo)
					.asJson();
			return response.getBody().getObject();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject sendIDs(String user, String pass, String[] idArr){
		JSONObject retVal = null;
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("user", user);
		jo.put("password",pass);
		//ja.put(new JSONObject("{\"id\":\"5855\"}"));
		
		for(String s: idArr){
			ja.put(new JSONObject("{\"id\" : \"" + s +"\"}"));
		}
		
		jo.put("id_list", ja);
		
		try {
			HttpResponse<JsonNode> response = Unirest.post("http://76.94.123.147:49180/LBsendID.php")
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.body(jo)
					.asJson();
			retVal = response.getBody().getObject();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}
}
