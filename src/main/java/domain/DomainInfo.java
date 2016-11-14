package domain;

import java.net.URL;

import org.apache.http.annotation.Immutable;
import org.apache.http.annotation.ThreadSafe;

/**
 * @author Emanuel
 *
 */
@Immutable
@ThreadSafe
public final class DomainInfo {

	private final URL url; // DO NOT PUBLISH this member to not break class immutability
	private final String ip;

	public DomainInfo(URL url, String ip) {
		super();
		this.url = url;
		this.ip = ip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainInfo other = (DomainInfo) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public String getUrlAsString() {
		return url.toString();
	}

	public String getIp() {
		return ip;
	}

	public String getUrlProtocol() {
		return url.getProtocol();
	}

	public String getUrlStringFromProtocolAndIp() {
		return url.getProtocol() + "//" + ip;
	}

	public String toString() {
		return "DomainInfo: [ url: " + getUrlAsString() + " ip: " + getIp() + "]";
	}

}