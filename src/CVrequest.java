import org.json.JSONObject;

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
	private String api_key = "bbfec73a78a7eb3b1c78389c8c13ce66835f5ede";
	private String baseURL = "http://api.comicvine.com";
	private JsonNode response;
	private String limit;

	public CVrequest() {
		response = null;
		limit = "10";
	}

	/**
	 * Default search, limit is 10, no given resource
	 * @param qString = the term to search for 
	 * @return JsonNode of the response body
	 */
	public JsonNode search(String qString){
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
	 * @param fieldList - Array of Strings of field names to return:
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
	 * @return JsonNode of the response body.
	 */
	public JsonNode search(String qString, String rString, int lim, String[] fieldList){
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
	 * @return JsonNode of the response body
	 */
	public JsonNode search(String qString, int limit, String rString){
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

	public JsonNode searchVolume(String qString){
		try {
			response = Unirest.get(baseURL + "/search")
					.header("Accept", "application/json")
					.queryString("api_key", api_key)
					.queryString("client","cvscrapper")
					.queryString("query", qString)
					.queryString("resources", "volume")
					.queryString("field_list", "name,start_year,publisher,id,count_of_issues")
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

	public JsonNode getVolumeYears(String volID){
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

	public JsonNode getVolumeIDs(String volID, int pageNum){
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
};
