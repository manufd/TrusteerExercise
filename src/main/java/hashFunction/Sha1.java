package hashFunction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.annotation.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.Singleton;

/**
 * Sha1 is able to perform sha1 hash functions
 * 
 * @author Emanuel
 *
 */
@Singleton(lazy = false)
@Immutable
public class Sha1 implements HashFunction {

	private static final Logger logger = LoggerFactory.getLogger(Sha1.class);
	private final static HashFunction INSTANCE = new Sha1();
	private static final String SHA1 = "SHA1";

	private Sha1() {

	}

	public static HashFunction getInstance() {
		return INSTANCE;
	}

	/**
	 * return hash function on input string, and return empty string in case
	 * algorithm identification fail.
	 */
	@Override
	public String apply(String input) {
		logger.info("applying sha1 hashfunction");
		byte[] digest = null;

		try {
			MessageDigest md = MessageDigest.getInstance(SHA1);
			md.update(input.getBytes());
			digest = md.digest();

		} catch (NoSuchAlgorithmException e) {
			logger.error("didn't identify the algorithm ");
			e.printStackTrace();
			return "";
		}
		return Hex.encodeHexString(digest);
	}
}
