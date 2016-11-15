/**
 * 
 */
package email;

import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * MimeMessageBuilder is a utility that supplies static methods to build
 * personalized MimeMessage
 * @author Emanuel
 *
 */
public class MimeMessageBuilder {

	private static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
	private static final String FORMAT = "format";
	private static final String CONTENT_TYPE = "Content-type";
	private static final String RECIPIENTS = "recipients";
	private static final String FROM = "from";
	private static final String REPLY_TO = "replyTo";

	/**
	 * @throws MessagingException
	 * 
	 */
	public static MimeMessage createAlertMimeMessage(Session alertEmailSession, ResourceBundle messageResourceBundle)
			throws MessagingException {
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

}
