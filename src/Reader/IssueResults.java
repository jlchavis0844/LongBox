package Reader;
import java.util.ArrayList;

//import model.IssueObject;
public class IssueResults {
	private ArrayList<IssueObject> IssueResults = new ArrayList<IssueObject>();

	public ArrayList<IssueObject> getIssueResults() {
		return IssueResults;
	}

	public void setIssueResults(ArrayList<IssueObject> issueResults) {
		IssueResults = issueResults;
	}
	
	// print the issue number and name out 
	// For loop will go through the whole arraylist
	public void PrintAllItem(){
		for(int i = 0; i < IssueResults.size(); i++){
			//System.out.println("Issue Number is: "+IssueResults.get(i).getIssue_Number());
			//System.out.println("Issue name is: "+IssueResults.get(i).getIssue_Name());
			//System.out.println("Issue id is: "+IssueResults.get(i).getIssue_ID());
			System.out.println();
			System.out.println("issue#: " + IssueResults.get(i).getIssue_Number()
					+ "\tid: " + IssueResults.get(i).getIssue_ID()
					+ "\t name: " + IssueResults.get(i).getIssue_Name()); 
			System.out.println("***************************END ISSUE**********************************\n\n");
			System.out.println();
		}
	}
}
