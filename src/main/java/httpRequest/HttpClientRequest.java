package httpRequest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
/**
 * HttpClientRequest provides http requests methods
 * @author Emanuel
 *
 */
public interface HttpClientRequest {
	public String get(String url) throws ClientProtocolException, IOException;

}
