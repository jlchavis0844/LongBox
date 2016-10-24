package requests;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.Issue;
import model.Volume;
import org.json.JSONException;

/**
 * A class to simplify the sending of API requests to the ComicVine API.<br>
 * This class should be called in the following manner:<br>
 * JSONObject = CVrequest.method("params");<br>
 * @author James
 *
 */
public class CVrequest {
	private static String API_KEY = "ebae3bcc02357fb42c9408727710be74f12576ce";
	private static String baseURL = "http://api.comicvine.com";
	private static JsonNode response = null;
	private static String LIMIT = "100";
        private static String FORMAT = "json";
        private static String CLIENT = "cvscrapper";
        private static String ACCEPT = "application/json";
        private static String BASE_URL = "/search";

	/**
	 * Default search, limit is 10, no given resource
	 * @param queryString = the term to search for 
	 * @return JSONObject of the response body, values are:<br>
	 * image JSON array<br>Aliases<br>Gender<br>site_detail_url<br>deck<br>origin array<br>
	 * resource_type<br>birth<br>description<br>real_name<br>api_detail_url<br>date_added<br>name<br>
	 * "publisher" array<br>id<br>first_appeared_in_issue<br>count_of_issue_appearances<br>date_last_updated<br>
	 */
	public static JSONObject search(String queryString){
		try {
			response = Unirest.get(baseURL + BASE_URL)
					.header("Accept", ACCEPT)
					.queryString("api_key", API_KEY)
					.queryString("client",CLIENT)
					.queryString("query", queryString)
					.queryString("format", FORMAT)
					.queryString("limit", LIMIT)
					.asJson().getBody();
			return response.getObject();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This search gives even more search options including query. resource, limit, and field_list 
	 * @param queryString - String to search for
	 * @param resourceString - String of the resource being searched
	 * @param limit - int of how many results to return, max of 100
	 * @param fieldList - Array of Strings of field names to return in addition to header:
	 * 	character<br>concept<br>origin<br>object<br>location<br>issue<br>story_arc<br>volume<br>publisher<br>
	 * 	person<br>team<br>video
	 * @return JsonNode of the response body, depends on resource
	 */
	public static JSONObject search(String queryString, String resourceString, int lim, String[] fieldList){
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
					.queryString("api_key", API_KEY)
					.queryString("client","cvscrapper")
					.queryString("query", queryString)
					.queryString("resources", resourceString)
					.queryString("field_list",fList)
					.queryString("format", "json")
					.queryString("limit", String.valueOf(lim))
					.asJson().getBody();
			return response.getObject();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Search with option of limit and resource string.
	 * @param qString - String to search for
	 * @param limit - int of how many results to return, max of 100
	 * @param rString - what resource to search for
	 * @return JsonNode of the response body, depends on resource
	 */
	public static JSONObject search(String qString, int limit, String rString){
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", API_KEY)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", rString)
					.queryString("format", "json")
					.queryString("limit", String.valueOf(limit))
					.asJson().getBody();
			return response.getObject();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Easy volume search, returns ArrayList of the volumes
	 * @param qString - what volume to search for
	 * @return ArrayList<Volume> of the search results,fields:
	 * name<br>start_year<br>publisher array<br>id<br>count_of_issues<br>image array
	 * 
	 */
	public static ArrayList<Volume> searchVolume(String qString) throws JSONException{

		ArrayList<Volume> vols = new ArrayList<>();
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", API_KEY)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", "volume")
					.queryString("field_list", "name,start_year,publisher,id,count_of_issues,image")
					.queryString("format", "json")
					.queryString("limit", "20")
					.asJson().getBody();
			JSONArray ja = response.getObject().getJSONArray("results");

			int JAsize = ja.length();
			for(int i = 0; i< JAsize; i++){
				vols.add(new Volume(ja.getJSONObject(i)));
			}

			return vols;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Super volume search, returns ArrayList of the volumes that published by publisher param
	 * @param qString - what volume to search for
	 * @param inputPublisher
	 * @return ArrayList<Volume> of the search results,fields:
	 * name<br>start_year<br>publisher array<br>id<br>count_of_issues<br>image array
	 * 
	 */
	public static ArrayList<Volume> searchVolume(String qString, String inputPublisher) throws JSONException{

		ArrayList<Volume> volumeArrayList = new ArrayList<>();
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", API_KEY)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", "volume")
					.queryString("field_list", "name,start_year,publisher,id,count_of_issues,image")
					.queryString("format", "json")
					.queryString("limit", "100")
					.asJson().getBody();

			int responseNumber = response.getObject().getInt("number_of_total_results");
			System.out.println("found " + responseNumber);
			int pages = (responseNumber/100)+1;		
			JSONObject jsonobject = response.getObject();
			JSONArray allResultsJSONArray[] = new JSONArray[pages]; 
			allResultsJSONArray[0] = jsonobject.getJSONArray("results");

			for(int i = 1; i < pages; i++){
				allResultsJSONArray[i] = Unirest.get(baseURL + "/search")
						.header("Accept", "application/json")
						.queryString("api_key", API_KEY)
						.queryString("client","cvscrapper")
						.queryString("query", qString)
						.queryString("resources", "volume")
						.queryString("field_list", "name,start_year,publisher,id,count_of_issues,image")
						.queryString("offset", String.valueOf(100*i))
						.queryString("format", "json")
						.queryString("limit", "100")
						.asJson().getBody().getObject().getJSONArray("results");
			}

			//Volume temp = null;
			JSONObject tempJSONObject;
			String publisherName = "";

			for(JSONArray elementJSONArray: allResultsJSONArray){				
				for(int k = 0; k < elementJSONArray.length() && volumeArrayList.size() < 30; k++){
					tempJSONObject = elementJSONArray.getJSONObject(k);
					if (!tempJSONObject.isNull("publisher") && !tempJSONObject.isNull("name")){
						publisherName = tempJSONObject.getJSONObject("publisher").get("name").toString();
						if(publisherName.contains(inputPublisher)){
							volumeArrayList.add(new Volume(tempJSONObject));
						}
					}
				}
			}						
			System.out.println(volumeArrayList.size() + " results");
			if(volumeArrayList.size() > 30){
			}
			System.out.println("returning " + volumeArrayList.size());
			return volumeArrayList;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns infomation for the given Volume ID
	 * @param volID String of the volume ID
	 * @return JSONObject with the following fields:<br>
	 * name<br>start_year<br>publisher<br>image<br>count_of_issues<br>id
	 */
	public static JSONObject getVolumeInfo(String volID) throws JSONException{
		try {
			String query = "http://comicvine.gamespot.com/api/volume/4050-" + volID;
			response = Unirest.get(query)
					.header("Accept", "application/json")
					.queryString("api_key", API_KEY)
					.queryString("client","cvscrapper")
					.queryString("field_list", "name,start_year,publisher,image,count_of_issues,id")
					.queryString("format", "json")
					.queryString("limit", "100")
					.asJson().getBody();
			return response.getObject().getJSONObject("results");
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns a JSONArray of <b>ALL</b> the issues for a volume
	 * @param volID - String of the unique identifier for the volume you want results for
	 * @return JSONArray sorted by cover_date (oldest first) with the following fields:<br>
	 * image json object<br>issue_number<br>id<br>name<br>cover_date
	 */
	public static ArrayList<Issue> getVolumeIDs(String volID) throws JSONException{
		ArrayList<Issue> list = new ArrayList<>();;

		try {
			JsonNode jn = Unirest.get(baseURL + "/issues")
					.header("Accept", "application/json")
					.queryString("api_key", API_KEY)
					.queryString("client","cvscrapper")
					.queryString("format", "json")
					.queryString("limit", "100")
					.queryString("field_list", "name,issue_number,id,image,cover_date,deck,description")
					.queryString("filter","volume:" + volID)
					.queryString("sort", "cover_date")
					//.queryString("page", String.valueOf(pageNum))
					.asJson().getBody();
			
			int resNum = jn.getObject().getInt("number_of_total_results");
			int pages = (resNum/100)+1;		
			JSONObject jo = jn.getObject();
			JSONArray allResults[] = new JSONArray[pages]; 
			allResults[0] = jo.getJSONArray("results");

			for(int i = 1; i < pages; i++){
				allResults[i] = Unirest.get(baseURL + "/issues")
						.header("Accept", "application/json")
						.queryString("api_key", API_KEY)
						.queryString("client","cvscrapper")
						.queryString("format", "json")
						.queryString("limit","100")
						.queryString("offset", String.valueOf(100*i))
						.queryString("sort", "cover_date")
						.queryString("field_list", "name,issue_number,id,image,cover_date,deck,description")
						.queryString("filter","volume:" + volID)
						.asJson().getBody().getObject().getJSONArray("results");
			}

			Issue temp = null;
			for(JSONArray j: allResults){
				for(int k = 0; k < j.length(); k++){
					//allIssues.put(j.get(k));
					temp = new Issue(j.getJSONObject(k));
					list.add(temp);
				}
			}
			return list;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all data for the given issue id
	 * @param issId - String of the comic's unqiue ID
	 * @return JSON object with the following keys
	 * person_credits<br> concept_credits<br> first_appearance_storyarcs<br> aliases<br> deck<br> description<br>
	 * api_detail_url<br> issue_number<br> location_credits<br> cover_date<br> id<br> date_last_updated<br> store_date<br>
	 * character_credits<br> first_appearance_locations<br> image<br> site_detail_url<br> first_appearance_objects<br>
	 * first_appearance_concepts<br> first_appearance_characters<br> volume<br>date_added<br> first_appearance_teams<br>
	 * team_credits<br>name<br>story_arc_credits<br>character_died_in<br>object_credits<br>has_staff_review<br>
	 * team_disbanded_in
	 */
	public static JSONObject getIssue(String issId) throws JSONException{
		try {
			String query = "http://comicvine.gamespot.com/api/issue/4000-" + issId;
			response = Unirest.get(query)
					.queryString("api_key", API_KEY)
					.queryString("format", "json")
					.asJson().getBody();
			return response.getObject().getJSONObject("results");
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
};
