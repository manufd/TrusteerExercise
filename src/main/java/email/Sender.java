package email;

import javax.mail.MessagingException;

/**
 * Sender interface provide method to send email
 * @author Emanuel
 *
 */
public interface Sender {

	/**
	 * 
	 * @param subject of the mail to send
	 * @param body of the mail to send
	 * @throws MessagingException if sending email failed
	 */
	void send(String subject, String body) throws MessagingException;

}
