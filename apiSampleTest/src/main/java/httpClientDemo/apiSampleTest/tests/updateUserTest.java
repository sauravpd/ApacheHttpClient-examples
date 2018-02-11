package httpClientDemo.apiSampleTest.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Update user test
 * @author Saurav
 *
 */
public class updateUserTest 
{
	public static Logger LOG = Logger.getLogger(updateUserTest.class);

	/**
	 * PUT request
	 */
	@Test
	public void updateUser() 
	{
		LOG.info("updateUser");
		try
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPut httpPut = new HttpPut("http://localhost:3000/users/11"); 

			byte[] jsonData =Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"updateUser.json"));
			httpPut.setEntity(new StringEntity(new String(jsonData)));
			httpPut.addHeader("Content-Type","application/json");

			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpPut);

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
