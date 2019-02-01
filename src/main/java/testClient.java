import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import base.RestBase;
import client.RestClient;

public class testClient extends RestBase {

	RestBase restBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;

	public testClient() {
		restBase=new RestBase();
	}

	private boolean checkStatus(int statusCode) {
		// TODO Auto-generated method stub
		if(statusCode==200||statusCode==201)
			return true;
		return false;
	}

	//To get stringEntity from the JSON file.
	public StringEntity getStringEntity(String fileName) throws IOException {

		File file = new File(System.getProperty("user.dir")+"/src/main/java/data/"+fileName);
		String JSON_STRING = FileUtils.readFileToString(file, "utf-8");
		StringEntity requestEntity = new StringEntity(JSON_STRING,ContentType.APPLICATION_JSON);
		return requestEntity;
	}

	//Task1: List of all the branches of a repo
	public void listBranches(String repoOwner,String repoName) throws ClientProtocolException, IOException{
		restClient= new RestClient();//Get the REST Client obj
		String serviceURI=prop.getProperty("listAllBranchesUnderURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		String URL=prop.getProperty("URL")+serviceURI;// making final URI
		String header=prop.getProperty("Authorization");//Adding header authorization 
		CloseableHttpResponse closebaleHttpResponse = restClient.get(URL,header);//GET call
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {
			System.out.println("ERROR: Task 1 Status Code:"+statusCode);
			return;
		}
		System.out.println("Task 1 Status Code:"+statusCode);
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONArray responseJsonArray = new JSONArray(responseString);//Take the reponse string into a JSON array.
		System.out.println("List of all the branches of :"+repoName);
		for (int i = 0; i < responseJsonArray.length(); i++) {
			JSONObject jsonobject = responseJsonArray.getJSONObject(i);//Parse the JSON 
			String name = jsonobject.getString("name");
			System.out.println((i+1)+" : "+name);
			
		}
	}

	//Task 2 Creating a repo
	public void createRepo(String file) throws ClientProtocolException, IOException, ParseException{

		StringEntity requestEntity=getStringEntity(file);
		restClient= new RestClient();
		String serviceURI=prop.getProperty("createRepoURL");
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.post(URL,header,requestEntity);
		
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {

			System.out.println("ERROR: Task 2 Status Code:"+statusCode);
			return;
		}
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonobject = new JSONObject(responseString);
		String name = jsonobject.getString("name");
		
		System.out.println("SUCCESS: Task 2 Status Code:"+statusCode);
		System.out.println("New repo :  "+name+"  has been created");
	}

	//Task 3 Create an Issue
	public void createIssue(String repoOwner,String repoName,String file) throws ClientProtocolException, IOException, ParseException{

		StringEntity requestEntity=getStringEntity(file);
		restClient= new RestClient();
		String serviceURI=prop.getProperty("createIssueURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.post(URL,header,requestEntity);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {

			System.out.println("ERROR: Task 3 Status Code:"+statusCode);
			return;
		}
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonobject = new JSONObject(responseString);
		String url = jsonobject.getString("url"); 
		String title = jsonobject.getString("title"); 
		int number = jsonobject.getInt("number"); 
		
		System.out.println("SUCCESS: Task 3 Status Code:"+statusCode);
		
		System.out.println("Issue has been created  Name: "+title+"   Number: "+number);
		System.out.println("URL: "+url);
		
	}
	//Task 4 adding an assignee, update the respective JSON file with number of assignees you want to add
	public void addAssignee(String repoOwner,String repoName,String number,String file) throws ClientProtocolException, IOException, ParseException{

		StringEntity requestEntity=getStringEntity(file);
		restClient= new RestClient();
		String serviceURI=prop.getProperty("addAssigneeURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		serviceURI=serviceURI.replace(":number", number);
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.post(URL,header,requestEntity);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {

			System.out.println("ERROR: Task 4 Status Code:"+statusCode);
			return;
		}
		
		System.out.println("SUCCESS: Task 4 Status Code:"+statusCode);
	}


	//Task 5 Editing Repo, update the JSON with the fetures you want to edit.
	public void editRepo(String repoOwner,String repoName,String file) throws ClientProtocolException, IOException, ParseException{

		StringEntity requestEntity=getStringEntity(file);
		restClient= new RestClient();
		String serviceURI=prop.getProperty("editRepoURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		prop.setProperty("name", repoName);
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.patch(URL,header,requestEntity);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {
			System.out.println("ERROR: Task 5 Status Code:"+statusCode);
			return;
		}
		System.out.println("SUCCESS: Task 5 Status Code:"+statusCode);
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonobject = new JSONObject(responseString);
		boolean flag = jsonobject.getBoolean("has_issues"); 
		System.out.println("Returned Flag:  has_issues:"+flag);
	}

	//Task 6, Listing Reactions
	public void listReaction(String repoOwner,String repoName,String number) throws ClientProtocolException, IOException{
		restClient= new RestClient();
		String serviceURI=prop.getProperty("listReactionURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		serviceURI=serviceURI.replace(":number", number);
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.get2(URL,header);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {
			System.out.println("ERROR: Task 6 Status Code:"+statusCode);
			return;
		}
		System.out.println("SUCCESS: Task 6 Status Code:"+statusCode);
		System.out.println("Below are the reactions: ");
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONArray responseJsonArray = new JSONArray(responseString);
		for (int i = 0; i < responseJsonArray.length(); i++) {
			JSONObject jsonobject = responseJsonArray.getJSONObject(i);
			String name = jsonobject.getString("content");
			System.out.println(name);
		}
		
	}



}
