package model;

import java.awt.image.BufferedImage;

import org.apache.commons.lang.ArrayUtils;
import org.json.JSONObject;

import localDB.LocalDB;
import org.json.JSONException;
import requests.CVImage;
import requests.CVrequest;

public class Volume {

    private JSONObject mJSONObject = new JSONObject();
    private String id;
    private String name;
    private String startYear;
    private String publisher;
    private String numIssues;
    private int cycles = 0;

    private boolean full = false;

    // constructor that take in a volume json object
    // the json object belong to an array of 
    // volume json object that alex get from calling
    // CVcrequest
    public Volume(JSONObject jo) throws JSONException {
        this.mJSONObject = jo;
        if (check(jo, "id")) {
            this.id = jo.get("id").toString();
        } else {
            System.out.println("id not found");
        }
        init();

        if (publisher == null && startYear == null && cycles < 10) {
            populate();
        }
    }

    /**
     * initializes member variables. Counter add to ensure there is never an
     * infinite loop
     */
    private void init() throws JSONException {
        cycles++;
        if (cycles < 10) {
            if (check(mJSONObject, "name")) {
                name = mJSONObject.get("name").toString();
            } else {
                name = null;
            }

            if (check(mJSONObject, "start_year")) {
                startYear = mJSONObject.get("start_year").toString();
            } else {
                startYear = null;
            }

            if (check(mJSONObject, "publisher")) {
                JSONObject publisherobjects = mJSONObject.getJSONObject("publisher");
                if (check(publisherobjects, "name")) {
                    publisher = publisherobjects.get("name").toString();
                }
            } else {
                publisher = null;
            }

            if (check(mJSONObject, "count_of_issues")) {
                numIssues = mJSONObject.get("count_of_issues").toString();
            } else {
                numIssues = null;
            }
        }
    }

    public void populate() throws JSONException {
        mJSONObject = CVrequest.getVolumeInfo(id);
        init();
    }

    public String toString() {
        return ("name: " + name + "\t\t\tstart_year: " + startYear + "\tpublisher: " + publisher
                + "\t\tid: " + id + "\tcount_of_issues: " + numIssues);
    }

    // return the json object id field
    // return null if the field contain null or empty
    public String getID() throws JSONException {
        if (check(mJSONObject, "id")) {
            return mJSONObject.get("id").toString();
        }
        return null;
    }

    public String getImgURL(String size) throws JSONException {
        String sizes[] = {"icon", "thumb", "tiny", "small", "super", "screen", "medium"};
        if (ArrayUtils.contains(sizes, size) && check(mJSONObject, "image")) {
            return mJSONObject.getJSONObject("image").getString(size + "_url");
        } else {
            return null;
        }
    }

    public BufferedImage getLocalImg(String size) throws JSONException {
        String sizes[] = {"icon", "thumb", "tiny", "small", "super", "screen", "medium"};
        if (mJSONObject.has(size) && ArrayUtils.contains(sizes, size)) {
            return CVImage.getLocalImage(mJSONObject.getString(size));
        } else {
            return null;
        }
    }

    // return the json object name field
    // return null if the field contain null or empty
    public String getName() {
        return name;
    }

    // return the json object count_of_issues field
    // return null if the field contain null or empty
    public String getCountofIssue() {
        return numIssues;
    }

    //return buffered image of the size given, locally if possible, remotely if needed
    public BufferedImage getImage(String size) throws JSONException {

        try {
            if (mJSONObject.has(size)) {
                return CVImage.getLocalImage(mJSONObject.getString(size));
            } else if (check(mJSONObject, "image")) {
                String url = mJSONObject.getJSONObject("image").getString(size + "_url");
                return CVImage.getRemoteImage(url);
            }
        } catch (Exception e) {

        }

        return null;
    }

    // return the json object resource_type field
    // return null if the field contain null or empty
    public String getResource_type() throws JSONException {
        if (check(mJSONObject, "resource_type")) {
            return mJSONObject.get("resource_type").toString();
        }
        return null;
    }

    // return the json object publisher field
    // return null if the field contain null or empty
    public String getPublisher() {
        return publisher;
    }

    // return the json object start_year field
    // return null if the field contain null or empty
    public String getStartYear() {
        return startYear;
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
    public JSONObject getVolume() {
        return mJSONObject;
    }

    public static boolean check(JSONObject jo, String target) throws JSONException {
        if (jo.has(target)) {
            String val = jo.get(target).toString();
            return (val != null && val.length() != 0);
        } else {
            return false;
        }

    }
}
