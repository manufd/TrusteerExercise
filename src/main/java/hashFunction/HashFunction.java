package hashFunction;

/**
 * HashFunction interface provides methods to perform hash functions
 * @author Emanuel
 *
 */
public interface HashFunction {

	/**
	 * @param input to perform hash function
	 * @return string representation of the hexadecimal result obtained by apply
	 *         hash function on content
	 */
	public String apply(String input);
}
