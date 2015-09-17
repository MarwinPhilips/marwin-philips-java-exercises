package taskList.definition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import taskList.enumerations.RepetitionType;
import taskList.enumerations.Status;

public interface Task {
	public String getDescription();
	public Date getDueDate();
	public Status getStatus();
	public RepetitionType getRepetitionType();
	public List<PrintTask> getPrintTasks();
}
