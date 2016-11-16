package domain;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DomainsConfigurationReaderImpl supply methods to read configurations
 * containing DomainInfo
 * 
 * @author Emanuel
 *
 */
public class DomainsConfigurationReaderImpl implements DomainsConfigurationReader {

	static final Logger logger = LoggerFactory.getLogger(DomainsConfigurationReaderImpl.class);
	private static final String UTF_8 = "UTF-8";
	private String PATH;

	public DomainsConfigurationReaderImpl(String path) {
		this.PATH = path;
	}

	public List<DomainInfo> read() throws IOException {
		List<DomainInfo> domainsInfo = new LinkedList<>();
		List<String> readLines = FileUtils.readLines(new File(getClass().getClassLoader().getResource(PATH).getFile()),
				UTF_8);
		for (String line : readLines) {
			String[] split = line.split("\\s+");
			domainsInfo.add(new DomainInfo(new URL(split[0]), split[1]));
			logger.info(domainsInfo.toString());
		}

		return domainsInfo;

	}

}