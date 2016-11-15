/**
 * 
 */
package email;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Emanuel
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AlertSenderImplTest {

	/**
	 * 
	 */
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	private static final String UTF_8 = null;

	@Mock
	private ResourceBundle credentials;

	@Mock
	private Transport transport;

	@Mock
	private MimeMessage mimeMessage;

	@Test
	public void sendTest() throws UnsupportedEncodingException, MessagingException {

		/*
		 * AlertSenderImpl alertSenderImpl = new AlertSenderImpl(mimeMessage,
		 * credentials);
		 * Mockito.when(mimeMessage.getSession()).thenReturn(session);
		 * Mockito.when(mimeMessage.getSession().getTransport()).thenReturn(
		 * transport);
		 * Mockito.when(credentials.getString(USERNAME)).thenReturn("manu");
		 * Mockito.when(credentials.getString(PASSWORD)).thenReturn("pass");
		 * alertSenderImpl.send("sub", "bo");
		 */
	}
}
