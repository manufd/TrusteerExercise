package client;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.MessagingException;

import domain.DomainInfo;
import domain.DomainsConfigurationReader;
import domain.DomainsConfigurationReaderImpl;
import email.Sender;
import email.SenderFactory;
import hashFunction.Sha1;
import httpRequest.HttpClientRequestImpl;
import task.TaskExecutorImpl;
import task.TrusteerExerciseTask;

public class TrusteerExerciseLauncher {
	private static final String EMAIL_CONFIG = "emailConfig";
	private static final String MESSAGE_CONFIG = "messageConfig";
	private static final String CONFIG = "config.properties";
	private static final String TASK_CONFIG = "taskConfig";
	private static final String CREDENTIALS = "credentials";

	public static void main(String[] args) {
		Sender alertSender;
		try {
			alertSender = SenderFactory.createAlertSenderImpl(ResourceBundle.getBundle(EMAIL_CONFIG),
					ResourceBundle.getBundle(MESSAGE_CONFIG), ResourceBundle.getBundle(CREDENTIALS));

			DomainsConfigurationReader configurationReader = new DomainsConfigurationReaderImpl(CONFIG);
			List<DomainInfo> domainsInfo = configurationReader.read();
			Runnable task = new TrusteerExerciseTask(domainsInfo, HttpClientRequestImpl.getInstance(),
					Sha1.getInstance(), alertSender, new ConcurrentHashMap<>());
			TaskExecutorImpl taskExecutor = new TaskExecutorImpl(task);
			taskExecutor.scheduleTask(ResourceBundle.getBundle(TASK_CONFIG));
		} catch (MessagingException | IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
