package httpRequest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

public class HttpClientRequesterTest {
	private static final HttpClientRequest httpClientRequester = HttpClientRequestImpl.getInstance();
	private static final String url = "http://www.google.com";
	private static final String ip = "http://216.58.213.110";

	@Test
	public void getIpTest() throws ClientProtocolException, IOException {
		String body = httpClientRequester.get(ip);
		Assert.assertNotNull(body);
	}

	@Test
	public void getUrlTest() throws ClientProtocolException, IOException {
		String body = httpClientRequester.get(url);
		Assert.assertNotNull(body);
	}
}
