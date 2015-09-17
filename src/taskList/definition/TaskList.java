package taskList.definition;

import java.util.List;

public interface TaskList {
	public List<PrintTask> getPrintTasks();
	public void AddTask(Task task);
	public void removeTask(Task task);
	
}
