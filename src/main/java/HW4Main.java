
public class HW4Main {
 public static void main (String []arg) throws Exception {
	 try {
		 testClient test= new testClient();
		 System.out.println("1. List of All the Branches");
		// test.listBranches("Yashad", "expertiza");
		 System.out.println("2.");
		 //test.createRepo("CreateRepo.json");
		// test.createIssue("Yashad", "Hello23", "createissue.json");
		 //test.addAssignee("Yashad", "Hello23", "1", "addAssignee.json");
		// test.editRepo("Yashad", "Hello23", "editRepo.json");
		 test.listReaction("Yashad", "RepoName-Edited", "1");
	 }catch(Exception e) {
	e.printStackTrace();	 
	 }
 } 
}
