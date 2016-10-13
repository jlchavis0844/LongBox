package model;

import org.json.JSONObject;
import requests.*;

public class Issue {
	private boolean full = false;
	private JSONObject jo;
	private String id;
	private String name;
	private String coverDate;
	private String issueNum;


	public Issue(JSONObject jo){
		this.jo = jo;
		id = jo.get("id").toString();
		
		if(check("name")){
			name = jo.getString("name");
		} else name = "No Name";
		
		if(check("cover_date")){
			coverDate = jo.getString("cover_date").toString();
		} else coverDate = "No Date";
		
		if(check("issue_number")){
			issueNum = jo.get("issue_number").toString();
		} else issueNum = "No Issue #";
	}

	public String getID(){
		return id;
	}

	public Volume getVolume(){
		if(check("volume")){
			return new Volume(jo.getJSONObject("volume"));
		} else return null;
	}

	public String getVolumeName(){
		if(check("volume")){
			JSONObject tempObj = jo.getJSONObject("volume");
			return tempObj.getString("name");
		} else return null;
	}

	public String getName(){
		return name;
	}
	
	public String getCoverDate(){
		return coverDate;
	}
	
	public String getDescription(){
		return jo.get("description").toString();
	}

	public void populate(){
		full = true;
		jo = CVrequest.getIssue(getID());
	}

	public JSONObject getFullObject(){
		if(!full){
			jo = CVrequest.getIssue(getID());
		}
		return jo;
	}

	public boolean isFull(){
		return full;
	}
	
	public String toString(){
		return "issue#: "+issueNum+"\tid: "+id+"\t name: "+name+"\t\tcover date: "+coverDate;
	}

	public boolean check (String target){
		if(jo.has(target) && !jo.isNull(target)){
			String val = jo.get(target).toString();
			if(val.equals("[]") || val.equals("null")){
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

}
