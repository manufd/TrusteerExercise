package httpRequest;

import java.io.IOException;

import org.apache.http.annotation.Immutable;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import annotation.Singleton;

/**
 * A HttpClientRequestImpl is able to perform http requests
 * @author Emanuel
 *
 */
@Singleton(lazy = false)
@Immutable
public class HttpClientRequestImpl implements HttpClientRequest {

	private static final HttpClientRequest INSTANCE = new HttpClientRequestImpl();

	private HttpClientRequestImpl() {

	}

	public static HttpClientRequest getInstance() {
		return HttpClientRequestImpl.INSTANCE;
	}
	
	@Override
	public String get(String url) throws ClientProtocolException, IOException {
		return Request.Get(url).execute().returnContent().asString();
	}
	
}
