package task;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.DomainInfo;
import email.Sender;
import hashFunction.HashFunction;
import httpRequest.HttpClientRequest;

/**
 * A TrusteerExerciseTask provides the functionalities to asynchrony perform the
 * following task: Given a list of DomainInfo, download the page and calculate a
 * SHA1 hash of the HTTP response body. Whenever the hash value changes, send an
 * email alert once.
 * 
 * @author Emanuel
 *
 */
public class TrusteerExerciseTask implements Runnable {

	private static final String ASTERISC = "*";
	private final List<DomainInfo> domainsInfo;
	private final HttpClientRequest clientRequest;
	private final HashFunction hashFunction;
	private final Sender sender;
	private final Map<DomainInfo, String> domainInfoToHashedBody;
	private static final Logger logger = LoggerFactory.getLogger(TrusteerExerciseTask.class);

	public TrusteerExerciseTask(List<DomainInfo> domainsInfo, HttpClientRequest clientRequest,
			HashFunction hashFunction, Sender sender, Map<DomainInfo, String> domainInfoToHashedBody) {

		this.domainsInfo = domainsInfo;
		this.clientRequest = clientRequest;
		this.hashFunction = hashFunction;
		this.sender = sender;
		this.domainInfoToHashedBody = domainInfoToHashedBody;
	}

	@Override
	public void run() {
		for (DomainInfo domainInfo : domainsInfo) {
			String body = null;
			String urlAsString = null;
			try {
				if (domainInfo.getIp().equals(ASTERISC)) {
					urlAsString = domainInfo.getUrlAsString();
				} else {
					urlAsString = domainInfo.getUrlStringFromProtocolAndIp();
				}
				body = clientRequest.get(urlAsString);
				String hashResult = hashFunction.apply(body);
				String oldValue = putInMap(domainInfo, hashResult);
				if (oldValue != null && !oldValue.equals(domainInfoToHashedBody.get(domainInfo))) {
					logger.info("the hash value of " + domainInfo + " has changed");
					try {
						sender.send("ALERT",
								"The content of: " + urlAsString + " " + domainInfo.getIp() + "has changed");
					} catch (MessagingException e) {
						logger.error("attempt to send failed, with domain: " + urlAsString + " " + domainInfo.getIp());
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				logger.error("connection with the url " + urlAsString + " failed");
				e.printStackTrace();
			}
		}
	}

	String putInMap(DomainInfo domainInfo, String hashResult) {
		return domainInfoToHashedBody.put(domainInfo, hashResult);
	}
}
