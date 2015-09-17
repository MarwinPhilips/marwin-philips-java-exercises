package taskList.definition;

import java.util.Date;

import taskList.enumerations.Status;

public interface PrintTask {
	public String getDescription();
	public Date getDueDate();
	public Status getStatus();
	public Task getParentTask();
}
