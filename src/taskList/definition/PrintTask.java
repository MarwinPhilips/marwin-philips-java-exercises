package taskList.definition;

import java.time.LocalDate;
import java.util.Date;

import taskList.enumerations.Status;

public interface PrintTask {
	public String getDescription();
	public LocalDate getDueDate();
	public Status getStatus();
	public Task getParentTask();
}
