package email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Emanuel
 *
 */
public class AlertSenderImpl implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(AlertSenderImpl.class);
	private static final String UTF_8 = null;
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private MimeMessage mimeMessage;
	private ResourceBundle credentials;

	public AlertSenderImpl(MimeMessage mimeMessage, ResourceBundle CREDENTIALS)
			throws MessagingException, UnsupportedEncodingException {
		this.mimeMessage = mimeMessage;
		this.credentials = CREDENTIALS;
	}

	@Override
	public void send(String subject, String body) throws MessagingException {
		 logger.info("sending");
		mimeMessage.setSubject(subject, UTF_8);
		mimeMessage.setText(body, UTF_8);
		mimeMessage.setSentDate(new Date());
		Transport transport = mimeMessage.getSession().getTransport();
		transport.connect(credentials.getString(USERNAME), credentials.getString(PASSWORD));
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	}
}
