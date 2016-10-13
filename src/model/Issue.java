package model;

import java.util.ArrayList;

import org.json.JSONArray;
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
	
	public String getMediumUrl(){
		return jo.getJSONObject("image").get("medium_url").toString();
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
	
	public String getPerson(String credit){
		if(!check("person_credits")){
			return null;
		}
		
		JSONArray ja = jo.getJSONArray("person_credits");
		String line = "";
		String role = null;
		int jasize = ja.length();
		
		for(int i = 0; i < jasize; i++){
			role = ja.getJSONObject(i).get("role").toString();
			if(role.contains(credit)){
				line += ja.getJSONObject(i).get("name").toString() + ",";
			}
		}
		line = line.substring(0, line.length()-1);
		return line;
	}

}
