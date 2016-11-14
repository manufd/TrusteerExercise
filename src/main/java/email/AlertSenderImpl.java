package email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SenderImpl wraps MimeMessage to configure it as email of type alert,
 * providing send email functionality
 * 
 * @author Emanuel
 *
 */
public class AlertSenderImpl implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(AlertSenderImpl.class);
	private static final String UTF_8 = "UTF-8";
	private static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
	private static final String FORMAT = "format";
	private static final String CONTENT_TYPE = "Content-type";
	private static final String RECIPIENTS = "recipients";
	private static final String FROM = "from";
	private static final String REPLY_TO = "replyTo";
	private static String USERNAME = "username";
	private static String PASSWORD = "password";
	private String usernameValue;
	private String passwordValue;
	private MimeMessage mimeMessage;

	public AlertSenderImpl(ResourceBundle messageResourceBundle, Session alertEmailSession)
			throws MessagingException, UnsupportedEncodingException {
		mimeMessage = newMimeMessage(messageResourceBundle, alertEmailSession);
		this.usernameValue = messageResourceBundle.getString(USERNAME);
		this.passwordValue = messageResourceBundle.getString(PASSWORD);
	}

	private MimeMessage newMimeMessage(ResourceBundle messageResourceBundle, Session alertEmailSession)
			throws MessagingException, AddressException {
		MimeMessage mimeMessage = new MimeMessage(alertEmailSession);
		mimeMessage.addHeader(CONTENT_TYPE, messageResourceBundle.getString(CONTENT_TYPE));
		mimeMessage.addHeader(FORMAT, messageResourceBundle.getString(FORMAT));
		mimeMessage.addHeader(CONTENT_TRANSFER_ENCODING, messageResourceBundle.getString(CONTENT_TRANSFER_ENCODING));
		mimeMessage.setFrom(new InternetAddress(messageResourceBundle.getString(FROM)));
		mimeMessage.setReplyTo(InternetAddress.parse(REPLY_TO));
		mimeMessage.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(messageResourceBundle.getString(RECIPIENTS)));
		return mimeMessage;
	}

	@Override
	public void send(String subject, String body) throws MessagingException {
		logger.info("sending");
		mimeMessage.setSubject(subject, UTF_8);
		mimeMessage.setText(body, UTF_8);
		mimeMessage.setSentDate(new Date());
		Transport transport = mimeMessage.getSession().getTransport();
		transport.connect(usernameValue, passwordValue);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	}
}
