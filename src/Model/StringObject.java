package Model;

public class StringObject {
	 private String CoverImage;
	 private String CoverDate;
	 private String Description;
	 private String Story_Arc;
	 private String IssueNumber;
	 private String ComicTitle;
	 private String Role;
	 private String Artist;
	 private String SiteDetailUrl;
	 private String StoreDate;
	 private String Volume_id;
	 private String Volume_name;
	 private String Volume_site_detail_url;
	 
	 // constructor
	 public StringObject (){
		 CoverImage = "null";
		 CoverDate = "null";
		 Description = "null";
		 Story_Arc = "null";
		 IssueNumber = "null";
		 ComicTitle = "null";
		 Role = "null";
		 Artist = "null";
		 SiteDetailUrl = "null";
		 StoreDate = "null";
		 Volume_id = "null";
		 Volume_name = "null";
		 Volume_site_detail_url = "null";
	 }
	 // list of set function
	 public void setCoverImage(String c){
		 CoverImage = c;
	 }
	 
	 public void setCoverDate(String p){
		 CoverDate = p;
	 }
	 
	 public void setDescription(String Per){
		 Description = Per;
	 }
	 public void setStory_Arc(String Peo){
		 Story_Arc = Peo;
	 }
	 public void setIssueNumber(String t){
		 IssueNumber = t;
	 }
	 public void setComicTitle(String s){
		 ComicTitle = s;
	 }
	 public void setRole(String r){
		 Role = r;
	 }
	 public void setArtist(String con){
		 Artist = con;
	 }
	 public void setSiteDetailUrl(String sdu){
		 SiteDetailUrl = sdu;
	 }
	 public void setStoreDate(String sdu){
		 StoreDate = sdu;
	 }
	 public void setVolume_id(String vid){
		 Volume_id = vid;
	 }
	 public void setVolume_name(String vn){
		 Volume_name = vn;
	 }
	 public void setVolume_site_detail_url(String vurl){
		 Volume_site_detail_url = vurl;
	 }
	 
	 // list of get function
	 public String getCoverImage(){
		 return CoverImage;
	 }
	 public String getCoverDate(){
		 return CoverDate;
	 }
	 public String getDescription(){
		 return Description;
	 }
	 public String getStory_Arc(){
		 return Story_Arc;
	 }
	 public String getIssueNumber(){
		 return IssueNumber;
	 }
	 public String getComicTitle(){
		 return ComicTitle;
	 }
	 public String getRole(){
		 return Role;
	 }
	 public String getArtist(){
		 return Artist;
	 }
	 public String getSiteDetailUrl(){
		 return SiteDetailUrl;
	 }
	 public String getStoreDate(){
		 return StoreDate;
	 }
	 public String getVolume_id(){
		 return Volume_id;
	 }
	 public String getVolume_name(){
		 return Volume_name;
	 }
	 public String getVolume_site_detail_url(){
		 return Volume_site_detail_url;
	 }
	 
	public void PrintAllItem(){
    	System.out.println();
    	System.out.println("issue object display in detail: ");
    	System.out.println();
    	System.out.println("medium cover image : "+getCoverImage());
    	System.out.println("cover date : "+getCoverDate());
    	System.out.println("issue number : "+getIssueNumber());
    	System.out.println("comic title : "+getComicTitle());
    	System.out.println("site detail url : "+getSiteDetailUrl());
    	System.out.println("store date : "+getStoreDate());
    	System.out.println("volume id : "+getVolume_id());
    	System.out.println("volume name : "+getVolume_name());
    	System.out.println("volume url : "+getVolume_site_detail_url());
    	System.out.println("description : "+getDescription());
    	System.out.println("story_arc: "+getStory_Arc());
    	System.out.println("artist name : "+getArtist());
    	System.out.println("artist role : "+getRole());
    	System.out.println();

	}	 
}
