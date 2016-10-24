package model;

import java.awt.image.BufferedImage;

import org.json.JSONArray;
import org.json.JSONObject;

import localDB.LocalDB;
import org.json.JSONException;
import requests.*;

public class Issue {
	private boolean full = false;
	private JSONObject jsonobject;
	private String id;
	private String name;
	private String coverDate;
	private String issueNum;
	private boolean local = false;


	public Issue(JSONObject jo) throws JSONException{
		this.jsonobject = jo;
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

		if(jo.has("timeStamp"))
			full = true;

		if(check("volume"))
			jo.put("volName", jo.getJSONObject("volume").getString("name"));
	}

	public String getID(){
		return id;
	}

	public Volume getVolume() throws JSONException{
		if(check("volume")){
			return new Volume(jsonobject.getJSONObject("volume"));
		} else return null;
	}

	public String getVolumeName() throws JSONException{
		if(check("volume")){
			JSONObject tempObj = jsonobject.getJSONObject("volume");
			return tempObj.getString("name");
		} else return null;
	}
	
	public String getVolumeID() throws JSONException{
		if(check("volume")){
			JSONObject tempObj = jsonobject.getJSONObject("volume");
			return tempObj.get("id").toString();
		} else return null;
	}

	public String getName(){
		return name;
	}

	public String getCoverDate(){
		return coverDate;
	}

	public String getDescription() throws JSONException{
		return jsonobject.get("description").toString();
	}

	public void populate() throws JSONException{
		full = true;
		jsonobject = CVrequest.getIssue(getID());
	}

	public JSONObject getFullObject() throws JSONException{
		if(!full){
			jsonobject = CVrequest.getIssue(getID());
		}
		return jsonobject;
	}

	public boolean isFull(){
		return full;
	}

	public String getDeck() throws JSONException{
		if(check("deck")){
			return jsonobject.getString("deck");
		} else return null;
	}

	public String getMediumUrl() throws JSONException{
		if(check("image")){
			return jsonobject.getJSONObject("image").get("medium_url").toString();
		} else return null;
	}

	public BufferedImage getMediumImg() throws JSONException{
		if(local && check("medium")){
			return CVImage.getLocalImage(id, "medium", LocalDB.ISSUE);
		} else if(check("image")){
			return CVImage.getRemoteImage(getMediumUrl());
		} else return null;
	}
	
	public String getThumbUrl() throws JSONException{
		if(check("image")){
			return jsonobject.getJSONObject("image").getString("thumb_url");
		} else return null;
	}

	public BufferedImage getThumbImg() throws JSONException{
		if(check("thumb")){
			return CVImage.getLocalImage(id, "thumb", LocalDB.ISSUE);
		} else if(check("image")){
			return CVImage.getRemoteImage(getThumbUrl());
		} else return null;
	}

	public String toString(){
		return "issue#: "+issueNum+"\tid: "+id+"\t name: "+name+"\t\tcover date: "+coverDate;
	}

	public boolean check (String target) throws JSONException{
		if(jsonobject.has(target) && !jsonobject.isNull(target)){
			String val = jsonobject.get(target).toString();
			if(val.equals("[]") || val.equals("null")){
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	public String getIssueNum(){
		return issueNum;
	}

	public String getPerson(String credit) throws JSONException{
		if(!check("person_credits")){
			return null;
		}

		JSONArray ja = jsonobject.getJSONArray("person_credits");
		String line = "";
		String role = null;
		int jasize = ja.length();

		for(int i = 0; i < jasize; i++){
			role = ja.getJSONObject(i).get("role").toString();
			if(role.contains(credit)){
				line += ja.getJSONObject(i).get("name").toString() + ", ";
			}
		}

		if(!line.equals(""))
			line = line.substring(0, line.length()-2);
		return line;
	}

}
