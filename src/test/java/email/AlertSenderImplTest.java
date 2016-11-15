/**
 * 
 */
package email;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

/**
 * @author Emanuel
 *
 */
public class AlertSenderImplTest {

	private static final String CREDENTIALS = "credentials";
	private static String EMAIL_CONFIG = "emailConfig";
	private static String MESSAGE_CONFIG = "messageConfig";

	@Test
	public void sendIntegrationTest() throws UnsupportedEncodingException, MessagingException {
		Session alertEmailSession = SessionBuilder.createAlertEmailSession(ResourceBundle.getBundle(EMAIL_CONFIG));
		MimeMessage mimeMessage = MimeMessageBuilder.createAlertMimeMessage(alertEmailSession,
				ResourceBundle.getBundle(MESSAGE_CONFIG));
		ResourceBundle.getBundle(CREDENTIALS);
		AlertSenderImpl alertSenderImpl = new AlertSenderImpl(mimeMessage, ResourceBundle.getBundle(CREDENTIALS));
		alertSenderImpl.send("ciao", "nice to meet you");
	}

}
