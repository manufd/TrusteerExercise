package email;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SenderFactory create Sender implementations 
 * 
 * @author Emanuel
 *
 */
public class SenderFactory {

	private static final Logger logger = LoggerFactory.getLogger(SenderFactory.class);

	public static Sender createAlertSenderImpl(final ResourceBundle EMAIL_CONFIG, final ResourceBundle MESSAGE_CONFIG, final ResourceBundle CREDENTIALS)
			throws UnsupportedEncodingException, MessagingException {
		logger.info("creating a sender");
		Session alertEmailSession = SessionBuilder.createAlertEmailSession(EMAIL_CONFIG);
		MimeMessage alertMimeMessage = MimeMessageBuilder.createAlertMimeMessage(alertEmailSession, MESSAGE_CONFIG);
		return new AlertSenderImpl(alertMimeMessage, CREDENTIALS);
	}
}
