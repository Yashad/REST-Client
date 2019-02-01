
import java.util.Scanner;

public class clientMain {
 public static void main (String []arg) throws Exception {
	 try {
		 testClient test= new testClient();

		 Scanner input = new Scanner(System.in);
		 
		 System.out.println("TASK 1: Listing all the brances:");
		 
		 System.out.println("Enter the Owner ID:");
		 String owner = input.nextLine();
		 owner=owner.trim();
		 System.out.println("Enter the Repo:");
		 String repo = input.nextLine();
		 repo=repo.trim();
		//TASK1 calling the method
		 test.listBranches(owner, repo);
		 System.out.println("--------------------------------");
		 
		 System.out.println("TASK 2: Create a new repo, (as per createRepo.json):");
		//TASK2 Creating a repo 
		 test.createRepo("createRepo.json");
		 
		 System.out.println("--------------------------------");
	 
		 
		 System.out.println("TASK 3: Create an issue for an existing repo:");
		 System.out.println("Enter the name of repo you want to create issue for (as per the createIssue.json) :");
		 String repoName = input.nextLine();
		 //TASK3 create an issue
		 test.createIssue(owner, repoName, "createIssue.json");
		 System.out.println("--------------------------------");
		 
		 
		 System.out.println("TASK 4: Add an assignee to an existing issue :");
		 //Task4 adding assignee
		
		 System.out.println("Enter the Respo name");
		 String issueRepo= input.nextLine();
		 
		 System.out.println("Enter the issue number( adding assignees as per addAssignee.json)");
		 String issueNum= input.nextLine();
		 test.addAssignee(owner, issueRepo, issueNum, "addAssignee.json");
		 System.out.println("--------------------------------");
		 
		 
	 
		 System.out.println("TASK 5: Edit a repo to disable issues:");
		 System.out.println("Enter the repo name (editing repo as per editRepo.json)");
		 String repoName2 = input.nextLine();
		 //Task5 editing a repo
		 test.editRepo(owner, repoName2, "editRepo.json");
		 System.out.println("--------------------------------");
	
		 System.out.println("TASK 6: List reactions for an issue:");
		 System.out.println("Enter the Repo name:");
		 String newrepoName = input.nextLine();
		 newrepoName=newrepoName.trim();
		 //Task6 List of reaction on an Issue
		 System.out.println("Enter the Issue Number:");
		 String issueNumber = input.nextLine();
		 test.listReaction(owner, newrepoName, issueNumber);
		 System.out.println("--------------------------------");
		 input.close();
	 }catch(Exception e) {
	e.printStackTrace();	 
	 }
 } 
}

