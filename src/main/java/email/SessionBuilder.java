package email;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Session;

/**
 * SessionBuilder is a utility that supplies static methods to build
 * personalized sessions
 * 
 * @author Emanuel
 *
 */
public class SessionBuilder {

	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";

	public static Session createAlertEmailSession(ResourceBundle emailConfigResourceBundle) {
		return newSession(emailConfigResourceBundle);
	}

	private static Session newSession(ResourceBundle resourceBundle) {
		return Session.getInstance(newProperties(resourceBundle));
	}

	private static Properties newProperties(ResourceBundle resourceBundle) {
		Properties props = new Properties();
		props.put(MAIL_SMTP_HOST, resourceBundle.getString(MAIL_SMTP_HOST));
		props.put(MAIL_SMTP_PORT, resourceBundle.getString(MAIL_SMTP_PORT));
		props.put(MAIL_SMTP_AUTH, resourceBundle.getString(MAIL_SMTP_AUTH));
		props.put(MAIL_SMTP_STARTTLS_ENABLE, resourceBundle.getString(MAIL_SMTP_STARTTLS_ENABLE));
		return props;
	}

}
