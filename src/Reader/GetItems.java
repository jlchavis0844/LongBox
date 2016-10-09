package Reader;

import Requests.*;
import org.json.JSONArray;
import org.json.JSONObject;

import Model.StringObject;
import Model.jsonParserObj;


public class GetItems {
	private	jsonParserObj parsedjsonObj = new jsonParserObj();
	public VolumeResults getVolumesList (String volumeName){
		VolumeResults volumes = new VolumeResults();
		
		// we call CVrequest pass in volume Name that we got from user
		// then we get a jsonarray back
		JSONArray volarray = CVrequest.searchVolume(volumeName);		
		
		volumes = jsonParserArray.VolumeArrayParser(volarray);
		
		return volumes;
	}
	
	public IssueResults getIssuesList (String issueID){
		// make call to CVrequest
		//JsonNode response = CVrequest.getVolumeIDs(issueID);
		//System.out.println(response);
		JSONArray ja = CVrequest.getVolumeIDs(issueID);
		//JSONArray ja = response.getObject().getJSONArray("results");
		//System.out.println(ja.toString());

		// issue obj that alex will get
		IssueResults issueObj = new IssueResults();
		
		issueObj = jsonParserArray.IssueArrayParser(ja);
		
		return issueObj;
	}
	
	public StringObject getIssuesDetail (String issueID){
		// make call to CVrequest
		JSONObject joIssue = CVrequest.getIssue(issueID);

		// issue obj that alex will get
		StringObject issueStringObj = new StringObject();

		issueStringObj = parsedjsonObj.issueDetailParser(joIssue);
		
		return issueStringObj;
	}
}
