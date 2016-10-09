package Reader;

//import model.IssueObject;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.jsonParserObj;

//import results.IssueResults;
//import results.VolumeResults;

// parser an array of type JSONArray to an Object of selected type
public class jsonParserArray {
	private static jsonParserObj jsonparserObj = new jsonParserObj();

	public	static VolumeResults VolumeArrayParser(JSONArray ja){
		VolumeResults volresult = new VolumeResults();
		// pull out the content of the item inside jsonarray object
		// fill the VolumeResults Object and return VolumeResults obj
		for(int i = 0; i < ja.length(); i++){
			JSONObject myobject = (JSONObject) ja.get(i);
			// add the parsered volume object to the arraylist of volume result
			volresult.getVolumeResults().add(jsonparserObj.volumeparser(myobject));			
		}
		return volresult;
	}
	
	public	static IssueResults IssueArrayParser(JSONArray ja){
		IssueResults issueresult = new IssueResults();
		// pull out the content of the item inside jsonarray object
		// fill the IssueResults Object and return IssueResults obj
		for(int i = 0; i < ja.length(); i++){
			JSONObject myobject = (JSONObject) ja.get(i);
			// add the parsered volume object to the arraylist of volume result
			issueresult.getIssueResults().add(jsonparserObj.issueparser(myobject));			
		}
		return issueresult;
	}
	
	public	static boolean check (JSONObject jo, String target){
		return (jo.has(target) && !jo.isNull(target) && !(jo.get(target).toString().isEmpty()));

	}
	
}
