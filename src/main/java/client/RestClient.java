package client;

/*
 * framework for REST calls
 * 
 * */

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class RestClient{

	public RestClient() {
	}

	//1. GET Call
	public CloseableHttpResponse  get(String url,String header) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader( "Authorization",header); 
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpGet); 			
		return closebaleHttpResponse;
	}

	//2.POST call
	public CloseableHttpResponse post(String url,String header,StringEntity requestEntity) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(requestEntity); 
		httpPost.addHeader( "Authorization",header); 
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpPost);
		return closebaleHttpResponse;
	}


	//3.PATCH call
	public CloseableHttpResponse patch(String url,String header,StringEntity requestEntity) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch(url);
		httpPatch.setEntity(requestEntity); 
		httpPatch.addHeader( "Authorization",header); 
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpPatch);
		return closebaleHttpResponse;
	}

	//4. GET Call2, wrote again as it required additional header information
	public CloseableHttpResponse  get2(String url,String header) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader( "Authorization",header); 
		httpGet.addHeader("Accept", "application/vnd.github.squirrel-girl-preview+json");
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpGet); 			
		return closebaleHttpResponse;
	}

}
