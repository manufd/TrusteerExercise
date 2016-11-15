package task;

import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskExecutorImpl implements TaskExecutor {

	private static final String INITIAL_DELAY = "initialDelay";
	private static final String PERIOD = "period";
	private static final String TIME_UNIT = "timeUnit";
	private final Runnable task;

	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	public TaskExecutorImpl(Runnable task) {
		this.task = task;
	}

	@Override
	public void scheduleTask(ResourceBundle taskResourceBundle) throws InterruptedException {
		Long initialDelay = Long.valueOf(taskResourceBundle.getString(INITIAL_DELAY));
		Long period = Long.valueOf(taskResourceBundle.getString(PERIOD));
		TimeUnit timeUnit = TimeUnit.valueOf(taskResourceBundle.getString(TIME_UNIT));

		scheduler.scheduleAtFixedRate(task, initialDelay, period, timeUnit);
	}

}
