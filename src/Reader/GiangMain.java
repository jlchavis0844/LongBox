package Reader;

import java.util.Scanner;

public class GiangMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetItems test = new GetItems();
		
		// first we get volume
		Scanner in = new Scanner(System.in);
		System.out.println("Enter volume to search for");
		String input = in.nextLine();
		
		// get a VolumeResults Object back and call it PrintAllItem function to see detail
		test.getVolumesList(input).PrintAllItem();

		// now the issue list
		System.out.println("Enter volume id to search issues");
		input = in.nextLine();
		// get a IssueResults Object back and call it PrintAllItem function to see detail
		test.getIssuesList(input).PrintAllItem();
		
		// the actual issue detail
		System.out.println("enter issue id");
		input = in.nextLine();
		in.close();
		// get a StringObject Object back and call it PrintAllItem function to see detail
		test.getIssuesDetail(input).PrintAllItem();
	}

}
