package taskList.definition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import taskList.enumerations.RepetitionType;
import taskList.enumerations.Status;

public interface Task {
	public String getDescription();
	public LocalDate getDueDate();
	public Status getStatus();
	public RepetitionType getRepetitionType();
	public List<PrintTask> getPrintTasks();
}
