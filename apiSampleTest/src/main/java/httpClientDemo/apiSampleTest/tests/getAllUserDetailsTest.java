package httpClientDemo.apiSampleTest.tests;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * get all user details test
 * @author Saurav
 *
 */
public class getAllUserDetailsTest 
{
	public static Logger LOG = Logger.getLogger(getAllUserDetailsTest.class);
	
	/**
	 * GET request
	 */
	@Test
	public void getAllUserDetails() 
	{
		LOG.info("getAllUserDetails");
		try
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet("http://localhost:3000/users"); 

			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget);

			if (closebaleHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
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
