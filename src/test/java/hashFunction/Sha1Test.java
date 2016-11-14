package hashFunction;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class Sha1Test {

	private static final String EXPECTED_VALUE = "fc9af3af6ac4e0871610da26def5501f0ee2f57e";
	private static final String EXPECTED_VALUE2 = "21ced5f8abad64543756f646a14d391cf8ece558";
	private final static String BODY = "piccino";
	private final static String BODY2 = "piccioncino";
	private static final Map<String, String> expectedResults = new HashMap<>();
	private static final HashFunction sha1 = Sha1.getInstance();
	static{
		expectedResults.put(BODY, EXPECTED_VALUE);
		expectedResults.put(BODY2, EXPECTED_VALUE2);
	}
	
	@Test
	public void applyTest(){
		
		Map<String, String> actualResults = new HashMap<>();
		actualResults.put(BODY, sha1.apply(BODY));
		actualResults.put(BODY2, sha1.apply(BODY2));
		Assert.assertEquals(expectedResults, actualResults);
	}
}
