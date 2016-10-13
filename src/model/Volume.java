package model;

import org.json.JSONObject;


public class Volume {

	private JSONObject vol = new JSONObject();
	private String id;
	private String name;
	private String startYear;
	private String publisher;
	private String numIssues;
	
	private boolean full = false;
	
	// constructor that take in a volume json object
	// the json object belong to an array of 
	// volume json object that alex get from calling
	// CVcrequest
	public Volume (JSONObject jo){
		this.vol = jo;
		if(check(jo, "id")){
			this.id = jo.get("id").toString();
		}
		else{
			System.out.println("id not found");
		}
		
		name=getName();
		startYear = getStartYear();
		publisher = getPublisher();
		numIssues = getCountofIssue();
	}
	
	public String toString(){		
		return ("name: " + name + "\t\t\tstart_year: " + startYear + "\tpublisher: " + publisher 
				+ "\t\tid: " + id + "\tcount_of_issues: " + numIssues);		
	}
	
	// return the json object id field
	// return null if the field contain null or empty
	public String getID (){
		if(check(vol, "id")){
			return vol.get("id").toString();
		}
		return null;
	}
	
	// return the json object name field
	// return null if the field contain null or empty
	public String getName (){
		if(check(vol, "name")){
			return vol.get("name").toString();
		}
		return null;
	}
	
	// return the json object count_of_issues field
	// return null if the field contain null or empty
	public String getCountofIssue (){
		if(check(vol, "count_of_issues")){
			return vol.get("count_of_issues").toString();
		}
		return null;
	}
	
	// return the json object image field
	// return null if the field contain null or empty
	public String getImage (){
		if(check(vol, "image")){
			JSONObject imageobjects = vol.getJSONObject("image"); 
        	if(check(imageobjects,"medium_url")){
        		return imageobjects.get("medium_url").toString();
        	}
		}
		return null;
	}
	
	// return the json object resource_type field
	// return null if the field contain null or empty
	public String getResource_type (){
		if(check(vol, "resource_type")){
			return vol.get("resource_type").toString();
		}
		return null;
	}
	
	// return the json object publisher field
	// return null if the field contain null or empty
	public String getPublisher (){
		if(check(vol, "publisher")){
        	JSONObject publisherobjects = vol.getJSONObject("publisher"); 
        	if(check(publisherobjects,"name")){
        		return publisherobjects.get("name").toString();
        	}
		}
		return null;
	}
	
	// return the json object start_year field
	// return null if the field contain null or empty
	public String getStartYear (){
		if(check(vol, "start_year")){
			return vol.get("start_year").toString();
		}
		return null;
	}
	/*
	public void populate (){
		full = true;
	}
	
	public boolean isFull (){
		return full;
	}*/
	
	// return the private member vol
	// which is volume json object
	public JSONObject getVolume (){
		return vol;
	}
	
	public	static boolean check (JSONObject jo, String target){
		return (jo.has(target) && !jo.isNull(target) && !(jo.get(target).toString().isEmpty()));

	}
}