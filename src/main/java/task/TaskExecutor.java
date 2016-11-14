package task;

import java.util.ResourceBundle;

public interface TaskExecutor {
	public void scheduleTask(ResourceBundle taskResourceBundle) throws InterruptedException;
}
