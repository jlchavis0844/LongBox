package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Reader.IssueObject;

public class jsonParserObj {

	public VolumeObj volumeparser (JSONObject jo){
		VolumeObj myVolume = new VolumeObj();
		try {
            // id
            if(check(jo,"id")){
                int id = jo.getInt("id");
                String volumeid = ""+id;
                volumeid = volumeid.trim();
                myVolume.setVolume_ID(volumeid);
            }
            // count_of_issues
            if(check(jo,"count_of_issues")){
                int count_of_issues = jo.getInt("count_of_issues");
                String countofissue = ""+count_of_issues;
                countofissue = countofissue.trim();
                myVolume.setVolume_CountofIssues(countofissue);
            }
            // name
            if(check(jo,"name")){
                String name = jo.getString("name");
                name = name.trim();
                myVolume.setVolume_Name(name);
            }
            // start_year
            if(check(jo,"start_year")){
                String start_year = jo.getString("start_year");
                start_year = start_year.trim();
                myVolume.setVolume_Start_Year(start_year);
            }
            // publisher
            if(check(jo,"publisher")){
            	JSONObject publisherobjects = jo.getJSONObject("publisher"); 
            	if(check(publisherobjects,"name")){
            		String publisherName = publisherobjects.getString("name");
            		myVolume.setVolume_Publisher(publisherName);
            	}
            }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myVolume;
	}
	
	public IssueObject issueparser (JSONObject jo){
		IssueObject myIssue = new IssueObject();
		try {
            // id
            if(check(jo,"id")){
                int id = jo.getInt("id");
                String issueid = ""+id;
                issueid = issueid.trim();
                myIssue.setIssue_ID(issueid);
            }
            // name
            if(check(jo,"name")){
                String name = jo.getString("name");
                name = name.trim();
                myIssue.setIssue_Name(name);
            }
            // number
            if(check(jo,"number")){
                String number = jo.getString("number");
                number = number.trim();
                myIssue.setIssue_Name(number);
            }

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return myIssue;
	}

	public StringObject issueDetailParser (JSONObject jo){
		// local json obj
		StringObject myarticle = new StringObject();
		
        try {    	        	        	        	       	
            // image
            if(check(jo,"image")){
            	JSONObject imageMapobjects = jo.getJSONObject("image");
                // the actual image link in string
                //JSONObject actualImagelink = imageMapobjects.getJSONObject("map");            
                String imageMediumUrl = imageMapobjects.getString("medium_url"); // cover image
                
                imageMediumUrl = imageMediumUrl.trim();
            	myarticle.setCoverImage(imageMediumUrl);
            }
            
            // cover date
            if(check(jo,"cover_date")){
                String cover_date = jo.getString("cover_date");
                cover_date = cover_date.trim();
            	myarticle.setCoverDate(cover_date);
            }
            
            // issue number
            if(check(jo,"issue_number")){
                int intissue_number = jo.getInt("issue_number");
                String issue_number = ""+intissue_number;
                issue_number = issue_number.trim();
            	myarticle.setIssueNumber(issue_number);
            }
            
            // name
            if(check(jo,"name")){
            	String name = jo.getString("name"); // individual comic title
                name = name.trim();
            	myarticle.setComicTitle(name);
            }
            
            // site detail url
            if(check(jo,"site_detail_url")){
                String site_detail_url = jo.getString("site_detail_url");
                site_detail_url = site_detail_url.trim();
            	myarticle.setSiteDetailUrl(site_detail_url);
            }
            
            // store date
            if(check(jo,"store_date")){
                String store_date = jo.getString("store_date");
                store_date = store_date.trim();
            	myarticle.setStoreDate(store_date);
            }
            
            // description
            if(check(jo,"description")){
                String description = jo.getString("description");
                description = description.trim();
            	myarticle.setDescription(description);
            }
           	          
            // story arc
            
            if(check(jo,"first_appearance_storyarcs")){
                JSONObject FirstAppearanceSAobjects = jo.getJSONObject("first_appearance_storyarcs");
                if(check(jo,"story_arc")){
                	JSONObject Story_ArcObjects = FirstAppearanceSAobjects.getJSONObject("story_arc");
                	String story_arc = Story_ArcObjects.getString("name");
                	story_arc = story_arc.trim();
                	myarticle.setStory_Arc(story_arc);
                }   
            }
           
            // the target is there and it value is not ""           
            if(check(jo,"person_credits")){
            	JSONObject personcreditsObjects = jo.getJSONObject("person_credits");
            	if(check(jo,"person")){
            		JSONObject PersonObjects = personcreditsObjects.getJSONObject("person");
            		if(check(jo,"myArrayList")){
            			JSONArray myArrayList = PersonObjects.getJSONArray("myArrayList");
                        JSONObject FirstArrayObj = (JSONObject) myArrayList.get(0);
                        
                        String artistName = FirstArrayObj.getString("name");
                        String artistRole = FirstArrayObj.getString("role");
                        
                        artistName = artistName.trim();
                        artistRole = artistRole.trim();
                        
                    	myarticle.setArtist(artistName);
                    	myarticle.setRole(artistRole);
            		}
            	}
            }
            
            // volume
            if(check(jo,"volume")){
                JSONObject volumeobjects = jo.getJSONObject("volume");
                // detail element in volume json object
                int volume_id = volumeobjects.getInt("id");
                String volumeid = ""+volume_id;
                volumeid = volumeid.trim();
                String volume_name = volumeobjects.getString("name");
                String volume_site_detail_url = volumeobjects.getString("site_detail_url");
                
                volume_name = volume_name.trim();
                volume_site_detail_url = volume_site_detail_url.trim();
            	
            	myarticle.setVolume_id(volumeid);
            	myarticle.setVolume_name(volume_name);
            	myarticle.setVolume_site_detail_url(volume_site_detail_url);
            }        	
           
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // return local json object
        return myarticle;
	}
	
	public	static boolean check (JSONObject jo, String target){
		return (jo.has(target) && !jo.isNull(target) && !(jo.get(target).toString().isEmpty()));

	}
}
