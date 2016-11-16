package task;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import domain.DomainInfo;
import email.Sender;
import hashFunction.HashFunction;
import httpRequest.HttpClientRequest;

/**
 * 
 */

/**
 * @author Emanuel
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TrusteerExerciseTaskTest {

	private static final String URL_1 = "http://www.go.com";
	private static final String URL_2 = "https://www.gi.com";
	private static final String ASTERISC = "*";
	private static final String HASH_RESULT_1 = "11";
	private static final String HASH_RESULT_2 = "12";
	private static final String HASH_RESULT_3 = "13";
	private static final String HASH_RESULT_4 = "14";

	@Mock
	HttpClientRequest clientRequest;

	@Mock
	HashFunction hashFunction;

	@Mock
	Sender sender;

	@Test
	public void runNoChangesTest() throws ClientProtocolException, IOException, MessagingException {
		List<DomainInfo> domainsInfo = Arrays.asList(new DomainInfo(new URL(URL_1), ASTERISC));
		TrusteerExerciseTask exerciseTask = new TrusteerExerciseTask(domainsInfo, clientRequest, hashFunction, sender,
				new HashMap<>());

		Mockito.when(clientRequest.get(URL_1)).thenReturn("body");
		Mockito.when(hashFunction.apply("body")).thenReturn(HASH_RESULT_1);
		exerciseTask.run();
		exerciseTask.run();
		Mockito.verify(sender, Mockito.never()).send(Mockito.eq("ALERT"), Mockito.anyString());

	}
	
	@Test
	public void runOneChangeTest() throws ClientProtocolException, IOException, MessagingException {
		List<DomainInfo> domainsInfo = Arrays.asList(new DomainInfo(new URL(URL_1), ASTERISC));
		TrusteerExerciseTask exerciseTask = new TrusteerExerciseTask(domainsInfo, clientRequest, hashFunction, sender,
				new HashMap<>());

		Mockito.when(clientRequest.get(URL_1)).thenReturn("body", "casa");
		Mockito.when(hashFunction.apply("body")).thenReturn(HASH_RESULT_1);
		Mockito.when(hashFunction.apply("casa")).thenReturn(HASH_RESULT_2);
		exerciseTask.run();
		exerciseTask.run();
		Mockito.verify(sender, Mockito.times(1)).send(Mockito.eq("ALERT"), Mockito.anyString());

	}

	@Test
	public void runTwoChangesTest() throws ClientProtocolException, IOException, MessagingException {
		List<DomainInfo> domainsInfo = Arrays.asList(new DomainInfo(new URL(URL_1), ASTERISC),
				new DomainInfo(new URL(URL_2), "*"));
		TrusteerExerciseTask exerciseTask = new TrusteerExerciseTask(domainsInfo, clientRequest, hashFunction, sender,
				new HashMap<>());

		Mockito.when(clientRequest.get(URL_1)).thenReturn("body", "casa");
		Mockito.when(clientRequest.get(URL_2)).thenReturn("body2", "casa2");
		Mockito.when(hashFunction.apply("body")).thenReturn(HASH_RESULT_1);
		Mockito.when(hashFunction.apply("body2")).thenReturn(HASH_RESULT_2);
		Mockito.when(hashFunction.apply("casa")).thenReturn(HASH_RESULT_3);
		Mockito.when(hashFunction.apply("casa2")).thenReturn(HASH_RESULT_4);
		exerciseTask.run();
		exerciseTask.run();
		Mockito.verify(sender, Mockito.times(2)).send(Mockito.eq("ALERT"), Mockito.anyString());

	}
	
}
