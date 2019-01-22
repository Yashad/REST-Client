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
	//Task1.1
	public void listBranches(String repoOwner,String repoName) throws ClientProtocolException, IOException{
		restClient= new RestClient();
		String serviceURI=prop.getProperty("listUnderURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.get(URL,header);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {
			System.out.println("Not working 1");
			return;
		}
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONArray responseJsonArray = new JSONArray(responseString);
		for (int i = 0; i < responseJsonArray.length(); i++) {
			JSONObject jsonobject = responseJsonArray.getJSONObject(i);
			String name = jsonobject.getString("name");
			System.out.println(name);
		}
	}

	//Task 2
	public void createRepo(String file) throws ClientProtocolException, IOException, ParseException{

		StringEntity requestEntity=getStringEntity(file);
		restClient= new RestClient();
		String serviceURI=prop.getProperty("createRepoURL");
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.post(URL,header,requestEntity);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {
			System.out.println("Not working 2");
			return;
		}
		System.out.println("WORKED 2!!");
	}

	//Task 3
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
			System.out.println("Not working 3");
			return;
		}
		System.out.println("WORKED3!!");
	}
	//Task 4
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
			System.out.println("Not working 4");
			return;
		}
		System.out.println("WORKED 4!!");
	}


	//Task 5
	public void editRepo(String repoOwner,String repoName,String file) throws ClientProtocolException, IOException, ParseException{

		StringEntity requestEntity=getStringEntity(file);
		restClient= new RestClient();
		String serviceURI=prop.getProperty("editRepoURL");
		serviceURI=serviceURI.replace(":owner", repoOwner);
		serviceURI=serviceURI.replace(":repo", repoName);
		String URL=prop.getProperty("URL")+serviceURI;
		String header=prop.getProperty("Authorization");
		CloseableHttpResponse closebaleHttpResponse = restClient.patch(URL,header,requestEntity);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		if(!checkStatus(statusCode)) {
			System.out.println("Not working 3");
			return;
		}
		System.out.println("WORKED5!!");
	}

	//Task 6
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
			System.out.println("Not working 6");
			return;
		}
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONArray responseJsonArray = new JSONArray(responseString);
		for (int i = 0; i < responseJsonArray.length(); i++) {
			JSONObject jsonobject = responseJsonArray.getJSONObject(i);
			String name = jsonobject.getString("content");
			System.out.println(name);
		}
		System.out.println("WORKING yeah ");
	}



}
