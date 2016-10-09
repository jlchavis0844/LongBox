package Reader;
import java.util.ArrayList;

import Model.VolumeObj;

//import model.VolumeObj;
public class VolumeResults {
	private ArrayList<VolumeObj> VolumeResults = new ArrayList<VolumeObj>();

	public ArrayList<VolumeObj> getVolumeResults() {
		return VolumeResults;
	}

	public void setVolumeResults(ArrayList<VolumeObj> volumeResults) {
		VolumeResults = volumeResults;
	}
	
	// print the issue number and name out 
	// For loop will go through the whole arraylist
	public void PrintAllItem(){
		for(int i = 0; i < VolumeResults.size(); i++){
			//System.out.println("Volume Name is: "+VolumeResults.get(i).getVolume_Name());
			//System.out.println("Volume Start Year is: "+VolumeResults.get(i).getVolume_Start_Year());
			//System.out.println("Volume Publisher is: "+VolumeResults.get(i).getVolume_Publisher());
			//System.out.println("Volume Count_of_Issues: "+VolumeResults.get(i).getVolume_CountofIssues());
			//System.out.println("Volume id is: "+VolumeResults.get(i).getVolume_ID());
			System.out.println();
			
			System.out.println("name: " + VolumeResults.get(i).getVolume_Name()
					+ "\t\t\tstart_year: " + VolumeResults.get(i).getVolume_Start_Year()
					+ "\tpublisher: " + VolumeResults.get(i).getVolume_Publisher() 
					+ "\t\tid: " + VolumeResults.get(i).getVolume_ID()
					+ "\tcount_of_issues: " + VolumeResults.get(i).getVolume_CountofIssues());
			System.out.println();
		}
	}


}
