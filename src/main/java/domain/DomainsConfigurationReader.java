package domain;

import java.io.IOException;
import java.util.List;

/**
 * DomainsConfigurationReader interface provides methods to read configurations containing DomainInfo
 * @author Emanuel
 *
 */
public interface DomainsConfigurationReader{
	public List<DomainInfo> read() throws IOException;

}
