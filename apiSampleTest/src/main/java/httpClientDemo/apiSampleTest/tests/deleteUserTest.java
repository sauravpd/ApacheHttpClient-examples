package httpClientDemo.apiSampleTest.tests;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * delete uset test
 * @author Saurav
 *
 */
public class deleteUserTest 
{
	public static Logger LOG = Logger.getLogger(deleteUserTest.class);

	/**
	 * DELETE request
	 */
	@Test
	public void deleteUser() 
	{
		LOG.info("deleteUser");
		try
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpDelete httpDelete = new HttpDelete("http://localhost:3000/users/3"); 

			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpDelete);

			if (closebaleHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
			{
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
