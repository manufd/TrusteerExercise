import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import domain.DomainInfo;
import email.Sender;
import hashFunction.HashFunction;
import httpRequest.HttpClientRequest;
import task.TrusteerExerciseTask;

/**
 * 
 */

/**
 * @author Emanuel
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TrusteerExerciseTaskTest {
	
	@Mock
	List<DomainInfo> domainsInfo;
	
	@Mock
	HttpClientRequest clientRequest;
	
	@Mock
	HashFunction hashFunction;
	
	@Mock
	Sender sender;
	
	@Mock
	Map<DomainInfo, String> domainInfoToHashedBody;
	
	@Test
	public void runTest(){
		//TrusteerExerciseTask exerciseTask = new TrusteerExerciseTask(domainsInfo, clientRequest, hashFunction, sender, domainInfoToHashedBody);
		//exerciseTask.run();
	}
}
