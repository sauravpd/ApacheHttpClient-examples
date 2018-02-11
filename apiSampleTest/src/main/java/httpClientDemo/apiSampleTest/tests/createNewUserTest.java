package httpClientDemo.apiSampleTest.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * create new user test
 * @author Saurav
 *
 */
public class createNewUserTest 
{
	public static Logger LOG = Logger.getLogger(getAllUserDetailsTest.class);
	
	/**
	 * POST request
	 */
	@Test
	public void createNewUser() 
	{
		LOG.info("createNewUser");
		try
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://localhost:3000/users"); 
			
			byte[] jsonData =Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"createUser.json"));
			httpPost.setEntity(new StringEntity(new String(jsonData)));
			httpPost.addHeader("Content-Type","application/json");
			
			
			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpPost);

			if (closebaleHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
				LOG.info("Test Pass");
				LOG.info(closebaleHttpResponse.getStatusLine());
				LOG.info("******************response body*******************");
				System.out.println(EntityUtils.toString(closebaleHttpResponse.getEntity()));

			} else {
				LOG.error("Test Fail");
				Assert.fail(closebaleHttpResponse.getStatusLine().getReasonPhrase());
			}

		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
}
