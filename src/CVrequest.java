import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * A class to simplify the sending of API requests to the ComicVine API.<br>
 * This class should be called in the following manner:<br>
 * JsonNode = new CVrequest().method("params");<br>
 * @author James
 *
 */
public class CVrequest {
	private static String api_key = "bbfec73a78a7eb3b1c78389c8c13ce66835f5ede";
	private static String baseURL = "http://api.comicvine.com";
	private static JsonNode response = null;
	private static String limit = "100";

	/**
	 * Default search, limit is 10, no given resource
	 * @param qString = the term to search for 
	 * @return JsonNode of the response body, values are:<br>
	 * image JSON array
	 * Aliases
	 * Gender
	 * "site_detail_url"
	 * "deck"
	 * "origin" array
	 * "resource_type"
	 * "birth"
	 * "description"
	 * "real_name"
	 * "api_detail_url"
	 * "date_added"
	 * "name"
	 * "publisher" array
	 * "id"
	 * "first_appeared_in_issue"
	 * "count_of_issue_appearances"
	 * "date_last_updated"
	 */
	public static JsonNode search(String qString){
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("format", "json")
					.queryString("limit", limit)
					.asJson().getBody();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * This search gives even more search options including query. resource, limit, and field_list 
	 * @param qString - String to search for
	 * @param rString - String of the resource being searched
	 * @param limit - int of how many results to return, max of 100
	 * @param fieldList - Array of Strings of field names to return in addition to header:
	 * 	character
	 * 	concept
	 * 	origin
	 * 	object
	 * 	location
	 * 	issue
	 * 	story_arc
	 * 	volume
	 * 	publisher
	 * 	person
	 * 	team
	 * 	video
	 * @return JsonNode of the response body, depends on resource
	 */
	public static JsonNode search(String qString, String rString, int lim, String[] fieldList){
		String fList = "";

		for(int i = 0; i< fieldList.length; i++){
			fList += fieldList[i];
			if(i != fieldList.length-1)
				fList += ",";
		}

		System.out.println(fList);

		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", rString)
					.queryString("field_list",fList)
					.queryString("format", "json")
					.queryString("limit", String.valueOf(lim))
					.asJson().getBody();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Search with option of limit and resource string.
	 * @param qString - String to search for
	 * @param limit - int of how many results to return, max of 100
	 * @param rString - what resource to search for
	 * @return JsonNode of the response body, depends on resource
	 */
	public static JsonNode search(String qString, int limit, String rString){
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", rString)
					.queryString("format", "json")
					.queryString("limit", String.valueOf(limit))
					.asJson().getBody();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Easy volume search, returns <b>WITHOUT</b> header.
	 * @param qString - what volume to search for
	 * @return JSONArray of the search results, <b>WITHOUT</b> header, fields:
	 * name<br>start_year<br>publisher array<br>id<br>count_of_issues<br>image array
	 * 
	 */
	public static JSONArray searchVolume(String qString){
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", "volume")
					.queryString("field_list", "name,start_year,publisher,id,count_of_issues,image")
					.queryString("format", "json")
					.queryString("limit", "100")
					.asJson().getBody();
			return response.getObject().getJSONArray("results");
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static JsonNode getVolumeInfo(String volID){
		try {
			String query = "http://comicvine.gamespot.com/api/volume/4050-" + volID;
			response = Unirest.get(query)
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("field_list", "name,start_year,publisher,image,count_of_issues,id")
					.queryString("format", "json")
					.queryString("limit", "100")
					.asJson().getBody();
			return response;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public static JsonNode getVolumeIDs(String volID, int pageNum){
		try {
			response = Unirest.get(baseURL + "/issues")
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("format", "json")
					.queryString("limit", "100")
					.queryString("field_list", "name,issue_number,id,image")
					.queryString("filter","volume:" + volID)
					.queryString("page", String.valueOf(pageNum))
					.asJson().getBody();
			return response;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static JSONObject getIssue(String issId){
		try {
			String query = "http://comicvine.gamespot.com/api/issue/4000-" + issId;
			HttpResponse<String> temp = Unirest.get(query)
					.queryString("api_key", api_key)
					.asString();
			JSONObject jo = XML.toJSONObject(temp.getBody());
			jo = jo.getJSONObject("response");
			return (JSONObject) jo.get("results");
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
};
